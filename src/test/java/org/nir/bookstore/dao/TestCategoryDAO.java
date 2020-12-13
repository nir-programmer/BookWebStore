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
	void testOpenCurrentSessionWithTransaction() {
		categoryDAO.openCurrentSessionWithTransaction();
	}

	@AfterEach
	@DisplayName("when calling closeCurrentSessionWithTransaction()")
	void testcloseCurrentSessionWithTransaction() {
		categoryDAO.closeCurrentSessionWithTransaction();
	}

	/*************************************************************/

	// OK
	@Test
	@DisplayName("when creating a new Category by the CategoryDAO")
	void testCreateCategoryWithCategoryDAO() {
		Category category = new Category("Cookings");

		System.out.println(">>testCreateCategoryWithCategoryDAO():try to create a new Category named Cookings");
		categoryDAO.create(category);

		System.out.println(">>testCreateCategoryWithCategoryDAO():Category persisted!");

	}

	// OK
	@Test
	@DisplayName("when calling get() method")
	void testGetCategoryDao() {
		// categoryDAO.openCurrentSessionWithTransaction();
		Integer id = 5;
		Category category = categoryDAO.get(id);
		assertNotNull(category);
		System.out.println(">>testGetCategoryDao():Category with id = " + id);
		System.out.println(category);
		// categoryDAO.closeCurrentSessionWithTransaction();
	}

	@Test
	@DisplayName("when calling update() method")
	void testUpdateCategoryDao() {
		/* categoryDAO.openCurrentSessionWithTransaction(); */
		Integer id = 5;
		Category category = categoryDAO.get(id);
		System.out.println(">>testUpdateCategoryDao():category BEFORE UDPATE:");
		System.out.println(category);
		/* categoryDAO.closeCurrentSession(); */

		category.setName("ZZZBBBBBB");

		category = categoryDAO.update(category);
		System.out.println(">>testUpdate():Category with id = " + id + " AFTER update:");
		System.out.println(category);
	}

	@Test
	@Disabled
	@DisplayName("when calling delete() metod")
	void testDeleteCategory()
	{
		Integer id = 4; 
		Category category = categoryDAO.get(id);
		
		System.out.println(">>testDeleteCategory():category with id = " + id); 
		System.out.println(category);
		
		categoryDAO.delete(id);
		
		category = categoryDAO.get(id);
		
		assertNull(category); 
		
		System.out.println(">>testDeleteCategory():category with id = " + id); 
		System.out.println(category);
		
	}
	
	@Test
	@DisplayName("when calling listAll()")
	void testListAll()
	{
		List<Category> categories = categoryDAO.listAll();
		System.out.println(">>testListAll():List of categories:"); 
		categories.forEach(c -> System.out.println(c.getName()));
	}
	
	@Test
	@DisplayName("when calling count()")
	void testCount()
	{
		long n = categoryDAO.count();
		System.out.println("Number of categories = " + n) ;
		
		//assertEquals(4, n);
	}
	
	@Test
	@DisplayName("when calling findByName()")
	void testFindByNameFound()
	{
		String name = "Cookings";
		Category category = categoryDAO.findByName(name); 
		
		assertNotNull(category);
		System.out.println(">>testFindByName(): Category with name = " + name); 
		System.out.println(category); 
	}
	
	@Test
	@DisplayName("when calling findByName() with non existing category")
	void testFindByNameNotFound()
	{
		String name = "Cookin";
		Category category = categoryDAO.findByName(name); 
		
		assertNull(category);
		System.out.println(">>testFindByName(): Category with name = " + name); 
		System.out.println(category); 
	}
	
	

}
