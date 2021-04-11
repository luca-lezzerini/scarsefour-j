package it.iad2.scarsefourserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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

    @JsonIgnore
    @OneToMany(mappedBy = "skuScaffale", cascade = CascadeType.REMOVE)
    private List<Prodotto> prodotti;

    @JsonIgnore
    @OneToMany(mappedBy = "skuScaffale", cascade = CascadeType.REMOVE)
    private List<PosizioneScaffale> posizioneScaffale;

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

    public List<Prodotto> getProdotti() {
        if (prodotti == null) {
            prodotti = new ArrayList<>();
        }
        return prodotti;
    }

    public void setProdotti(List<Prodotto> prodotti) {
        this.prodotti = prodotti;
    }

    public List<PosizioneScaffale> getPosizioneScaffale() {
        if (posizioneScaffale == null) {
            posizioneScaffale = new ArrayList<>();
        }
        return posizioneScaffale;
    }

    public void setPosizioneScaffale(List<PosizioneScaffale> posizioneScaffale) {
        this.posizioneScaffale = posizioneScaffale;
    }

}
