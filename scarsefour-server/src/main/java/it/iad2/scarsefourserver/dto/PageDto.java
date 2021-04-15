package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import org.springframework.data.domain.Page;


public class PageDto {
    
    Page<PosizioneScaffale> listaElemPag;

    public PageDto() {
    }

    public PageDto(Page<PosizioneScaffale> listaElemPag) {
        this.listaElemPag = listaElemPag;
    }

    public Page<PosizioneScaffale> getListaElemPag() {
        return listaElemPag;
    }

    public void setListaElemPag(Page<PosizioneScaffale> listaElemPag) {
        this.listaElemPag = listaElemPag;
    }

    @Override
    public String toString() {
        return "PageDto{" + "listaElemPag=" + listaElemPag + '}';
    }

    
}
