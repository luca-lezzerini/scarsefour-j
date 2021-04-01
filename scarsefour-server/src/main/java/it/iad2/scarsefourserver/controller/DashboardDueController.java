package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.AggiungiDto;
import it.iad2.scarsefourserver.dto.BarcodeDto;
import it.iad2.scarsefourserver.service.DashboardDueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.iad2.scarsefourserver.dto.EanDto;
import it.iad2.scarsefourserver.dto.PrezzoDto;
import it.iad2.scarsefourserver.dto.ScontrinoDto;

@CrossOrigin("*")
@RestController
public class DashboardDueController {
	@Autowired
	DashboardDueService dashboardDueService;
	
	@RequestMapping("/annulla-scontrino-2")
	@ResponseBody
	public ScontrinoDto annullaScontrino2(@RequestBody ScontrinoDto dto)
	{
		ScontrinoDto dtoR = new ScontrinoDto();
		dtoR.setScontrino(dashboardDueService.annullaScontrino(dto.getScontrino()));
		return dtoR;
	}
	
	@RequestMapping("/vedi-prezzo-due")
	@ResponseBody
	public PrezzoDto vediPrezzo2(@RequestBody EanDto dto)
	{
		return new PrezzoDto(dashboardDueService.vediPrezzo(dto.getBarcode()));
	}
	
	@RequestMapping("/aggiungi-due")
        @ResponseBody
        public AggiungiDto aggiungi(@RequestBody BarcodeDto dto) {
            return dashboardDueService.aggiungi(dto.getBarcode(), dto.getScontrino());
        }
        
        @RequestMapping("/chiudi-scontrino-due")
        @ResponseBody
        public ScontrinoDto chiudiScontrino(@RequestBody ScontrinoDto dto) {
            return new ScontrinoDto(dashboardDueService.chiudiScontrino(dto.getScontrino()));
        }
        
        @RequestMapping("/storna-ultimo-due")
        @ResponseBody
        public ScontrinoDto stornaUltimo(@RequestBody ScontrinoDto dto) {
            return new ScontrinoDto(dashboardDueService.stornaUltimo(dto.getScontrino()));
        }
         
}
