package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.Prodotto;

public class ProdottoPosizioneDto {
    private Prodotto prodotto;
    private PosizioneScaffale posizioneScaffale;

    public ProdottoPosizioneDto() {
    }

    public ProdottoPosizioneDto(Prodotto prodotto, PosizioneScaffale posizioneScaffale) {
        this.prodotto = prodotto;
        this.posizioneScaffale = posizioneScaffale;
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

    @Override
    public String toString() {
        return "ProdottoPosizioneDto{" + "prodotto=" + prodotto + ", posizioneScaffale=" + posizioneScaffale + '}';
    }
    
    
    
}
