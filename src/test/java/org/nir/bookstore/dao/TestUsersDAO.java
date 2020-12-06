package org.nir.bookstore.dao;

import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Users;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestUsersDAO {
	private static SessionFactory sessionFactory;
	private static Session session;
	private static UsersDAO usersDAO;

	@BeforeAll
	@DisplayName("when try to create a session factory")
	public static void init() {
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Users.class)
				.buildSessionFactory();

		
		  session = sessionFactory.getCurrentSession();
		  
		  usersDAO = new UsersDAO(session);
		  System.out.println("\n***************************************************");
		
	}
	
	
	
	
	@AfterAll
	@DisplayName("when try to close SessionFactory and Session objects")
	public static void tearDown() {
		if (session != null)
			session.close();
		if (sessionFactory != null)
			sessionFactory.close();
	}
	
	////////////////////////////////////////////////////
	
	@Test
	@Disabled
	@DisplayName("testGetFoundUser")
	void testGetFoundUser()
	{
		Integer id = 1; 
		Users users = usersDAO.get(id);
		
		System.out.println("testGetFoundSuser(): user with id = " + id);
		System.out.println(users);
		
	}
	
	@Test
	void testDeleteFoundUsers()
	{
		Integer id = 1; 
		
		usersDAO.delete(id);
		
		
	}
}
