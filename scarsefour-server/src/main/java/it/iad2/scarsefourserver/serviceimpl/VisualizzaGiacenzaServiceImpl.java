package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ListaGiacenzaDto;
import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.model.Giacenza;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.SkuScaffale;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import it.iad2.scarsefourserver.service.VisualizzaGiacenzaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisualizzaGiacenzaServiceImpl implements VisualizzaGiacenzaService {

    @Autowired
    PosizioneScaffaleRepository posizioneScaffaleRepository;

    @Override
    public ListaPosizioneScaffaleDto aggiornaPosizioni() {
        return new ListaPosizioneScaffaleDto(posizioneScaffaleRepository.findAll());
    }

    @Override
    public ListaGiacenzaDto visualizzaGiacenza(PosizioneScaffaleDto dto) {
        List<Object[]> listaObj = posizioneScaffaleRepository.visualizzaGiacenzaProdotti(dto.getPosizione().getId());
        List<Giacenza> listaGiacenza = new ArrayList<>();
        for (Object o[] : listaObj) {
            Prodotto p = (Prodotto) o[0];
            SkuScaffale s = (SkuScaffale) o[1];
            Giacenza g = new Giacenza();
            g.setCodice(p.getCodice());
            g.setDescrizione(p.getDescrizione());
            g.setGiacenza(s.getGiacenza());
            g.setScortaMinima(s.getScortaMinima());
            listaGiacenza.add(g);
        }
        //return new ListaGiacenzaDto(posizioneScaffaleRepository.visualizzaGiacenzaProdotti(dto.getPosizione().getId()));
        return new ListaGiacenzaDto(listaGiacenza);
    }

}
