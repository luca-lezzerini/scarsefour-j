package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Cassa;

public class ModificaCassaDto {
    private Cassa cassa;
    private String nuovoCodice;

    public ModificaCassaDto() {
    }

    public ModificaCassaDto(Cassa cassa, String nuovoCodice) {
        this.cassa = cassa;
        this.nuovoCodice = nuovoCodice;
    }

    public Cassa getCassa() {
        return cassa;
    }

    public void setCassa(Cassa cassa) {
        this.cassa = cassa;
    }

    public String getNuovoCodice() {
        return nuovoCodice;
    }

    public void setNuovoCodice(String nuovoCodice) {
        this.nuovoCodice = nuovoCodice;
    }

    @Override
    public String toString() {
        return "ModificaCassaDto{" +
                "cassa=" + cassa +
                ", nuovoCodice='" + nuovoCodice + '\'' +
                '}';
    }
}
