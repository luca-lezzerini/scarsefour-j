package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.util.List;

public class RispostaEanDto {
    
    private String barcode;
    private Scontrino scontrino;
    private List<RigaScontrino> righeScontrino;

    public RispostaEanDto() {
    }

    public RispostaEanDto(String barcode, Scontrino scontrino, List<RigaScontrino> righeScontrino) {
        this.barcode = barcode;
        this.scontrino = scontrino;
        this.righeScontrino = righeScontrino;
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

    public List<RigaScontrino> getRigheScontrino() {
        return righeScontrino;
    }

    public void setRigheScontrino(List<RigaScontrino> righeScontrino) {
        this.righeScontrino = righeScontrino;
    }
    
    
    
}
