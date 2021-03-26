package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.dto.ProdottoDto;
import it.iad2.scarsefourserver.service.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")

public class AnagraficaProdottiController {

    @Autowired
    ProdottoService prodottoService;

    @ResponseBody
    @RequestMapping("/modifica-prodotto-quattro")
    public ListaProdottiDto modificaProdotto(@RequestBody ProdottoDto dto) {
        return prodottoService.modificaProdotto(dto.getProdotto());
    }

    @ResponseBody
    @RequestMapping("/conferma-prodotto-quattro")
    public ListaProdottiDto confermaProdotto(@RequestBody ProdottoDto dto) {
        return prodottoService.confermaProdotto(dto.getProdotto());
    }

    @ResponseBody
    @RequestMapping("/rimuovi-prodotto-quattro")
    public ListaProdottiDto rimuoviProdotto(@RequestBody ProdottoDto dto) {
        return prodottoService.rimuoviProdotto(dto.getProdotto());
    }

    @ResponseBody
    @RequestMapping("/aggiorna-prodotto-quattro")
    public ListaProdottiDto aggiornaProdotto() {
        return prodottoService.aggiornaProdotto();
    }
}
