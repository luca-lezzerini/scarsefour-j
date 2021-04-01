package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Scontrino;

/**
 *
 * @author utente
 */
public class AggiungiDto {
    Scontrino scontrino;
    boolean esito;

    public AggiungiDto() {
    }

    public AggiungiDto(Scontrino scontrino, boolean esito) {
        this.scontrino = scontrino;
        this.esito = esito;
    }

    public Scontrino getScontrino() {
        return scontrino;
    }

    public void setScontrino(Scontrino scontrino) {
        this.scontrino = scontrino;
    }

    public boolean isEsito() {
        return esito;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    @Override
    public String toString() {
        return "AggiungiDto{" + "scontrino=" + scontrino + ", esito=" + esito + '}';
    }
    
}
