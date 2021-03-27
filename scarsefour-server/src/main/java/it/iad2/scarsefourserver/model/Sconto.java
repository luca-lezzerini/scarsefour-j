package it.iad2.scarsefourserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

@Entity
public class Sconto {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private LocalDateTime dallaData;
    @Column
    private LocalDateTime allaData;
    @Column
    private double sconto;
    @Column
    private String descrizione;
    @Column
    private String codice;
    
    @ManyToMany
    @JoinColumn(referencedColumnName = "id")
    private List<Prodotto> prodotti;

    public Sconto() {
    }

    public Sconto(LocalDateTime dallaData, LocalDateTime allaData, double sconto, String descrizione, String codice) {
        this.dallaData = dallaData;
        this.allaData = allaData;
        this.sconto = sconto;
        this.descrizione = descrizione;
        this.codice = codice;
    }

    public List<Prodotto> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDallaData() {
        return dallaData;
    }

    public void setDallaData(LocalDateTime dallaData) {
        this.dallaData = dallaData;
    }

    public LocalDateTime getAllaData() {
        return allaData;
    }

    public void setAllaData(LocalDateTime allaData) {
        this.allaData = allaData;
    }

    public double getSconto() {
        return sconto;
    }

    public void setSconto(double sconto) {
        this.sconto = sconto;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Sconto{" +
                "id=" + id +
                ", dallaData=" + dallaData +
                ", allaData=" + allaData +
                ", sconto=" + sconto +
                ", descrizione='" + descrizione + '\'' +
                ", codice='" + codice + '\'' +
                '}';
    }
}
