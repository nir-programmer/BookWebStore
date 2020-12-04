package org.nir.bookstore.dao;

import org.hibernate.Session;

public class HibernateDAO<T>
{
	protected Session session ;

	public HibernateDAO(Session session) 
	{
		super();
		this.session = session;
	} 
	
	
	public T create(T t)
	{
		session.getTransaction().begin();
		session.save(t);
		session.flush();
		session.refresh(t);
		
		session.getTransaction().commit();
		return t; 
	}
}
