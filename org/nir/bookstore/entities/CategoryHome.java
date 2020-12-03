package org.nir.bookstore.entities;
// Generated 3 Dec 2020, 21:03:10 by Hibernate Tools 5.2.12.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Category.
 * @see org.nir.bookstore.entities.Category
 * @author Hibernate Tools
 */
@Stateless
public class CategoryHome {

	private static final Log log = LogFactory.getLog(CategoryHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Category transientInstance) {
		log.debug("persisting Category instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Category persistentInstance) {
		log.debug("removing Category instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Category merge(Category detachedInstance) {
		log.debug("merging Category instance");
		try {
			Category result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Category findById(Integer id) {
		log.debug("getting Category instance with id: " + id);
		try {
			Category instance = entityManager.find(Category.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
