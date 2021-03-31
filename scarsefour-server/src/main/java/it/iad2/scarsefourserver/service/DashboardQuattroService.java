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

    RispostaEanDto inserisciEanAction(String ean, Scontrino scontrino);

    RispostaEanDto stornaAction(String ean, Scontrino scontrino);

    RispostaEanDtoQuattro verificaEanAction(String ean);

}
