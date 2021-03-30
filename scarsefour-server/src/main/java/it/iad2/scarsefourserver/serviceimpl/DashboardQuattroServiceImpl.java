package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.service.DashboardQuattroService;
import org.springframework.stereotype.Service;

/**
 *
 * @author Valerio
 */
@Service
public class DashboardQuattroServiceImpl implements DashboardQuattroService {

    @Override
    public RispostaEanDto vediPrezzo(String ean) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDto chiudiScontrino(Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDto stornaUltimo(String ean, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDto annullaScontrino(Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RispostaEanDto inserisciEan(String ean, Scontrino scontrino) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
