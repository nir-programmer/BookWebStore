package org.nir.bookstore.dao;

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
		session.saveOrUpdate(e);
		session.flush();
		session.refresh(e);
		
		session.getTransaction().commit();
		return e; 
	}
	
	public E find(Class<E>)
	{
		
	}
	
	
	
}
