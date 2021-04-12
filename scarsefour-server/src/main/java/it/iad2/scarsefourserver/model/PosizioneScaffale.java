/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    @JsonIgnore
    @OneToMany(mappedBy = "posizioneScaffale", cascade = CascadeType.REMOVE)
    private List<SkuScaffale> listaSku;

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

    public List<SkuScaffale> getListaSku() {
        if (listaSku == null) {
            listaSku = new ArrayList<>();
        }
        return listaSku;
    }

    public void setListaSku(List<SkuScaffale> listaSku) {
        this.listaSku = listaSku;
    }

    @Override
    public String toString() {
        return "PosizioneScaffale{" + "id=" + id + ", codice=" + codice + ", descizione=" + descrizione + '}';
    }

}
