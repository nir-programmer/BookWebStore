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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Users;

public class TestCategoryDAO {

	private static CategoryDAO categoryDAO = null;

	@BeforeAll
	@DisplayName("when create UsersDAO object")
	public static void init() {
		categoryDAO = new CategoryDAO();
	}
	
	
	@BeforeEach
	@DisplayName("when calling openCurrentSessionWithTransaction()")
	void testOpenCurrentSessionWithTransaction()
	{
		categoryDAO.openCurrentSessionWithTransaction();
	}
	
	@AfterEach
	@DisplayName("when calling closeCurrentSessionWithTransaction()")
	void testcloseCurrentSessionWithTransaction()
	{
		categoryDAO.closeCurrentSessionWithTransaction();
	}

	/*************************************************************/

	//OK
	@Test
	@DisplayName("when creating a new Category by the CategoryDAO")
	void testCreateCategoryWithCategoryDAO() 
	{
		Category category = new Category("Cookings");
		
		System.out.println(">>testCreateCategoryWithCategoryDAO():try to create a new Category named Cookings");
		categoryDAO.create(category);
		
		System.out.println(">>testCreateCategoryWithCategoryDAO():Category persisted!");

		
	}
	
	//OK
	@Test
	@DisplayName("when calling get() method")
	void testGetCategoryDao()
	{
		Integer id = 3 ; 
		Category category = categoryDAO.get(id);
		assertNotNull(category);
		System.out.println(">>testGetCategoryDao():Category with id = " + id); 
		System.out.println(category); 
	}
	
	@Test
	@DisplayName("when calling update() method")
	void testUpdateCategoryDao()
	{
		Integer id = 4; 
		
		System.out.println(">>testUpdateCategoryDao(): try to get Category with id = " + id); 
		Category category = categoryDAO.get(id);
		
		System.out.println(">>testUpdateCategoryDao(): Category BEFORE update:"); 
		System.out.println(category); 
		
		category.setName("JAVA");
		categoryDAO.update(category) ;
		
		 category = categoryDAO.get(id);
		
		 System.out.println(">>testUpdateCategoryDao(): Category AFTER update:"); 
		System.out.println(category); 
		
		
	}

	
	/*
	 * @Test
	 * 
	 * @DisplayName("when calling get(id)") void testGet() { Integer id = 2;
	 * 
	 * usersDAO.openCurrentSession(); Users user = usersDAO.get(id);
	 * System.out.println("User with id = " + user); usersDAO.closeCurrentSession();
	 * 
	 * assertNotNull(user);
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("when calling delete(id)") void testDelete() { Integer id = 4;
	 * 
	 * usersDAO.openCurrentSession(); Users user = usersDAO.get(4);
	 * System.out.println("testDelete():User with id = " + id +" BEFORE delete:");
	 * System.out.println(user); usersDAO.closeCurrentSession();
	 * 
	 * usersDAO.openCurrentSessionWithTransaction();
	 * assertThrows(EntityNotFoundException.class , () ->usersDAO.delete(4));
	 * usersDAO.closeCurrentSessionWithTransaction();
	 * 
	 * 
	 * usersDAO.openCurrentSession(); user = usersDAO.get(4);
	 * System.out.println("testDelete():User with id = " + id +" AFTER delete:");
	 * System.out.println(user); usersDAO.closeCurrentSession();
	 * 
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("when calling update(user)") void testUpdate() {
	 * usersDAO.openCurrentSession(); Integer id = 3; Users user = usersDAO.get(id);
	 * System.out.println(">>testUpdate():User with id = " + id +" Before update:");
	 * System.out.println(user); usersDAO.closeCurrentSession();
	 * 
	 * usersDAO.openCurrentSessionWithTransaction(); user.setEmail("NNN");
	 * user.setFullName("NNN"); user.setPassword("NNN"); user =
	 * usersDAO.update(user); System.out.println(">>testUpdate():User with id = " +
	 * id +" AFTER update:"); System.out.println(user);
	 * usersDAO.closeCurrentSessionWithTransaction();
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("when calling findAll() ") void testFindAll() {
	 * 
	 * usersDAO.openCurrentSession(); List<Users> users = usersDAO.listAll();
	 * System.out.println("List Of All users:");
	 * users.stream().forEach(System.out::println);
	 * 
	 * usersDAO.closeCurrentSession();
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("when calling count") void testCount() {
	 * 
	 * usersDAO.openCurrentSession(); long n= usersDAO.count();
	 * System.out.println("Number of users = " + n);
	 * 
	 * usersDAO.closeCurrentSession();
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("when calling findByEmail()") void testFindByEmail() { String
	 * email = "XXXX"; usersDAO.openCurrentSessionWithTransaction(); Users user =
	 * usersDAO.findByEmail(email);
	 * System.out.println("testFindByEmail():The user with email = " + email);
	 * System.out.println(user);
	 * 
	 * assertNotNull(user); usersDAO.closeCurrentSessionWithTransaction(); }
	 * 
	 * 
	 */
}
