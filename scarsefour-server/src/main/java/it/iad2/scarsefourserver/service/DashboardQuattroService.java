package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.EsitoDtoQuattro;
import it.iad2.scarsefourserver.dto.ProdottoDto;
import it.iad2.scarsefourserver.dto.RispostaEanDto;
import it.iad2.scarsefourserver.dto.RispostaEanDtoQuattro;
import it.iad2.scarsefourserver.model.Scontrino;

/**
 *
 * @author Valerio
 */
public interface DashboardQuattroService {

    ProdottoDto vediPrezzoAction(String ean);

    RispostaEanDto chiudiScontrinoAction(Scontrino scontrino);

    RispostaEanDto stornaUltimoAction(String ean, Scontrino scontrino);

    RispostaEanDto annullaScontrinoAction(Scontrino scontrino);

    RispostaEanDtoQuattro inserisciEanAction(String ean, Scontrino scontrino);

    RispostaEanDto stornaAction(String ean, Scontrino scontrino);

    /**
     * Dato il codice a barre e lo scontrino, cerca il prodotto e lo aggiunge, se esiste, nello scontrino
     * @param ean il codice a barre da cercare
     * @param scontrino lo scontrino (al limite null o con id null se nuovo)
     * @return un DTO con lo scontrino aggiornato (righe incluse) o indicazione di problemi nella ricerca dell'EAN
     */
    RispostaEanDtoQuattro verificaEanAction(String ean, Scontrino scontrino);

}
