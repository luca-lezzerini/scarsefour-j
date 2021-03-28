package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.Sconto;
import java.util.List;

/**
 *
 * @author Valerio
 */
public interface AnagraficaScontiService {

    List<Sconto> aggiornaListaSconti();

    List<Sconto> aggiungiSconto(Sconto sconto);

    List<Sconto> modificaSconto(Sconto sconto);

    List<Sconto> rimuoviSconto(Sconto sconto);

    List<Sconto> ricercaSconto(String criterio);

}
