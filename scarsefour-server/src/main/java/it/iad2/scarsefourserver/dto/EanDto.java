package it.iad2.scarsefourserver.dto;

public class EanDto {
    
    private String barcode;

    public EanDto() {
    }

    public EanDto(String barcode) {
        this.barcode = barcode;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }
}
