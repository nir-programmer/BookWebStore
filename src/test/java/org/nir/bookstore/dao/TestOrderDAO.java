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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.hibernate.criterion.DetachedCriteria;
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
		// OrderDetailId orderDetailId;
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

		// Create the Book with existing id : 33(java 8)
		book = new Book(33);

		// Create set of orderDetails
		orderDetails = new HashSet<OrderDetail>();

		// create a new OrderDetail
		orderDetail = new OrderDetail();

		// MANY TO MANY: add the book and the bookOrder references to the
		// Join table(orderDetail) I have to do this!he got EXCEPTION
		orderDetail.setBook(book);
		orderDetail.setBookOrder(bookOrder);
		// set the quantity and subtotal vlues
		orderDetail.setQuantity(2);
		orderDetail.setSubtotal(68f);

		// add the orderDetail to the set
		orderDetails.add(orderDetail);

		// add the Set to the BookOrder
		bookOrder.setOrderDetails(orderDetails);

		// persist the BookOrder in db and save the returned value

		BookOrder savedOrder = orderDAO.create(bookOrder);

		assertTrue(savedOrder != null && savedOrder.getOrderDetails().size() > 0);

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
		bookOrder.setRecipientName("Nir Ithzak");
		bookOrder.setRecipientPhone("0544678017");
		bookOrder.setShippingAddress("Hod Hasharon, Hatzanchanim 13, 3");

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
		orderId = 30;
		bookOrder = orderDAO.get(orderId);

		assertNotNull(bookOrder);

		System.out.println(
				">>testGetFound():bookOrder with id " + orderId + " ,has " + bookOrder.getOrderDetails().size());
		System.out.println(">>testGetFOune():order.ReciepentName: " + bookOrder.getRecipientName());

	}

	// OK
	@Test
	@DisplayName("when calling listAll() method")
	public void testListAll()
	{
		List<BookOrder> bookOrders = orderDAO.listAll();

		assertTrue(bookOrders.size() > 0);

		bookOrders.forEach(o -> System.out.println(o.getOrderId()));

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
			System.out.println(bookOrder.getOrderId() + " - " + bookOrder.getCustomer().getFullname() + " - "
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

	@Test
	@DisplayName("when calling update()")
	public void testUpdateBookOrder()
	{
		// Read a BookOreder from the database
		Integer id = 30;
		BookOrder bookOrder = orderDAO.get(30);

		assertNotNull(bookOrder);

		// set a new Reciepient name - AAAA
		bookOrder.setRecipientName("AAAAAAAAAA");

		// call update()
		orderDAO.update(bookOrder);
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

	//OK
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
		orderDetails.forEach(d -> System.out.println("order id = " + d.getBookOrder().getOrderId()
				+ " , book id = "
				+ d.getBook().getBookId() + " , quantity= " + d.getQuantity() + 
				" , subtotal = " + d.getSubtotal()));

		// 3. Loop over the set and find the OrderDetail object with book_id = 32 and update
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
		orderDetails.forEach(d -> System.out.println("order id = " + d.getBookOrder().getOrderId()
				+ " , book id = "
				+ d.getBook().getBookId() + " , quantity= " + d.getQuantity() + 
				" , subtotal = " + d.getSubtotal()));
		
	}

}