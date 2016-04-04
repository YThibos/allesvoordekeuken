package be.vdab.services;

import javax.persistence.EntityManager;

import be.vdab.entities.Artikel;
import be.vdab.filters.JPAFilter;
import be.vdab.repositories.ArtikelRepository;

public class ArtikelService {
	
	private final ArtikelRepository artikelRepository = new ArtikelRepository();
	
	public Artikel read(long id) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			return artikelRepository.read(id, entityManager);
		} finally {
			entityManager.close();
		}
		
	}
	
	public void create(Artikel artikel) {
		EntityManager entityManager = JPAFilter.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			artikelRepository.create(artikel, entityManager);
			entityManager.getTransaction().commit();
		}
		catch (RuntimeException ex) {
			entityManager.getTransaction().rollback();
			throw ex;
		} finally {
			entityManager.close();
		}
	}

}
