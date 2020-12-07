package org.nir.bookstore.dao;

import javax.persistence.EntityManager;

import org.hibernate.Session;

public class HibernateDAO<E>
{
	protected Session session ;

	public HibernateDAO(Session session) 
	{
		super();
		this.session = session;
	} 
	
	public E create(E e)
	{
		session.getTransaction().begin();
		session.save(e);
		session.flush();
		session.refresh(e);
		
		session.getTransaction().commit();
		return e; 
	}
	
	public E update(E e)
	{
		session.getTransaction().begin();
		//he used merge...
		session.saveOrUpdate(e);
		session.flush();
		session.refresh(e);
		
		session.getTransaction().commit();
		
		return e; 
	}
	
	public E find(Class<E> e , Object id)
	{
		session.getTransaction().begin();
		E entity =  session.find(e, id);
		if(entity != null)
			session.refresh(entity);
		
		session.getTransaction().commit();
		return entity ;
		
	}
	
	public void delete(Class<E> e , Object id)
	{
		//session.getTransaction().begin();
		
		Object refeObject  = this.find(e,  id);
		session.delete(refeObject);
		//session.getTransaction().commit();
		
		
	}
	
	
	
}
