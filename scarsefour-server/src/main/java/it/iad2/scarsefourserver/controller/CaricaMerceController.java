package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CaricaMerceDto;
import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.EsitoDtoDue;
import it.iad2.scarsefourserver.dto.IdPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaProdottiGiacenzaDto;
import it.iad2.scarsefourserver.service.AnagraficaPosizioneScaffaleService;
import it.iad2.scarsefourserver.service.CaricaMerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Portatile
 */
@CrossOrigin("*")
@RestController
public class CaricaMerceController {

    @Autowired
    CaricaMerceService caricaMerceService;

    @Autowired
    AnagraficaPosizioneScaffaleService anagraficaPosizioniScaffaleService;
    
    @RequestMapping("/cerca-posizioni")
    public ListaPosizioneScaffaleDto cercaPosizioni(@RequestBody CriterioRicercaDto dto) {
        System.out.println("sono in cerca-posizioni");
        return new ListaPosizioneScaffaleDto(caricaMerceService.cercaPosizioni(dto.getCriterio()));
    }

    @RequestMapping("/carica-posizioni")
    @ResponseBody
    public ListaPosizioneScaffaleDto caricaPosizioni() {
        return new ListaPosizioneScaffaleDto(anagraficaPosizioniScaffaleService.aggiorna());
    }

    @RequestMapping("/carica-prodotti")
    public ListaProdottiGiacenzaDto caricaProdotti(@RequestBody IdPosizioneScaffaleDto dto) {
        System.out.println("sono in carica prodotti");
        return new ListaProdottiGiacenzaDto(caricaMerceService.caricaProdottiScaffale(dto.getId()));
    }
    
    @RequestMapping("/carica-merce")
    public ListaProdottiGiacenzaDto caricaMerce(@RequestBody CaricaMerceDto dto) {
        System.out.println("sono in carica merce");
        return new ListaProdottiGiacenzaDto(caricaMerceService.caricaMerce(dto));
    }
}
