package it.iad2.scarsefourserver.service;

import java.util.List;

import it.iad2.scarsefourserver.model.Cassa;

public interface AnagraficaCasseService
{
	List<Cassa> aggiornaCasse();
	List<Cassa> nuovaCassa(String codice);
	List<Cassa> cercaCassa(String codice);
	List<Cassa> modificaCassa(Cassa cassa, String nuovoCodice);
	List<Cassa> rimuoviCassa(Cassa cassa);
}
