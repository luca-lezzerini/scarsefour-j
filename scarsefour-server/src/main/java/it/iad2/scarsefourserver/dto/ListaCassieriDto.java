package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Cassiere;
import java.util.List;


public class ListaCassieriDto {
    
     private List<Cassiere> listaCassieri;

    public ListaCassieriDto() {
    }

    public ListaCassieriDto(List<Cassiere> listaCassieri) {
        this.listaCassieri = listaCassieri;
    }

    public List<Cassiere> getListaCassieri() {
        return listaCassieri;
    }

    public void setListaCassieri(List<Cassiere> listaCassieri) {
        this.listaCassieri = listaCassieri;
    }

    @Override
    public String toString() {
        return "ListaCassieriDto{" + "listaCassieri=" + listaCassieri + '}';
    }
     
}
