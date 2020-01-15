package com.fdmgroup.getaways.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fdmgroup.getaways.model.accommodations.Accommodation;
import com.fdmgroup.getaways.model.locations.Locations;

@Repository
public class AccommodationJpaDao implements CRUD<Accommodation>, AccommodationQuerier<Accommodation>{

	private EntityManager entityManager;
	
	@Autowired
	public AccommodationJpaDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void create(Accommodation t) {
		entityManager.getTransaction().begin();
		entityManager.persist(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public Accommodation readOneById(long id) {
		return entityManager.find(Accommodation.class, id);
	}

	@Override
	public List<Accommodation> readAll() {
		return entityManager.createQuery("SELECT A FROM ACCOMMODATIONS A", Accommodation.class).getResultList();
	}

	@Override
	public void update(Accommodation t) {
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public void delete(Accommodation t) {	
		entityManager.getTransaction().begin();
		entityManager.remove(t);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Accommodation> searchAccommodationLocation(Locations locations) {
		return entityManager.createQuery("SELECT A FROM ACCOMMODATIONS A WHERE accommodationLocation =: searchLocation", Accommodation.class)
				.setParameter("searchLocation", locations)
				.getResultList();
	}
}
