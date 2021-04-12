package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.Prodotto;
import java.util.List;

public interface ScaricaMerceInPosizioneService {

    List<Prodotto> mostraProdottiScaffale(Long id);
}
