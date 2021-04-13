package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.CaricaMerceDto;
import it.iad2.scarsefourserver.dto.EsitoDtoDue;
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
    public List<Prodotto> caricaProdottiScaffale(Long id) {
        System.out.println("sono in carica prodotti service impl");
        System.out.println("id : " + id);
        return caricaMerceRepository.selezionaProdottiInPosizioneScaffale(id);
    }

    @Override
    public boolean caricaMerce(CaricaMerceDto dto) {
        //caricaMerceRepository.selezionaSkuScaffale(, Long.MIN_VALUE)
        
        return true;
    }
    
    
}
