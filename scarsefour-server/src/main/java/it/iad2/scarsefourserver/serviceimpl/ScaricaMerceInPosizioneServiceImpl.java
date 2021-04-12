package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.service.ScaricaMerceInPosizioneService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class ScaricaMerceInPosizioneServiceImpl implements ScaricaMerceInPosizioneService {

    @Autowired
    ProdottoRepository prodottoRepository;
    
    @Override
    public List<Prodotto> mostraProdottiScaffale() {
        
        return null;
    }
    
}
