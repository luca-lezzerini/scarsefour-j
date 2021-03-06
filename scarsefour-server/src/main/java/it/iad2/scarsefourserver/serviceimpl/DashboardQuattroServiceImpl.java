package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ProdottoDto;
import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.dto.RispostaEanDtoQuattro;
import it.iad2.scarsefourserver.dto.ScontrinoDto;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;
import it.iad2.scarsefourserver.service.DashboardQuattroService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardQuattroServiceImpl implements DashboardQuattroService {

    @Autowired
    ProdottoRepository prodottoRepository;
    @Autowired
    ScontrinoRepository scontrinoRepository;
    @Autowired
    RigaScontrinoRepository rigaScontrinoRepository;

    @Override
    public ProdottoDto vediPrezzoAction(String ean) {
        Prodotto prodotto = prodottoRepository.findByEanEquals(ean);
        if (prodotto == null) {
            prodotto = new Prodotto();
        }
        return new ProdottoDto(prodotto);
    }

    @Override
    public RispostaEanDto chiudiScontrinoAction(Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public ScontrinoDto annullaScontrinoAction(Scontrino scontrino) {
        // prendo dalla repository lo scontrino con Id del client
        Scontrino scontrinoBis = scontrinoRepository.findByIdEquals(scontrino.getId());

        //setto il totale a zero
        scontrinoBis.setTotale(0.0);
        //salvo lo scontrino sul database
        scontrinoRepository.save(scontrinoBis);
        //cancello le righe dello scontrino dalla repository Righe Scontrino
        scontrinoBis.getRighe().forEach(r -> {
            rigaScontrinoRepository.deleteById(r.getId());
        });
        //Si cancellano le righe dello scontrino
        List<RigaScontrino> listaRighe = new ArrayList<>();
        scontrinoBis.setRighe(listaRighe);
        //Si salva lo scontrino con le righe azzerate
        scontrinoRepository.save(scontrinoBis);
        //scontrinoBis = scontrinoRepository.findByIdEquals(scontrinoBis.getId());

        return new ScontrinoDto(scontrinoBis);
    }
    
    
    @Override
    public RispostaEanDtoQuattro verificaEanAction(String ean, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDto stornaAction(String ean, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDtoQuattro inserisciEanAction(String ean, Scontrino scontrino) {
        // prepara una variabile per ritornare l'esito (EAN trovato o meno)
        boolean esito;
        RigaScontrino ultimaRiga = new RigaScontrino();

        // ------ vede se lo scontrino esiste gi?? su DB ---
        // non esiste? (ossia il client me lo manda vuoto?)
        if (scontrino == null || scontrino.getId() == null) {
            // ... ricevuto scontrino vuoto, quindi lo creo ...
            scontrino = new Scontrino();
            scontrino = scontrinoRepository.save(scontrino);
        } else {
            // ... poich?? ricevuto non vuoto, lo cerco aggiornato sul DB
            var opt = scontrinoRepository.findById(scontrino.getId());
            // ... l'ho trovato su DB?
            if (opt.isEmpty()) {
                // non lo trovo, strano..., lo ricreo e mando un messaggio di errore
                System.out.println("ERRORE: scontrino inesistente, ne creo uno nuovo");
                scontrino = new Scontrino();
                scontrino = scontrinoRepository.save(scontrino);
            } else {
                // ... trovato, lo recupero dall'Optional
                scontrino = opt.get();
            }
        }
        // ----------- cerchiamo ora il prodotto ... ---------------
        // verifichiamo se il prodotto ?? presente
        Prodotto prodotto = prodottoRepository.findByEanEquals(ean);
        // trovato?
        if (prodotto == null) {
            // no, esito negativo e ritorno cos??
            esito = false;
        } else {
            // si, allora aggiungo una riga e esito positivo
            esito = true;
            RigaScontrino ulrimaRiga = new RigaScontrino();
            // aggiungo la riga allo scontrino
            RigaScontrino rs = new RigaScontrino();
            rs.setProdotto(prodotto);
            rs.setQuantita(1);
            rs.setScontrino(scontrino);
            rs = rigaScontrinoRepository.save(rs);
            //Valorizziamo ultimaRiga con l'ultima riga inserita
            ultimaRiga = rs;
            // aggiorno relazione lato prodotto
            prodotto.getRighe().add(rs);
            prodotto = prodottoRepository.save(prodotto);
            //Si salva l'ultima riga dello scontrino

            // aggiungo relazione lato scontrino
            scontrino.getRighe().add(rs);
            scontrino = scontrinoRepository.save(scontrino);

        }
        // calcoliamo il totale
        double totale = 0;
        List<RigaScontrino> lista = scontrino.getRighe();
//        // metodo 1
//        for (int i = 0; i < lista.size(); i++) {
//            RigaScontrino riga = lista.get(i);
//            totale += riga.getProdotto().getPrezzo();
//        }
//        // metodo 2
//        for (RigaScontrino rigaScontrino : lista) {
//            totale += rigaScontrino.getProdotto().getPrezzo();
//        }

        // metodo 3
        totale = lista.stream()
                .mapToDouble(r -> r.getProdotto().getPrezzo())
                .sum();
        // salvo il totale sullos contrino
        scontrino.setTotale(totale);
        scontrino = scontrinoRepository.save(scontrino);

        // ritorno DTO con esito, scontrino e righe
        return new RispostaEanDtoQuattro(esito, scontrino, scontrino.getRighe(), ultimaRiga);
    }

    @Override
    public ScontrinoDto stornaUltimoAction(Scontrino scontrino) {
        //Si prende lo scontrino aggiornato dalla repository
        scontrino = scontrinoRepository.findByIdEquals(scontrino.getId());
        //Si crea una lista con le righe dello scontrino
        List<RigaScontrino>listaSenzaUltimo = scontrino.getRighe();
        //Si rimuove dalla lista l'ultimo elemento della collezione
        //Si setta la lista righe dello scontrino con la nuova lista
        RigaScontrino rigaDaRimuovere = listaSenzaUltimo.get(listaSenzaUltimo.size()-1);
       
        rigaDaRimuovere = listaSenzaUltimo.remove(listaSenzaUltimo.size()-1);
        scontrino.setRighe(listaSenzaUltimo);
        //Si salva lo scontrino nella repo
        scontrino = scontrinoRepository.save(scontrino);
         //Si rimuove l'ultima riga dalla repo RigheScontrino
        rigaScontrinoRepository.deleteById(rigaDaRimuovere.getId());
        return new ScontrinoDto(scontrino);
        
    }
}

    