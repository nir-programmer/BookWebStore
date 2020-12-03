package org.nir.bookstore.entities;
// Generated 3 Dec 2020, 21:03:10 by Hibernate Tools 5.2.12.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class Book.
 * @see org.nir.bookstore.entities.Book
 * @author Hibernate Tools
 */
@Stateless
public class BookHome {

	private static final Log log = LogFactory.getLog(BookHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Book transientInstance) {
		log.debug("persisting Book instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(Book persistentInstance) {
		log.debug("removing Book instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public Book merge(Book detachedInstance) {
		log.debug("merging Book instance");
		try {
			Book result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Book findById(Integer id) {
		log.debug("getting Book instance with id: " + id);
		try {
			Book instance = entityManager.find(Book.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
