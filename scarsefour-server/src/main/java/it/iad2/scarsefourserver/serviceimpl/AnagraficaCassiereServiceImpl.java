package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Cassiere;
import it.iad2.scarsefourserver.repository.CassiereRepository;
import it.iad2.scarsefourserver.service.AnagraficaCasseService;
import it.iad2.scarsefourserver.service.AnagraficaCassiereService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnagraficaCassiereServiceImpl implements AnagraficaCassiereService {

    @Autowired
    CassiereRepository cassiereRepository;

    @Override
    public List<Cassiere> aggiungiCassiere(Cassiere cassiere) {
        cassiereRepository.save(cassiere);
        return visualizzaListaCassieri();
    }

    @Override
    public List<Cassiere> modificaCassiere(Cassiere cassiere, String criterio) {
        cassiereRepository.save(cassiere);
        return visualizzaListaCassieri();
    }

    @Override
    public List<Cassiere> rimuoviCassiere(Cassiere cassiere, String criterio) {
        cassiereRepository.delete(cassiere);
        return visualizzaListaCassieri();
    }

    @Override
    public List<Cassiere> ricercaCassiere(String criterio) {
        return cassiereRepository.cercaLikeCognome(criterio);
    }

    @Override
    public List<Cassiere> visualizzaListaCassieri() {
        return cassiereRepository.findAll();
    }
}
