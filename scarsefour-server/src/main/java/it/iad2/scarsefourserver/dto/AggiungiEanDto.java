package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.util.List;

public class AggiungiEanDto extends ScontrinoRigheDto {

    private String barcode;

    public AggiungiEanDto() {
    }

    public AggiungiEanDto(String barcode, Scontrino scontrino, List<RigaScontrino> righeScontrino) {
        super(scontrino, righeScontrino);
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    @Override
    public String toString() {
        return "AggiungiEanDto{" + "barcode=" + barcode + "; scontrino=" + getScontrino() + '}';
    }

}
