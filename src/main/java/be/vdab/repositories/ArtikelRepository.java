package be.vdab.repositories;

import javax.persistence.EntityManager;

import be.vdab.entities.Artikel;

public class ArtikelRepository {

	public Artikel read(long id, EntityManager entityManager) {
		return entityManager.find(Artikel.class, id);
	}

	public void create(Artikel artikel, EntityManager entityManager) {
		entityManager.persist(artikel);
	}

}
