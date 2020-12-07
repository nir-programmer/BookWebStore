package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Users;

public class TestUsersDAO 
{
	private static SessionFactory sessionFactory; 
	private static Session session ; 
	private static UsersDAO usersDAO; 
	
	
	@BeforeAll
	@DisplayName("when create SessionFactory , Session , UsersDAO object")
	public static void init()
	{
		sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Users.class)
				.buildSessionFactory();

		 session = sessionFactory.getCurrentSession();
		 
		 usersDAO = new UsersDAO(session); 
		 
	}
	
	@AfterAll
	@DisplayName("when colose the SessionFactroy and Session")
	public static void tearDown()
	{
		session.close();
		sessionFactory.close();
	}
	
	
	
	
	@Test
	public void testCreateUsers() {
		Users user1 = new Users();
		user1.setEmail("niritziahk10@gmial.com");
		user1.setFullName("Nir Ithzak");
		user1.setPassword("power");

		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Users.class).buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();

		session.getTransaction().begin();

		session.persist(user1);

		session.getTransaction().commit();

		session.close();
		sessionFactory.close();

		assertTrue(user1.getUserId() > 0);

	}

	@Test
	@Disabled
	void testCreateUsersFieldsNotSet() 
	{
		Users user1 = new Users();
		/*
		 * user1.setEmail("niritziahk10@gmial.com"); user1.setFullName("Nir Ithzak");
		 * user1.setPassword("power");
		 */
		
		SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Users.class)
				.buildSessionFactory();
		
		Session session = sessionFactory.getCurrentSession();
		
		session.getTransaction().begin();
		
		session.persist(user1);
		
		session.getTransaction().commit();
		
		session.close();
		sessionFactory.close();
		
	}

}
