package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.CaricaMerceDto;
import it.iad2.scarsefourserver.model.MovimentiScaffale;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.ProdottoGiacenza;
import it.iad2.scarsefourserver.model.SkuScaffale;
import it.iad2.scarsefourserver.repository.CaricaMerceRepository;
import it.iad2.scarsefourserver.repository.MovimentiScaffaleRepository;
import it.iad2.scarsefourserver.repository.SkuScaffaleRepository;
import it.iad2.scarsefourserver.service.CaricaMerceService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CaricaMerceServiceImpl implements CaricaMerceService {

    @Autowired
    CaricaMerceRepository caricaMerceRepository;
    
    @Autowired
    MovimentiScaffaleRepository movimentiScaffaleRepository;
    
    @Autowired
    SkuScaffaleRepository skuScaffaleRepository;
    
    @Override
    public List<PosizioneScaffale> cercaPosizioni(String criterio) {
        return caricaMerceRepository.cercaLikeDescrizione(criterio);
    }
    
    @Override
    public List<ProdottoGiacenza> caricaProdottiScaffale(Long id) {
        return caricaMerceRepository.selezionaProdottiGiacenzaByIdPos(id);
    }

    @Override
    public List<ProdottoGiacenza> caricaMerce(CaricaMerceDto dto) {
        System.out.println("sono in carica merce - service impl");
        System.out.println("dto.getId_Pos : " + dto.getId_Pos());
        System.out.println("dto.getId_Sku : " + dto.getId_Sku());
        System.out.println("dto.getQuantita : " + dto.getQuantita());

        //Seleziono il record di skuScaffale in base all'id passato dal client e aggiorno la giacenza
          SkuScaffale ss = caricaMerceRepository.selezionaSkuScaffaleById(dto.getId_Sku());
          ss.setGiacenza(ss.getGiacenza() + dto.getQuantita());
          skuScaffaleRepository.save(ss);

        //Inserisco un movimento magazzino di carico con la quantità caricata
        MovimentiScaffale ms = new MovimentiScaffale();
        ms.setQuantita(dto.getQuantita());
        ms.setTimestamp(LocalDateTime.now());
        ms.setTipo("carico");  
        //DA FARE....gestire su movimentoScaffale(sku_scaffale_id) fk con skuscaffale
        movimentiScaffaleRepository.save(ms);
        
        return caricaProdottiScaffale(dto.getId_Pos());
    }

    @Override
    public Page<PosizioneScaffale> elementiPaginati(int numPage, int elemPage) {
        return caricaMerceRepository.trovaTuttiPaginati(PageRequest.of(numPage, elemPage));
    }
}
