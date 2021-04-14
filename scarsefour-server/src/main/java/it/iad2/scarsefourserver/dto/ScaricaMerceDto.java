package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.SkuScaffale;

public class ScaricaMerceDto {
   private int quantita;
   private SkuScaffale skuScaffale;

    public ScaricaMerceDto() {
    }

    public ScaricaMerceDto(int quantita, SkuScaffale skuScaffale) {
        this.quantita = quantita;
        this.skuScaffale = skuScaffale;
    }

   
    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public SkuScaffale getSkuScaffale() {
        return skuScaffale;
    }

    public void setSkuScaffale(SkuScaffale skuScaffale) {
        this.skuScaffale = skuScaffale;
    }
   
   
}
