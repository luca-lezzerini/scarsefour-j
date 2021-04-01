package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ProdottoDto;
import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.dto.RispostaEanDtoQuattro;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;
import it.iad2.scarsefourserver.service.DashboardQuattroService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Valerio
 */
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
    public RispostaEanDto stornaUltimoAction(String ean, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDto annullaScontrinoAction(Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDto inserisciEanAction(String ean, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDto stornaAction(String ean, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDtoQuattro verificaEanAction(String ean, Scontrino scontrino) {
        // prepara una variabile per ritornare l'esito (EAN trovato o meno)
        boolean esito;
        // ------ vede se lo scontrino esiste già su DB ---
        // non esiste? (ossia il client me lo manda vuoto?)
        if (scontrino == null || scontrino.getId() == null) {
            // ... ricevuto scontrino vuoto, quindi lo creo ...
            scontrino = new Scontrino();
            scontrino = scontrinoRepository.save(scontrino);
        } else {
            // ... poichè ricevuto non vuoto, lo cerco aggiornato sul DB
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
        // verifichiamo se il prodotto è presente
        Prodotto prodotto = prodottoRepository.findByEanEquals(ean);
        // trovato?
        if (prodotto == null) {
            // no, esito negativo e ritorno così
            esito = false;
        } else {
            // si, allora aggiungo una riga e esito positivo
            esito = true;
            // aggiungo la riga allo scontrino
            RigaScontrino rs = new RigaScontrino();
            rs.setProdotto(prodotto);
            rs.setQuantita(1);
            rs.setScontrino(scontrino);
            rs = rigaScontrinoRepository.save(rs);
            // aggiorno relazione lato prodotto
            prodotto.getRighe().add(rs);
            prodotto = prodottoRepository.save(prodotto);
            // aggiungo relazione lato scontrino
            scontrino.getRighe().add(rs);
            scontrino = scontrinoRepository.save(scontrino);
        }
        // calcoliamo il totale
        double totale = 0;
        List<RigaScontrino> lista = scontrino.getRighe();
        for (int i = 0; i < lista.size(); i++) {
            RigaScontrino riga = lista.get(i);
            totale += riga.getProdotto().getPrezzo();
        }

        // ritorno DTO con esito, scontrino e righe
        return new RispostaEanDtoQuattro(esito, scontrino, scontrino.getRighe());
    }
}
