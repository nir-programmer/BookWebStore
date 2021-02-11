package org.nir.bookstore.service;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nir.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import org.nir.bookstore.dao.OrderDAO;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.OrderDetail;

import net.bytebuddy.matcher.SubTypeMatcher;

public class OrderService
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private OrderDAO orderDAO;

	public OrderService(HttpServletRequest request, HttpServletResponse response)
	{
		super();
		this.request = request;
		this.response = response;
		this.orderDAO = new OrderDAO();
	}

	public void listAll() throws ServletException, IOException
	{
		listAll(null);

	}

	public void listAll(String message) throws ServletException, IOException
	{
		if (message != null) {
			request.setAttribute("message", message);
		}
		String ordersPage = "orders_list.jsp";
		// OrderDAO orderDAO;
		List<BookOrder> bookOrders;

		// 1.read all orders from the data base into List orders
		// orderDAO = new OrderDAO() ;
		orderDAO.openCurrentSession();
		bookOrders = orderDAO.listAll();
		orderDAO.closeCurrentSession();

		// 2.set the list into the request attriubute
		request.setAttribute("orders", bookOrders);

		// 3.forward the request to the orders page
		CommonUtitlity.forwardToPage(ordersPage, request, response);
	}

	public void viewOrderDetailForAdmin() throws ServletException, IOException
	{
		String orderDetailPage = "order_detail.jsp";
		Integer id = Integer.parseInt(request.getParameter("id"));
		System.out.println(">>OrderService.viewOrderDetailForAdmin(): id of BookOrder is :" + id);
		// Just for testing a non existing order: id = 2
		orderDAO.openCurrentSession();
		// change to id!!just test
		BookOrder bookOrder = this.orderDAO.get(id);
		orderDAO.closeCurrentSession();

		// Assignment 21:
		if (bookOrder == null) {
			String message = "Could not find order with ID 2";
			request.setAttribute("message", message);

		} else {
			System.out.println(">>OrderService.viewOrderDetailForAdmin(): List Of Books id's in the OrderDetails:");
			Set<OrderDetail> orderDetails = bookOrder.getOrderDetails();

			orderDetails.forEach(d -> System.out.println(d.getBook().getBookId()));

			request.setAttribute("order", bookOrder);
		}

		request.getRequestDispatcher(orderDetailPage).forward(request, response);

	}

	public void showCheckOutForm() throws ServletException, IOException
	{
		Map<String, String> mapCountries;
		String checkoutPage = "frontend/checkout.jsp";
		// FOR PAY PAL: I need the transactions in the cart to display in the checkout
		// page
		HttpSession session = request.getSession();

		// read the cart from the session
		ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");

		// tax is 10% of subtoal
		float tax = cart.getTotalAmount() * 0.1f;

		// shipping fee is 1.0 USD Per one copy of a book
		float shippingFee = cart.getTotalQuantity() * 1.0f;

		float total = cart.getTotalAmount() + tax + shippingFee;

		session.setAttribute("tax", tax);
		session.setAttribute("shippingFee", shippingFee);
		session.setAttribute("total", total);

		// IMPORTANT: load the countries into the request
		CommonUtility.generateCountries(request);
		// CommonUtitlity.forwardToPage(checkoutPage, request, response);
		request.getRequestDispatcher(checkoutPage).forward(request, response);

	}

	public void placeOrder() throws ServletException, IOException
	{

		// This will created here and added to the database
		BookOrder bookOrder;

		/*
		 * These objects will be read from the session: The Customer property of the
		 * BookOrder will be set to customer The total property of the BookOrder will be
		 * set a computed value later..
		 */
		Customer customer;
		ShoppingCart cart;

		/*
		 * This set will be created here and will be set to the orderDetails property
		 */
		Set<OrderDetail> orderDetails;

		// This value will be computed by the ShoppingCart object and will be set to the
		// order
		float total;

		// for customer data stored in 'loggedCustomer' and shopping cart data stored in
		// 'cart'
		HttpSession session;

		// Update with PayPal
		// form fields for book order and shipping ...
		// reading request form parameters into of BookOrder properties
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");

		String phone = request.getParameter("phone");

		// These fields ared used to build the String address for the BookOrder
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");

		String city = request.getParameter("city");
		String state = request.getParameter("state");

		String zipcode = request.getParameter("zipcode");
		String country = request.getParameter("country");

		// I added this now!it didn't have it before PayPal , intresting....
		String paymentMethod = request.getParameter("paymentMethod");

		// Remove this - Don't need this value with paypal..
		// String shippingAddress = address1 + " ,"+ city + ", " + zipCode + ", " +
		// country;

		// Create a BookOrder and store order information and shopping information from
		// the form
		bookOrder = new BookOrder();

		// Set all the values to the order.
		bookOrder.setFirstname(firstname);
		bookOrder.setLastname(lastname);
		bookOrder.setPhone(phone);
		bookOrder.setAddressLine1(address1);
		bookOrder.setAddressLine2(address2);
		bookOrder.setCity(city);
		bookOrder.setState(state);
		bookOrder.setZipcode(zipcode);

		// NOTE :This is the country CODE not country NAME!
		bookOrder.setCountry(country);

		// remove the statement in the OrderDAO that set this property!
		String paymenMethod = request.getParameter("paymentMethod");
		bookOrder.setPaymentMethod(paymenMethod);

		// Get the SESSION
		session = request.getSession();

		// Get LOGGED customer from the session and set the customer for the book
		// order..
		customer = (Customer) session.getAttribute("loggedCustomer");
		bookOrder.setCustomer(customer);

		// get the shopping cart from the session
		cart = (ShoppingCart) session.getAttribute("cart");

		// get the Map<Book,Integer> from the cart
		Map<Book, Integer> items = cart.getItems();

		// get the Set of keys from the map of ( book , quantity)
		Set<Book> books = items.keySet();

		// Create an Iterator<Book> from books Set
		Iterator<Book> iterator = books.iterator();

		// Create an empty HashSet<OrderDetail>
		orderDetails = new HashSet<OrderDetail>();

		// Loop over the books in the set and build the Set of orderDetail..
		while (iterator.hasNext()) {
			OrderDetail orderDetail = new OrderDetail();
			// the key(book) that the iterator points on
			Book book = iterator.next();
			// the value of the book key - number of books
			Integer quantity = items.get(book);
			// the product of book's price and quantity
			float subtotal = book.getPrice() * quantity;

			// set the orderDetail
			orderDetail.setBook(book);
			orderDetail.setBookOrder(bookOrder);
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);

			// adding the orderDetail to the Set<OrderDetail>
			orderDetails.add(orderDetail);
		}

		// adding the orderDetails set to the order
		bookOrder.setOrderDetails(orderDetails);

		/*
		 * IMPORTANT: TRANSACTION INFORMATION FOR PAY PAL UPDATE FOR PAYPAL: Read the
		 * TRANSACTION data (tax , shipping fee , subtotal) form the session
		 */
		float tax = (float) session.getAttribute("tax");
		float shippingFee = (float) session.getAttribute("shippingFee");
		
		// NOTE: The totalAmount is the cart is the SUBTOTAL of the order
		float subtotal = cart.getTotalAmount();

		// Compute total by the ShoppingCart and set to the order
		// total = cart.getTotalAmount();
		total = (float) session.getAttribute("total"); 
		
		
		//Set transaction inforamtion to the order object: shipping fee , tax , subtotal , total)
		bookOrder.setTax(tax);
		bookOrder.setShippingFee(shippingFee);
		bookOrder.setSubtotal(subtotal);
		bookOrder.setTotal(total);

		
		// adding the BookOrder to the database
		this.orderDAO.openCurrentSessionWithTransaction();
		this.orderDAO.create(bookOrder);
		this.orderDAO.closeCurrentSessionWithTransaction();

		// forward to the message.jsp page
		String message = "Thank you.Your order has been recieved." + "We will deflever your books whithin a few days.";

		String targetPage = "frontend/message.jsp";
		request.setAttribute("message", message);

		request.getRequestDispatcher(targetPage).forward(request, response);

	}

	// He changed the type of the listByCustomer() to Integer( instead of Customer)
	public void listOrdersByCustomer() throws ServletException, IOException
	{

		Customer customer;
		Integer customerId;
		List<BookOrder> bookOrders;
		HttpSession session;
		String targetPage = "frontend/order_list.jsp";
		// 1.read the logged customer FROM THE SESSION
		session = request.getSession();
		customer = (Customer) session.getAttribute("loggedCustomer");

		// 2.read the list of orders for this customer
		this.orderDAO.openCurrentSession();
		bookOrders = orderDAO.listByCustomer(customer.getCustomerId());
		this.orderDAO.closeCurrentSession();

		// 3.set the list in the request and forward to the target page
		request.setAttribute("orders", bookOrders);
		request.getRequestDispatcher(targetPage).forward(request, response);

	}

	public void showOrderDetailForCustomer() throws ServletException, IOException
	{
		Integer orderId;
		BookOrder bookOrder;
		String orderDetailsPage;
		// For Security!
		Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");

		// 1.Read the orderId from the request
		orderId = Integer.parseInt(request.getParameter("id"));

		// 2.read the BookOrder with this id from the database
		this.orderDAO.openCurrentSession();
		// I HAVE TO CALL THE SECURITY OVERLOAD GET() METHOD!!
		bookOrder = this.orderDAO.get(orderId, customer.getCustomerId());
		this.orderDAO.closeCurrentSession();

		// 3.Forward to page : 'frontend/order_details.jsp'
		orderDetailsPage = "frontend/order_detail.jsp";
		request.setAttribute("order", bookOrder);
		CommonUtitlity.forwardToPage(orderDetailsPage, request, response);
	}

	public void showEditOrderForm() throws ServletException, IOException
	{
		Integer orderId;
		BookOrder order;
		String editPage;
		String message;
		HttpSession session;
		//////////////////////////////////

		// For Pay Pal - generete the countries and set in the request
		CommonUtitlity.generateCountries(request);

		session = request.getSession();

		// 1.Read the orderId form the request
		orderId = Integer.parseInt(request.getParameter("id"));
		///////////////////////////////////////////////////////////////
		/*
		 * Assingment 24: Check if there is an order with this id in the database
		 */
		this.orderDAO.openCurrentSession();
		order = this.orderDAO.get(orderId);
		if (order == null) {
			message = "Could not find order with ID " + orderId;
			this.orderDAO.closeCurrentSession();
			CommonUtitlity.forwardToPage("message.jsp", message, request, response);
			return;
		}
		this.orderDAO.closeCurrentSession();

		/////////////////////////////////////////////////////////
		// Important: Check the value of the NewBookPendingToAddToOrder session
		///////////////////////////////////////////////////////// attribute
		Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");

		// if there is no pending book in the session - then fetch the book form the db
		// and
		// set it into the session
		if (isPendingBook == null) {
			// 2. Read the BookOrder object from the data base
			this.orderDAO.openCurrentSession();
			order = this.orderDAO.get(orderId);
			this.orderDAO.closeCurrentSession();

			/*
			 * 3.IMPORTANT:I will need order object also in is the add_book_form.jsp , So I
			 * need to set it in the session INSTEAD of the request attribute
			 */
			session.setAttribute("order", order);
		}
		// There is a pending Book in the session - remove this flag attribute from the
		// session..
		else {
			session.removeAttribute("NewBookPendingToAddToOrder");
		}

		editPage = "order_form.jsp";
		CommonUtitlity.forwardToPage(editPage, request, response);

		/*
		 * THIS IS WRONG - I want the order in the session //4.Add the order to the
		 * request and Forward to order_form.jsp page request.setAttribute("order",
		 * order);
		 */
		// no need - 'admin/order_form.jsp'

	}

	public void updateOrder() throws ServletException, IOException
	{
		// read the BookOrder from the SESSION!!
		HttpSession session;
		BookOrder bookOrder;
		Set<OrderDetail> orderDetails;

		// Read GENERAL information of the order from the form(section 1 )
		String firstname;
		String lastname;
		String phone;
		String address1;
		String address2;
		String city;
		String state;
		String zipcode;
		String country;

		// Transaction information for PayPal
		float shippingFee;
		float tax;
		/*
		 * float subtotal; float total ;
		 */

		String paymentMethod;
		String status;

		// total for the BookOrder - calculated in this method !
		float totalAmount = 0.0f;

		// this message will be displayed in the REFRESHED list_order.jsp page
		String message;

		// Read OrderDetails information of the order from the form(section 2)
		String[] arrayBookId; // hidden fields
		String[] arrayPrice;// hidden fields
		String[] arrayQuantity; // not hidden field

		// CREATION
		// 1.Create the BookOrder object from the session .
		session = request.getSession();
		bookOrder = (BookOrder) session.getAttribute("order");

		// 2.Read GENERAL values from the form in the order_form.jsp :
		firstname = request.getParameter("firstname");
		lastname = request.getParameter("lastname");
		phone = request.getParameter("phone");
		address1 = request.getParameter("address1");
		address2 = request.getParameter("address2");
		city = request.getParameter("city");
		state = request.getParameter("state");
		zipcode = request.getParameter("zipcode");
		country = request.getParameter("country");

		// Read transaction information input for PayPal
		shippingFee = Float.parseFloat(request.getParameter("shippingfee"));
		tax = Float.parseFloat(request.getParameter("tax"));

		paymentMethod = request.getParameter("paymentMethod");
		status = request.getParameter("status");

		// Test it in the console: >>updateOrder():status = Shipping : OK
		System.out.println(">>updateOrder():status = " + status);
		// Read ORDER DETAIL related values from the form(section 2)
		arrayBookId = request.getParameterValues("bookId");
		arrayPrice = request.getParameterValues("price");
		arrayQuantity = new String[arrayBookId.length];

		// read quantities into the array
		for (int i = 1; i <= arrayQuantity.length; i++)
			arrayQuantity[i - 1] = request.getParameter("quantity" + i);

		// read the Set of OrderDetail from the order in the session
		orderDetails = bookOrder.getOrderDetails();

		// Remove all OrderDetail from this BookOrder's orderDetails!
		orderDetails.clear();

		/*
		 * Recreate them from the form fields(section 2) //quantity, subtotal , Book,
		 * BookOrder For PayPal : subtotal is the same! the total amout is updated by
		 * adding the shipping fee and tax values
		 */
		for (int i = 0; i < arrayBookId.length; i++) {
			int bookId = Integer.parseInt(arrayBookId[i]);
			int quantity = Integer.parseInt(arrayQuantity[i]);
			float price = Float.parseFloat(arrayPrice[i]);

			// calculate subtotal for the order detail
			float subtotal = quantity * price;

			// create a new OrderDetail for the current book and set its values
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setBook(new Book(bookId));
			orderDetail.setQuantity(quantity);
			orderDetail.setSubtotal(subtotal);
			orderDetail.setBookOrder(bookOrder);

			// add this OrderDetail to the OrderDetails(was cleared before the loop) of the
			// BookOrder
			orderDetails.add(orderDetail);

			// update the totalAmount
			totalAmount += subtotal;
		}

		// PayPal: set Transaction information in the BookOrder:(tax, shippingFee,
		// subtotal , total)
		bookOrder.setTax(tax);
		bookOrder.setShippingFee(shippingFee);
		// From the loop above
		bookOrder.setSubtotal(totalAmount);

		// calculae the totalAmount
		totalAmount += shippingFee + tax;

		// set the new total (with tax and shippingfee)
		bookOrder.setTotal(totalAmount);

		// set the general values in the BookOrder
		bookOrder.setFirstname(firstname);
		bookOrder.setLastname(lastname);
		bookOrder.setPhone(phone);
		bookOrder.setAddressLine1(address1);
		bookOrder.setAddressLine2(address2);
		bookOrder.setCity(city);
		bookOrder.setState(state);
		bookOrder.setZipcode(zipcode);
		bookOrder.setCountry(country);
		bookOrder.setPaymentMethod(paymentMethod);
		// bookOrder.setTotal(totalAmount);
		bookOrder.setStatus(status);

		// update the database - add this BookOrder
		this.orderDAO.openCurrentSessionWithTransaction();
		this.orderDAO.update(bookOrder);
		this.orderDAO.closeCurrentSessionWithTransaction();

		// Refresh the order_list.jsp by calling the listAll() ;
		message = "The order " + bookOrder.getOrderId() + " has been updated successfully";
		this.listAll(message);

	}

	public void deleteOrder() throws ServletException, IOException
	{

		Integer orderId;
		String message;

		// 1.Read the orderId from the request : "id"
		orderId = Integer.parseInt(request.getParameter("id"));

		/*
		 * Assingment 25 - check if there is an order with this id in the databases
		 * 
		 */
		BookOrder bookOrder;
		this.orderDAO.openCurrentSession();
		bookOrder = this.orderDAO.get(orderId);
		if (bookOrder == null) {
			message = "Could not find order with ID " + orderId + ", or it might have been deleted by another admin";

			this.orderDAO.closeCurrentSession();
			CommonUtitlity.forwardToPage("message.jsp", message, request, response);
			return;
		}

		this.orderDAO.closeCurrentSession();

		// Assingment 25 END

		// 2.Just delete with OrderDAO:
		this.orderDAO.openCurrentSessionWithTransaction();
		this.orderDAO.delete(orderId);
		this.orderDAO.closeCurrentSessionWithTransaction();

		// 3.Forward to the refreshed order_list.jsp page with a message
		message = "The order ID  " + orderId + " has been deleted.";
		/* request.setAttribute("message", message); */
		this.listAll(message);

	}

}
