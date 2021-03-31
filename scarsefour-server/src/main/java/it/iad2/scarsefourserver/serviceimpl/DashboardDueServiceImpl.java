package it.iad2.scarsefourserver.serviceimpl;

import it.iad2.scarsefourserver.service.DashboardDueService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.iad2.scarsefourserver.model.RigaScontrino;
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

	@Override
	public Scontrino chiudiScontrino(Scontrino scontrino, List<RigaScontrino> righeScontrino)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Scontrino stornaUltimo(Scontrino scontrino)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RigaScontrino aggiungi(String barcode, Scontrino scontrino)
	{
		// TODO Auto-generated method stub
		return null;
	}
	
}
