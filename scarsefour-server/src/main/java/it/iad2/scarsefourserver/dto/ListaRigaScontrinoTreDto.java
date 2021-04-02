package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.util.List;

public class ListaRigaScontrinoTreDto extends ScontrinoDto{
    private List<RigaScontrino> righeScontrino;

    public ListaRigaScontrinoTreDto() {
    }

    public ListaRigaScontrinoTreDto(List<RigaScontrino> righeScontrino) {
        this.righeScontrino = righeScontrino;
    }

    public ListaRigaScontrinoTreDto(List<RigaScontrino> righeScontrino, Scontrino scontrino) {
        super(scontrino);
        this.righeScontrino = righeScontrino;
    }

    public List<RigaScontrino> getRigheScontrino() {
        return righeScontrino;
    }

    public void setRigheScontrino(List<RigaScontrino> righeScontrino) {
        this.righeScontrino = righeScontrino;
    }

}
