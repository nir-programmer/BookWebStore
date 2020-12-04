package org.nir.bookstore.entities;
// Generated 4 Dec 2020, 11:44:04 by Hibernate Tools 5.2.12.Final

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Home object for domain model class OrderDetail.
 * @see org.nir.bookstore.entities.OrderDetail
 * @author Hibernate Tools
 */
@Stateless
public class OrderDetailHome {

	private static final Log log = LogFactory.getLog(OrderDetailHome.class);

	@PersistenceContext
	private EntityManager entityManager;

	public void persist(OrderDetail transientInstance) {
		log.debug("persisting OrderDetail instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void remove(OrderDetail persistentInstance) {
		log.debug("removing OrderDetail instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	public OrderDetail merge(OrderDetail detachedInstance) {
		log.debug("merging OrderDetail instance");
		try {
			OrderDetail result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public OrderDetail findById(OrderDetailId id) {
		log.debug("getting OrderDetail instance with id: " + id);
		try {
			OrderDetail instance = entityManager.find(OrderDetail.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
