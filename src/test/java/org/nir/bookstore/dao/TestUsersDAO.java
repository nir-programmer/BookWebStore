package org.nir.bookstore.dao;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Users;

import static org.junit.jupiter.api.Assertions.assertTrue;;
public class TestUsersDAO 
{
	private static SessionFactory sessionFactory; 
	private static Session session; 
	
	@BeforeAll
	@DisplayName("when try to create a session factory")
	static void init()
	{
		Users user1 = new Users();
		user1.setEmail("NironYYYY");
		user1.setFullName("NIRONYYYY");
		user1.setPassword("123");
		
		
		System.out.println(">>init():try to create session factory"); 
		sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Users.class).buildSessionFactory();
		System.out.println(">>init():SESSION FACTORY CREATED!!!"); 
		
		session = sessionFactory.getCurrentSession();
		System.out.println(">>init():SESSION CREATED!!!"); 
		
		System.out.println(">>init():Try to create UserDAO object.."); 
		UsersDAO usersDAO = new UsersDAO(session);
		
		user1 = usersDAO.create(user1);
		
		assertTrue(user1.getUserId() > 0);
		
		
		
		
		System.out.println("\n***************************************************");
	}
	
	@AfterAll
	@DisplayName("when try close the session factory")
	static void tearDown()
	{
		if(sessionFactory != null)
		{
		System.out.println(">>tearDown():try to close the session factory..."); 
		sessionFactory.close();
		System.out.println(">>teadDown():session factory  closed!"); 
		System.out.println("\n***************************************************");
		}
		
		
	}
	@BeforeEach
	@DisplayName(">>createSession():try to create a new Session")
	void createSession()
	{
		System.out.println(">>createSession():try to create a new Session..."); 
		session = sessionFactory.getCurrentSession();
		System.out.println(">>createSession():Session Created!");
		
		
		System.out.println("\n***************************************************");

	}
	
	@AfterEach
	@DisplayName("when try close the session")
	void closeSession()
	{
		if(session != null)
		{
		System.out.println(">>closeSession():try to close the session..."); 
		session.close();
		System.out.println(">>closeSession():session closed!"); 
		System.out.println("\n***************************************************");

		}
		
		
	}
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	@Test
	void f()
	{
		
	}
}
