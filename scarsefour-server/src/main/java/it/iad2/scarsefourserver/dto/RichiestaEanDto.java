package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Scontrino;

public class RichiestaEanDto {
    private String barcode;
    private Scontrino scontrino;

    public RichiestaEanDto() {
    }

    public RichiestaEanDto(String barcode, Scontrino scontrino) {
        this.barcode = barcode;
        this.scontrino = scontrino;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Scontrino getScontrino() {
        return scontrino;
    }

    public void setScontrino(Scontrino scontrino) {
        this.scontrino = scontrino;
    }
    
    
}
