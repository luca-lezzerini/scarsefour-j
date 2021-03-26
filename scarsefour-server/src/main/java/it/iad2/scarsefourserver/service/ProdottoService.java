package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.ListaProdottiDto;
import it.iad2.scarsefourserver.model.Prodotto;

public interface ProdottoService {

    ListaProdottiDto modifica(Prodotto prodotto);

    ListaProdottiDto confermaProdotto(Prodotto prodotto);

    ListaProdottiDto annullaProdotto(Prodotto prodotto);

    ListaProdottiDto rimuoviProdotto(Prodotto prodotto);

    ListaProdottiDto aggiornaProdotto(Prodotto prodotto);
}
