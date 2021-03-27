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

//    @Autowired
//    AnagraficaCassiereService anagraficaCassiereService;

    @RequestMapping("/aggiungi-cassiere")
    @ResponseBody
    public ListaCassieriDto aggiungiCassiere(@RequestBody CassiereDto dto) {
        throw new UnsupportedOperationException();
    }

    @RequestMapping("/modifica-cassiere")
    @ResponseBody
    public ListaCassieriDto modificaCassiere(@RequestBody CassiereRicercaDto dto) {
        throw new UnsupportedOperationException();
    }

    @RequestMapping("/rimuovi-cassiere")
    @ResponseBody
    public ListaCassieriDto rimuoviCassiere(@RequestBody CassiereRicercaDto dto) {
        throw new UnsupportedOperationException();
    }

    @RequestMapping("/ricerca-cassiere")
    @ResponseBody
    public ListaCassieriDto ricercaCassiere(@RequestBody CriterioRicercaDto dto) {
        throw new UnsupportedOperationException();
    }

    @RequestMapping("/visualizza-lista-cassieri")
    @ResponseBody
    public ListaCassieriDto visualizzaListaCassieri() {
        throw new UnsupportedOperationException();
    }

}
