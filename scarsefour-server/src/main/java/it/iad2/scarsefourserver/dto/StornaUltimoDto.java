package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Scontrino;

public class StornaUltimoDto {

    private Scontrino scontrino;
    private String lastBarcode;

    public StornaUltimoDto() {
    }

    public StornaUltimoDto(Scontrino scontrino, String lastBarcode) {
        this.scontrino = scontrino;
        this.lastBarcode = lastBarcode;
    }

    public Scontrino getScontrino() {
        return scontrino;
    }

    public void setScontrino(Scontrino scontrino) {
        this.scontrino = scontrino;
    }

    public String getLastBarcode() {
        return lastBarcode;
    }

    public void setLastBarcode(String lastBarcode) {
        this.lastBarcode = lastBarcode;
    }

}
