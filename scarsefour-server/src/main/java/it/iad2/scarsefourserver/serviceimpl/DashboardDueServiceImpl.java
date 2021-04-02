package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.AggiungiDto;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.service.DashboardDueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DashboardDueServiceImpl implements DashboardDueService {

    @Autowired
    ScontrinoRepository scontrinoRepository;
    @Autowired
    ProdottoRepository prodottoRepository;
    @Autowired
    RigaScontrinoRepository rigaScontrinoRepository;

    @Override
    public Scontrino annullaScontrino(Scontrino scontrino) {
        for (int i = 0; i < scontrino.getRighe().size();){
            stornaUltimo(scontrino);
        }
        return scontrino;
    }

    @Override
    public double vediPrezzo(String barcode) {
        boolean esitoRicerca = cercaProdotto(barcode);
        if (esitoRicerca){
            return prodottoRepository.findByEanEquals(barcode).getPrezzo();
        }
        return 0.0;
    }

    @Override
    public Scontrino chiudiScontrino(Scontrino scontrino) {
        scontrino.setTimeStamp(LocalDateTime.now());
        scontrino.setNumero(scontrinoRepository.cercaUltimoScontrino() + 1);
        scontrinoRepository.save(scontrino);
        //stampato
        return generaScontrino();

    }

    @Override
    public Scontrino stornaUltimo(Scontrino scontrino) {
        RigaScontrino riga = scontrino.getRighe().get(scontrino.getRighe().size() - 1);
        double totale = scontrino.getTotale();
        totale -= riga.getProdotto().getPrezzo();
        scontrino.setTotale(totale);
        List<RigaScontrino> lista = scontrino.getRighe();
        lista.remove(riga);
        scontrino.setRighe(lista);
        rigaScontrinoRepository.deleteById(riga.getId());
        scontrinoRepository.save(scontrino);
        return scontrino;
    }

    @Override
    public AggiungiDto aggiungi(String barcode, Scontrino scontrino) {
        boolean esito;
        Prodotto prodotto = prodottoRepository.findByEanEquals(barcode);
        if (prodotto == null) {
            esito = false;
        } else {
            RigaScontrino riga = new RigaScontrino();
            riga.setProdotto(prodotto);
            riga.setScontrino(scontrino);
            double totale = scontrino.getTotale();
            totale += prodotto.getPrezzo();
            scontrino.setTotale(totale);
            List<RigaScontrino> lista = scontrino.getRighe();
            lista.add(riga);
            scontrino.setRighe(lista);
            rigaScontrinoRepository.save(riga);
            scontrinoRepository.save(scontrino);
            esito = true;
        }
        return new AggiungiDto(scontrino, esito);
    }

    @Override
    public boolean cercaProdotto(String barcode) {
        boolean esito;
        Prodotto prodotto = prodottoRepository.findByEanEquals(barcode);
        if (prodotto == null) {
            esito = false;
        } else {
            esito = true;
        }
        return esito;
    }

    @Override
    public Scontrino generaScontrino() {
        Scontrino scontrino = new Scontrino();
        scontrino = scontrinoRepository.save(scontrino);
        return scontrino;
    }

}
