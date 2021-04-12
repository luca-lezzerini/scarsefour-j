package it.iad2.scarsefourserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
    private int giacenza;

    @Column
    private int scortaMinima;

    @JsonIgnoreProperties(value = "listaSku",allowGetters = true,allowSetters = true)
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "id")
    private Prodotto prodotto;

    @JsonIgnoreProperties(value = "listaSku",allowGetters = true,allowSetters = true)
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "id")
    private PosizioneScaffale posizioneScaffale;

    public SkuScaffale() {
    }

    public SkuScaffale(int giacenza, int scortaMinima) {
        this.giacenza = giacenza;
        this.scortaMinima = scortaMinima;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(int giacenza) {
        this.giacenza = giacenza;
    }

    public int getScortaMinima() {
        return scortaMinima;
    }

    public void setScortaMinima(int scortaMinima) {
        this.scortaMinima = scortaMinima;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public PosizioneScaffale getPosizioneScaffale() {
        return posizioneScaffale;
    }

    public void setPosizioneScaffale(PosizioneScaffale posizioneScaffale) {
        this.posizioneScaffale = posizioneScaffale;
    }

}
