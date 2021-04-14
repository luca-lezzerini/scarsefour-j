package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.IdPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ScaricaMerceDto;
import it.iad2.scarsefourserver.service.ScaricaMerceInPosizioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class ScaricaMerceInPosizioneController {

    @Autowired
    ScaricaMerceInPosizioneService scaricaMerceInPosizioneService;

    @RequestMapping("/mostra-merce-scaffali")
    @ResponseBody
    public ListaProdottiDto mostraMerceScaffali(@RequestBody IdPosizioneScaffaleDto dto) {
        return new ListaProdottiDto(scaricaMerceInPosizioneService.mostraProdottiScaffale(dto.getId()));
    }

    @RequestMapping("/cerca-posizione-scaffale")
    @ResponseBody
    public PosizioneScaffaleDto cercaPosizioneScaffale(@RequestBody CriterioRicercaDto dto) {
        return new PosizioneScaffaleDto(scaricaMerceInPosizioneService.cercaScaffale(dto.getCriterio()));
    }

     @RequestMapping("/scarica-merce-scaffale")
     public void scaricaMerce(@RequestBody ScaricaMerceDto dto){
         scaricaMerceInPosizioneService.scaricaMerce(dto.getSkuScaffale(),dto.getQuantita());
     }
}
