package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;

public class CaricaMerceDto {
    
    
    Long id_Pos;
    Long id_Sku;
    int quantita;

    public CaricaMerceDto(Long id_Pos, Long id_Sku, int quantita) {
        this.id_Pos = id_Pos;
        this.id_Sku = id_Sku;
        this.quantita = quantita;
    }

    public Long getId_Pos() {
        return id_Pos;
    }

    public void setId_Pos(Long id_Pos) {
        this.id_Pos = id_Pos;
    }

    public Long getId_Sku() {
        return id_Sku;
    }

    public void setId_Sku(Long id_Sku) {
        this.id_Sku = id_Sku;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public String toString() {
        return "CaricaMerceDto{" + "id_Pos=" + id_Pos + ", id_Sku=" + id_Sku + ", quantita=" + quantita + '}';
    }

    
}
