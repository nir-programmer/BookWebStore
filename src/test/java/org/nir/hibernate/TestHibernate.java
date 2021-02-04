package org.nir.hibernate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.entities.Review;
import org.nir.bookstore.entities.Users;

public class TestHibernate
{
	private static SessionFactory sessionFactory;
	private static Session session;

	@BeforeAll
	public static void init()
	{
		// no addAnnotated class??

		System.out.println(">>TestHibernate.init(): creating SessionFactory...");
		sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Category.class)
				.addAnnotatedClass(Book.class).addAnnotatedClass(BookOrder.class).addAnnotatedClass(Review.class)
				.addAnnotatedClass(OrderDetail.class).addAnnotatedClass(Customer.class).buildSessionFactory();

		System.out.println(">>TestHibernate.init(): SessionFactore Created!");

	}

	@AfterAll
	public static void tearDown()
	{

		boolean isSessionFactoryOpen = sessionFactory.isOpen();
		System.out.println(">>TestHibernate.tearDown(): Session Factory is open: " + isSessionFactoryOpen);

		if (isSessionFactoryOpen) {

			System.out.println(">>TestHibernate.tearDown(): Trying to close sessionFactory..");
			sessionFactory.close();
			System.out.println(">>TestHibernate.tearDown(): sessionFactory has been closed successfully!");
		} else
			System.out.println(">>TestHibernate.tearDown(): Session Factory is closed already!");

	}

	@BeforeEach
	public void openSession()
	{
		session = sessionFactory.openSession();
		boolean isSessionOpen = session.isOpen();
		session.beginTransaction();

		if (isSessionOpen)
			System.out.println(">>openSession:The Session is opened already! ");
		else {
			System.out.println(">>openSession:Trying to  open the session...");
			session = sessionFactory.openSession();
			System.out.println(">>openSession:The Session opened succesffully! ");

			System.out.println(">>openSession:Trying to begin transaction...");
			session.beginTransaction();
			System.out.println(">>openSession:Transaction begun succesffully!");
		}

	}

	@AfterEach
	public void closeSession()
	{
		boolean isSessionOpen = session.isOpen();

		if (!isSessionOpen)
			System.out.println(">>closeSession:The Session is closed already! ");
		else {

			System.out.println(">>closeSession:Trying commit transation...");
			session.getTransaction().commit();
			System.out.println(">>closeSession:The Transaction has been commited successfully! ");

			System.out.println(">>closeSession:Trying to  close the session...");
			session.close();
			System.out.println(">>closeSession:The Session closed succesffully! ");

		}
	}

	////////////////////////////////////////////////////////////////////////////////
	// OK

	@Test
	public void test()
	{
		System.out.println(">>test(): Hello");
	}

	@Test
	public void testCloseSessionFactory()
	{
		/*
		 * boolean isSessionFactoryOpen = sessionFactory.isOpen(); System.out.
		 * println(">>testCLoseSessionFactory:SessionFactory is opened already ? " +
		 * isSessionFactoryOpen);
		 * 
		 * System.out.
		 * println(">>testCLoseSessionFactory:Trying to close the sessionFactory... ");
		 * sessionFactory.close();
		 * 
		 * isSessionFactoryOpen = sessionFactory.isOpen();
		 * System.out.println(">>testCLoseSessionFactory:SessionFactory is open ? " +
		 * isSessionFactoryOpen);
		 */
		System.out.println("HELLO");
	}

	@Test
	public void testBestSellerBook()
	{
		//Reading the book's prices from the data base
		Query<Book> query = session.createQuery("FROM Book b");
		
		List<Book> books = query.getResultList();
				
		assertNotNull(books);
		
		books.forEach(b -> System.out.println("Book ID: " + b.getBookId() + ", price = " + b.getPrice()));
		
		
		
		  //Creating the CriteriaBuilder from the session object CriteriaBuilder 
		CriteriaBuilder  cb =   session.getCriteriaBuilder();
		  
		  //Creating the CriteriaQuery of and 'Object' type to represent result of this
		  CriteriaQuery<Object> crt1 = cb.createQuery(Object.class);
		  
		  //CHECK .. 
		  Root<Book> root1 = (Root<Book>) crt1.from(Book.class);
		  
		  
		  //Finding the maximum price from a column mapping the property "price"
		  crt1.select(cb.max(root1.get("price")));
		 
		  Query query1 = session.createQuery(crt1);
		  
		  Object maxPrice = query1.getSingleResult();
		  
		  System.out.println("Maximum Price: " + maxPrice) ;
		
	}

}
