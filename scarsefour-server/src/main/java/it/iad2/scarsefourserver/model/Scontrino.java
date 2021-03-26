package it.iad2.scarsefourserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Scontrino {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime timeStamp;
    @Column
    private Integer numero;
    @Column
    private Double totale;

    public Scontrino() {
    }

    public Scontrino(LocalDateTime timeStamp, Integer numero, Double totale) {
        this.timeStamp = timeStamp;
        this.numero = numero;
        this.totale = totale;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Double getTotale() {
        return totale;
    }

    public void setTotale(Double totale) {
        this.totale = totale;
    }

    @Override
    public String toString() {
        return "Scontrino{" +
                "id=" + id +
                ", timeStamp=" + timeStamp +
                ", numero=" + numero +
                ", totale=" + totale +
                '}';
    }
}
