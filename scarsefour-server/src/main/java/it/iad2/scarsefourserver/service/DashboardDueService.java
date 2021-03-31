package it.iad2.scarsefourserver.service;

import java.util.List;

import it.iad2.scarsefourserver.model.RigaScontrino;
import it.iad2.scarsefourserver.model.Scontrino;

public interface DashboardDueService
{
	Scontrino annullaScontrino(Scontrino scontrino);
	double vediPrezzo(String barcode);
	Scontrino chiudiScontrino(Scontrino scontrino, List<RigaScontrino> righeScontrino);
	Scontrino stornaUltimo(Scontrino scontrino);
	RigaScontrino aggiungi(String barcode, Scontrino scontrino);
}
