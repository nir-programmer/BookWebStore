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
	public static void init()
	{
		orderDAO = new OrderDAO();
	}

	@BeforeEach
	@DisplayName("when calling openCurrentSessionWithTransaction()")
	void testOpenCurrentSessionWithTransaction()
	{
		orderDAO.openCurrentSessionWithTransaction();
	}

	@AfterEach
	@DisplayName("when calling closeCurrentSessionWithTransaction()")
	void testcloseCurrentSessionWithTransaction()
	{
		orderDAO.closeCurrentSessionWithTransaction();
	}

	/**********************************************************
	 * TESTS
	 * 
	 * @throws ParseException
	 * @throws IOException
	 ************************************************************/

	// OK!!!!!!
	@Test
	@DisplayName("when create a BookOrder by BookDAO")
	public void testCreateBookOrder()
	{
		Customer customer;
		BookOrder bookOrder;
		OrderDetail orderDetail;
		//OrderDetailId orderDetailId;
		Set<OrderDetail> orderDetails;
		Book book;

		// create the customer with id = 11
		customer = new Customer();
		customer.setCustomerId(11);

		// create the BookOrder
		bookOrder = new BookOrder();

		// add the customer and other fields to the bookOrder
		bookOrder.setCustomer(customer);
		bookOrder.setRecipientName("Nir Ithzak");
		bookOrder.setRecipientPhone("0544678017");
		bookOrder.setShippingAddress("Hod Hasharon, Hatzanchanim 13, 3");

		// Create the Book with existing id
		book = new Book(32);

		// Create set of orderDetails
		orderDetails = new HashSet<OrderDetail>();

		// create a new OrderDetail
		orderDetail = new OrderDetail();
		
		//MANY TO MANY: add the book and the bookOrder references to the 
		//Join table(orderDetail) I have to do this!he got EXCEPTION
		orderDetail.setBook(book);
		orderDetail.setBookOrder(bookOrder);
		//set the quantity and subtotal vlues
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(68f);
		
		//add the orderDetail to the set
		orderDetails.add(orderDetail);
		
		//add the Set to the BookOrder
		bookOrder.setOrderDetails(orderDetails);
		
		//persist the BookOrder in db and save the returned value
		
		BookOrder savedOrder = orderDAO.create(bookOrder);
		
		assertTrue(savedOrder != null && savedOrder.getOrderDetails().size() > 0);
		
	}
	
	@Test
	@DisplayName("when create a BookOrder With more than one OrderDetails")
	public void testCreateBookOrderWithManyOrderDetails()
	{
		Customer customer1;
		BookOrder bookOrder;
		OrderDetail orderDetail;
		//OrderDetailId orderDetailId;
		Set<OrderDetail> orderDetails;
		Book book;

		// create the customer with id = 11
		customer1 = new Customer();
		customer1.setCustomerId(11);

		// create the BookOrder
		bookOrder = new BookOrder();

		// add the customer and other fields to the bookOrder
		bookOrder.setCustomer(customer1);
		bookOrder.setRecipientName("Nir Ithzak");
		bookOrder.setRecipientPhone("0544678017");
		bookOrder.setShippingAddress("Hod Hasharon, Hatzanchanim 13, 3");

		// Create the Book with existing id
		book = new Book(32);

		// Create set of orderDetails
		orderDetails = new HashSet<OrderDetail>();

		// create a new OrderDetail
		orderDetail = new OrderDetail();
		
		//MANY TO MANY: add the book and the bookOrder references to the 
		//Join table(orderDetail) I have to do this!he got EXCEPTION
		orderDetail.setBook(book);
		orderDetail.setBookOrder(bookOrder);
		//set the quantity and subtotal vlues
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(68f);
		
		//add the orderDetail to the set
		orderDetails.add(orderDetail);
		
		//add the Set to the BookOrder
		bookOrder.setOrderDetails(orderDetails);
		
		//persist the BookOrder in db and save the returned value
		
		BookOrder savedOrder = orderDAO.create(bookOrder);
		
		assertTrue(savedOrder != null && savedOrder.getOrderDetails().size() > 0);
		
	}

}