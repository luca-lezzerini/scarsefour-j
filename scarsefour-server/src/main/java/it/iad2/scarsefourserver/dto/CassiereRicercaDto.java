package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Cassiere;

/**
 *
 * @author irene
 */
public class CassiereRicercaDto {
    
    private Cassiere cassiere;
    private String criterio;

    public CassiereRicercaDto() {
    }

    public CassiereRicercaDto(Cassiere cassiere, String criterio) {
        this.cassiere = cassiere;
        this.criterio = criterio;
    }

    public Cassiere getCassiere() {
        return cassiere;
    }

    public void setCassiere(Cassiere cassiere) {
        this.cassiere = cassiere;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    @Override
    public String toString() {
        return "CassiereRicercaDto{" + "cassiere=" + cassiere + ", criterio=" + criterio + '}';
    }
}
