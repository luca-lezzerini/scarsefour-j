package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.ProdottoDto;
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

}
