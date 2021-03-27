/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.iad2.scarsefourserver.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 *
 * @author utente
 */
@Entity
public class MovimentiScaffale {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int quantita;

    @Column
    private LocalDateTime timestamp;

    public MovimentiScaffale(int quantita, LocalDateTime timestamp) {
        this.quantita = quantita;
        this.timestamp = timestamp;
    }
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private RigaScontrino rigaScontrino;

    public MovimentiScaffale() {
    }

    public RigaScontrino getRigaScontrino() {
        return rigaScontrino;
    }

    public void setRigaScontrino(RigaScontrino rigaScontrino) {
        this.rigaScontrino = rigaScontrino;
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "MovimentiScaffale{" + "id=" + id + ", quantita=" + quantita + ", timestamp=" + timestamp + '}';
    }

}
