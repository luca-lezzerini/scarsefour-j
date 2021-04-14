package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.CaricaMerceDto;
import it.iad2.scarsefourserver.model.MovimentiScaffale;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.repository.CaricaMerceRepository;
import it.iad2.scarsefourserver.repository.MovimentiScaffaleRepository;
import it.iad2.scarsefourserver.repository.SkuScaffaleRepository;
import it.iad2.scarsefourserver.service.CaricaMerceService;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaricaMerceServiceImpl implements CaricaMerceService {

    @Autowired
    CaricaMerceRepository caricaMerceRepository;
    @Autowired
    SkuScaffaleRepository skuScaffaleRepository;
    @Autowired
    MovimentiScaffaleRepository movimentiScaffaleRepository;

    @Override
    public List<PosizioneScaffale> cercaPosizioni(String criterio) {
        return caricaMerceRepository.cercaLikeDescrizione(criterio);
    }
    
    @Override
    public List<Prodotto> caricaProdottiScaffale(Long id) {
        System.out.println("sono in carica prodotti - service impl");
        System.out.println("id : " + id);
        return caricaMerceRepository.selezionaProdottiInPosizioneScaffale(id);
    }

    @Override
    public boolean caricaMerce(CaricaMerceDto dto) {
        System.out.println("sono in carica merce - service impl");

//        //in base alla posizione scaffale e al prodotto, seleziono SkuScaffale e aggiorno la giacenza
//        SkuScaffale ss = caricaMerceRepository.selezionaSkuScaffale(dto.getPosizioneScaffale().getId(), dto.getProdotto().getId());
//        ss.setGiacenza(ss.getGiacenza() + dto.getQuantita());
//        skuScaffaleRepository.save(ss);

        //Inserisco un movimento magazzino di carico con la quantità caricata
        MovimentiScaffale ms = new MovimentiScaffale();
        ms.setQuantita(dto.getQuantita());
        ms.setTimestamp(LocalDateTime.now());
        //ms.setTipo("carico");
        movimentiScaffaleRepository.save(ms);

        return true;
    }

}
