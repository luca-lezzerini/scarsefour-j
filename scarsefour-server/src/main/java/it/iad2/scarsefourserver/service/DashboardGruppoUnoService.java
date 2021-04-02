package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.dto.ScontrinoRigheDto;
import it.iad2.scarsefourserver.model.Scontrino;

public interface DashboardGruppoUnoService {

    /**
     * Consente di associare il prodotto inserito ad uno scontrino
     * andando a incrementare le quantità qualora il prodotto sia gia presente nello scontrino o inserendo una nuova riga
     * ad ogni inserimento verrà aggiornato anche il prezzo totale dello scontrino.
     * @param barcode il codice a barre da ricercare
     * @param scontrino una lista di righe di prodotti
     * @return lo scontrino e le righe presenti all'interno dello stesso
     */
    RispostaEanDto cercaEan1(String barcode, Scontrino scontrino);
    
    /**
     * Consente la chiusura dello scontrino attribuendogli un numero progressivo continuativo.
     * @param scontrino una lista di righe di prodotti
     * @return scontrino una lista di righe di prodotti
     */
    Scontrino chiudiScontrino1(Scontrino scontrino);
    
    /**
     * Consente di vedere il prezzo del prodotto inserito nel barcode.
     * @param barcode il codice a barre da ricercare
     * @return il prezzo di un prodotto(numero)
     */
    double vediPrezzo1(String barcode);
    
    /**
     * Consente di eliminare dallo scontrino l'ultimo prodotto inserito.
     * @param scontrino una lista di righe di prodotti
     * @param lastBarcode l'ultimo codice a barre inserito
     * @return le righe presenti in uno scontrino
     */
    ScontrinoRigheDto stornaUltimo1(Scontrino scontrino, String lastBarcode);
    
    /**
     * consente di annullare uno scontrino contenente dei prodotti.
     * @param scontrino una lista di righe di prodotti
     * @return uno scotrino (null)
     */
    Scontrino annullaScontrino1(Scontrino scontrino);
    
    

}
