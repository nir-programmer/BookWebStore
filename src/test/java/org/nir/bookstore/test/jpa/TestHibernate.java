package org.nir.bookstore.test.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entity.Users;

public class TestHibernate 
{
	private static SessionFactory sessionFactory; 
	private static Session session; 
	
	@BeforeAll
	@DisplayName("when try to create a session factory")
	static void init()
	{
		
		System.out.println(">>init():try to create session factory"); 
		sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Users.class).buildSessionFactory();
		System.out.println(">>init():SESSION FACTORY CREATED!!!"); 
		
		System.out.println(">>init():try to create a Users..."); 
		Users users = new Users("niritzhak10@gmail.com", "Nir Ithzak" , "hello world"); 
		
		session  = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		session.save(users);
		
		
		
		System.out.println(">>init():Users persisted!!!"); 
		session.getTransaction().commit();
		
		
		System.out.println("\n***************************************************");
	}
	
	@Test
	void f()
	{
		
	}
	

}
