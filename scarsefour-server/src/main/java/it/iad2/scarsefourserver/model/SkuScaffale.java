package it.iad2.scarsefourserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author utente
 */
@Entity
public class SkuScaffale {
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    private int quantita;

    public SkuScaffale() {
    }

    public SkuScaffale(int quantita) {
        this.quantita = quantita;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "SkuScaffale{" + "id=" + id + ", quantita=" + quantita + '}';
    }
    
    
    
    
}
