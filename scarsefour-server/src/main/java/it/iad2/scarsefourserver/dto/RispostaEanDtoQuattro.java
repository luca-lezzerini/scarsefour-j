package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.util.List;

public class RispostaEanDtoQuattro {

    private Boolean rigaSuccesso = true;
    private Scontrino scontrino;
    private List<RigaScontrino> righeScontrino;
    private RigaScontrino ultimaRiga;

    public RispostaEanDtoQuattro() {
    }

    public RispostaEanDtoQuattro(Scontrino scontrino, List<RigaScontrino> righeScontrino ) {
        this.scontrino = scontrino;
        this.righeScontrino = righeScontrino;
    }

    public RispostaEanDtoQuattro(Boolean rigaSuccesso, Scontrino scontrino, List<RigaScontrino> righeScontrino, RigaScontrino ultimaRiga) {
        this.rigaSuccesso = rigaSuccesso;
        this.scontrino = scontrino;
        this.righeScontrino = righeScontrino;
        this.ultimaRiga = ultimaRiga;
        
    }

    public Boolean getRigaSuccesso() {
        return rigaSuccesso;
    }

    public void setRigaSuccesso(Boolean rigaSuccesso) {
        this.rigaSuccesso = rigaSuccesso;
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

    public RigaScontrino getUltimaRiga() {
        return ultimaRiga;
    }

    public void setUltimaRiga(RigaScontrino ultimaRiga) {
        this.ultimaRiga = ultimaRiga;
    }

    @Override
    public String toString() {
        return "RispostaEanDtoQuattro{" + "rigaSuccesso=" + rigaSuccesso + ", scontrino=" + scontrino + ", righeScontrino=" + righeScontrino + ", ultimaRiga=" + ultimaRiga + '}';
    }

    

}
