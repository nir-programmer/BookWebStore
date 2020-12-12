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

public class TestCategory 
{
	private static SessionFactory sessionFactory; 
	private Session session; 
	private Transaction transaction; 
	
	@BeforeAll
	@DisplayName("when try to create a SessionFactory object")
	public static void init()
	{
		System.out.println(">>init():try to create SessionFactory...");
		 sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				 .addAnnotatedClass(Category.class)
				 .addAnnotatedClass(Book.class)
				 .addAnnotatedClass(Customer.class)
				 .addAnnotatedClass(OrderDetailId.class)
				 .addAnnotatedClass(OrderDetail.class)
				 .addAnnotatedClass(Review.class)
				 .addAnnotatedClass(Users.class)
				 .addAnnotatedClass(BookOrder.class)
				 .buildSessionFactory();
		 
		 System.out.println(">>init():SessionFactory Created!");
		 
		 System.out.println("\n\n"); 
	}
	
	@BeforeEach
	@DisplayName("when try to create a session and begin transaction")
	void createSessionBeginTransaction()
	{
		System.out.println("\n\n"); 
		
		System.out.println(">>createSessionBeginTransaction():try to create a new Session...");
		session = sessionFactory.openSession();
		System.out.println(">>createSessionBeginTransaction():Session Created!");
		
		System.out.println(">>createSessionBeginTransaction():try to begin a new Transuction...");
		transaction = session.beginTransaction();
		
		System.out.println(">>createSessionBeginTransaction():Transaction Started!");
		 
		System.out.println("\n\n"); 
	}
	
	@AfterEach
	@DisplayName("when trying to commit a transaction and close a session")
	void commitTransactionCloseSession()
	{
		System.out.println("\n\n"); 
		System.out.println(">>commitTransactionCloseSession():try to commit transaction...");
		transaction.commit();
		System.out.println(">>commitTransactionCloseSession():Transaction commited!");
		
		System.out.println(">>commitTransactionCloseSession():try to close the session...");
		session.close();
		System.out.println(">>commitTransactionCloseSession():Session Closed!");
		 
		System.out.println("\n\n"); 
	}
	
	
	@Test
	@DisplayName("when create a category")
	void testCreateCategory()
	{ 
		/*
		 * Session session = sessionFactory.openSession(); Transaction transaction =
		 * session.beginTransaction();
		 */
		
		Category category = new Category("Science Fiction"); 
		System.out.println(">>testCreateCategory():try to save the category...");
		
		session.save(category);
		
		
		/*
		 * transaction.commit(); session.close();
		 */
		System.out.println(">>testCreateCategory():Category Persisted!");
		
		
	}
	
	@Test
	@DisplayName("when try to get Category with id")
	void testGetCategoryFound()
	{
		Integer id = 1; 
		System.out.println(">>testCreateCategory():try to read a category with id = " + id);
		Category category =  session.get(Category.class, id);
		System.out.println(">>testCreateCategory(): The Category is: " + category);
		
	}
	
	@Test
	@DisplayName("when try to update a Category ")
	void testUpdateCategory()
	{
		
		Integer id = 1; 
		System.out.println(">>testUpdateCategory():Category with id = " + id +" BEFORE update:"); 
		Category category = session.get(Category.class, id);
		System.out.println(category); 
		
		
		category.setName("Comix");
		
		System.out.println(">>testUpdateCategory():Category with id = " + id +" AFTER update:"); 
		
		category = session.get(Category.class, id);
		System.out.println(category); 
		
	}
	
	@Test
	@DisplayName("when trying to delete Category")
	void testDeleteCategory()
	{
		Integer id = 1; 
		System.out.println(">>testDeleteCategory():Category with id = " + id +" BEFORE update:"); 
		Category category = session.get(Category.class, id);
		System.out.println(category); 
		
		session.delete(category); 
		
		System.out.println(">>testUpdateCategory():Category with id = " + id +" AFTER update:"); 
		
		category = session.get(Category.class, id);
		System.out.println(category); 
		
		
		
	}
}
