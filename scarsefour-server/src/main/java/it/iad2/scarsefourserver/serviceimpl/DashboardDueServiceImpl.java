package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.service.DashboardDueService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iad2.scarsefourserver.model.Prodotto;
import it.iad2.scarsefourserver.model.Scontrino;
import it.iad2.scarsefourserver.repository.ProdottoRepository;
import it.iad2.scarsefourserver.repository.ScontrinoRepository;

@Service
public class DashboardDueServiceImpl implements DashboardDueService
{
	@Autowired 
	ScontrinoRepository scontrinoRepository;
	@Autowired
	ProdottoRepository prodottoRepository;

	@Override
	public Scontrino annullaScontrino(Scontrino scontrino) {
		scontrinoRepository.delete(scontrino);
		return new Scontrino();
	}

	@Override
	public double vediPrezzo(String barcode)
	{
		return prodottoRepository.findByEanEquals(barcode).getPrezzo();
	}
	
}
