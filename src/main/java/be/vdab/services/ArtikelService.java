package be.vdab.services;

import java.math.BigDecimal;
import java.util.List;

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
	
	public List<Artikel> findLikeNaam(String naam) {
		return artikelRepository.findLikeNaam(naam);
	}
	
	public void prijsverhoging(BigDecimal percentage) {
		
		BigDecimal factor = BigDecimal.ONE.add(percentage.divide(BigDecimal.valueOf(100)));
		
		beginTransaction();
		artikelRepository.prijsverhoging(factor);
		commit();
		
	}
	
	public List<Artikel> findAll() {
		return artikelRepository.findAll();
	}

}
