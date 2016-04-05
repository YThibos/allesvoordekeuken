package be.vdab.repositories;

import java.util.List;

import be.vdab.entities.Artikel;

public class ArtikelRepository extends AbstractRepository {
	
	public Artikel read(long id) {
		return getEntityManager().find(Artikel.class, id);
	}

	public void create(Artikel artikel) {
		getEntityManager().persist(artikel);
	}
	
	public List<Artikel> findLikeNaam(String naam) {
		return getEntityManager().createNamedQuery("Artikel.findLikeNaam", Artikel.class)
			.setParameter("naam", naam)
			.getResultList();
	}

}
