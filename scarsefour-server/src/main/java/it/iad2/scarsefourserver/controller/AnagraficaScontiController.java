package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.ListaScontiDto;
import it.iad2.scarsefourserver.dto.ScontoDto;
import it.iad2.scarsefourserver.service.AnagraficaScontiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AnagraficaScontiController {

    @Autowired
    AnagraficaScontiService scontiService;

    @RequestMapping("/aggiorna-lista-sconti")
    @ResponseBody
    public ListaScontiDto aggiornaListaSconti() {
        return new ListaScontiDto(scontiService.aggiornaListaSconti());
    }

    @RequestMapping("/aggiungi-sconto")
    @ResponseBody
    public ListaScontiDto aggiungiSconto(@RequestBody ScontoDto dto) {
       return new ListaScontiDto(scontiService.aggiungiSconto(dto.getSconto()));
    }

    @RequestMapping("/modifica-sconto")
    @ResponseBody
    public ListaScontiDto modificaSconto(@RequestBody ScontoDto dto) {
        return new ListaScontiDto(scontiService.modificaSconto(dto.getSconto()));
    }
    
    @RequestMapping("/rimuovi-sconto")
    @ResponseBody
    public ListaScontiDto rimuoviSconto(@RequestBody ScontoDto dto){
       return new ListaScontiDto(scontiService.rimuoviSconto(dto.getSconto()));
    }
    
    @RequestMapping("/ricerca-sconto")
    @ResponseBody
    public ListaScontiDto ricercaSconto (@RequestBody CriterioRicercaDto dto){
        return new ListaScontiDto(scontiService.ricercaSconto(dto.getCriterio()));
    }

}
