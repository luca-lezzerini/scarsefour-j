package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.EanDto;
import it.iad2.scarsefourserver.dto.PrezzoDto;
import it.iad2.scarsefourserver.dto.RichiestaEanDto;
import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.dto.ScontrinoDto;
import it.iad2.scarsefourserver.dto.ScontrinoRigheDto;
import it.iad2.scarsefourserver.dto.StornaUltimoDto;
import it.iad2.scarsefourserver.service.DashboardGruppoUnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class DashboardGruppoUnoController {

    @Autowired
    DashboardGruppoUnoService dashboardGruppoUnoService;

    @RequestMapping("/cerca-ean-1")
    @ResponseBody
    public RispostaEanDto cercaEan1(@RequestBody RichiestaEanDto dto) {
        return dashboardGruppoUnoService.cercaEan1(dto.getBarcode(), dto.getScontrino());
    }

    @RequestMapping("/chiudi-scontrino-1")
    @ResponseBody
    public ScontrinoDto chiudiScontrino1(@RequestBody ScontrinoDto dto) {
        return new ScontrinoDto(dashboardGruppoUnoService.chiudiScontrino1(dto.getScontrino()));
    }

    @RequestMapping("/vedi-prezzo-1")
    @ResponseBody
    public PrezzoDto vediPrezzo1(@RequestBody EanDto dto) {
        return new PrezzoDto(dashboardGruppoUnoService.vediPrezzo1(dto.getBarcode()));
    }

    @RequestMapping("/storna-ultimo-1")
    @ResponseBody
    public ScontrinoRigheDto stornaUltimo1(@RequestBody StornaUltimoDto dto) {
        return dashboardGruppoUnoService.stornaUltimo1(dto.getScontrino(), dto.getLastBarcode());
    }

    @RequestMapping("/annulla-scontrino-1")
    @ResponseBody
    public ScontrinoDto annullaScontrino1(@RequestBody ScontrinoDto dto) {
        return new ScontrinoDto(dashboardGruppoUnoService.annullaScontrino1(dto.getScontrino()));
    }

}
