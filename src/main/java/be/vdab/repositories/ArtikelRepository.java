package be.vdab.repositories;

import java.math.BigDecimal;
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
	
	public void prijsverhoging(BigDecimal factor) {
		getEntityManager().createNamedQuery("Artikel.prijsverhoging")
			.setParameter("factor", factor)
			.executeUpdate();
	}

	public List<Artikel> findAll() {
		return getEntityManager().createNamedQuery("Artikel.findAll", Artikel.class)
				.getResultList();
	}
	
	public List<Artikel> findAllMetArtikelgroep() {
		return getEntityManager().createNamedQuery("Artikel.findAll", Artikel.class)
				.setHint("javax.persistence.loadgraph", getEntityManager().createEntityGraph(Artikel.MET_ARTIKELGROEP))
				.getResultList();
	}

}
