package it.iad2.scarsefourserver.dto;


public class PrezzoDto {
    private double prezzo;

    public PrezzoDto() {
    }

    public PrezzoDto(double prezzo) {
        this.prezzo = prezzo;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }
}
