package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.CaricaMerceDto;
import it.iad2.scarsefourserver.dto.EsitoDtoDue;
import it.iad2.scarsefourserver.model.Prodotto;
import java.util.List;

public interface CaricaMerceService {

    List<Prodotto> caricaProdottiScaffale(Long id);
    
    boolean caricaMerce(CaricaMerceDto dto);

}
