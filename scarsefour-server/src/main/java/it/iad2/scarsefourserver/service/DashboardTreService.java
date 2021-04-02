package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.AggiungiEanRispostaDto;
import it.iad2.scarsefourserver.dto.ListaRigaScontrinoTreDto;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.util.List;

public interface DashboardTreService {

    Prodotto vediPrezzo(String ean);

    Scontrino chiudiScontrino(Scontrino scontrino, List<RigaScontrino> righe);

    Scontrino annullaScontrino(Scontrino scontrino);
//    List<RigaScontrino> stornaUltimo(Scontrino scontrino);
//    List<RigaScontrino> aggiungiRigaScontrino(Scontrino scontrino);
//    List<RigaScontrino> aggiornaRighe(Scontrino scontrino);

    ListaRigaScontrinoTreDto stornaUltimo(Scontrino scontrino);

    AggiungiEanRispostaDto aggiungiRigaScontrino(Scontrino scontrino, String ean);

    Scontrino aggiornaRighe(Scontrino scontrino);
    
    Scontrino creaNuovoScontrinoVuoto();
    
}
