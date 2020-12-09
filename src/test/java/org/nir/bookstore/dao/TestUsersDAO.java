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
	
	private static UsersDAO usersDAO; 
	
	
	@BeforeAll
	@DisplayName("when create UsersDAO object")
	public static void init()
	{
		 usersDAO = new UsersDAO(); 
	}
	
	
	
	/*************************************************************/
	
	
	@Test
	@DisplayName("when creating a new user by the userDao")
	void testCreateUsers()
	{
		Users user1 = new Users("YYY", "YYY", "YYY");
		usersDAO.openCurrentSessionWithTransaction();
		usersDAO.create(user1);
		usersDAO.closeCurrentSessionWithTransaction();
	}
	
	
	@Test
	@DisplayName("when calling get(id)")
	void testGet()
	{
		Integer id = 2; 
		
		usersDAO.openCurrentSession();
		Users user = usersDAO.get(id);
		System.out.println("User with id = " + user);
		usersDAO.closeCurrentSession();
	
	}
	
	@Test
	@DisplayName("when calling delete(id)")
	void testDelete()
	{
		Integer id = 14; 
		
		usersDAO.openCurrentSessionWithTransaction();
		usersDAO.delete(id);
		usersDAO.closeCurrentSessionWithTransaction();
		
		
	}
	
}
