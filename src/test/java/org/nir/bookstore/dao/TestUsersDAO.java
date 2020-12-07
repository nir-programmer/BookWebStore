package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

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
	//@Disabled
	public void testCreateUsers() 
	{
		Users user1 = new Users();
		user1.setEmail("jon@gmail.com");
		user1.setFullName("John Smith");
		user1.setPassword("123444");
		
		Users users2 = new Users("Chad@gmail.com", "1233", "Chad Darby");
		
		Users users3 = new Users("mary@tmail.com", "1223", "Mary Loo"); 
		
		System.out.println(">>testCreateUsers():try to add new User ");
		usersDAO.create(user1);
		/*
		 * usersDAO.create(users2); usersDAO.create(users3);
		 */
		
		System.out.println(">>testCreateUsers():user persisted!");
		
		//assertTrue(user1.getUserId() > 0);
		
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
	@Disabled
	@DisplayName("when calling get() method on no exiting user")
	void testGetUsersNotFound()
	{
		Integer id = 9; 
		System.out.println(">>testGetUsersNOtFound():try to get user with id = " + id);
		Users users = usersDAO.get(id);
		
		assertNull(users);
		
		System.out.println("The user: " + users);
	}
	
	@Test
	@Disabled
	@DisplayName("when calling the delete method on exititng user")
	public void testDeleteUsersFound()
	{
		Integer id = 1; 
		System.out.println(">>testDeleteUsersFound():try to delete user with id = " + id);
		usersDAO.delete(id);
		
	}
	
	@Test
	@Disabled
	@DisplayName("when calling listAll() method")
	void testListAll()
	{
		System.out.println(">>testListAll():try to get all users form db.");
		List<Users> users = usersDAO.listAll();
		
		System.out.println(">>testListAll():List of users:");
		
		assertTrue(users.size() == 0);
		users.stream().forEach(System.out::println);
		
	}
	
	@Test
	@DisplayName("when calling to count() method")
	void testCount()
	{
		System.out.println(">>testCount():Calling to count method...");
		long count = usersDAO.count();
		
		System.out.println("number of users = " + count);
		
		assertEquals(2, count);
		
	}
	
	
	
	
	
	
}
