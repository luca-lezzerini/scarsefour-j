package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.repository.CaricaMerceRepository;
import it.iad2.scarsefourserver.service.CaricaMerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaricaMerceServiceImpl implements CaricaMerceService{
    
    @Autowired
    CaricaMerceRepository caricaMerceRepository;
    
    @Override
    public ListaProdottiDto caricaMerce() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        //long idPos = 1;
        //return new ListaProdottiDto(caricaMerceRepository.selezionaProdottiInPosizioneScaffale(idPos));
    }
    
}
