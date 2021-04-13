package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Giacenza;
import java.util.List;

/**
 *
 * @author user
 */
public class ListaGiacenzaDto {

    private List<Giacenza> listaGiacenza;

    public ListaGiacenzaDto(List<Giacenza> listaGiacenza) {
        this.listaGiacenza = listaGiacenza;
    }

    public ListaGiacenzaDto() {
    }

    public List<Giacenza> getListaGiacenza() {
        return listaGiacenza;
    }

    public void setListaGiacenza(List<Giacenza> listaGiacenza) {
        this.listaGiacenza = listaGiacenza;
    }
}
