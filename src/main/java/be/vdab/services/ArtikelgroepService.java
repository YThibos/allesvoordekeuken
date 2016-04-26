package be.vdab.services;

import java.util.List;

import be.vdab.entities.Artikelgroep;
import be.vdab.repositories.ArtikelgroepRepository;

public class ArtikelgroepService extends AbstractService {
	
	private final ArtikelgroepRepository artikelgroepRepository = new ArtikelgroepRepository();
	
	public List<Artikelgroep> findAll() {
		return artikelgroepRepository.findAll();
	}
	
	public Artikelgroep read(long id) {
		return artikelgroepRepository.read(id);
	}

}
