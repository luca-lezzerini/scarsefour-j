package it.iad2.scarsefourserver.dashboardtre;

import it.iad2.scarsefourserver.model.RigaScontrino;
import java.util.List;

public class ListaRigaScontrinoTreDto {
    private List<RigaScontrino> righeScontrino;

    public ListaRigaScontrinoTreDto() {
    }

    public ListaRigaScontrinoTreDto(List<RigaScontrino> righeScontrino) {
        this.righeScontrino = righeScontrino;
    }

    public List<RigaScontrino> getRigheScontrino() {
        return righeScontrino;
    }

    public void setRigheScontrino(List<RigaScontrino> righeScontrino) {
        this.righeScontrino = righeScontrino;
    }
    
}
