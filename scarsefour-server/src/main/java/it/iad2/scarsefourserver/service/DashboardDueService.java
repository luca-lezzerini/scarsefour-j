package it.iad2.scarsefourserver.service;

import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.Scontrino;

public interface DashboardDueService
{
	Scontrino annullaScontrino(Scontrino scontrino);
	double vediPrezzo(String barcode);
}
