package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.AggiungiEanRispostaDto;
import it.iad2.scarsefourserver.dto.ListaRigaScontrinoTreDto;
import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;
import java.util.List;

public interface DashboardTreService {

    /**
     * Consente di ricercare un prodotto per codice ean
     * @param ean criterio di ricerca
     * @return restituisce un Prodotto
     * @see Prodotto
     */
    Prodotto vediPrezzo(String ean);

    /**
     * Consente di associare e salvare uno scontrino e le righeScontrino
     * crea uno scontrino nuovo e vuoto
     * @param scontrino di tipo Scontrino
     * @see Scontrino
     * @param righe una lista di RigaScontrino
     * @see RigaScontrino
     * @return uno scontrino vuoto
     */
    Scontrino chiudiScontrino(Scontrino scontrino, List<RigaScontrino> righe);

    /**
     * Cancella tutte le righe associate allo scontrino in ingresso
     * @param scontrino di tipo Scontrino
     * @see Scontrino
     * @return lo scontrino con la lista di righe vuota
     */
    Scontrino annullaScontrino(Scontrino scontrino);

    /**
     * Rimuove l'ultima riga scontrino associata allo scontrino in ingresso
     * e ricalcola il totale dello scontrino
     * @param scontrino di tipo Scontrino
     * @see Scontrino
     * @return una lista di RigaScontrino
     * @see RigaScontrino
     */
    ListaRigaScontrinoTreDto stornaUltimo(Scontrino scontrino);

    /**
     * Aggiunge una riga Scontrino allo scontrino in ingresso e ricalcola il 
     * totale dello scontrino, salva lo scontrino modificato
     * @param scontrino di tipo Scontrino
     * @see Scontrino
     * @see RigaScontrino
     * @param ean barcode del prodotto per la ricerca del prodotto da associare
     * alla riga scontrino
     * @return un AggiungiEanRispostaDto che contiene lo scontrino, 
     * le righe scontrino e l'esito dell'operazione
     * @see AggiungiEanRispostaDto
     */
    AggiungiEanRispostaDto aggiungiRigaScontrino(Scontrino scontrino, String ean);

    /**
     * Restituisce le righe associate allo scontrino
     * @param scontrino di tipo Scontrino
     * @see Scontrino
     * @return uno Scontrino con le righe Scontrino
     */
    Scontrino aggiornaRighe(Scontrino scontrino);
    
    /**
     * Crea un nuovo scontrino vuoto
     * @return scontrino di tipo Scontrino
     * @see Scontrino
     */
    Scontrino creaNuovoScontrinoVuoto();
    
}
