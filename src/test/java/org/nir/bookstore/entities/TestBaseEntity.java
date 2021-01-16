package org.nir.bookstore.entities;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class TestBaseEntity 
{
	protected static SessionFactory sessionFactory; 
	protected static  Session session; 
	protected static Transaction transaction; 
	
	protected static void init()
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
	
	protected void createSessionBeginTransaction()
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
	
	protected void commitTransactionCloseSession()
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
	
}
