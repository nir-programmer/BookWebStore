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
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.entities.OrderDetailId;
import org.nir.bookstore.entities.Review;
import org.nir.bookstore.entities.Users;

public class HibernateDAO<E>
{
	private  Session currentSession;  ;
	private Transaction currentTransaction; 
	
	public HibernateDAO() {}
	
	

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
		/*
		 * Have to invoke inside a transaction, else will not work
		 * it will not throw exception -just does not effect the db
		 */
		session.remove(reference);
		
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

	public List<E> findWithNamedQuery(String queryName, String paramName , Object id)
	{	
		Session session = getCurrentSession();
		Query<E> query = session.createNamedQuery(queryName);
		query.setParameter(paramName, id);
		return query.getResultList();
	}
	
	/******************************************************
	 * METHODS FOR SESSIONS AND TRANSACTIONS HANDLINGS
	 ***************************************************/
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
				 .addAnnotatedClass(Category.class)
				 .addAnnotatedClass(Book.class)
				 .addAnnotatedClass(Customer.class)
				 .addAnnotatedClass(OrderDetailId.class)
				 .addAnnotatedClass(OrderDetail.class)
				 .addAnnotatedClass(Review.class)
				 .addAnnotatedClass(Users.class)
				 .addAnnotatedClass(BookOrder.class)
				 .buildSessionFactory();
		
		
		return sessionFactory;
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
	
	
}
