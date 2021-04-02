package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.AggiungiDto;

import it.iad2.scarsefourserver.model.Scontrino;


/**
 * @author gruppo-due
 */
public interface DashboardDueService {

    /**
     * @param scontrino = lo scontrino da annullare 
     * @return ricicla lo scontrino cancellandone le righe associate
     */
    Scontrino annullaScontrino(Scontrino scontrino);

    /**
     * @param barcode = barcode del prodotto di cui voglio sapere il prezzo
     * @return ritorna il prezzo del prodotto, altrimenti 0.0
     */
    double vediPrezzo(String barcode);

    /**
     * @param scontrino = lo scontrino da chiudere
     * @return ritorna un nuovo scontrino dopo aver chiuso il precedente
     */
    Scontrino chiudiScontrino(Scontrino scontrino);

    /**
     * @param scontrino = lo scontrino a cui voglio cancellare l`ultima riga
     * @return ritorna lo stesso scontrino senza l`ultima riga
     */
    Scontrino stornaUltimo(Scontrino scontrino);

    /**
     * @param barcode = il codice del prodotto da aggiungere
     * @param scontrino = lo scontrino a cui aggiungere il prodotto
     * @return ritorna lo scontrino con una nuova riga ed il prodotto associato
     */
    AggiungiDto aggiungi(String barcode, Scontrino scontrino);

    /**
     * @param barcode = codice del prodotto da cercare
     * @return true se il prodotto esiste, false altrimenti
     */
    boolean cercaProdotto(String barcode);

    /**
     * @return ritorna un nuovo scontrino
     */
    Scontrino generaScontrino();
}
