package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ProdottoDto;
import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.RigaScontrinoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;
import it.iad2.scarsefourserver.service.DashboardQuattroService;
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
        Prodotto prodotto = prodottoRepository.findByCodice(ean);
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
    public RispostaEanDto verificaEanAction(String ean, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
