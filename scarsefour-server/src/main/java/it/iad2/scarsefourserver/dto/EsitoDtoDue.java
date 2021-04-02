package it.iad2.scarsefourserver.dto;

public class EsitoDtoDue {
    private boolean esito;

    public EsitoDtoDue() {
    }

    public EsitoDtoDue(boolean esito) {
        this.esito = esito;
    }

    public boolean isEsito() {
        return esito;
    }

    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    @Override
    public String toString() {
        return "EsitoDtoDue{" +
                "esito=" + esito +
                '}';
    }
}
