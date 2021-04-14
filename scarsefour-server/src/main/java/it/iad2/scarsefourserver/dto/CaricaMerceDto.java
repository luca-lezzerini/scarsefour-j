package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;

public class CaricaMerceDto {
    Prodotto prodotto;
    PosizioneScaffale posizioneScaffale;
    int quantita;

    public CaricaMerceDto(Prodotto prodotto, PosizioneScaffale posizioneScaffale, int quantita) {
        this.prodotto = prodotto;
        this.posizioneScaffale = posizioneScaffale;
        this.quantita = quantita;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public PosizioneScaffale getPosizioneScaffale() {
        return posizioneScaffale;
    }

    public int getQuantita() {
        return quantita;
    }
    
}
