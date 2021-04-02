package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.dto.ScontrinoRigheDto;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;
import it.iad2.scarsefourserver.service.DashboardGruppoUnoService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardGruppoUnoServiceImpl implements DashboardGruppoUnoService {

    @Autowired
    ScontrinoRepository scontrinoRepository;
    @Autowired
    RigaScontrinoRepository rigaScontrinoRepository;
    @Autowired
    ProdottoRepository prodottoRepository;

    @Override
    public RispostaEanDto cercaEan1(String barcode, Scontrino scontrino) {
        RispostaEanDto rispostaEanDto = new RispostaEanDto();
        System.out.println("barcode: " + barcode);
        //verifico l'esistenza di un prodotto nel repository con il barcode passato in input
        Prodotto prodotto = prodottoRepository.findByEanEquals(barcode);
        if (prodotto == null) {
            //Se prodotto non trovato stampo a console messaggio di errore preparo i dati da inviare al client
            System.out.println("Errore EAN sconosciuto");
            //Preparo i dati da rinviare al client
            rispostaEanDto.setScontrino(scontrino);
            if (scontrino != null) {
                rispostaEanDto.setRigheScontrino(scontrinoRepository.findById(scontrino.getId()).get().getRighe());
            }
        } else {
            //prodotto trovato
            if (scontrino == null) {
                //se lo scontrino che arriva dal client è null, creo nuovo scontrino e lo salvo nel repository 
                scontrino = new Scontrino(LocalDateTime.now(), 0, 0.);
                scontrinoRepository.save(scontrino);
                // salvo nel repository una nuova rigaScontrino
                RigaScontrino riga = new RigaScontrino();
                riga.setScontrino(scontrino);
                riga.setProdotto(prodotto);
                rigaScontrinoRepository.save(riga);
                // Aggiorno il totale dello scontrino e la lista righe scotrino con il prodotto in esame
                scontrino.setTotale(scontrino.getTotale() + prodotto.getPrezzo());
                scontrino.getRighe().add(riga);
                scontrinoRepository.save(scontrino);
                //Preparo i dati da rinviare al client
                rispostaEanDto.setBarcode(barcode);
                rispostaEanDto.setScontrino(scontrino);
                rispostaEanDto.setRigheScontrino(scontrinoRepository.findById(scontrino.getId()).get().getRighe());
            } else {
                // lo scontrino è esistente:
                // carico la lista delle righe associate allo scontrino
                List<RigaScontrino> listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
                boolean trovato = false;
                // scorro la lista
                for (RigaScontrino riga : listaRighe) {
                    // verifico se il prodotto in esame è già presente nell'elenco
                    if (riga.getProdotto().getEan().equals(barcode)) {
                        //se già presente, incremento la quantità del prodotto                        
                        riga.setQuantita(riga.getQuantita() + 1);
                        //aggiorno il totale dello scontrino sommandogli il preddo del prodotto 
                        scontrino.setTotale(scontrino.getTotale() + prodotto.getPrezzo());
                        scontrino.setRighe(listaRighe);
                        //aggiorno il database 
                        rigaScontrinoRepository.save(riga);
                        scontrinoRepository.save(scontrino);
                        trovato = true;
                        //Preparo i dati da rinviare al client
                        rispostaEanDto.setBarcode(barcode);
                        rispostaEanDto.setScontrino(scontrino);
                        rispostaEanDto.setRigheScontrino(listaRighe);
                        break;
                    }
                }
                if (!trovato) {
                    //il prodotto in esame non è già presente nello scontrino
                    listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
                    // salvo nel repository una nuova rigaScontrino
                    RigaScontrino riga = new RigaScontrino();
                    riga.setScontrino(scontrino);
                    riga.setProdotto(prodotto);
                    rigaScontrinoRepository.save(riga);
                    listaRighe.add(riga);
                    // Aggiorno il totale dello scontrino e la lista righe scotrino con il prodotto in esame
                    scontrino.setTotale(scontrino.getTotale() + prodotto.getPrezzo());
                    scontrino.setRighe(listaRighe);
                    scontrinoRepository.save(scontrino);
                    //Preparo i dati da rinviare al client
                    rispostaEanDto.setBarcode(barcode);
                    rispostaEanDto.setScontrino(scontrino);
                    rispostaEanDto.setRigheScontrino(listaRighe);
                }
            }

        }
        return rispostaEanDto;
    }

    @Override
    public Scontrino chiudiScontrino1(Scontrino scontrino) {
        //chiudo lo scontrino assegnandogli un numero progressivo continuativo
        scontrino.setNumero(scontrinoRepository.findAll().size() + 1);
        //aggiorno il database 
        scontrinoRepository.save(scontrino);
        return null;
    }

    @Override
    public double vediPrezzo1(String barcode) {
        //prendo dal DB i dati relativi al prodotto in esame e rimando il prezzo 
        Prodotto prodotto = prodottoRepository.findByEanEquals(barcode);
        if (prodotto != null) {
            return prodotto.getPrezzo();
        }
        return 0;
    }

    //metodo con operatori funzionali
    @Override
    public ScontrinoRigheDto stornaUltimo1(Scontrino scontrino, String lastBarcode) {
        List<RigaScontrino> listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
        listaRighe.stream()
                .filter(f -> f.getProdotto().getEan().equals(lastBarcode) && f.getQuantita() == 1)
                .forEach(r -> {
                    scontrino.setTotale(scontrino.getTotale() - r.getProdotto().getPrezzo());
                    rigaScontrinoRepository.delete(r);
                });
        listaRighe
                .removeIf((f) -> f.getProdotto().getEan().equals(lastBarcode) && f.getQuantita() == 1);
        listaRighe.stream()
                .filter(f -> f.getProdotto().getEan().equals(lastBarcode) && f.getQuantita() > 1)
                .forEach(r -> {
                    scontrino.setTotale(scontrino.getTotale() - r.getProdotto().getPrezzo());
                    r.setQuantita(r.getQuantita() - 1);
                    rigaScontrinoRepository.save(r);
                });
        scontrino.setRighe(listaRighe);
        scontrinoRepository.save(scontrino);
        
        return new ScontrinoRigheDto(scontrino, listaRighe);
    }

    //Prima versione del metodo 
//        //seleziono tutte le righe dello scontrino 
//        List<RigaScontrino> listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
//        //scorro tutta la lista
//        for (RigaScontrino riga : listaRighe) {
//            // verifico se il prodotto della lista che sto scorrendo corrispondesse all'ultimo prodotto da cancellare
//            if (riga.getProdotto().getEan().equals(lastBarcode)) {
//                // se corrisponde sottraggo l'imporo del prodotto dal totale
//                scontrino.setTotale(scontrino.getTotale() - riga.getProdotto().getPrezzo());
//                // se il prodotto ha più di un elemento decremento solo la quantità e aggiorno la tabella RigaScontrino
//                if (riga.getQuantita() > 1) {
//                    riga.setQuantita(riga.getQuantita() - 1);
//                    rigaScontrinoRepository.save(riga);
//                } else {
//                    //cancello il prodotto  
//                    rigaScontrinoRepository.delete(riga);
//                    listaRighe.remove(riga);
//                    scontrino.setRighe(listaRighe);
//                }
//                scontrinoRepository.save(scontrino);
//                break;
//            }
//        }
//        return new ScontrinoRigheDto(scontrino, listaRighe);
//}

@Override
public Scontrino annullaScontrino1(Scontrino scontrino) {
        //seleziono tutte le righe dello scontrino da cancellare che andrò a cancellare dalla tabella RigaScontrino
        List<RigaScontrino> listaRighe = scontrinoRepository.findById(scontrino.getId()).get().getRighe();
        listaRighe.forEach(x -> {
            rigaScontrinoRepository.delete(x);
        });
        //Cancello lo scontrino nella tabella Scontrino
        scontrinoRepository.delete(scontrino);
        return null;
    }

}
