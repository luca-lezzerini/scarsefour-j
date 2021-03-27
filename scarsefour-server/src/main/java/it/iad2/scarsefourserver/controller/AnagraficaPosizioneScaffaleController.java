package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.service.PosizioneScaffaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AnagraficaPosizioneScaffaleController {
    
    @Autowired
    PosizioneScaffaleService posizioneService;
    
    @RequestMapping("/ricerca-posizioni")
    @ResponseBody
    public ListaPosizioneScaffaleDto ricerca (@RequestBody CriterioRicercaDto dto){
        return new ListaPosizioneScaffaleDto(posizioneService.ricerca(dto.getCriterio()));
    }
    
    @RequestMapping("/rimuovi-posizioni")
    @ResponseBody
    public ListaPosizioneScaffaleDto rimuovi (@RequestBody PosizioneScaffaleDto dto){
        return new ListaPosizioneScaffaleDto(posizioneService.rimuovi(dto.getPosizione()));
    }
    
    @RequestMapping("/modifica-posizioni")
    @ResponseBody
    public ListaPosizioneScaffaleDto modifica (@RequestBody PosizioneScaffaleDto dto){
        return new ListaPosizioneScaffaleDto(posizioneService.modifica(dto.getPosizione()));
    }
    
    @RequestMapping("/aggiungi-posizioni")
    @ResponseBody
    public ListaPosizioneScaffaleDto aggiungi (@RequestBody PosizioneScaffaleDto dto){
        return new ListaPosizioneScaffaleDto(posizioneService.aggiungi(dto.getPosizione()));
    }
    
    @RequestMapping("/aggiorna-posizioni")
    @ResponseBody
    public ListaPosizioneScaffaleDto aggiorna (){
        return new ListaPosizioneScaffaleDto(posizioneService.aggiorna());
    }

}
