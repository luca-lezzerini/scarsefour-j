package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.PosizioneScaffale;

public class PosizioneScaffaleDto {

    private PosizioneScaffale posizione;

    public PosizioneScaffaleDto() {
    }

    public PosizioneScaffaleDto(PosizioneScaffale posizione) {
        this.posizione = posizione;
    }

    public PosizioneScaffale getPosizione() {
        return posizione;
    }

    public void setPosizione(PosizioneScaffale posizione) {
        this.posizione = posizione;
    }
    
}
