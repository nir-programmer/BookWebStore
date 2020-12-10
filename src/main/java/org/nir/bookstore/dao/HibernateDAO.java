package org.nir.bookstore.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.nir.bookstore.entities.Users;

public class HibernateDAO<E>
{
	private  Session currentSession;  ;
	private Transaction currentTransaction; 
	
	public HibernateDAO()
	{
		
	}
	
	public Session openCurrentSession()
	{
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}
	
	public void openCurrentSessionWithTransaction() 
	{
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
	}
	
	
	public void closeCurrentSession()
	{
		currentSession.close();
	}
	
	public void closeCurrentSessionWithTransaction()
	{
		currentTransaction.commit();
		currentSession.close();
	}
	
	private static SessionFactory getSessionFactory()
	{
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Users.class)
				.buildSessionFactory();
		return sessionFactory;
		/*
		 * Configuration configuration = new Configuration().configure();
		 * StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		 * .applySettings(configuration.getProperties());
		 * 
		 * SessionFactory sessionFactory =
		 * configuration.buildSessionFactory(builder.build()); return sessionFactory;
		 */
	}
	
	/*
	 * **************************Getters and setters***********************************************
	 */
	public Session getCurrentSession()
	{
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

/*************************************Interface********************************************** */

	public HibernateDAO(Session session) 
	{
		super();
		this.currentSession = session;
	} 
	
	//OK
	protected E create(E entity)
	{
		getCurrentSession().save(entity);
		
		return null; 
	}
	
	//OK
	protected E find (Class<E> entity ,Object id)
	{
		Session session = getCurrentSession();
		
		//find method can return null but get doesn't
		E e = session.find(entity, id);
		if(e != null)
			session.refresh(e);
		return e; 
	}
	
	protected void delete(Class<E> entity, Object id) 
	{
		Session session = getCurrentSession();
		Object reference = session.getReference(entity, id);
		session.delete(reference);
		
	}

	protected E update(E entity) 
	{
		Session session = getCurrentSession();
		/*
		 * The merge method will create an entity if 
		 * there isn't
		 */
		
		E e = (E)session.merge(entity);
		return entity;
		
	}

	protected List<E> findWithNamedQuery(String queryName) 
	{
		Session session = getCurrentSession();
		Query<E> query = session.createNamedQuery(queryName);
		List<E> entities = query.getResultList();
		return entities;
	}

	protected long countWithNamedQuery(String queryName) 
	{
		Session session = getCurrentSession();
		Query<E> query = session.createNamedQuery(queryName);
		long n = (long)query.getSingleResult(); 
		return n;
	}

	
	
	
	/*
	 * public E update(E entity) { getCurrentSession().update(entity);
	 * 
	 * return entity; }
	 * 
	 * public E find(Class<E> e , Object id) {
	 * 
	 * //E entity = getCurrentSession().get(e, id); Session session =
	 * getCurrentSession(); E entity = session.find(e, id); if(entity != null)
	 * session.refresh(entity);
	 * 
	 * return entity ;
	 * 
	 * }
	 * 
	 * public void delete(Class<E> e , Object id) {
	 * session.getTransaction().begin(); Object reference = session.getReference(e,
	 * id);
	 * 
	 * session.delete(reference); session.getTransaction().commit();
	 * 
	 * }
	 * 
	 * public List<E> findWithNamedQuery(String queryName) {
	 * session.getTransaction().begin(); Query<E> query =
	 * session.createNamedQuery(queryName);
	 * 
	 * List<E> elements = query.getResultList();
	 * 
	 * session.getTransaction().commit(); return elements;
	 * 
	 * }
	 * 
	 * public long countWithNamedQuery(String queryName) {
	 * session.getTransaction().begin(); Query<E> query =
	 * session.createNamedQuery(queryName); long count =
	 * (long)query.getSingleResult();
	 * 
	 * session.getTransaction().commit();
	 * 
	 * return count; }
	 * 
	 * public List<E> findWithNamedQuery(String hql, String paramName, Object
	 * paramValue) { session.getTransaction().begin(); Query<E> query =
	 * session.createNamedQuery(hql); query.setParameter(paramName, paramValue);
	 * return query.getResultList();
	 * 
	 * }
	 */

	
	
}
