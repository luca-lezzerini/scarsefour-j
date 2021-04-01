package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.util.List;

public class AggiungiEanRispostaDto {

    private Scontrino scontrino;
    private Boolean esito;
    private List<RigaScontrino> righe;

    public AggiungiEanRispostaDto() {
    }

    public AggiungiEanRispostaDto(Scontrino scontrino, Boolean esito, List<RigaScontrino> righe) {
        this.scontrino = scontrino;
        this.esito = esito;
        this.righe = righe;
    }

    public Scontrino getScontrino() {
        return scontrino;
    }

    public void setScontrino(Scontrino scontrino) {
        this.scontrino = scontrino;
    }

    public Boolean getEsito() {
        return esito;
    }

    public void setEsito(Boolean esito) {
        this.esito = esito;
    }

    public List<RigaScontrino> getRighe() {
        return righe;
    }

    public void setRighe(List<RigaScontrino> righe) {
        this.righe = righe;
    }

    @Override
    public String toString() {
        return "AggiungiEanRispostaDto{" + "scontrino=" + scontrino + ", esito=" + esito + ", righe=" + righe + '}';
    }

}
