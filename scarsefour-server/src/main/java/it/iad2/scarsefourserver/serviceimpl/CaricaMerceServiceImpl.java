package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.repository.CaricaMerceRepository;
import it.iad2.scarsefourserver.service.CaricaMerceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaricaMerceServiceImpl implements CaricaMerceService{
    
    @Autowired
    CaricaMerceRepository caricaMerceRepository;
    
    @Override
    public List<Prodotto> caricaMerce() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        long idPos = 1;
        return caricaMerceRepository.selezionaProdottiInPosizioneScaffale(idPos);
    }
    
}
