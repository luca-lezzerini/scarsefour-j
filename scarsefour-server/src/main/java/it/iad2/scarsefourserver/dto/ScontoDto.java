package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Sconto;

/**
 *
 * @author Valerio
 */
public class ScontoDto {
    private Sconto sconto;

    public ScontoDto() {
    }

    public ScontoDto(Sconto sconto) {
        this.sconto = sconto;
    }

    public Sconto getSconto() {
        return sconto;
    }

    public void setSconto(Sconto sconto) {
        this.sconto = sconto;
    }

    @Override
    public String toString() {
        return "ScontoDto{" + "sconto=" + sconto + '}';
    }
    
    
    
    
}
