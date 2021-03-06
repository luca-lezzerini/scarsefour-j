package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.AggiungiEanDto;
import it.iad2.scarsefourserver.dto.AggiungiEanRispostaDto;
import it.iad2.scarsefourserver.service.DashboardTreService;
import it.iad2.scarsefourserver.dto.ScontrinoTreDto;
import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.ListaRigaScontrinoTreDto;
import it.iad2.scarsefourserver.dto.ProdottoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class DashboardTreController {
    @Autowired 
    DashboardTreService dashBoardService;
    
    @RequestMapping("/vedi-prezzo-tre")
    @ResponseBody
    public ProdottoDto vediPrezzo(@RequestBody CriterioRicercaDto dto){
        return new ProdottoDto(dashBoardService.vediPrezzo(dto.getCriterio()));
    }
    
    @RequestMapping("/chiudi-scontrino-tre")
    @ResponseBody
    public ScontrinoTreDto chiudiScontrino(@RequestBody ScontrinoTreDto dto){
        return new ScontrinoTreDto(dashBoardService.chiudiScontrino(dto.getScontrino(),
                dto.getScontrino().getRighe()));
    }
    
    @RequestMapping("/annulla-scontrino-tre")
    @ResponseBody
    public ScontrinoTreDto annullaScontrino(@RequestBody ScontrinoTreDto dto){
        return new ScontrinoTreDto(dashBoardService.annullaScontrino(dto.getScontrino()));
    }
    
    @RequestMapping("/storna-ultimo-tre")
    @ResponseBody
    public ListaRigaScontrinoTreDto stornaUltimo(@RequestBody ScontrinoTreDto dto){
        return dashBoardService.stornaUltimo(dto.getScontrino());
    }
    
    @RequestMapping("/aggiungi-scontrino-tre")
    @ResponseBody
    public AggiungiEanRispostaDto aggiungiRigaScontrino(@RequestBody AggiungiEanDto dto){
        System.out.println("Siamo in aggiungi riga scontrino " + dto);
        var r = dashBoardService.aggiungiRigaScontrino(dto.getScontrino(), dto.getBarcode());
        return r;
    }
    
     @RequestMapping("/aggiorna-righe-tre")
    @ResponseBody
    public ScontrinoTreDto aggiornaRighe(@RequestBody ScontrinoTreDto dto){
        return new ScontrinoTreDto(dashBoardService.aggiornaRighe(dto.getScontrino()));
    }
}
