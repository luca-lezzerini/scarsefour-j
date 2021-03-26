package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CassaDto;
import it.iad2.scarsefourserver.dto.ListaCasseDto;
import it.iad2.scarsefourserver.dto.ModificaCassaDto;
import it.iad2.scarsefourserver.service.AnagraficaCasseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class AnagraficaCasseController {

    @Autowired
    AnagraficaCasseService anagraficaCasseService;

    @RequestMapping("/aggiorna-lista-casse")
    @ResponseBody
    public ListaCasseDto aggiornaListaCasse() {
        ListaCasseDto dtoR = new ListaCasseDto(anagraficaCasseService.aggiornaCasse());
        return dtoR;
    }

    @RequestMapping("/crea-nuova-cassa")
    @ResponseBody
    public ListaCasseDto CreaNuovaCassa(@RequestBody CodiceCassaDto dto) {
        ListaCasseDto dtoR = new ListaCasseDto(anagraficaCasseService.nuovaCassa(dto.getCodice()));
        return dtoR;
    }

    @RequestMapping("/cerca-cassa")
    @ResponseBody
    public ListaCasseDto CercaCassa(@RequestBody CodiceCassaDto dto) {
        ListaCasseDto dtoR = new ListaCasseDto(anagraficaCasseService.cercaCassa(dto.getCodice()));
        return dtoR;
    }

    @RequestMapping("/modifica-cassa")
    @ResponseBody
    public ListaCasseDto ModificaCassa(@RequestBody ModificaCassaDto dto) {
        ListaCasseDto dtoR = new ListaCasseDto(anagraficaCasseService.modificaCassa(dto.getCassa(), dto.getNuovoCodice()));
        return dtoR;
    }

    @RequestMapping("/elimina-cassa")
    @ResponseBody
    public ListaCasseDto eliminaCassa(@RequestBody CassaDto dto) {
        ListaCasseDto dtoR = new ListaCasseDto(anagraficaCasseService.rimuoviCassa(dto.getCassa()));
        return dtoR;
    }
}
