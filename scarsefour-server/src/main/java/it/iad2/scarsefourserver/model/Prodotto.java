package it.iad2.scarsefourserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Prodotto {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String ean;
    @Column
    private String codice;
    @Column
    private String descrizione;
    @Column
    private double prezzo;
    @Column
    private int scortaMinScaffaleDefault;
    @Column
    private int scortaMinMagazzinoDefault;
    @Column
    private int lottoRiordino;

    @JsonIgnore
    @OneToOne(cascade= CascadeType.REMOVE, mappedBy="prodotto")
    private PosizioneScaffale posizioneScaffale;

    @JsonIgnore
    @OneToMany(cascade= CascadeType.REMOVE, mappedBy="prodotto")
    private List<RigaScontrino> righe;

    @JsonIgnoreProperties(value = "prodotto", allowGetters = true, allowSetters = true)
    @ManyToMany
    @JoinColumn(referencedColumnName = "id")
    private List<Sconto> sconti;

    public PosizioneScaffale getPosizioneScaffale() {
        return posizioneScaffale;
    }

    public void setPosizioneScaffale(PosizioneScaffale posizioneScaffale) {
        this.posizioneScaffale = posizioneScaffale;
    }

    public List<Sconto> getSconti() {
        if (sconti == null) {
            sconti = new ArrayList();
        }
        return sconti;
    }

    public void setSconti(List<Sconto> sconti) {
        this.sconti = sconti;
    }

    public Prodotto() {
    }

    public Prodotto(String ean, String codice, String descrizione, double prezzo, int scortaMinScaffaleDefault, int scortaMinMagazzinoDefault, int lottoRiordino) {
        this.ean = ean;
        this.codice = codice;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.scortaMinScaffaleDefault = scortaMinScaffaleDefault;
        this.scortaMinMagazzinoDefault = scortaMinMagazzinoDefault;
        this.lottoRiordino = lottoRiordino;
    }

    public List<RigaScontrino> getRighe() {
        if (righe == null) {
            righe = new ArrayList();
        }
        return righe;
    }

    public void setRighe(List<RigaScontrino> righe) {
        this.righe = righe;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int getScortaMinScaffaleDefault() {
        return scortaMinScaffaleDefault;
    }

    public void setScortaMinScaffaleDefault(int scortaMinScaffaleDefault) {
        this.scortaMinScaffaleDefault = scortaMinScaffaleDefault;
    }

    public int getScortaMinMagazzinoDefault() {
        return scortaMinMagazzinoDefault;
    }

    public void setScortaMinMagazzinoDefault(int scortaMinMagazzinoDefault) {
        this.scortaMinMagazzinoDefault = scortaMinMagazzinoDefault;
    }

    public int getLottoRiordino() {
        return lottoRiordino;
    }

    public void setLottoRiordino(int lottoRiordino) {
        this.lottoRiordino = lottoRiordino;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Prodotto{"
                + "id=" + id
                + ", ean='" + ean + '\''
                + ", codice='" + codice + '\''
                + ", descrizione='" + descrizione + '\''
                + ", prezzo=" + prezzo
                + ", scortaMinScaffaleDefault=" + scortaMinScaffaleDefault
                + ", scortaMinMagazzinoDefault=" + scortaMinMagazzinoDefault
                + ", lottoRiordino=" + lottoRiordino
                + ", listaSconti=" + sconti
                + '}';
    }
}
