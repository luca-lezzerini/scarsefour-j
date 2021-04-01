package it.iad2.scarsefourserver.dto;

public class CodiceCassaDto {
    private String codice;

    public CodiceCassaDto() {
    }

    public CodiceCassaDto(String codice) {
        this.codice = codice;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    @Override
    public String toString() {
        return "CodiceCassaDto{" +
                "codice='" + codice + '\'' +
                '}';
    }
}
