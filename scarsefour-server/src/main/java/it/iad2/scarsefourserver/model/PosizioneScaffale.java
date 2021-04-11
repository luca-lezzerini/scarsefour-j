/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.iad2.scarsefourserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author matte
 */
@Entity
public class PosizioneScaffale {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String codice;
    @Column
    private String descrizione;

//    @JsonIgnore
//    @OneToOne(cascade = CascadeType.REMOVE)
//    @JoinColumn(referencedColumnName = "id")
//    private Prodotto prodotto;

    @JsonIgnoreProperties(value = "posizioneScaffale", allowGetters = true, allowSetters = true)
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(referencedColumnName = "id")
    private SkuScaffale skuScaffale;

    public PosizioneScaffale() {
    }

    public PosizioneScaffale(String codice, String descizione) {
        this.codice = codice;
        this.descrizione = descizione;
    }

    public Long getId() {
        return id;
    }

//    public Prodotto getProdotto() {
//        return prodotto;
//    }
//
//    public void setProdotto(Prodotto prodotto) {
//        this.prodotto = prodotto;
//    }

    public void setId(Long id) {
        this.id = id;
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

    public SkuScaffale getSkuScaffale() {
        return skuScaffale;
    }

    public void setSkuScaffale(SkuScaffale skuScaffale) {
        this.skuScaffale = skuScaffale;
    }

    @Override
    public String toString() {
        return "PosizioneScaffale{" + "id=" + id + ", codice=" + codice + ", descizione=" + descrizione + '}';
    }

}
