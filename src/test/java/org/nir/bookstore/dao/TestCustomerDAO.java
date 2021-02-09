package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.sound.sampled.ReverbType;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.Review;

public class TestCustomerDAO 
{
	private static  CustomerDAO customerDAO = null;

	@BeforeAll
	@DisplayName("when create CustomerDAO object")
	public static void init() {
		customerDAO = new CustomerDAO();
	}

	@BeforeEach
	@DisplayName("when calling openCurrentSessionWithTransaction()")
	void testOpenCurrentSessionWithTransaction() 
	{
		customerDAO.openCurrentSessionWithTransaction();
	}

	@AfterEach
	@DisplayName("when calling closeCurrentSessionWithTransaction()")
	void testcloseCurrentSessionWithTransaction() 
	{
		customerDAO.closeCurrentSessionWithTransaction();
	}
	
	/******************************************************************************
	 * 							TESTS
	 ******************************************************************************/
	//ok
	@Test
	@DisplayName("checking if CustomrDAO is not null")
	void testCustomerDAONotNull()
	{
			assertNotNull(customerDAO); 
			System.out.println(">>testCustomerDAONOTNull(): CustomerDAO not null"); 
			
			
	}
	
	//OK
	@Test
	@DisplayName("when trying to create a new Customer")
	void testcreateCustomer()
	{
		Customer customer1  = new Customer("a@a", "a", "Hatzhanchnim", "Hod Hasharon",
				"Israel", "0544678017", "12344", "03266900",
				new Date(), null, null);
		
		Customer customer2  = new Customer("b@b", "b", "Hatzhanchnim", "Hod Hasharon",
				"Israel", "0544678017", "12344", "03266900",
				new Date(), null, null);
				
		
		Customer c1 = customerDAO.create(customer1);
		Customer c2 = customerDAO.create(customer2);
		assertNotNull(c1); 
		assertNotNull(c2);
		
		
		
	}
	
	@Test
	@DisplayName("when trying to read exists customer")
	void testGetCustomerExist()
	{
		Integer id = 1; 
		Customer customer = customerDAO.get(id); 
		
		assertNotNull(customer); 
		System.out.println(">>testGetCustomerExists():The Customer: "); 
		
		System.out.println(customer.getFirstname()); 
		
	}
	
	//OK
	@Test
	@DisplayName("when trying to read non exists customer")
	void testGetCustomerNotExits()
	{
		Integer id = 2; 
		Customer customer = customerDAO.get(id); 
		
		assertNull(customer); 
		/*
		 * System.out.println(">>testGetCustomerNotExists():The Customer: ");
		 * 
		 * System.out.println(customer.getFullname());
		 */
		
	}
	
	
	//OK
	@Test
	@DisplayName("when trying to update a customer")
	void testUpdateCustomer()
	{
		int id = 1; 
		Customer customer = customerDAO.get(id);
		customer.setFullname("Shalom Ithzak");
		
		assertEquals("Shalom Ithzak", customer.getFirstname());
	}
	
	@Test
	@DisplayName("When delete a customer")
	void testDeleteCustomer()
	{	
		Integer id = 37 ;
		customerDAO.delete(id);
		
		Customer c = customerDAO.get(id); 
		assertNull(c); 
	}
	
	@Test
	@DisplayName("When delete a customer with reviews")
	void testDeleteCustomerWithReviews()
	{	
		Integer id ; 
		Customer customer; 
		Set<Review> reviews ; 
		
		id = 14; 
		customer = customerDAO.get(id);
		reviews  = customer.getReviews();
		
		if(!reviews.isEmpty())
			System.out.println(">>testDeleteCustomerWithReviews():There are reviews made by this customer!");
		else
		{
			customerDAO.delete(id);
			Customer c = customerDAO.get(id); 
			assertNull(c); 
			System.out.println(">>testDeleteCustomerWithReviews():Customer :" + customer.getFirstname()
			+ " has been deleted!");
			
			
		}
		
		
	}
	
	//ok
	@Test
	@DisplayName("when calling listAll()")
	void testListAll()
	{
		List<Customer> customers = customerDAO.listAll();
		
		assertEquals(1, customers.size());
		
		customers.forEach(c -> System.out.println(c.getFirstname()));
		
	}
	
	//ok
	@Test
	@DisplayName("when calling count()")
	void testCount()
	{
		long num = customerDAO.count();
		
		assertEquals(3, num);
		
	}
	
	@Test
	@DisplayName("when calling findByEmail() method")
	void testFindByEmailFound()
	{
		String email  = "nik10@.com"; 
		Customer customer = customerDAO.findByEmail(email);
		
		assertNotNull(customer); 
		
		System.out.println(">>testFindByEmailFound(): the customer full name: " + customer.getFirstname());
	
	}
	
	@Test
	@DisplayName("when calling checkLogin() method on exists customer")
	void testCheckLoginSuccess()
	{
		//Given 
		String email = "nik10@gmail.com"; 
		String password = "03266900"; 
		
		//when 
		Customer customer  = customerDAO.checkLogin(email, password);
		
		//then
		assertNotNull(customer);
		
		System.out.println("The customer fullname: " + customer.getFirstname()); 
		
	}
	
	@Test
	@DisplayName("when calling checkLogin() method on non exist customer")
	void testCheckLoginFail()
	{
		//Given 
		String email = "nik10@gmail.co"; 
		String password = "03266900"; 
		
		//when 
		Customer customer  = customerDAO.checkLogin(email, password);
		
		//then
		assertNull(customer);
		
		//System.out.println("The customer fullname: " + customer.getFullname()); 
		
	}
	
	@Test
	@DisplayName("when reading all reviews of a given customer")
	void testAllReviewsOfCustomer()
	{
		Integer customerId ; 
		Customer customer ; 
		Set<Review> reviews ; 
		
		customerId = 15; 
		customer = customerDAO.get(customerId); 
		assertNotNull(customer); 
		System.out.println(">>testAllReviewsOfCustomer(): Customer fullname: " + customer.getFirstname());
		
		reviews = customer.getReviews();
		assertNotNull(reviews);
		assertEquals(2, reviews.size());
		
		System.out.println(">>testAllReviewsOfCustomer(): reviews:");
		reviews.forEach(r -> System.out.println(r.getHeadline()));
		//get the reviews of the customer
		
	}

}
