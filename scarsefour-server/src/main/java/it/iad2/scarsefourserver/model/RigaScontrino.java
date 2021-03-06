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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class RigaScontrino {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int quantita = 1;

    @JsonIgnoreProperties(value = "rigaScontrino",allowGetters = true,allowSetters = true)
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Scontrino scontrino;

    /*@JsonIgnore
    @OneToOne(mappedBy = "rigaScontrino" )
    private MovimentiScaffale movimentiScaffale;*/

    @JsonIgnoreProperties(value = "rigaScontrino", allowGetters = true, allowSetters = true)
    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private Prodotto prodotto;

    public RigaScontrino() {
    }

    /*public MovimentiScaffale getMovimentiScaffale() {
        return movimentiScaffale;
    }

    public void setMovimentiScaffale(MovimentiScaffale movimentiScaffale) {
        this.movimentiScaffale = movimentiScaffale;
    }*/

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

    public Scontrino getScontrino() {
        return scontrino;
    }

    public void setScontrino(Scontrino scontrino) {
        this.scontrino = scontrino;
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

    @Override
    public String toString() {
        return "RigaScontrino{"
                + "id=" + id
                + ", quantita=" + quantita
                + '}';
    }
}
