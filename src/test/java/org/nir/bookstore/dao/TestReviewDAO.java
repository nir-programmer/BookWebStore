package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.Review;

public class TestReviewDAO 
{
	private static ReviewDAO reviewDAO;
	private static SessionFactory sessionFactory ;
	/*
	 * private Session currentSession ; private Transaction currentTransaction;
	 */
	
	@BeforeAll
	static void init()
	{
		reviewDAO = new ReviewDAO();
		sessionFactory = reviewDAO.getSessionFactory();
		System.out.println(">>TestReviewDAO.init():SessionFactory Created!");
	}
	
	@BeforeEach
	void openSessionGetTransaction()
	{
		reviewDAO.openCurrentSessionWithTransaction();
		/*
		 * currentSession = reviewDAO.getCurrentSession(); currentTransaction =
		 * reviewDAO.getCurrentTransaction();
		 */
		System.out.println(">>TestReviewDAO.openSessionGetTransaction():session and transaction created!"); 
	}
	
	@AfterEach
	void closeSessionCommitTransaction()
	{
		reviewDAO.closeCurrentSessionWithTransaction();
		System.out.println(">>TestReviewDAO.closeSessionCommitTransaction():session and transaction closed!"); 
		
	}
	
	/*********************************************************************************
	 * 									TESTS
	 * *****************************************************************************
	 */
	
	//test get Review by id - OK
	@Test
	@DisplayName("when calling get()")
	void testGetReviewFound()
	{
		Integer id = 2; 
		Review review  = reviewDAO.get(id);
		
		assertNotNull(review); 
		//assertEquals("Amazing", review.getHeadline());
		System.out.println(">>testGetReview():Review's Headline: " + review.getHeadline());
	}
	
	
	//OK
	@Test
	@DisplayName("when calling update()")
	void testCreateReview()
	{
		//Create Book and Customer
		Book book = new Book(); 
		book.setBookId(46);
		System.out.println(">>testCreateReview():The author of the book from db: " + book.getAuthor());
		
		Customer customer = new Customer(); 
		customer.setCustomerId(15);
	
		//Create the Review and set values
		Review review = new Review();
		review.setHeadline("PERFECT!");
		review.setRating(5);
		review.setComment("Amazing book . much better than others!");
		review.setCustomer(customer);
		review.setBook(book);
		
		Review savedReview = reviewDAO.create(review);
		
		assertNotNull(savedReview);
		assertTrue(savedReview.getReviewId() > 0);
		System.out.println(">>testCreateReview():a new review with head line = " + savedReview.getHeadline() +
				" has been save to the data base"); 
	
		
	}
	
	//OK
	@Test
	@DisplayName("when reading customer's fullname and books' title for a given review ")
	void testReadCustomerAndBookOfReview()
	{
		Integer reviewId = 10; 
		
		Review review = reviewDAO.get(reviewId);
		
		Book book = review.getBook();
		
		Customer customer = review.getCustomer();
		
		assertNotNull(book);
		assertNotNull(customer);
		
		
		System.out.println(">> testReadCustomerAndBookOfReview():Customer's fullname : " + customer.getFullname() +
			"Book's title : " + book.getTitle()	); 
		
	}
	//OK
	@Test
	@DisplayName("when calling update() method")
	void testUpdate()
	{
		//read a review form db into a Review object
		Integer reviewId= 15; 
		Review review = reviewDAO.get(reviewId);
		System.out.println(">>testUpdate():Head line before updateing: " + review.getHeadline());
	
		//update the Review object by settin a new Head line
		review.setHeadline("This Book is AWSOME");
		
		//call update() on the ReviewDAO
		Review updatedReview = reviewDAO.update(review);
		
		assertNotNull(updatedReview); 
		assertEquals("This Book is AWSOME", updatedReview.getHeadline());
	}
	
	//OK
	@Test
	@DisplayName("when calling listAll() method")
	void testListAll()
	{
		
		List<Review> reviews  = reviewDAO.findWithNamedQuery("Review.listAll"); 
		
		assertTrue(reviews.size() > 0);
		
		reviews.forEach(c -> System.out.println(c.getReviewId() + " - " + c.getBook().getTitle()
				+ " - " + c.getCustomer().getFullname() + " - " + 
				c.getReviewTime()));

	}
	
	
	//OK
	@Test
	@DisplayName("when calling count()")
	void testCount()
	{
		long numberOfReviews = reviewDAO.count();
		
		assertEquals(5, numberOfReviews);
		
		System.out.println(">>testCount():nember of review : " + numberOfReviews); 
	}
	
	
	@Test
	@DisplayName("when calling delete() method")
	void testDelete()
	{
		Integer reviewId = 13; 
		
		reviewDAO.delete(reviewId);
		
		Review review = reviewDAO.get(reviewId);
		
		assertNull(review); 
		
	}
	
	@Test
	@DisplayName("when calling delete() method on non exist Review")
	void testDeleteNotFound()
	{
		Integer reviewId = 3; 
		
		Review review = reviewDAO.get(reviewId);
		assertNull(review);
		
		if(review == null)
			System.out.println(">>testDeleteNotFound(): there is no review with id = " + reviewId); 
		
		else
		{
		reviewDAO.delete(reviewId);
		System.out.println(">>testDeleteNotFound(): Review with  id = " + reviewId  + " has been deleted!"); 
		}
		
		
	}
	
	//OK
	@Test
	@DisplayName("when calling countByBook() method ")
	void testCountByBook()
	{
		Integer bookId = 43; 
		
		long numberOfReviews = reviewDAO.countByBook(bookId);
		
		assertTrue(numberOfReviews > 0); 
		
		System.out.println(">>testCountByBook(): number of reviews on book with id " + bookId + " is" + numberOfReviews);
		
	}
	
	@Test
	@DisplayName("when calling countByCustomer() method")
	public void testCoutntByCusgtomer()
	{
		Integer customerId = 15; 
		long numberOfReviews = reviewDAO.countByCustomer(customerId); 
		
		assertTrue(numberOfReviews > 0);
		System.out.println(">>testCountByBook(): number of reviews made by customer"
				+ " with id " + customerId + " is" + numberOfReviews);
	}


	@Test
	@DisplayName("When calling findByCustomerAndBook() method")
	void testFindByCustomerAndBook()
	{
		Integer customerId = 15;
		Integer bookId = 43; 
		
		Review review = reviewDAO.findByCustomerAndBook(customerId, bookId); 
		
		assertNotNull(review); 
		
		System.out.println(">testFindByCustomerAndBook(): review Id is " + review.getReviewId());
		
		
		
		
	}
}
