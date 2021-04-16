package it.iad2.scarsefourserver.dto;

public class PaginazioneDto extends DatiPageDto{
    
    private String criterio;

    public PaginazioneDto() {
    }

    public PaginazioneDto(String criterio, int numPag, int elemPag) {
        super(numPag, elemPag);
        this.criterio = criterio;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }
    
    
}
