package com.arcticsoft;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import junit.framework.TestCase;

public class JPAEntityManagerTest extends TestCase
{
	private EntityManagerFactory emf;

	@Override
	protected void setUp() throws Exception	{
		emf = Persistence.createEntityManagerFactory("com.arcticsoft.persistenceunit");
	}

	@Override
	protected void tearDown() throws Exception {
		if (emf.isOpen()) {
			emf.close();
		}
	}

	public void testBasicUsage()
	{
		EntityManager em;
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(new Album("KIDS SEE GHOSTS", LocalDate.of(2018, Month.MAY, 8)));
		em.persist(new Album("A Love Supreme", LocalDate.of(1965, Month.FEBRUARY, 1)));
		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();
		List<Album> list = em.createQuery("FROM Album", Album.class).getResultList();
		list.stream().forEach(System.out::println);
		em.getTransaction().commit();
		em.close();

		Album album = new Album();
		em = emf.createEntityManager();
		em.getTransaction().begin();
		album.setReleaseDate(LocalDate.now());
		album = em.merge(album);
		album.setTitle("Test Album");
		em.remove(album);
		list = em.createQuery("FROM Album", Album.class).getResultList();
		list.stream().forEach(System.out::println);
		em.getTransaction().commit();
		em.close();
	}
}