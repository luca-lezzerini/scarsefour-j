package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import java.util.List;

public class ListaPosizioneScaffaleDto {
    
    private List<PosizioneScaffale> listaPosizioni;

    public ListaPosizioneScaffaleDto() {
    }

    public ListaPosizioneScaffaleDto(List<PosizioneScaffale> listaPosizioni) {
        this.listaPosizioni = listaPosizioni;
    }

    public List<PosizioneScaffale> getListaPosizioni() {
        return listaPosizioni;
    }

    public void setListaPosizioni(List<PosizioneScaffale> listaPosizioni) {
        this.listaPosizioni = listaPosizioni;
    }
    
}
