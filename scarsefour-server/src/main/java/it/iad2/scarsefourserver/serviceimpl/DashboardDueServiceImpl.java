package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.service.DashboardDueService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;

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
        scontrinoRepository.delete(scontrino);
        return new Scontrino();
    }

    @Override
    public double vediPrezzo(String barcode) {
        return prodottoRepository.findByEanEquals(barcode).getPrezzo();
    }

    @Override
    public Scontrino chiudiScontrino(Scontrino scontrino) {
        scontrinoRepository.save(scontrino);
        //stampato
        return new Scontrino();

    }

    @Override
    public Scontrino stornaUltimo(Scontrino scontrino) {
        RigaScontrino riga = scontrino.getRighe().get(scontrino.getRighe().size() - 1);
        rigaScontrinoRepository.deleteById(riga.getId());
        return scontrino;
    }

    @Override
    public Scontrino aggiungi(String barcode, Scontrino scontrino) {
        boolean esito;
        Prodotto prodotto = prodottoRepository.findByEanEquals(barcode);
        if (prodotto == null) {
            esito = false;
        } else {
            RigaScontrino riga = new RigaScontrino();
            riga.setProdotto(prodotto);
            riga.setScontrino(scontrino);
            rigaScontrinoRepository.save(riga);
            esito = true;
        }
        return scontrino;

    }

}
