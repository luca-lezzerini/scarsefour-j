package it.iad2.scarsefourserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
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

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Cassa cassa;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Cassiera cassiera;

    @OneToMany(mappedBy = "quantita")
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

    public Cassiera getCassiera() {
        return cassiera;
    }

    public void setCassiera(Cassiera cassiera) {
        this.cassiera = cassiera;
    }

    public List<RigaScontrino> getRighe() {
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
