package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.ListaGiacenzaDto;
import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.service.VisualizzaGiacenzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class VisualizzaGiacenzaController {

    @Autowired
    VisualizzaGiacenzaService visualizzaGiacenzaService;

    @RequestMapping("/aggiorna-posizioni-giacenza")
    @ResponseBody
    public ListaPosizioneScaffaleDto aggiornaPosizioni() {
        return visualizzaGiacenzaService.aggiornaPosizioni();
    }

    @RequestMapping("/visualizza-giacenza")
    @ResponseBody
    public ListaGiacenzaDto visualizzaGiacenza(@RequestBody PosizioneScaffaleDto dto) {
        return visualizzaGiacenzaService.visualizzaGiacenza(dto);
    }
}
