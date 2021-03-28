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
        return scontoRepository.findAll();
    }

    @Override
    public List<Sconto> aggiungiSconto(Sconto sconto) {
       scontoRepository.save(sconto);
       return aggiornaListaSconti();
    }

    @Override
    public List<Sconto> modificaSconto(Sconto sconto) {
        scontoRepository.save(sconto);
        return aggiornaListaSconti();
    }

    @Override
    public List<Sconto> rimuoviSconto(Sconto sconto) {
       scontoRepository.delete(sconto);
       return aggiornaListaSconti();
    }

    @Override
    public List<Sconto> ricercaSconto(String s) {
       return scontoRepository.findByCodiceContains(s);
    }
    
}
