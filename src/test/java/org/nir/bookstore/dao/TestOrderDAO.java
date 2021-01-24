package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.entities.OrderDetailId;
import org.nir.bookstore.entities.Review;

public class TestOrderDAO 
{
	
	private static OrderDAO orderDAO = null;

	@BeforeAll
	@DisplayName("when create OrderDAO object")
	public static void init() {
		orderDAO = new OrderDAO();
	}

	@BeforeEach
	@DisplayName("when calling openCurrentSessionWithTransaction()")
	void testOpenCurrentSessionWithTransaction() {
		orderDAO.openCurrentSessionWithTransaction();
	}

	@AfterEach
	@DisplayName("when calling closeCurrentSessionWithTransaction()")
	void testcloseCurrentSessionWithTransaction() {
		orderDAO.closeCurrentSessionWithTransaction();
	}
	
	/**********************************************************
	 * 					TESTS
	 * @throws ParseException 
	 * @throws IOException 
	 * ************************************************************/
	
	//OK!!!!!!
	@Test
	@DisplayName("when create a BookOrder by BookDAO")
	public void testCreateBookOrder() 
	{
		Book book1 ,book2 , book3 , book4; 
		Customer customer ; 
		BookOrder bookOrder;
		OrderDetail oredDetail1, orderDetail2, orderDetail3;
		OrderDetailId orderDetailId1, orderDetailId2, orderDetailId3;
		
		//store information about the detail order object (such as books , quantites
		Set<OrderDetail> details ; 
		
		/**************************************************************
		 * 						INITIALIZTION
		 *************************************************************/
		//Create a customer with an existing id in the db
		customer  = new Customer();
		customer.setCustomerId(15);
		
		//create Books  with an existing id's in the db
		 book1 = new Book(40); 
		 book2 = new Book(43); 
		 book3 = new Book(45); 
		 book4 = new Book(46); 
		 
		
		 //create the OrderDetailsId and set it's quanitity and subtotal
		 orderDetailId1 = new OrderDetailId();
		 orderDetailId1.setQuantity(3);
		 //In the db the book with id 40 has a price of 111 !
		 orderDetailId1.setSubtotal(333);
		
		 //Create the Set<OrderDetail> 
		 details = new HashSet<>(); 
		
		 //Create the OrderDetail object and set it's values
		OrderDetail orderDetail1 = new OrderDetail() ;
		orderDetail1.setId(orderDetailId1);
		
		
		
		
		details.add(orderDetail1);
		
		//Create the BookOrder instance with all the fields
		bookOrder = new BookOrder(); 
		bookOrder.setCustomer(customer);
		bookOrder.setRecipientName("Nir");
		bookOrder.setRecipientPhone("0544788017");
		bookOrder.setShippingAddress("Hod Hasharon");
		bookOrder.setOrderDetails(details); 
		
		
		//Add the BookOrder instance to the db
		BookOrder persistBookOrder = orderDAO.create(bookOrder);
		
		assertTrue(persistBookOrder.getOrderId() > 0); 
		
		
		
		
		
		//customer = new Customer("x@x.gmail.com","x", "x" ,"x" , "x" , "x" , "x" ,"x",  new Date());
		
		
		
		/*
		 * bookOrder = new BookOrder(customer, new Date() ,"x", "x" , "x" ,"x" , 33 ,
		 * "x" ,);
		 * 
		 * BookOrder res = orderDAO.create(bookOrder);
		 * 
		 * assertTrue(res.getOrderId() > 0);
		 */
		
	}
	

}