package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Sconto;
import java.util.List;

/**
 *
 * @author Valerio
 */
public class ListaScontiDto {
    
    private List<Sconto> listaSconti;

    public ListaScontiDto() {
    }

    public ListaScontiDto(List<Sconto> listaSconti) {
        this.listaSconti = listaSconti;
    }

    public List<Sconto> getListaSconti() {
        return listaSconti;
    }

    public void setListaSconti(List<Sconto> listaSconti) {
        this.listaSconti = listaSconti;
    }

    

    
    
    
    
    
    
}
