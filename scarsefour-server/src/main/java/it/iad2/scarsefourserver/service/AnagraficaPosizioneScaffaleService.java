package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import java.util.List;

public interface AnagraficaPosizioneScaffaleService {

    /**
     * Consente di effettuare una ricerca per codice di una posizione scaffale 
     * @param s criterio di ricerca per codice posizione
     * @return lista delle posizioni scaffale presenti in archivio
     */
    List<PosizioneScaffale> ricerca(String s);

    /**
     * Rimuove una posizione scaffale dai dati in archivio
     * @param posizione entità di tipo 
     * @see PosizioneScaffale
     * @return lista delle posizioni scaffale aggiornate
     */
    List<PosizioneScaffale> rimuovi(PosizioneScaffale posizione);

    /**
     * Modifica una posizione scaffale dai dati in archivio
     * @param posizione entità di tipo
     * @see PosizioneScaffale
     * @return lista delle posizioni scaffale aggiornate
     */
    List<PosizioneScaffale> modifica(PosizioneScaffale posizione);

    /**
     * Aggiunge una posizione scaffale ai dati in archivio
     * @param posizione di tipo
     * @see PosizioneScaffale
     * @return lista di posizioni scaffale aggiornate
     */
    List<PosizioneScaffale> aggiungi(PosizioneScaffale posizione);
    
    /**
     * Restituisce la lista di posizioni scaffale presente in archivio
     * @return lista di posizioni scaffale
     */
    List<PosizioneScaffale> aggiorna();
}
