package be.vdab.services;

import be.vdab.entities.Artikel;
import be.vdab.repositories.ArtikelRepository;

public class ArtikelService extends AbstractService {
	
	private final ArtikelRepository artikelRepository = new ArtikelRepository();
	
	public Artikel read(long id) {
		return artikelRepository.read(id);
	}
	
	public void create(Artikel artikel) {
		beginTransaction();
		artikelRepository.create(artikel);
		commit();
	}

}
