package it.iad2.scarsefourserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "scontrino")
    private Cassa cassa;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "scontrino")
    private Cassiere cassiere;

    @JsonIgnore
    @OneToMany(mappedBy = "scontrino",cascade= CascadeType.REMOVE)
    private List<RigaScontrino> righe;

    public Scontrino() {
    }

    public Scontrino(LocalDateTime timeStamp, Integer numero, Double totale) {
        this.timeStamp = timeStamp;
        this.numero = numero;
        this.totale = totale;
    }

    public Cassa getCassa() {
        return cassa;
    }

    public void setCassa(Cassa cassa) {
        this.cassa = cassa;
    }

    public Cassiere getCassiere() {
        return cassiere;
    }

    public void setCassiere(Cassiere cassiere) {
        this.cassiere = cassiere;
    }

    public List<RigaScontrino> getRighe() {
        if (righe == null) {
            righe = new ArrayList<>();
        }
        return righe;
    }

    public void setRighe(List<RigaScontrino> righe) {
        this.righe = righe;
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
        return "Scontrino{"
                + "id=" + id
                + ", timeStamp=" + timeStamp
                + ", numero=" + numero
                + ", totale=" + totale
                + '}';
    }
}
