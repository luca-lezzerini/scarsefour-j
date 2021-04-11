package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.repository.PosizioneScaffaleRepository;
import it.iad2.scarsefourserver.service.VisualizzaGiacenzaService;
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
    public ListaProdottiDto visualizzaGiacenza(PosizioneScaffaleDto dto) {
        return new ListaProdottiDto(posizioneScaffaleRepository.visualizzaGiacenzaProdotti(dto.getPosizione().getId()));
    }

}
