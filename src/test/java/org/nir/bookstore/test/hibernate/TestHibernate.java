package org.nir.bookstore.test.hibernate;

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

public class TestHibernate 
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
		
		System.out.println(">>closeSession():try to close the session..."); 
		session.close();
		System.out.println(">>closeSession():session closed!"); 
		System.out.println("\n***************************************************");

		}
		
		
	}
	
	@Test
	@Disabled
	@DisplayName("when trying to save a new Book")
	void testAddBook()
	{
		
		//session = sessionFactory.getCurrentSession();
		Users users = new Users("niritzha@sdf", "password", "Nir Ithzak");
		System.out.println(">>testAddBook():try to add a new book to db..");
		session.save(users);
		System.out.println(">>testAddBook():new Book Persisted");
		
		session.getTransaction().commit();
	}
	
	@Test
	@DisplayName("when reading users")
	void testGetAllUsers() 
	{
		
		Query<Users> query = session.createQuery("FROM Users");
		List<Users> users = query.getResultList();
		System.out.println(">>testGetAllUsers():users:");
		System.out.println(users);
		
	}

}
