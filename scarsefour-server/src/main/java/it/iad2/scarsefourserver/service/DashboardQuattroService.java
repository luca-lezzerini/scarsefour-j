package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.model.Scontrino;

/**
 *
 * @author Valerio
 */
public interface DashboardQuattroService {
    
     RispostaEanDto vediPrezzo (String ean);
     RispostaEanDto chiudiScontrino (Scontrino scontrino);
     RispostaEanDto stornaUltimo(String ean,Scontrino scontrino);
     RispostaEanDto annullaScontrino (Scontrino scontrino);
     RispostaEanDto inserisciEan (String ean,Scontrino scontrino);
     
     
    
}
