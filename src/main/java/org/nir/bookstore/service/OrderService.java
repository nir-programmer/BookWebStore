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
		this.orderDAO = new OrderDAO() ;
	}

	public  void listAll() throws ServletException, IOException
	{
	 listAll(null);
		
	}

	public void listAll(String message) throws ServletException, IOException
	{
		if(message != null)
		{
			request.setAttribute("message", message);
		}
		String ordersPage = "orders_list.jsp"; 
		//OrderDAO orderDAO;
		List<BookOrder> bookOrders; 
		
		//1.read all orders from the data base into List  orders
		//orderDAO = new OrderDAO() ; 
		orderDAO.openCurrentSession();
		bookOrders = orderDAO.listAll();
		orderDAO.closeCurrentSession();
		
		//2.set the list into the request attriubute
		request.setAttribute("orders", bookOrders) ;

		
		//3.forward the request to the orders page
		CommonUtitlity.forwardToPage(ordersPage, request, response);
	}
	
	public void  viewOrderDetailForAdmin() throws ServletException, IOException
	{
		String orderDetailPage = "order_detail.jsp";
		Integer id = Integer.parseInt(request.getParameter("id")); 
		System.out.println(">>OrderService.viewOrderDetailForAdmin(): id of BookOrder is :" + id);
		//Just for testing a non existing order: id = 2
		orderDAO.openCurrentSession();
		//change to id!!just test
		BookOrder bookOrder = this.orderDAO.get(id); 
		orderDAO.closeCurrentSession();
		
		//Assignment 21:
		if(bookOrder == null)
		{
			String message = "Could not find order with ID 2";
			request.setAttribute("message", message);
			
		}
		else
		{
		System.out.println(">>OrderService.viewOrderDetailForAdmin(): List Of Books id's in the OrderDetails:");
		Set<OrderDetail> orderDetails = bookOrder.getOrderDetails();
		
		orderDetails.forEach(d -> System.out.println(d.getBook().getBookId()));
		
		request.setAttribute("order", bookOrder);
		}
		
		request.getRequestDispatcher(orderDetailPage).forward(request, response);
		
		
		
	}


	public void showCheckOutForm() throws ServletException, IOException
	{
		String checkoutPage = "frontend/checkout.jsp";
		System.out.println(">>OrderSerice.showCheckOutForm():URL IS : " +request.getRequestURI().toString());
		
		//CommonUtitlity.forwardToPage(checkoutPage, request, response);
		request.getRequestDispatcher(checkoutPage).forward(request, response);
		
	}


	public void placeOrder() throws ServletException, IOException
	{
		
		//This will created here and added to the database
		BookOrder bookOrder ; 
		
		/*
		 * These objects will be read from the session:
		 * The Customer property of the BookOrder will be set to customer
		 * The total property of the BookOrder will be set a computed value later..
		 */ 
		Customer customer ;
		ShoppingCart cart;
		
		/*
		 * This set will be created here and will be set to the orderDetails property 
		 */
		Set<OrderDetail> orderDetails;
		
		//This value will be computed by the ShoppingCart object and will be set to the order
		float total; 
		
		//for customer data stored in 'loggedCustomer'  and shopping cart data stored in 'cart'
		HttpSession session;
		
	    //form fields for book order and shipping ...
		//reading request form parameters into of BookOrder properties
		String recipientName = request.getParameter("recipientName");
		String  recipientPhone = request.getParameter("recipientPhone");
		
		//These fields ared used to build the String address for the BookOrder
		String address = request.getParameter("address"); 
		String city = request.getParameter("city"); 
		String zipCode = request.getParameter("zipcode"); 
		String country = request.getParameter("country"); 
		 
		String shippingAddress =  address +  " ,"+ city + ", " + zipCode + ", " + country;
		 
		
		
		 //Create a BookOrder and store order information and shopping information from the form
		 bookOrder = new BookOrder();
		 
		 bookOrder.setRecipientName(recipientName);
		 bookOrder.setRecipientPhone(recipientPhone);
		 bookOrder.setShippingAddress(shippingAddress);
		 //remove the statement in the OrderDAO that set this property!
		 String paymenMethod = request.getParameter("paymentMethod");
		 bookOrder.setPaymentMethod(paymenMethod);
		 
		  session =request.getSession();
		 //Get customer from the session and set the customer for the book order..
		 customer =(Customer) session.getAttribute("loggedCustomer"); 
		 bookOrder.setCustomer(customer);
		 
		 //get the shopping cart from the session 
		 cart = (ShoppingCart)session.getAttribute("cart");
		 
		 //get the Map<Book,Integer> from the cart
		 Map<Book, Integer> items = cart.getItems();
		 
		 //get the Set of keys from the map
		 Set<Book> books = items.keySet();
		 
		//Create an Iterator<Book> from books Set
		 Iterator<Book> iterator = books.iterator();
		 
		 
		 //Create an empty HashSet<OrderDetail> 
		 orderDetails = new HashSet<OrderDetail>();
		 
		 //Loop over the books in the set and build the Set of orderDetail..
		 while(iterator.hasNext())
		 {
			 OrderDetail orderDetail = new OrderDetail(); 
			 //the key(book) that the iterator points on
			 Book book = iterator.next();
			 //the value of the book key - number of books
			 Integer quantity = items.get(book);
			 //the product of book's price and quantity
			 float subtotal = book.getPrice() * quantity;
			 
			 
			 //set the orderDetail 
			 orderDetail.setBook(book);
			 orderDetail.setBookOrder(bookOrder);
			 orderDetail.setQuantity(quantity);
			 orderDetail.setSubtotal(subtotal);
			 
			 //adding the orderDetail to the Set<OrderDetail>
			 orderDetails.add(orderDetail);
		 }
		 
		 //adding the orderDetails set to the order
		 bookOrder.setOrderDetails(orderDetails);
		 
		
		 //Compute total by the ShoppingCart and set to the order
		 total = cart.getTotalAmount();
		 bookOrder.setTotal(total);
		 
		 
		 //adding the BookOrder to the database
		 this.orderDAO.openCurrentSessionWithTransaction();
		 this.orderDAO.create(bookOrder);
		 this.orderDAO.closeCurrentSessionWithTransaction();
		 
		//forward to the message.jsp page
		 String message = "Thank you.Your order has been recieved."
		 		+ "We will deflever your books whithin a few days.";
		 
		 String targetPage = "frontend/message.jsp";
		 request.setAttribute("message", message);
		 
		 request.getRequestDispatcher(targetPage).forward(request, response);
		 
		 	
	}


	//He changed the type of the listByCustomer() to Integer( instead of Customer)
	public void listOrdersByCustomer() throws ServletException, IOException
	{
		
		Customer customer ; 
		Integer customerId;
		List<BookOrder> bookOrders; 
		HttpSession session ;
		String targetPage = "frontend/order_list.jsp";
		//1.read the logged customer  FROM THE SESSION
		session = request.getSession();
		customer = (Customer)session.getAttribute("loggedCustomer");
		
		
				
		//2.read the list of orders for this customer
		this.orderDAO.openCurrentSession();
		bookOrders = orderDAO.listByCustomer(customer.getCustomerId());
		this.orderDAO.closeCurrentSession();
		
		//3.set the list in the request and forward to the target page
		request.setAttribute("orders", bookOrders);
		request.getRequestDispatcher(targetPage).forward(request, response);
		
	}


	public void showOrderDetailForCustomer() throws ServletException, IOException
	{
		Integer orderId ; 
		BookOrder bookOrder;
		String orderDetailsPage; 
		//For Security!
		Customer customer = (Customer)request.getSession().getAttribute("loggedCustomer");
		
		
		//1.Read the orderId from the request
		orderId = Integer.parseInt(request.getParameter("id")); 
		
		//2.read the BookOrder with this id from the database 
		this.orderDAO.openCurrentSession();
		//I HAVE TO CALL THE SECURITY OVERLOAD GET() METHOD!!
		bookOrder = this.orderDAO.get(orderId, customer.getCustomerId());
		this.orderDAO.closeCurrentSession();
		
		//3.Forward to page : 'frontend/order_details.jsp'
		orderDetailsPage = "frontend/order_detail.jsp";
		request.setAttribute("order", bookOrder);
		CommonUtitlity.forwardToPage(orderDetailsPage, request, response);
	}


	public void showEditOrderForm() throws ServletException, IOException
	{
		Integer orderId; 
		BookOrder order;
		String editPage ; 
		String message; 
		HttpSession session;
		//////////////////////////////////
		session = request.getSession();
		
		//1.Read the orderId form the request
		orderId = Integer.parseInt(request.getParameter("id"));

		//Important: Check the value of the NewBookPendingToAddToOrder session attribute
		Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");
		
		//if there is no pending  book in the session - then fetch the book form the db and
		//set it into the session
		if(isPendingBook == null)
		{
			//2. Read the BookOrder object from the data base
			this.orderDAO.openCurrentSession();
			order= this.orderDAO.get(orderId);
			this.orderDAO.closeCurrentSession();
			
			/*
			 * 3.IMPORTANT:I will need order object also in is the add_book_form.jsp 
			 * , So I need to set it in the session INSTEAD  of the request attribute
			 */
			session.setAttribute("order", order);
		}
		//There is a pending Book in the session - remove this flag attribute from the session..
		else
		{
			session.removeAttribute("NewBookPendingToAddToOrder");
		}
		
		editPage = "order_form.jsp";
		CommonUtitlity.forwardToPage(editPage, request, response);
		  
		
		/*THIS IS WRONG - I want the order in the session
		 * //4.Add the order to the request and Forward to order_form.jsp page
		 * request.setAttribute("order", order);
		 */
		//no need - 'admin/order_form.jsp'
		
			
		
		
	}


	public void updateOrder() throws ServletException, IOException
	{
		//read the BookOrder from the SESSION!!
		HttpSession session ;
		BookOrder bookOrder;
		Set<OrderDetail> orderDetails;
		
		//Read GENERAL information of the order from the form(section 1 )
		String recipientName;
		String recipientPhone; 
		String shippingAddress; 
		String paymentMethod;
		String status; 
		
		//total for the BookOrder - calculated in this method !
		float totalAmount = 0.0f ; 
		 
		 //this message will be displayed in the REFRESHED list_order.jsp page
		 String message; 
		
		
		
		//Read OrderDetails information of the order from the form(section 2)
		String[] arrayBookId ; //hidden fields
		String[] arrayPrice;//hidden fields
		String[] arrayQuantity; //not hidden field
		
		
		//CREATION
		//1.Create the BookOrder object from the session .
		 session = request.getSession();
		 bookOrder = (BookOrder) session.getAttribute("order"); 
		 
		 
		 
		 //2.Read GENERAL values from the form in the order_form.jsp :
		 recipientName = request.getParameter("recipientName");
		 recipientPhone = request.getParameter("recipientPhone");
		 shippingAddress = request.getParameter("shippingAddress"); 
		 paymentMethod = request.getParameter("paymentMethod"); 
		 status = request.getParameter("status"); 
		 
		//Test it in the console:  >>updateOrder():status = Shipping : OK
		 System.out.println(">>updateOrder():status = " + status); 
		 //Read ORDER DETAIL related values from the form(section 2) 
		 arrayBookId = request.getParameterValues("bookId"); 
		 arrayPrice = request.getParameterValues("price"); 
		 arrayQuantity = new String[arrayBookId.length]; 
		 
		 //read quantities into the array 
		 for (int i = 1 ; i <= arrayQuantity.length; i++)
			 arrayQuantity[i -1 ]  = request.getParameter("quantity" + i); 
		 
		 //read the Set of OrderDetail from the order in the session
		 orderDetails = bookOrder.getOrderDetails();
		 //Remove all OrderDetail from this BookOrder's  orderDetails! 
		 orderDetails.clear();
		 
		//Recreate them from the form fields(section 2) //quantity, subtotal , Book, BookOrder
		 for(int i = 0 ; i < arrayBookId.length; i ++ )
		 {
			 int bookId = Integer.parseInt(arrayBookId[i]); 
			 int quantity = Integer.parseInt(arrayQuantity[i]); 
			 float price = Float.parseFloat(arrayPrice[i]); 
			 
			 //calculate subtotal for the order detail 
			 float subtotal = quantity * price;
			 
			 
			 
			 //create a new OrderDetail for the current book and set its values
			 OrderDetail orderDetail  = new OrderDetail() ;
			 orderDetail.setBook(new Book(bookId));
			 orderDetail.setQuantity(quantity);
			 orderDetail.setSubtotal(subtotal);
			 orderDetail.setBookOrder(bookOrder);
			 
			 
			 //add this OrderDetail to the OrderDetails(was cleared before the loop) of the BookOrder
			 orderDetails.add(orderDetail);
			 
			 //update the totalAmount
			 totalAmount += subtotal;
		 }
		
		 //set the totalAmout in the BookOrder
		 bookOrder.setTotal(totalAmount);
		 bookOrder.setStatus(status);
		 
		 //update the database - add this BookOrder
		 this.orderDAO.openCurrentSessionWithTransaction();
		 this.orderDAO.update(bookOrder);
		 this.orderDAO.closeCurrentSessionWithTransaction();
		 
		 //Refresh the order_list.jsp by calling the listAll() ;
		 message = "The order " + bookOrder.getOrderId() + " has been updated successfully";
		 this.listAll(message);
		 
	}


	
	
	
	
	
	

	

}
