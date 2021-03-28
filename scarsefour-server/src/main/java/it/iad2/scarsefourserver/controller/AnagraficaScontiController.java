package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.ListaScontiDto;
import it.iad2.scarsefourserver.dto.ScontoDto;
import it.iad2.scarsefourserver.service.AnagraficaScontiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class AnagraficaScontiController {

    @Autowired
    AnagraficaScontiService scontiService;

    @RequestMapping("/aggiorna-lista-sconti")
    @ResponseBody
    public ListaScontiDto aggiorna() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @RequestMapping("/aggiungi-sconto")
    @ResponseBody
    public ListaScontiDto aggiungi(ScontoDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @RequestMapping("/modifica-sconto")
    @ResponseBody
    public ListaScontiDto modifica(ScontoDto dto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @RequestMapping("/rimuovi-sconto")
    @ResponseBody
    public ListaScontiDto rimuovi(ScontoDto dto){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @RequestMapping("/ricerca-sconto")
    @ResponseBody
    public ListaScontiDto ricerca (CriterioRicercaDto dto){
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
