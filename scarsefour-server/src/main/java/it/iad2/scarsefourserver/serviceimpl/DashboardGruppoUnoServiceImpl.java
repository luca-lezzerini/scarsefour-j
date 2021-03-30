package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.RispostaEanDto;
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
        //System.out.println("scontrino: " + scontrino.toString());

        Prodotto prodotto = prodottoRepository.findByEanEquals(barcode); //cerco se esiste un prodotto nel repository con il barcode passato in input
        if (prodotto == null) {
            //prodotto non trovato
            System.out.println("Errore EAN sconosciuto");
            rispostaEanDto.setScontrino(scontrino);
            if (scontrino != null) {
                rispostaEanDto.setRigheScontrino(scontrinoRepository.findById(scontrino.getId()).get().getRighe());
            }

        } else {
            //prodotto trovato
            if (scontrino == null) {
                //se lo scontrino che arriva dal client è vuoto, creo nuovo scontrino e lo salvo nel repository 
                scontrino = new Scontrino(LocalDateTime.now(), 0, 0.);
                scontrinoRepository.save(scontrino);
            }
            // salvo nel repository una nuova rigaScontrino
            RigaScontrino riga = new RigaScontrino();
            riga.setScontrino(scontrino);
            riga.setProdotto(prodotto);
            rigaScontrinoRepository.save(riga);
            // Aggiorno il totale dello scontrino e la lista righe scotrino con il prodotto in esame
            scontrino.setTotale(scontrino.getTotale() + (riga.getProdotto().getPrezzo() * riga.getQuantita()));
            scontrino.getRighe().add(riga);
            scontrinoRepository.save(scontrino);
            // preparo la risposta
            rispostaEanDto.setBarcode(barcode);
            rispostaEanDto.setScontrino(scontrino);
            rispostaEanDto.setRigheScontrino(scontrinoRepository.findById(scontrino.getId()).get().getRighe());
        }
        return rispostaEanDto;
    }

    @Override
    public RispostaEanDto chiudiScontrino1(String barcode, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double vediPrezzo1(String barcode) {
        return prodottoRepository.findByEanEquals(barcode).getPrezzo();
    }

    @Override
    public RispostaEanDto stornaUltimo1(String barcode, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDto annullaScontrino1(String barcode, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
