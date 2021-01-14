package org.nir.bookstore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.archive.scan.spi.ClassDescriptor.Categorization;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.entities.OrderDetailId;
import org.nir.bookstore.entities.Review;
import org.nir.bookstore.entities.Users;

public class TestUsers extends TestBaseEntity
{
	@BeforeAll
	@DisplayName("when try to create a SessionFactory object")
	public static void init()
	{
		TestBaseEntity.init();
	}
	
	@BeforeEach
	@DisplayName("when try to create a session and begin transaction")
	public void createSessionBeginTransaction()
	{
		super.createSessionBeginTransaction();
	}
	
	@AfterEach
	@DisplayName("when trying to commit a transaction and close a session")
	public void commitTransactionCloseSession()
	{
		super.commitTransactionCloseSession();
	}
	
	/*********************************************************************
	 * 								TESTS
	 *******************************************************************/
	@Test
	//@Disabled
	@DisplayName("when trying to save a new Book")
	void testAddUsers()
	{
		Users users = new Users("niritzha@sdf", "password", "NironXXXX");
		System.out.println(">>testAddUsers():try to add a new Users named NironXXXX to db..");
		session.save(users);
		System.out.println(">>testAddUsers()):new Users named NironXXXX Persisted");
			
	}
	
	@Test
	@DisplayName("when try to get an existing user by id") 
	void testGetUserByIdFound()
	{
		Integer id = 1; 
		Users user = session.get(Users.class, id);
		
		System.out.println("testGetUserByIdFound():the user with id = " + id );
		System.out.println(user); 
	}
	
	@Test
	@DisplayName("when try to get a non existing user by id")
	void testGetUserByIdNotFound()
	{
		Integer id = 2; 
		Users user = session.get(Users.class, id);
		
		System.out.println("testGetUserByIdNotFound():the user with id = " + id );
		System.out.println(user); 
	}
	@Test
	@DisplayName("when try to update a user")
	void testUpdateUser()
	{
		Users user = new Users(1, "niritzhak10@outlook.com", "1234", "Nir Itzhak");
		
		session.saveOrUpdate(user) ;
		
		user = session.get(Users.class, 1); 
		assertEquals("niritzhak10@outlook.com", user.getEmail());
	}
	
	@Test
	@DisplayName("when try to delete a user")
	void testDeleteUser()
	{
		Users user = session.get(Users.class, 1); 
		
		session.delete(user);
		
		user = session.get(Users.class, 1); 
		assertNull(user); 
		/*
		 * javax.persistence.PersistenceException: org.hibernate.PropertyValueException:
		 * not-null property references a null or transient value :
		 * org.nir.bookstore.entities.Users.email
		 */
	}
	
	@Test
	//@Disabled
	
	@DisplayName("when reading users")
	void testGetAllUsers() 
	{
		
		Query<Users> query = session.createQuery("FROM Users");
		List<Users> users = query.getResultList();
		System.out.println(">>testGetAllUsers():users:");
		System.out.println(users);	
	}
	
	
	
	
	

}
