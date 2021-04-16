package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.ListaGiacenzaDto;
import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.dto.PageDto;
import it.iad2.scarsefourserver.dto.PaginazioneDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import org.springframework.data.domain.Page;

public interface VisualizzaGiacenzaService {

    /**
     * Restituisce la lista della posizioni
     *
     * @return ListaPosizioneScaffaleDto
     * @see ListaPosizioneScaffaleDto
     */
    ListaPosizioneScaffaleDto aggiornaPosizioni();

    /**
     * Restiuisce una lista di prodotto con giacenze prendendo in ingresso una
     * posizione scaffale
     *
     * @param dto di tipo PosizioneScaffaleDto
     * @return ListaGiacenzaDto
     * @see ListaGiacenzaDto
     */
    ListaGiacenzaDto visualizzaGiacenza(PosizioneScaffaleDto dto);
    
    Page<PosizioneScaffale> trovaPaginatiConCriterio(PaginazioneDto dto);

}
