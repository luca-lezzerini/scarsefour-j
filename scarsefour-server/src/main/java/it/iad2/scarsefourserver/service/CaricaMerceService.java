package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.CaricaMerceDto;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.ProdottoGiacenza;
import java.util.List;

public interface CaricaMerceService {
    
    List<PosizioneScaffale> cercaPosizioni( String criterio);

    List<ProdottoGiacenza> caricaProdottiScaffale(Long id);
    
    List<ProdottoGiacenza> caricaMerce(CaricaMerceDto dto);

}
