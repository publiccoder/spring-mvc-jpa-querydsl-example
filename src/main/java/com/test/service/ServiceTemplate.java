package com.test.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;

public abstract class ServiceTemplate<T, ID> {

	@Autowired
	protected JpaRepository<T, ID> repository;
	@Autowired
	private EntityManager entityManager;

	protected JPAQueryFactory queryFactory;

	@PostConstruct
	public void construct() {
		this.queryFactory = new JPAQueryFactory(this.entityManager);
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#save(Object)
	 */
	public T save(T entity) {
		return this.repository.save(entity);
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#saveAll(Iterable)
	 */
	public List<T> saveAll(Iterable<T> entities) {
		return this.repository.saveAll(entities);
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#findById(Object)
	 */
	public Optional<T> findById(ID id) {
		return this.repository.findById(id);
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#existsById(Object)
	 */
	public boolean existsById(ID id) {
		return this.repository.existsById(id);
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	public List<T> findAll() {
		return this.repository.findAll();
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#findAllById(Iterable)
	 */
	public List<T> findAllById(Iterable<ID> ids) {
		return this.repository.findAllById(ids);
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#count()
	 */
	public long count() {
		return this.repository.count();
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#deleteById(Object)
	 */
	public void deleteById(ID id) {
		this.repository.deleteById(id);
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#delete(Object)
	 */
	public void delete(T entity) {
		this.repository.delete(entity);
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#deleteAll(Iterable)
	 */
	public void deleteAll(Iterable<? extends T> entities) {
		this.repository.deleteAll(entities);
	}

	/**
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	public void deleteAll() {
		this.repository.deleteAll();
	}

}
