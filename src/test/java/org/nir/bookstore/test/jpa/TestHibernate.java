package org.nir.bookstore.test.jpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
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
	
	@BeforeEach
	@DisplayName("when try to create a new session")
	void createSessionAndTransaction()
	{
		System.out.println(">>createSessionAndTransaction():try to create a new session...");
		session = sessionFactory.getCurrentSession();
		System.out.println(">>createSessionAndTransaction():try to create a new Transaction");
		session.beginTransaction();
	}
	
	@AfterAll
	@DisplayName("when try to close the sessionFactory")
	static void tearDown()
	{
		System.out.println(">>tearDown():try to close the session factory...");
		if(sessionFactory != null)
		{
			System.out.println(">>tearDown():try to close the session factory...");
			sessionFactory.close();
			System.out.println(">>tearDown():Session Factory Closed!");
		}
	}
	
	@AfterEach
	@DisplayName("when trying to close the session")
	void closeSession()
	{
		
		if(session != null)
		{
			System.out.println(">>testCloseSession():try to close the session...");
			session.close();
		}
	}
	
	@Test
	@DisplayName("when adding new users")
	void testAddNewUsers()
	{
		
		System.out.println(">>testAddNewUsers():try to add a new users -Nir Itzhkon");
		Users users = new Users("niritzhak10@gmail.com","Nir Itzhakon", "nir password");
		
		session.save(users);
		
		System.out.println(">>testAddNewUsers():Object persisted");
		
		
		
	}
	

}
