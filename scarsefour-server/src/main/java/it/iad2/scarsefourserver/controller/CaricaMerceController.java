package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.service.CaricaMerceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Portatile
 */
@CrossOrigin("*")
@RestController
public class CaricaMerceController {

    @Autowired
    CaricaMerceService caricaMerceService;

    @RequestMapping("/carica-merce")
    @ResponseBody
    public ListaProdottiDto caricaMerce() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return new ListaProdottiDto(caricaMerceService.caricaMerce());
    }    
}

