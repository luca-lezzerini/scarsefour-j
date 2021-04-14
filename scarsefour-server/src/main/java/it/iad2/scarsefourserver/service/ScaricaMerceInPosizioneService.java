package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.SkuScaffale;
import java.util.List;

public interface ScaricaMerceInPosizioneService {

    List<Prodotto> mostraProdottiScaffale(Long id);
    
    PosizioneScaffale cercaScaffale(String codicePosizioneScaffale);
    
    void scaricaMerce(SkuScaffale sku, int qtaDaRimuovere);
}
