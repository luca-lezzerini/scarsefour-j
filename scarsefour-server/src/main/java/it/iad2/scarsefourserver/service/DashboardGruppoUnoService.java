package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.dto.ScontrinoRigheDto;
import it.iad2.scarsefourserver.model.Scontrino;

public interface DashboardGruppoUnoService {

    RispostaEanDto cercaEan1(String barcode, Scontrino scontrino);
    
    Scontrino chiudiScontrino1(Scontrino scontrino);
    
    double vediPrezzo1(String barcode);
    
    ScontrinoRigheDto stornaUltimo1(Scontrino scontrino);
    
    Scontrino annullaScontrino1(Scontrino scontrino);

}
