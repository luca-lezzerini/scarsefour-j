package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.util.List;

public class ScontrinoRigheDto {

    private Scontrino scontrino;
    private List<RigaScontrino> righeScontrino;

    public ScontrinoRigheDto() {
    }

    public ScontrinoRigheDto(Scontrino scontrino, List<RigaScontrino> righeScontrino) {
        this.scontrino = scontrino;
        this.righeScontrino = righeScontrino;
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
