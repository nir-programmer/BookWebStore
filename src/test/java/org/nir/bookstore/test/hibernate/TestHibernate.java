package org.nir.bookstore.test.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.archive.scan.spi.ClassDescriptor.Categorization;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Category;
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
				.buildSessionFactory();
		
		session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		
		
		
		session.getTransaction().commit();
	}
	
	@Test
	void f()
	{
		
	}

}
