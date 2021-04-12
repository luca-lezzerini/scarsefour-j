package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.service.AssociaProdottoAScaffaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController

public class AssociaProdottoAScaffaleController {

    @Autowired
    AssociaProdottoAScaffaleService associaProdottoAScaffaleService;

    @RequestMapping("/associa-prodotto-scaffale")
    @ResponseBody
    public ListaProdottiDto associaProdottoScaffale() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}
