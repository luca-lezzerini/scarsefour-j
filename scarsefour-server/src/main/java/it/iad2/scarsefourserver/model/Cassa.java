package it.iad2.scarsefourserver.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Cassa {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String codice;

    @JsonIgnoreProperties(value = "cassa", allowGetters = true,allowSetters = true)
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Scontrino scontrino;

    public Cassa() {
    }

    public Cassa(String codice) {
        this.codice = codice;
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

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "Cassa{"
                + "id=" + id
                + ", codice='" + codice + '\''
                + '}';
    }
}
