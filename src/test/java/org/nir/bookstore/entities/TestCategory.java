package org.nir.bookstore.entities;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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

public class TestCategory extends TestBaseEntity
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
	@DisplayName("when create a category")
	void testCreateCategory()
	{ 
		
		Category category = new Category("Science Fiction"); 
		System.out.println(">>testCreateCategory():try to save the category...");
		
		session.save(category);
		
		
		
		System.out.println(">>testCreateCategory():Category Persisted!");
		
		
	}
	
	@Test
	@DisplayName("when try to get Category with id")
	void testGetCategoryFound()
	{
		Integer id = 2; 
		System.out.println(">>testCreateCategory():try to read a category with id = " + id);
		Category category =  session.get(Category.class, id);
		System.out.println(">>testCreateCategory(): The Category is: " + category);
		
	}
	
	@Test
	@DisplayName("when try to update a Category ")
	void testUpdateCategory()
	{
		
		Integer id = 2; 
		System.out.println(">>testUpdateCategory():Category with id = " + id +" BEFORE update:"); 
		Category category = session.get(Category.class, id);
		System.out.println(category); 
		
		
		category.setName("Comix");
		
		System.out.println(">>testUpdateCategory():Category with id = " + id +" AFTER update:"); 
		
		category = session.get(Category.class, id);
		System.out.println(category); 
		
	}
	
	@Test
	@Disabled
	@DisplayName("when trying to delete Category")
	void testDeleteCategory()
	{
		Integer id = 2; 
		System.out.println(">>testDeleteCategory():Category with id = " + id +" BEFORE update:"); 
		Category category = session.get(Category.class, id);
		System.out.println(category); 
		
		session.delete(category); 
		
		System.out.println(">>testUpdateCategory():Category with id = " + id +" AFTER update:"); 
		
		category = session.get(Category.class, id);
		System.out.println(category); 
		
		
		
	}
}
