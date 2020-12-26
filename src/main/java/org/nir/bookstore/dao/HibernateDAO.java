package org.nir.bookstore.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.entities.OrderDetailId;
import org.nir.bookstore.entities.Review;
import org.nir.bookstore.entities.Users;

public class HibernateDAO<E> {
	/*
	 * Instead of initiailzie the factory in the servlet like he does- initialize it
	 * here
	 */
	private static SessionFactory sessionFactory;

	static {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Category.class)
				.addAnnotatedClass(Book.class).addAnnotatedClass(Customer.class).addAnnotatedClass(OrderDetailId.class)
				.addAnnotatedClass(OrderDetail.class).addAnnotatedClass(Review.class).addAnnotatedClass(Users.class)
				.addAnnotatedClass(BookOrder.class).buildSessionFactory();

	}

	//////////////////////////////////////////////////////////////////////////////////
	// protected Session currentSession;
	// protected Transaction currentTransaction;

	public HibernateDAO() {
	}

	/*****************************************************************************
	 * Interface Methods Implementations
	 * ***********************************************
	 ***************************/

	// OK
	protected E create(E entity) {
		/*
		 * Previous version getCurrentSession().save(entity);
		 */

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		session.persist(entity);
		session.flush();
		session.refresh(entity);

		// new code
		session.getTransaction().commit();
		session.close();
		return entity;
	}

	// OK
	protected E find(Class<E> entity, Object id) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		// find method can return null but get doesn't
		E e = session.find(entity, id);
		if (e != null)
			session.refresh(e);

		// new code
		session.getTransaction().commit();
		session.close();

		return e;
	}

	protected void delete(Class<E> entity, Object id) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		Object reference = session.getReference(entity, id);
		/*
		 * Have to invoke inside a transaction, else will not work it will not throw
		 * exception -just does not effect the db
		 */
		session.remove(reference);

		// new code
		session.getTransaction().commit();
		session.close();

	}

	protected E update(E entity) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();
		/*
		 * The merge method will create an entity if there isn't
		 */

		E e = (E) session.merge(entity);

		// new code
		session.getTransaction().commit();
		session.close();
		return entity;

	}

	protected List<E> findWithNamedQuery(String queryName) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		Query<E> query = session.createNamedQuery(queryName);
		List<E> entities = query.getResultList();

		// new code
		session.getTransaction().commit();
		session.close();

		return entities;
	}

	public List<E> findWithNamedQuery(String queryName, String paramName, Object id) {

		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		Query<E> query = session.createNamedQuery(queryName);
		query.setParameter(paramName, id);

		// new code
		session.getTransaction().commit();
		session.close();

		return query.getResultList();
	}

	protected List<E> findWithNamedQuery(String queryName, Map<String, Object> parameters) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		Query query = session.createNamedQuery(queryName);

		Set<Entry<String, Object>> setParameters = parameters.entrySet();

		// Copy each (key , value) pair from the map into the (name, value) pair of the
		// parameter name
		for (Entry<String, Object> entry : setParameters) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		List<E> result = query.getResultList();
		// new code
		session.getTransaction().commit();
		session.close();

		return result;
	}

	protected long countWithNamedQuery(String queryName) {
		Session session = sessionFactory.openSession();
		session.getTransaction().begin();

		Query<E> query = session.createNamedQuery(queryName);
		long n = (long) query.getSingleResult();

		// new code
		session.getTransaction().commit();
		session.close();
		
		return n;
	}

	/******************************************************
	 * METHODS FOR SESSIONS AND TRANSACTIONS HANDLINGS
	 ***************************************************/

}
