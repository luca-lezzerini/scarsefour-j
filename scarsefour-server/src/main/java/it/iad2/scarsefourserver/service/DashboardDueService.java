package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.RigaScontrino;
import java.util.List;

import it.iad2.scarsefourserver.model.Scontrino;

public interface DashboardDueService {

    Scontrino annullaScontrino(Scontrino scontrino);

    double vediPrezzo(String barcode);

    Scontrino chiudiScontrino(Scontrino scontrino);

    Scontrino stornaUltimo(Scontrino scontrino);

    Scontrino aggiungi(String barcode, Scontrino scontrino);
}
