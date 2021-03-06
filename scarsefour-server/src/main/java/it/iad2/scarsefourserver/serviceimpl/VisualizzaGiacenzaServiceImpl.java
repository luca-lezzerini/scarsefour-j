package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ListaGiacenzaDto;
import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.PageDto;
import it.iad2.scarsefourserver.dto.PaginazioneDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.model.Giacenza;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.SkuScaffale;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import it.iad2.scarsefourserver.service.VisualizzaGiacenzaService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        List<Giacenza> listaGiacenza = posizioneScaffaleRepository.visualizzaGiacenzaProdotti(dto.getPosizione().getId());
        return new ListaGiacenzaDto(listaGiacenza);
    }

    @Override
    public Page<PosizioneScaffale> trovaPaginatiConCriterio(PaginazioneDto dto) {
        return posizioneScaffaleRepository.trovaPaginatiConCriterio(dto.getCriterio(), PageRequest.of(dto.getNumPag(), dto.getElemPag()));
    }

}
