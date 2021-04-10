package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.Cassiere;
import it.iad2.scarsefourserver.model.MovimentiScaffale;
import it.iad2.scarsefourserver.model.PosizioneScaffale;
import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Sconto;
import it.iad2.scarsefourserver.model.SkuScaffale;

public interface SystemAdminService {

    /**
     * Cancella tutti i dati in archivio relativi alle entità
     * @see MovimentiScaffale
     * @see Sconto
     * @see RigaScontrino
     * @see Cassa
     * @see Cassiere
     * @see Scontrino
     * @see PosizioneScaffale
     * @see Prodotto
     * @see SkuScaffale
     * Ripopola i dati in archivio con dati di test e crea le associazioni tra
     * Prodotto e RigaScontrino, Scontrino e RigaScontrino, Prodotto e Sconto,
     * Prodotto e PosizioneScaffale
     */
    void generaDatiTest();
}
