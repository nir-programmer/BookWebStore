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
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestUsersDAO 
{
	private static SessionFactory sessionFactory;
	private static Session session;
	private static UsersDAO usersDAO;
	
	@BeforeAll
	@DisplayName("when try to create a session factory")
	public static void init() 
	{
		sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Users.class)
				.buildSessionFactory();
		
		session = sessionFactory.getCurrentSession();
	
		usersDAO = new UsersDAO(session);
		System.out.println("\n***************************************************");
	}

	@AfterAll
	@DisplayName("when try to close SessionFactory and Session objects")
	public  static void tearDown()
	{
		if(session != null)
			session.close();
		if(sessionFactory != null)
			sessionFactory.close();	
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////

	@Test
	@Disabled
	void testCreateUsersWithMandotoryFileds() 
	{
		Users users = new Users("niritzhak10@gmail.com", "1234", "nir itzhak");
		System.out.println(">>testCreateUsersWithMandotoryFileds() :try to create a users nir itzhak..");
		users = usersDAO.create(users);
		System.out.println(">>testCreateUsersWithMandotoryFileds():users nir itzhak created");

		System.out.println("\n***************************************************");
		
	}
	
	@Test
	@Disabled
	@DisplayName("when trying to add a user with empty field")
	void testAddEmptyUsers()
	{
		
		System.out.println(">>testAddEmptyUsers():before assertThrows...");
		assertThrows(PropertyValueException.class,() -> new UsersDAO(session).create(new Users())); 
		System.out.println(">>testAddEmptyUsers():After assertThrows");

		System.out.println("\n***************************************************");
	}
	
	@Test
	//@Disabled
	@DisplayName("when update a user")
	void testUpdateUser()
	{
		
		Users user = new Users();
		
		user.setUserId(19);
		user.setEmail("ZZZZZZZ");
		user.setFullName("YYY");
		user.setPassword("11111111111");
		
		
		
		System.out.println(">>testUpdateUser():user after update:" + usersDAO.update(user));
		
		
	}
	
}
