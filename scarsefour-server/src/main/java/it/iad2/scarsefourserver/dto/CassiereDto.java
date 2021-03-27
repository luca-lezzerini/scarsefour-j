package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Cassiere;

/**
 *
 * @author irene
 */
public class CassiereDto {
    
    private Cassiere cassiere;

    public CassiereDto() {
    }

    public CassiereDto(Cassiere cassiere) {
        this.cassiere = cassiere;
    }

    public Cassiere getCassiere() {
        return cassiere;
    }

    public void setCassiere(Cassiere cassiere) {
        this.cassiere = cassiere;
    }

    @Override
    public String toString() {
        return "CassiereDto{" + "cassiere=" + cassiere + '}';
    }
    
    
}
