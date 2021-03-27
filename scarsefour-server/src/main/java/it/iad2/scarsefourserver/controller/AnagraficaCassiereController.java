package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CassiereDto;
import it.iad2.scarsefourserver.dto.CassiereRicercaDto;
import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.ListaCassieriDto;
import it.iad2.scarsefourserver.service.AnagraficaCassiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AnagraficaCassiereController {

    @Autowired
    AnagraficaCassiereService anagraficaCassiereService;

    @RequestMapping("/aggiungi-cassiere")
    @ResponseBody
    public ListaCassieriDto aggiungiCassiere(@RequestBody CassiereDto dto) {
        return new ListaCassieriDto(anagraficaCassiereService.aggiungiCassiere(dto.getCassiere()));
    }

    @RequestMapping("/modifica-cassiere")
    @ResponseBody
    public ListaCassieriDto modificaCassiere(@RequestBody CassiereRicercaDto dto) {
        return new ListaCassieriDto(anagraficaCassiereService.modificaCassiere(dto.getCassiere(), dto.getCriterio()));
    }

    @RequestMapping("/rimuovi-cassiere")
    @ResponseBody
    public ListaCassieriDto rimuoviCassiere(@RequestBody CassiereRicercaDto dto) {
        return new ListaCassieriDto(anagraficaCassiereService.rimuoviCassiere(dto.getCassiere(), dto.getCriterio()));
    }

    @RequestMapping("/ricerca-cassiere")
    @ResponseBody
    public ListaCassieriDto ricercaCassiere(@RequestBody CriterioRicercaDto dto) {
        return new ListaCassieriDto(anagraficaCassiereService.ricercaCassiere(dto.getCriterio()));
    }

    @RequestMapping("/visualizza-lista-cassieri")
    @ResponseBody
    public ListaCassieriDto visualizzaListaCassieri() {
        return new ListaCassieriDto(anagraficaCassiereService.visualizzaListaCassieri());
    }
}
