package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Artikelgroep;

public class ArtikelgroepRepository extends AbstractRepository {

	public List<Artikelgroep> findAll() {
		return getEntityManager().createNamedQuery("Artikelgroep.findAll", Artikelgroep.class)
				.getResultList();
	}
	
	public Artikelgroep read(long id) {
		return getEntityManager().find(Artikelgroep.class, id);
	}
	
}
