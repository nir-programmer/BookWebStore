package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.service.BookService;

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

	// With PayPal -OK!
	@Test
	@DisplayName("when create a BookOrder by BookDAO After Refactoring PayPal")
	public void testCreateBookOrder()
	{
		Customer customer;
		BookOrder bookOrder;
		OrderDetail orderDetail;

		// OrderDetailId orderDetailId;
		Set<OrderDetail> orderDetails;
		Book book;

		// create the customer with id = 12
		customer = new Customer();
		Integer customerId = 12;
		customer.setCustomerId(customerId);
		System.out.println(">>testCreateBookOrder():name of customer with id = " + customerId);

		// create the BookOrder
		bookOrder = new BookOrder();

		// Set Order's Overview and Order's Recipient Information -OK
		bookOrder.setCustomer(customer);
		bookOrder.setFirstname("Nir");
		bookOrder.setLastname("b");
		bookOrder.setPhone("0544678017");
		bookOrder.setAddressLine1("123 , South Street");
		bookOrder.setAddressLine2("Clifton Park");
		bookOrder.setCity("New York");
		bookOrder.setState("New York");
		bookOrder.setCountry("US");
		bookOrder.setPaymentMethod("paypal");
		bookOrder.setZipcode("123456");
		// this is the coutnry code . not country name
		bookOrder.setCountry("US");

		// Create the Book with existing id : 33(java 8)
		book = new Book(33);

		orderDetails = new HashSet<OrderDetail>();

		// create a new OrderDetail
		orderDetail = new OrderDetail();

		/* orderDetail2 = new OrderDetail(); */
		// MANY TO MANY: add the book and the bookOrder references to the
		// Join table(orderDetail) I have to do this!he got EXCEPTION
		orderDetail.setBook(book);
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(178.f);
		orderDetail.setBookOrder(bookOrder);

		orderDetails.add(orderDetail);

		// Transaction information
		bookOrder.setOrderDetails(orderDetails);
		bookOrder.setTax(17.8f);
		bookOrder.setShippingFee(2.0f);
		bookOrder.setTotal(178f + 17.8f + 2.0f);

		BookOrder savedOrder = orderDAO.create(bookOrder);

		System.out.println("\n\n>>The id of the  savedorder is: " + savedOrder.getOrderId());

		assertTrue(savedOrder.getOrderId() > 0);
		// loop over the set of OrderDetails
		System.out.println("\n\n\n\n");
		savedOrder.getOrderDetails().forEach(
				o -> System.out.println("oid = " + o.getBookOrder().getOrderId() + " , bid = " + o.getBook().getBookId()
						+ " , quantity = " + o.getQuantity() + " ,subtotal = " + o.getSubtotal()));

	}

	@Test
	@DisplayName("when create a single BookOrder WITH 2 OrderDetail objects")
	public void testCreateBookOrderWithManyOrderDetails()
	{
		Customer customer1;
		BookOrder bookOrder;
		OrderDetail orderDetail1;
		OrderDetail orderDetail2;
		// OrderDetailId orderDetailId;
		Set<OrderDetail> orderDetails;
		Book book1;
		Book book2;
		// create the customer with id = 12(a)
		customer1 = new Customer();
		customer1.setCustomerId(12);

		// create the BookOrder
		bookOrder = new BookOrder();

		// add the customer and other fields to the bookOrder
		bookOrder.setCustomer(customer1);
		bookOrder.setFirstname("Nir");
		bookOrder.setLastname("Itzhak");
		bookOrder.setPhone("0544678017");
		bookOrder.setAddressLine1("123 , South Street");
		bookOrder.setAddressLine2("Clifton Park");
		bookOrder.setCity("New York");
		bookOrder.setState("New York");
		bookOrder.setCountry("US");
		bookOrder.setPaymentMethod("paypal");
		bookOrder.setZipcode("123456");

		// Create new Books with existing id's
		book1 = new Book(32);
		book2 = new Book(33);

		// Create set of orderDetails
		orderDetails = new HashSet<OrderDetail>();

		// create The first OrderDetail with book1 and bookOrder
		orderDetail1 = new OrderDetail();

		// MANY TO MANY: add the book and the bookOrder references to the
		// Join table(orderDetail) I have to do this!he got EXCEPTION
		orderDetail1.setBook(book1);
		orderDetail1.setBookOrder(bookOrder);
		// set the quantity and subtotal vlues
		orderDetail1.setQuantity(2);
		orderDetail1.setSubtotal(78f);

		// Transaction Information
		// about 10% of the subtotal
		bookOrder.setTax(7.8f);
		bookOrder.setShippingFee(2.0f);
		// total = subtotal + tax + shipping fee
		bookOrder.setTotal(78f + 7.8f + 2.0f);

		// Create the second orderDetail with book2 and the bookOrder
		orderDetail2 = new OrderDetail();
		orderDetail2.setBook(book2);
		orderDetail2.setBookOrder(bookOrder);
		orderDetail2.setQuantity(3);
		orderDetail2.setSubtotal(110.16f);

		// add orderDetail1 and orderDetail2 to the set
		orderDetails.add(orderDetail1);
		orderDetails.add(orderDetail2);

		// add the Set to the BookOrder
		bookOrder.setOrderDetails(orderDetails);

		// persist the BookOrder in db and save the returned value
		BookOrder savedOrder = orderDAO.create(bookOrder);

		assertTrue(savedOrder != null && savedOrder.getOrderDetails().size() > 0);

	}

	// OK
	@Test
	@DisplayName("when calling get()")
	public void testGetFound()
	{
		Integer orderId;
		BookOrder bookOrder;

		// get the bookOrder from the database
		orderId = 50;
		bookOrder = orderDAO.get(orderId);

		assertNotNull(bookOrder);

		System.out.println(">>testGetFound():The BookOrder from the data base:");
		
		System.out.println("First Name: " + bookOrder.getFirstname());
		System.out.println("Last Name: " + bookOrder.getLastname());
		System.out.println("Phone: " + bookOrder.getPhone());
		System.out.println("Address Line 1: " + bookOrder.getAddressLine1());
		System.out.println("Address Line 2: " + bookOrder.getAddressLine2());
		System.out.println("City: " + bookOrder.getCity());
		System.out.println("State: " + bookOrder.getState());
		System.out.println("Country: " + bookOrder.getCountry());
		System.out.println("zipcode: " + bookOrder.getZipcode());
		System.out.println("Payment Method: " + bookOrder.getPaymentMethod());
		System.out.println("Status: " + bookOrder.getStatus());
		System.out.println("Subtotal " + bookOrder.getSubtotal());
		System.out.println("Shipping Fee" + bookOrder.getShippingFee());
		System.out.println("Tax " + bookOrder.getTax());
		System.out.println("Total: " + bookOrder.getTotal());
		
		assertEquals(1, bookOrder.getOrderDetails().size());
		
		

	}

	// OK
	@Test
	@DisplayName("when calling get()")
	public void testGetByIdAndCustomerNull()
	{
		Integer orderId;
		Integer customerId;
		BookOrder bookOrder;

		// get the bookOrder from the database for this customer only!
		orderId = 45;
		customerId = 99;
		bookOrder = orderDAO.get(orderId, customerId);

		assertNull(bookOrder);

	}

	@Test
	@DisplayName("when calling get()")
	public void testGetByIdAndCustomerNotNull()
	{
		Integer orderId;
		Integer customerId;
		BookOrder bookOrder;

		// get the bookOrder from the database for this customer only!
		orderId = 45;
		customerId = 18;
		bookOrder = orderDAO.get(orderId, customerId);

		assertNotNull(bookOrder);

	}

	// OK
	@Test
	@DisplayName("when calling listAll() method")
	public void testListAll()
	{
		List<BookOrder> bookOrders = orderDAO.listAll();

		assertTrue(bookOrders.size() > 0);

		bookOrders.forEach(o -> System.out.println("orderId = " + o.getOrderId() + " - " + o.getOrderDate()));
	}

	/*
	 * OK : BUT BEFORE I FETCHED THE CUSTOMER WITHOUT EAGER! HOW IT IS POSSIBLE I
	 * CHANGE THE FETCH TYPE ANY WAY . BUT STILL IT'S NOT CLEAR!
	 */
	@Test
	@DisplayName("when calling listAll() method")
	public void testListAllWithCustomer()
	{
		List<BookOrder> bookOrders = orderDAO.listAll();

		assertTrue(bookOrders.size() > 0);
		assertTrue(bookOrders.size() == 4);

		for (BookOrder bookOrder : bookOrders) {
			System.out.println(bookOrder.getOrderId() + " - " + bookOrder.getCustomer().getFirstname() + " - "
					+ bookOrder.getStatus() + " - " + bookOrder.getTotal());

			// loop over the OrderDetails Set
			for (OrderDetail detail : bookOrder.getOrderDetails()) {
				Book book = detail.getBook();
				int quantity = detail.getQuantity();
				float subTotal = detail.getSubtotal();

				System.out.println("\t" + book.getTitle() + " - " + quantity + " - " + subTotal);
			}
		}

	}

	//OK -  PAYPAL
	@Test
	@DisplayName("when calling update()")
	public void testUpdateBookOrder()
	{
		// Read a BookOreder from the database
		Integer id = 50;
		BookOrder bookOrder = orderDAO.get(id);

		assertNotNull(bookOrder);

		// set a new Reciepient name - AAAA
		bookOrder.setFirstname("AAAAAAAAAA");
		bookOrder.setSubtotal(267f);
		bookOrder.setShippingFee(2.0f);
		bookOrder.setTax(26.7f);
		bookOrder.setTotal(267f + 2f + 26.7f);
		// call update()
		orderDAO.update(bookOrder);
	}

	@Test
	@DisplayName("when calling update()")
	public void testUpdateTotalOfBookOrder()
	{
		// Read a BookOreder from the database
		Integer id = 34;
		BookOrder bookOrder = orderDAO.get(id);

		assertNotNull(bookOrder);

		// set a new Reciepient name - AAAA
		bookOrder.setTotal(253.41f);

		// call update()
		orderDAO.update(bookOrder);
	}

	@Test
	@DisplayName("when calling getBookCopies")
	public void testGetBookCopies()
	{
		Integer orderId = 34;
		BookOrder bookOrder = orderDAO.get(orderId);

		int expected = 13;
		int actual = bookOrder.getBookCopies();

		assertEquals(expected, actual);

	}

	// Great!
	@Test
	@DisplayName("when calling update()")
	public void testUpdateOrderDetailOfBookOrder()
	{
		// Read a BookOreder from the database
		Integer id = 34;
		BookOrder bookOrder = orderDAO.get(id);

		assertNotNull(bookOrder);

		// read the Set<OrderDetail> from the BookOrder
		Set<OrderDetail> orderDetails = bookOrder.getOrderDetails();
		// assertEquals(3, orderDetails.size());

		// loop over the set of orderDetails :Ok
		System.out.println(
				">>testUpdateOrderDetailsOfBookOrder():Quantities of each orderDetail in the Original Set of  orderDetails:");

		orderDetails.forEach(o -> System.out.println("quantity: " + o.getQuantity()));

		// update the quantites from 2 , 3 to 10 , 10 (the same)
		for (OrderDetail orderDetail : orderDetails) {
			orderDetail.setQuantity(10);
		}

		// call update() on BookOrder - should update also the order_detail table!(child
		// table because the cascade all)
		orderDAO.update(bookOrder);

	}

	// OK
	@Test
	@DisplayName("when updating BookOrder and it's order detail (UPDATE CASCADE)")
	public void testUpdateBookOrderAndOrderDetail()
	{
		Integer bookOrderId;
		Integer bookId;
		BookOrder bookOrder;
		Set<OrderDetail> orderDetails;
		Iterator<OrderDetail> iter;

		// 1.read a BookOrder bookOrder from the database(id 34)
		bookOrderId = 34;
		bookOrder = orderDAO.get(bookOrderId);
		assertNotNull(bookOrder);

		// 2. read the bookOrder's Set<OrderDetail> into a Set orderDetails.
		orderDetails = bookOrder.getOrderDetails();
		assertEquals(2, orderDetails.size());
		System.out.println("OrderDetails for BookOrder with id = " + bookOrderId);
		orderDetails.forEach(d -> System.out.println("order id = " + d.getBookOrder().getOrderId() + " , book id = "
				+ d.getBook().getBookId() + " , quantity= " + d.getQuantity() + " , subtotal = " + d.getSubtotal()));

		// 3. Loop over the set and find the OrderDetail object with book_id = 32 and
		// update
		// quantity to 3 and subtotal 117
		bookId = 32;
		iter = orderDetails.iterator();

		while (iter.hasNext()) {
			OrderDetail orderDetail = iter.next();
			if (orderDetail.getBook().getBookId() == bookId) {
				orderDetail.setQuantity(3);
				orderDetail.setSubtotal(117);
			}
		}

		// 5. PERSIST the bookOrder into the database
		orderDAO.update(bookOrder);

		System.out.println("After updating BookOrder, OrderDetails for BookOrder with id = " + bookOrderId);
		orderDetails.forEach(d -> System.out.println("order id = " + d.getBookOrder().getOrderId() + " , book id = "
				+ d.getBook().getBookId() + " , quantity= " + d.getQuantity() + " , subtotal = " + d.getSubtotal()));

	}

	// ok
	@Test
	@DisplayName("when calling count() ")
	public void testCount()
	{
		Integer id;
		long actual;
		long expected;
		BookOrder bookOrder;

		id = 34;
		bookOrder = orderDAO.get(id);

		actual = orderDAO.count();
		expected = 4;

		assertEquals(expected, actual);

		System.out.println(">>testCount():number of rows in book_order table: " + actual);
	}

	// OK
	@Test
	@DisplayName("when calling delete() ")
	public void testDelete()
	{
		Integer id;

		// 1. delete book_order with id = 35
		id = 35;
		orderDAO.delete(id);

		// 2.test deletion by reading it again from data base
		BookOrder bookOrder = orderDAO.get(id);
		assertNull(bookOrder);

	}

	// Assignment 22
	@Test
	@DisplayName("when calling countOrderDetailByBook() method:")
	public void testCountOrderDetailByBook()
	{

		Integer bookId;
		long expected;
		long actual;

		bookId = 32;
		expected = 2;
		actual = orderDAO.countOrderDetailByBook(bookId);

		assertEquals(expected, actual);

		System.out.println("Number of order details with this book id :" + actual);

	}

	// Assignment 23
	@Test
	@DisplayName("when calling countByCustomer() method:")
	public void testCountByCustomer()
	{
		Integer customerId = 11;
		long actual;
		long expected = 3;

		actual = orderDAO.countWithNamedQuery("BookOrder.countByCustomer", "customerId", customerId);

		assertEquals(expected, actual);

	}

	// OK
	@Test
	@DisplayName("when calling listByCustomer() method:")
	public void testListByCustomerWithfindWithNamedQuery()
	{
		Integer customerId = 11;
		List<BookOrder> bookOrders;

		bookOrders = orderDAO.findWithNamedQuery("BookOrder.findByCustomer", "customerId", customerId);

		int actual = bookOrders.size();
		int expected = 3;
		assertEquals(expected, actual);

		bookOrders.forEach(o -> System.out.println("order id: " + o.getOrderId()));

	}

	// OK
	@Test
	@DisplayName("when calling listByCustomer() method:")
	public void testListByCustomer()
	{
		Integer customerId = 11;
		List<BookOrder> bookOrders;
		// Customer customer ;

		// HE CHANGE THE PARAMTER TYPE FORM CUSTOMER TO INTEGER
		// create a customer with exiting id = 11;
		/*
		 * customer =new Customer(); customer.setCustomerId(customerId);
		 */

		bookOrders = orderDAO.listByCustomer(customerId);

		int actual = bookOrders.size();
		int expected = 3;
		// assertEquals(expected, actual);
		assertEquals(expected, actual);
		System.out.println(">>List of orders for customer id : " + customerId);
		bookOrders.forEach(o -> System.out.println("order id: " + o.getOrderId() + " - " + o.getOrderDate()));

	}

	// OK
	@Test
	@DisplayName("when calling listByCustomer() method:")
	public void testListByCustomerNonExists()
	{
		Integer customerId = 22;
		List<BookOrder> bookOrders;

		bookOrders = orderDAO.listByCustomer(customerId);

		int actual = bookOrders.size();
		int expected = 3;
		// assertEquals(expected, actual);
		// assertEquals(expected, actual);
		assertTrue(bookOrders.isEmpty());

		System.out.println(">>List of orders for customer id : " + customerId);
		bookOrders.forEach(o -> System.out.println("order id: " + o.getOrderId() + " - " + o.getOrderDate()));

	}

	@Test
	@DisplayName("When calling listRecentSales()")
	public void getListRecentSales()
	{
		List<BookOrder> recentSales = orderDAO.listRecentSales();

		assertEquals(3, recentSales.size());

		recentSales.forEach(s -> System.out.println(s.getOrderDate()));

	}

	//PayPal: OK
	@Test
	@DisplayName("After PayPal: When update the OrderDetail's quantity")
	public void testUpdateOrderDetail()
	{
		Integer orderId = 49; 
		BookOrder order = orderDAO.get(orderId);
		Set<OrderDetail> orderDetails = order.getOrderDetails();
		
		assertNotNull(order);
		System.out.println(">>The order with id = " + orderId + " Exists!") ;
		
		System.out.println("\n\n>>The Set of order details is : ");
		orderDetails.forEach(od -> 
				System.out.println("oid = " + od.getBookOrder().getOrderId() + 
				", bid = " + od.getBook().getBookId() +
				", quantity = " + od.getQuantity() + 
				", Subototal = " + od.getSubtotal() + "\n\n"));
		
		
		
		//Create an iterator on the Ser of order Details
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();
		
		
		while(iterator.hasNext())
		{
			OrderDetail orderDetail = iterator.next();
			
			if(orderDetail.getBook().getBookId() == 33)
			{
				//System.out.println("in if..");
				System.out.println(">>in if ! order id = " + orderDetail.getBookOrder().getOrderId());
				orderDetail.setQuantity(3);
				orderDetail.setSubtotal(267);
				
			}
		}
		
		System.out.println(">>Before Updating the order....");
		orderDAO.update(order);
		System.out.println(">>After Updating the order!Check in get()..");
		
		
	}
	
	@Test
	@DisplayName("PayPal: When tring to get order details ")
	public void testGetOrderDetails()
	{
		Integer  orderId = 49; 
		Integer bookId = 33; 
		BookOrder bookOrder = orderDAO.get(orderId);
		
		Iterator<OrderDetail> iterator = bookOrder.getOrderDetails().iterator();
		
		int expectedQuantity = 3;
		float expectedSubtotal = 267;
		int actualQuantity  = 0;
		float actualSubtotal = 0;
		
		while(iterator.hasNext())
		{
			OrderDetail orderDetail = iterator.next();
			
			if(orderDetail.getBook().getBookId() == bookId)
			{
				actualQuantity = orderDetail.getQuantity();
				actualSubtotal = orderDetail.getSubtotal();
			}
		}
		
		
		assertEquals(expectedQuantity, actualQuantity);
		assertEquals(expectedSubtotal, actualSubtotal);
		
		
		
	}
}