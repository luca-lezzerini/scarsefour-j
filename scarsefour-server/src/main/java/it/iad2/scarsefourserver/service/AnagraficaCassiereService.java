package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.Cassiere;
import java.util.List;


public interface AnagraficaCassiereService {
    
    List<Cassiere> aggiungiCassiere(Cassiere cassiere);
    
    List<Cassiere> modificaCassiere(Cassiere cassiere, String criterio);
    
    List<Cassiere> rimuoviCassiere(Cassiere cassiere, String criterio);
    
    List<Cassiere> ricercaCassiere(String criterio);
    
    List<Cassiere> visualizzaListaCassieri();
}
