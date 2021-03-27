package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import java.util.List;

public interface PosizioneScaffaleService {

    List<PosizioneScaffale> ricerca(String s);

    List<PosizioneScaffale> rimuovi(PosizioneScaffale posizione);

    List<PosizioneScaffale> modifica(PosizioneScaffale posizione);

    List<PosizioneScaffale> aggiungi(PosizioneScaffale posizione);
    
    List<PosizioneScaffale> aggiorna();
}
