package it.iad2.scarsefourserver.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iad2.scarsefourserver.model.Cassa;
import it.iad2.scarsefourserver.repository.CassaRepository;
import it.iad2.scarsefourserver.service.AnagraficaCasseService;

@Service
public class AnagraficaCasseServiceImpl implements AnagraficaCasseService
{
	@Autowired
	CassaRepository cassaRepository;

	@Override
	public List<Cassa> aggiornaCasse()
	{
		return cassaRepository.findAll();
	}

	@Override
	public List<Cassa> nuovaCassa(String codice)
	{
		Cassa cassa = new Cassa(codice);
		cassaRepository.save(cassa);
		return aggiornaCasse();
	}

	@Override
	public List<Cassa> cercaCassa(String codice)
	{
		return cassaRepository.findByCodiceContains(codice);
	}

	@Override
	public List<Cassa> rimuoviCassa(Cassa cassa)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Cassa> modificaCassa(Cassa cassa, String nuovoCodice)
	{
		cassa.setCodice(nuovoCodice);
		cassaRepository.save(cassa);
		return aggiornaCasse();
	}

}
