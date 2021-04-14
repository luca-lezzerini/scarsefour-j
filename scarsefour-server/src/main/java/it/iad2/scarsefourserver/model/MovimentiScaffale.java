package it.iad2.scarsefourserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;
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
public class MovimentiScaffale {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private int quantita;

    @Column
    private LocalDateTime timestamp;

    @Column
    private String tipo;

    @JsonIgnoreProperties(value = "listaMovimentiScaffale", allowGetters = true, allowSetters = true)
    @ManyToOne
    private SkuScaffale skuScaffale;

    public MovimentiScaffale(int quantita, LocalDateTime timestamp, String tipo) {
        this.quantita = quantita;
        this.timestamp = timestamp;
        this.tipo = tipo;
    }

    /*@JsonIgnoreProperties(value = "movimentiScaffale", allowGetters = true, allowSetters = true)
    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private RigaScontrino rigaScontrino;*/
    public MovimentiScaffale() {
    }

    /*public RigaScontrino getRigaScontrino() {
        return rigaScontrino;
    }

    public void setRigaScontrino(RigaScontrino rigaScontrino) {
        this.rigaScontrino = rigaScontrino;
    }*/
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "MovimentiScaffale{" + "id=" + id + ", quantita=" + quantita + ", timestamp=" + timestamp + "tipo" + tipo + '}';
    }

}
