package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Sconto;
import it.iad2.scarsefourserver.repository.ScontoRepository;
import it.iad2.scarsefourserver.service.AnagraficaScontiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnagraficaScontiServiceImpl implements AnagraficaScontiService {
    
    @Autowired
    ScontoRepository scontoRepository;
    

    @Override
    public List<Sconto> aggiornaListaSconti() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sconto> aggiungiSconto(Sconto sconto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sconto> modificaSconto(Sconto sconto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sconto> rimuoviSconto(Sconto sconto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Sconto> ricercaSconto(String criterio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
