package be.vdab.repositories;

import be.vdab.entities.Artikel;

public class ArtikelRepository extends AbstractRepository {
	
	public Artikel read(long id) {
		return getEntityManager().find(Artikel.class, id);
	}

	public void create(Artikel artikel) {
		getEntityManager().persist(artikel);
	}

}
