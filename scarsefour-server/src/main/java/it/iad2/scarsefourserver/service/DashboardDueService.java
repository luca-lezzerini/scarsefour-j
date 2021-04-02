package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.dto.AggiungiDto;

import it.iad2.scarsefourserver.model.Scontrino;

public interface DashboardDueService {

    Scontrino annullaScontrino(Scontrino scontrino);

    double vediPrezzo(String barcode);

    Scontrino chiudiScontrino(Scontrino scontrino);

    Scontrino stornaUltimo(Scontrino scontrino);

    AggiungiDto aggiungi(String barcode, Scontrino scontrino);

    boolean cercaProdotto(String barcode);

    Scontrino generaScontrino();
}
