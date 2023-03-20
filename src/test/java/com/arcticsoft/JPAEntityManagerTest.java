package com.arcticsoft;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class JPAEntityManagerTest {
	private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistenceunit");
	private static EntityManager em;
	private static List<Album> albumList = new ArrayList<>();

	@BeforeAll
	static void setUp() {
		em = emf.createEntityManager();
		em.getTransaction().begin();
		var album1 = new Album("KIDS SEE GHOSTS", LocalDate.of(1999, Month.AUGUST, 19));
		var album2 = new Album("A Love Supreme", LocalDate.of(1965, Month.FEBRUARY, 1));
		albumList.add(album1);
		albumList.add(album2);
		em.persist(album1);
		em.persist(album2);
		em.getTransaction().commit();
	}

	@Test
	public void insertAlbum() {
		em.getTransaction().begin();
		Album album = new Album();
		album.setReleaseDate(LocalDate.now());
		album.setTitle("New Album");
		album = em.merge(album);
		albumList.add(album);
		var list = em.createQuery("FROM Album", Album.class).getResultList();
		assertEquals(albumList, list);
		em.getTransaction().commit();
		em.close();
	}
}