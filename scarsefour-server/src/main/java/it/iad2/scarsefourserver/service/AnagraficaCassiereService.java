package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.Cassiere;
import java.util.List;


public interface AnagraficaCassiereService {
    
    /**
     * Salva i dati di un nuovo cassiere nel archivio.
     * @param cassiere persona che gestisce una cassa
     * @return lista dei cassieri presenti in archivio
     */
    List<Cassiere> aggiungiCassiere(Cassiere cassiere);
    
    /**
     * Modifica i dati inerenti ad un cassiere presente in archivio.
     * @param cassiere persona che gestisce una cassa
     * @param criterio criterio di ricerca 
     * @return lista dei cassieri presenti in archivio
     */
    List<Cassiere> modificaCassiere(Cassiere cassiere, String criterio);
    
    /**
     * Rimuove i dati inerenti ad un cassiere presente in archivio.
     * @param cassiere persona che gestisce una cassa
     * @param criterio criterio di ricerca
     * @return lista dei cassieri presenti in archivio
     */
    List<Cassiere> rimuoviCassiere(Cassiere cassiere, String criterio);
    
    /**
     * Consente di ricercare i dati di un cassiere presente in un archivio.
     * @param criterio criterio di ricerca
     * @return lista dei cassieri presenti in archivio 
     */
    List<Cassiere> ricercaCassiere(String criterio);
    
    /**
     * Consente di visualizzare la lista di tutti i cassieri presenti nell'archivio.
     * @return lista dei cassieri presenti in archivio
     */
    List<Cassiere> visualizzaListaCassieri();
}
