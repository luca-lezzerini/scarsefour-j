package it.iad2.scarsefourserver.controller;

import it.iad2.scarsefourserver.dto.CriterioRicercaDto;
import it.iad2.scarsefourserver.dto.ListaPosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.dto.PosizioneScaffaleDto;
import it.iad2.scarsefourserver.dto.ProdottoPosizioneDto;
import it.iad2.scarsefourserver.service.AssociaProdottoAScaffaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
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
    public PosizioneScaffaleDto associaProdottoScaffale(@RequestBody ProdottoPosizioneDto dto) {
        return associaProdottoAScaffaleService.associaProdottoScaffale(dto);
    }

    @RequestMapping("/seleziona-posizioni")
    @ResponseBody
    public ListaPosizioneScaffaleDto selezionaPosizioni(@RequestBody CriterioRicercaDto dto) {
        return associaProdottoAScaffaleService.selezionaPosizioni(dto.getCriterio());
    }

    @RequestMapping("/cerca-prodotti-non-associati")
    @ResponseBody
    public ListaProdottiDto cercaProdottiNonAssociati(@RequestBody PosizioneScaffaleDto dto) {
        return associaProdottoAScaffaleService.cercaProdottiNonAssociati(dto.getPosizione());

    }
}
