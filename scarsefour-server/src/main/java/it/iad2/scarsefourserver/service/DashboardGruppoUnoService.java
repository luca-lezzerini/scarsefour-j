package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.model.Scontrino;

public interface DashboardGruppoUnoService {

    RispostaEanDto cercaEan1(String barcode, Scontrino scontrino);
    
    RispostaEanDto chiudiScontrino1(String barcode, Scontrino scontrino);
    
    double vediPrezzo1(String barcode);
    
    RispostaEanDto stornaUltimo1(String barcode, Scontrino scontrino);
    
    RispostaEanDto annullaScontrino1(String barcode, Scontrino scontrino);

}
