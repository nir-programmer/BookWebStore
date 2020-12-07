package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
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
	
	/*************************************************************/
	@Test
	@Disabled
	public void testCreateUsers() 
	{
		Users user1 = new Users();
		user1.setEmail("niritziahk10@gmial.com");
		user1.setFullName("nironnnnn");
		user1.setPassword("powerrrrrr");
		
		System.out.println(">>testCreateUsers():try to add nironnnnn user... ");
		usersDAO.create(user1);
		System.out.println(">>testCreateUsers():nironnnn user persisted!");
		
		assertTrue(user1.getUserId() > 0);
		
	}

	@Test
	@Disabled
	void testCreateUsersFieldsNotSet() 
	{
		System.out.println(">>testCreateUsersFieldsNotSet() :try to add a user with empty fields....");
		
		assertThrows(PropertyValueException.class, () -> usersDAO.create(new Users()));	
	}
	
	@Test
	@Disabled
	@DisplayName("when trying to update users")
	public void testUpdataUsers()
	{
		Users user = new Users();
		
		Integer id = 2; 
		user.setUserId(id);
		user.setEmail("xxxxxxxxxxx");
		user.setFullName("YYYYYYYY");
		user.setPassword("mysecret");
		
		
		System.out.println(">>testUpdataUsers():try to update users with id = " + id);
		usersDAO.update(user);
		
		assertEquals("mysecret", user.getPassword());
		
		System.out.println(">>testUpdataUsers():user updated!");
		
	}

	@Test
	@Disabled
	@DisplayName("when calling get() method on exiting user")
	void testGetUsersFound()
	{
		Integer id = 1; 
		System.out.println(">>testGetUsersFound():try to get user with id = " + id);
		Users users = usersDAO.get(id);
		
		assertNotNull(users);
		
		System.out.println("The user: " + users);
		
	}
	
	@Test
	@DisplayName("when calling get() method on no exiting user")
	void testGetUsersNotFound()
	{
		Integer id = 9; 
		System.out.println(">>testGetUsersNOtFound():try to get user with id = " + id);
		Users users = usersDAO.get(id);
		
		assertNull(users);
		
		System.out.println("The user: " + users);
	}
}
