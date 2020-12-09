package org.nir.bookstore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.nir.bookstore.entities.Users;


public class HibernateDAO<E> {
	private Session currentSession;

	private Transaction currentTransaction;

	public HibernateDAO() {

	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) {
		this.currentTransaction = currentTransaction;
	}

	public E create(E entity) {
		getCurrentSession().save(entity);
		return entity;
	}

	public E update(E entity) {
		getCurrentSession().update(entity);

		return entity;
	}

	public E find(Class<E> entity, Integer id) {
		E e = (E) getCurrentSession().get(entity, id);
		return e;

	}

	public void delete(Class<E> entity, Object id) {
		Session session = getCurrentSession();
		Object reference = session.getReference(entity, id);

		session.delete(reference);

		/*
		 * getCurrentSession().delete(entity.getSimpleName(), id);
		 * session.getTransaction().begin();
		 * ObjecgetCurrentSession().getReference(entity, id);
		 * 
		 * session.delete(reference); session.getTransaction().commit();
		 */

	}

	public List<E> findWithNamedQuery(String queryName) {
		List<E> entities = (List<E>) getCurrentSession().createQuery(queryName).getResultList();
		return entities;
		/*
		 * session.getTransaction().begin(); Query<E> query =
		 * session.createNamedQuery(queryName);
		 * 
		 * List<E> elements = query.getResultList();
		 * 
		 * session.getTransaction().commit(); return elements;
		 */

	}

	public long countWithNamedQuery(String queryName) {
		/*
		 * Session session = getCurrentSession();
		 * 
		 * session.getTransaction().begin(); Query<E> query =
		 * session.createNamedQuery(queryName); long count = (long)
		 * query.getSingleResult();
		 * 
		 * session.getTransaction().commit();
		 * 
		 * return count;
		 */
		return 0 ; 
	}

	public List<E> findWithNamedQuery(String hql, String paramName, Object paramValue)
	{
		return null; 
		/*
		 * session.getTransaction().begin(); Query<E> query =
		 * session.createNamedQuery(hql); query.setParameter(paramName, paramValue);
		 * return query.getResultList();
		 */

	}

}
