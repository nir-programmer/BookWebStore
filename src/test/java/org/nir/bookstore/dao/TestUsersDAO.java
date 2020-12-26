package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Users;

public class TestUsersDAO 
{
	
	private  static  UsersDAO usersDAO = null;
	
	@BeforeAll
	@DisplayName("when create UsersDAO object")
	public static void init()
	{
		 usersDAO = new UsersDAO(); 
	}
	
	
	/**********************************************************
	 * 					TESTS
	 * ************************************************************/
	
	//OK
	@Test
	@DisplayName("when creating a new user by the userDao")
	void testCreateUsers()
	{
		
		Users user1 = new Users("YYY", "YYY", "YYY");
		Users user2 = new Users("AAA", "AAA", "AAA");
		Users user3 = new Users("BBB", "BBB", "BBB");
		Users user4 = new Users("niritzhak10@gmail.com", "superduper100", "Niron Itzhak"); 
		Users user5 = new Users("a@a", "a", "a"); 
		usersDAO.openCurrentSessionWithTransaction();
		usersDAO.create(user1);
		usersDAO.create(user2);
		usersDAO.create(user3);
		usersDAO.create(user4);
		usersDAO.create(user5);
		
		usersDAO.closeCurrentSessionWithTransaction();
	}
	
	//OK
	@Test
	@DisplayName("when calling get(id)")
	void testGet()
	{
		Integer id = 2; 
		
		usersDAO.openCurrentSession();
		Users user = usersDAO.get(id);
		System.out.println("User with id = " + user);
		usersDAO.closeCurrentSession();
		
		assertNotNull(user);
		
	}
	
	@Test
	@DisplayName("when calling delete(id)")
	void testDelete()
	{
		Integer id = 21; 
		
		usersDAO.openCurrentSession();
		Users user = usersDAO.get(id);
		System.out.println("testDelete():User with id = " + id +" BEFORE delete:");
		System.out.println(user);
		usersDAO.closeCurrentSession();
		
		usersDAO.openCurrentSessionWithTransaction();
		usersDAO.delete(id);
		//assertThrows(EntityNotFoundException.class , () ->usersDAO.delete(id));
		usersDAO.closeCurrentSessionWithTransaction();
		
		/*
		 * usersDAO.openCurrentSession(); user = usersDAO.get(4);
		 * System.out.println("testDelete():User with id = " + id +" AFTER delete:");
		 * System.out.println(user); usersDAO.closeCurrentSession();
		 * 
		 */
	}
	
	@Test
	@DisplayName("when calling update(user)")
	void testUpdate()
	{
		usersDAO.openCurrentSession();
		Integer id = 3; 
		Users user = usersDAO.get(id);
		System.out.println(">>testUpdate():User with id = " + id +" Before update:");
		System.out.println(user);
		usersDAO.closeCurrentSession();
		
		usersDAO.openCurrentSessionWithTransaction();
		user.setEmail("ZZZZZ");
		user.setFullName("NNN");
		user.setPassword("NNN");
		user = usersDAO.update(user);
		System.out.println(">>testUpdate():User with id = " + id +" AFTER update:");
		System.out.println(user);
		usersDAO.closeCurrentSessionWithTransaction();
		
	}
	
	@Test
	@DisplayName("when calling findAll() ")
	void testFindAll()
	{
		
		usersDAO.openCurrentSession();
		List<Users> users = usersDAO.listAll();
		System.out.println("List Of All users:");
		users.stream().forEach(System.out::println);
		
		usersDAO.closeCurrentSession();
		
	}
	
	@Test
	@DisplayName("when calling count")
	void testCount()
	{
		
		usersDAO.openCurrentSession();
		long n= usersDAO.count();
		System.out.println("Number of users = " + n); 
		
		usersDAO.closeCurrentSession();
		
	}
	
	@Test 
	@DisplayName("when calling findByEmail()")
	void testFindByEmail()
	{
		String email = "XXXX"; 
		usersDAO.openCurrentSessionWithTransaction();
		Users user = usersDAO.findByEmail(email);
		System.out.println("testFindByEmail():The user with email = " + email); 
		System.out.println(user);
		
		assertNotNull(user);
		usersDAO.closeCurrentSessionWithTransaction();
	}
	
	@Test
	//@Disabled
	@DisplayName("when tring to delete all users")
	void testDeleteAllUsers()
	{
		usersDAO.openCurrentSessionWithTransaction();
		List<Users> users = usersDAO.listAll();
		
		System.out.println(">>testDeleteAllUsers():list of all users BEFORE deleteing"); 
		users.forEach(u -> System.out.println(u.getFullName()));
		
		users.forEach(u -> usersDAO.delete(u.getUserId()));
		
		System.out.println(">>testDeleteAllUsers():list of all Users AFTER deleteing"); 
		
		users.forEach(u -> System.out.println(u.getFullName()));
		
		usersDAO.closeCurrentSessionWithTransaction();
	}
	
	@Test
	@DisplayName("when calling checkLogin() method")
	void testCheckLogin()
	{
		String email = "niritzhak10@gmail.com"; 
		String password = "superduper100"; 
		
		usersDAO.openCurrentSessionWithTransaction();
		
		boolean admin = usersDAO.checkLogin(email, password); 
		assertTrue(admin); 
		System.out.println("Is Admin? " + admin); 
		
		usersDAO.closeCurrentSessionWithTransaction();	
	}
	
	
	
	
}
