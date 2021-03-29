package it.iad2.scarsefourserver.dto;

import it.iad2.scarsefourserver.model.Scontrino;

public class ScontrinoDto
{
	private Scontrino scontrino;

	public ScontrinoDto()
	{
		
	}

	public ScontrinoDto(Scontrino scontrino)
	{
		this.scontrino = scontrino;
	}

	public Scontrino getScontrino()
	{
		return scontrino;
	}

	public void setScontrino(Scontrino scontrino)
	{
		this.scontrino = scontrino;
	}

	@Override
	public String toString()
	{
		return "ScontrinoDto [scontrino=" + scontrino + "]";
	}	
	
	
}
