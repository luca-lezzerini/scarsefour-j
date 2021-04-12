package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.IdPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.service.ScaricaMerceInPosizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class ScaricaMerceInPosizioneController {

    @Autowired
    ScaricaMerceInPosizioneService scaricaMerceInPosizioneService;

    @RequestMapping("/mostra-merce-scaffali")
    public ListaProdottiDto mostraMerceScaffali(@RequestBody IdPosizioneScaffaleDto dto){
        return new ListaProdottiDto(scaricaMerceInPosizioneService.mostraProdottiScaffale(dto.getId()));
    }
    
}
