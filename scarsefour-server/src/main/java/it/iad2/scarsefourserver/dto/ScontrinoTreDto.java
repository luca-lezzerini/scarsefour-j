package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Scontrino;

public class ScontrinoTreDto {
    private Scontrino scontrino;

    public ScontrinoTreDto() {
    }

    public ScontrinoTreDto(Scontrino scontrino) {
        this.scontrino = scontrino;
    }

    public Scontrino getScontrino() {
        return scontrino;
    }

    public void setScontrino(Scontrino scontrino) {
        this.scontrino = scontrino;
    }

    @Override
    public String toString() {
        return "ScontrinoTreDto{" + "scontrino=" + scontrino + '}';
    }
    
    
}
