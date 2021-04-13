package it.iad2.scarsefourserver.model;

/**
 *
 * @author user
 */
public class Giacenza {
    private String codice;
    private String descrizione;
    private int giacenza;
    private int scortaMinima;

    public Giacenza() {
    }

    public Giacenza(String codice, String descrizione, int giacenza, int scortaMinima) {
        this.codice = codice;
        this.descrizione = descrizione;
        this.giacenza = giacenza;
        this.scortaMinima = scortaMinima;
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

    public int getScortaMinima() {
        return scortaMinima;
    }

    public void setScortaMinima(int scortaMinima) {
        this.scortaMinima = scortaMinima;
    }
    
    
}
