package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Scontrino;

/**
 *
 * @author utente
 */
public class BarcodeDto {
    String barcode;
    Scontrino scontrino;

    public BarcodeDto() {
    }

    public BarcodeDto(String barcode, Scontrino scontrino) {
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

    @Override
    public String toString() {
        return "BarcodeDto{" + "barcode=" + barcode + ", scontrino=" + scontrino + '}';
    }

    
    
}
