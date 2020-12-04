package org.nir.bookstore.entities;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
	private static SessionFactory sessionFactory ; 
	private static Session session; 
	@BeforeAll
	@DisplayName("when create session factory")
	static void init()
	{
		System.out.println(">>init():try to create session factory..");
		sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Book.class)
				.addAnnotatedClass(BookOrder.class)
				.addAnnotatedClass(Category.class)
				.addAnnotatedClass(OrderDetail.class)
				.addAnnotatedClass(OrderDetailId.class)
				.addAnnotatedClass(Review.class)
				.addAnnotatedClass(Users.class)
				.addAnnotatedClass(Customer.class)
				.buildSessionFactory();
		
	
	}
	
	@AfterAll
	@DisplayName("when try close the session factory")
	static void tearDown()
	{
		if(sessionFactory != null)
		{
		System.out.println(">>tearDown():try to close the session factory..."); 
		sessionFactory.close();
		System.out.println(">>teadDown():session factory  closed!"); 
		System.out.println("\n***************************************************");
		}
		
		
	}
	@BeforeEach
	@DisplayName(">>createSession():try to create a new Session")
	void createSession()
	{
		System.out.println(">>createSession():try to create a new Session and transaction"); 
		session = sessionFactory.getCurrentSession();
		session.getTransaction();
		
		session.beginTransaction();
		System.out.println(">>createSession():Session Created!");
		
		
		
		
		System.out.println("\n***************************************************");

	}
	
	@AfterEach
	@DisplayName("when try close the session")
	void closeSession()
	{
		if(session != null)
		{
			System.out.println(">>closeSession():try to commit transaction..."); 
		session.getTransaction().commit();
		System.out.println(">>closeSession():transaction commited!"); 
		
		System.out.println(">>closeSession():try to close the session..."); 
		session.close();
		System.out.println(">>closeSession():session closed!"); 
		System.out.println("\n***************************************************");

		}
		
		
	}
	
	
	/*
	 * **********************************TESTS***********************************
	 * */
	
	
	@Test
	@Disabled
	@DisplayName("when trying to add a new category to db")
	void testAddNewCategory()
	{
			System.out.println(">>testAddNewCategory():try to add 2 categories : Comix and Science");
			Category category1 = new Category("Comix");
			Category category2 = new Category("Science");
			
			session.save(category1);
			session.save(category2);
			
			
			System.out.println(">>testAddNewCategory():Comix and Science presisted!!!");
	}
	
	@Test
	//@Disabled
	@DisplayName("when reading categories")
	void testGetAllCategories() 
	{
		
		Query<Category> query = session.createQuery("FROM Category");
		List<Category> categories = query.getResultList();
		System.out.println(">>testGetAllCategories(): categories:");
		System.out.println(categories);
		
	}

}
