package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.EsitoDtoQuattro;
import it.iad2.scarsefourserver.dto.ProdottoDto;
import it.iad2.scarsefourserver.dto.RichiestaEanDto;
import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.dto.RispostaEanDtoQuattro;
import it.iad2.scarsefourserver.dto.ScontrinoDto;
import it.iad2.scarsefourserver.service.DashboardQuattroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class DashboardQuattroController {

    @Autowired
    DashboardQuattroService dashboardquattro;

    @RequestMapping("/vedi-prezzo-quattro")
    @ResponseBody
    public ProdottoDto vediPrezzoAction(@RequestBody CriterioRicercaDto dto) {
        return dashboardquattro.vediPrezzoAction(dto.getCriterio());

    }

    @RequestMapping("/inserisci-ean-quattro")
    @ResponseBody
    public RispostaEanDtoQuattro inserisciEanAction(@RequestBody RichiestaEanDto dto) {
        return dashboardquattro.inserisciEanAction(dto.getBarcode(), dto.getScontrino());
    }

    @RequestMapping("/annulla-scontrino-quattro")
    @ResponseBody
    public ScontrinoDto annullaScontrinoAction(@RequestBody ScontrinoDto dto) {
        return dashboardquattro.annullaScontrinoAction(dto.getScontrino());
    }
    @RequestMapping("/storna-ultimo-quattro")
    @ResponseBody
    public ScontrinoDto stornaUltimoAction(@RequestBody ScontrinoDto dto) {
        return dashboardquattro.stornaUltimoAction(dto.getScontrino());
    }
    
}
