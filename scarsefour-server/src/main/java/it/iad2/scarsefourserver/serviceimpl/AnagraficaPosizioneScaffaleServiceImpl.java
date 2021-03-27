package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.iad2.scarsefourserver.service.AnagraficaPosizioneScaffaleService;

@Service
public class AnagraficaPosizioneScaffaleServiceImpl implements AnagraficaPosizioneScaffaleService{
    
    @Autowired
    PosizioneScaffaleRepository posizioneRepository;

    @Override
    public List<PosizioneScaffale> ricerca(String s) {
        return posizioneRepository.findByCodiceContains(s);
    }

    @Override
    public List<PosizioneScaffale> rimuovi(PosizioneScaffale posizione) {
        posizioneRepository.delete(posizione);
        return aggiorna();
    }

    @Override
    public List<PosizioneScaffale> modifica(PosizioneScaffale posizione) {
        posizioneRepository.save(posizione);
        return aggiorna();
    }

    @Override
    public List<PosizioneScaffale> aggiungi(PosizioneScaffale posizione) {
        posizioneRepository.save(posizione);
        return aggiorna();
    }

    @Override
    public List<PosizioneScaffale> aggiorna() {
        return posizioneRepository.findAll();
    }

}
