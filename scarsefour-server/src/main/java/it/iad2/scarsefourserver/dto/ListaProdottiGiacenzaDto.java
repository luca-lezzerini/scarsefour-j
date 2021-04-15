package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.ProdottoGiacenza;
import java.util.List;

public class ListaProdottiGiacenzaDto {
    List<ProdottoGiacenza> listaProdottiGiacenza;

    public ListaProdottiGiacenzaDto(List<ProdottoGiacenza> listaProdottiGiacenza) {
        this.listaProdottiGiacenza = listaProdottiGiacenza;
    }

    public List<ProdottoGiacenza> getListaProdottiGiacenza() {
        return listaProdottiGiacenza;
    }

    public void setListaProdottiGiacenza(List<ProdottoGiacenza> listaProdottiGiacenza) {
        this.listaProdottiGiacenza = listaProdottiGiacenza;
    }

    @Override
    public String toString() {
        return "ListaProdottiGiacenzaDto{" + "listaProdottiGiacenza=" + listaProdottiGiacenza + '}';
    }

   
}
