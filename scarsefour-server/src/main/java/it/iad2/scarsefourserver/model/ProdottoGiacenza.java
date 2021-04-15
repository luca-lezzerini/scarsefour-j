package it.iad2.scarsefourserver.model;

public class ProdottoGiacenza {
    private Long id_Prodotto;
    private String codice;
    private String descrizione;
    private int giacenza;
    private Long id_Sku;

    public ProdottoGiacenza(Long id_Prodotto, String codice, String descrizione, int giacenza, Long id_Sku) {
        this.id_Prodotto = id_Prodotto;
        this.codice = codice;
        this.descrizione = descrizione;
        this.giacenza = giacenza;
        this.id_Sku = id_Sku;
    }

    public Long getId_Prodotto() {
        return id_Prodotto;
    }

    public void setId_Prodotto(Long id_Prodotto) {
        this.id_Prodotto = id_Prodotto;
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

    public int getGiacenza() {
        return giacenza;
    }

    public void setGiacenza(int giacenza) {
        this.giacenza = giacenza;
    }

    public Long getId_Sku() {
        return id_Sku;
    }

    public void setId_Sku(Long id_Sku) {
        this.id_Sku = id_Sku;
    }

    @Override
    public String toString() {
        return "ProdottoGiacenza{" + "id_Prodotto=" + id_Prodotto + ", codice=" + codice + ", descrizione=" + descrizione + ", giacenza=" + giacenza + ", id_Sku=" + id_Sku + '}';
    }


    
}
