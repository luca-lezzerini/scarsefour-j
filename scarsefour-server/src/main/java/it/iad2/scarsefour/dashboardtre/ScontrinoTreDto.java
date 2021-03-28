package it.iad2.scarsefour.dashboardtre;

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
    
    
}
