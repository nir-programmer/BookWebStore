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
	
	@BeforeAll
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
	}
	
	@Test
	@DisplayName("when create a category")
	void testCreateCategory()
	{ 
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		
		Category category = new Category("Science Fiction"); 
		System.out.println(">>testCreateCategory():try to save the category...");
		
		session.save(category);
		
		
		transaction.commit();
		session.close();
		System.out.println(">>testCreateCategory():Category Persisted!");
		
		/*
		org.hibernate.AnnotationException: 
		@OneToOne or @ManyToOne on org.nir.bookstore.entities.OrderDetail.bookOrder references an unknown entity:
		 org.nir.bookstore.entities.BookOrder
		*/
		
		
	}
}
