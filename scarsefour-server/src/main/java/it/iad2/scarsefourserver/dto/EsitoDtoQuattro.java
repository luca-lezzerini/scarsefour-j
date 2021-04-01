package it.iad2.scarsefourserver.dto;

public class EsitoDtoQuattro {
    private boolean esito;

    public EsitoDtoQuattro() {
    }

    public EsitoDtoQuattro(boolean esito) {
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
        return "EsitoDtoQuattro{" + "esito=" + esito + '}';
    }
}
