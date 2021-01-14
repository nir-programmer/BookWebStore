package org.nir.bookstore.service;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nir.bookstore.dao.CustomerDAO;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.Review;

public class CustomerService {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private CustomerDAO customerDAO;

	public CustomerService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.customerDAO = new CustomerDAO();
	}

	public void listCustomers() throws ServletException, IOException {
		this.listCustomers(null);
	}

	public void listCustomers(String message) throws ServletException, IOException {

		this.customerDAO.openCurrentSession();
		List<Customer> customers = this.customerDAO.listAll();
		this.customerDAO.closeCurrentSession();

		if (message != null)
			request.setAttribute("message", message);

		System.out.println(">>CustomerService.listAll():list of customers:");
		customers.forEach(c -> System.out.println(c.getFullname()));

		request.setAttribute("customers", customers);
		request.getRequestDispatcher("customers_list.jsp").forward(request, response);

	}

	public void createCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");

		this.customerDAO.openCurrentSession();
		Customer existCustomer = this.customerDAO.findByEmail(email);
		this.customerDAO.closeCurrentSession();

		String message = "";

		if (existCustomer != null) {
			message = "Could not create a customer: The email " + email + " is already registred" + "by other customer";
		}

		else {

			Customer newCustomer = new Customer();

			// read the form fields into the customer
			readFormFields(newCustomer);

			// save the cusotmer in the database
			this.customerDAO.openCurrentSessionWithTransaction();
			this.customerDAO.create(newCustomer);
			this.customerDAO.closeCurrentSessionWithTransaction();

			message = "New customer has been created successfully!";
		}

		listCustomers(message);

	}

	public void editCustomer() throws ServletException, IOException {

		// get the id of the customer to be updated
		Integer id = Integer.parseInt(request.getParameter("id"));

		this.customerDAO.openCurrentSession();

		Customer customer = this.customerDAO.get(id);
		// Assignment 12: Check if there is a customer with this id
		if (customer == null) {
			this.customerDAO.closeCurrentSession();
			/// CommonUtility.showMessageBackend(request, response, "Could not find a
			/// customer with id = "+ id);
			CommonUtitlity.showMessageBackend("Could not find a customer with id = " + id, request, response);
			return;
		}

		this.customerDAO.closeCurrentSession();

		// Assignment 12: invoke the forwardToPage() method
		request.setAttribute("customer", customer);
		// CommonUtility.forwardToPage(request, response, "customer_form.jsp" );
		CommonUtitlity.forwardToPage("customer_form.jsp", request, response);

	}

	public void updateCustomer() throws ServletException, IOException {
		// retrieve the id and email from the form
		Integer id = Integer.parseInt(request.getParameter("customerId"));
		String email = request.getParameter("email");
		String message = "";
		// OK
		System.out.println(">>CustomerService.updateCustomer(): id in the form = " + id);
		System.out.println(">>CustomerService.updateCustomer(): email in the form = " + email);

		// retrieve 2 customers from the database by id and by email
		this.customerDAO.openCurrentSession();

		Customer customerByEmail = this.customerDAO.findByEmail(email);
		System.out.println(">>CustomerService.updateCustomer(): DATABASE:Customer's full name with id = " + id);
		System.out.println(">>CustomerService.updateCustomer(): DATABASE:Customer's full name with email = " + email);

		this.customerDAO.closeCurrentSession();
		// OK
		if (customerByEmail != null) {
			System.out.println(">>CustomerService.updateCustomer(): there is a customer with " + "email : " + email);
		}

		if (customerByEmail != null && customerByEmail.getCustomerId() != id) {
			message = "Could not update customer : There is a customer with email = " + email;
		}

		else {

			// read the customer by his id from the database
			this.customerDAO.openCurrentSessionWithTransaction();
			Customer customerById = this.customerDAO.get(id);

			// read the customer values form fields from the form
			readFormFields(customerById);

			// update the customer by the dao
			this.customerDAO.update(customerById);
			this.customerDAO.closeCurrentSessionWithTransaction();

			message = "Customer update successfully";

		}

		this.listCustomers(message);

	}

	public void deleteCustomer() throws ServletException, IOException {
		Integer id;
		Customer customer;
		Set<Review> reviews;
		String message;

		id = Integer.parseInt(request.getParameter("id"));
		System.out.println(">>CustomerService.deleteCustomer(): customerId = " + id);

		this.customerDAO.openCurrentSessionWithTransaction();

		customer = customerDAO.get(id);
		// Assignment 13: Check if there is a customer before deleting
		if (customer == null) {
			message = "Could not find customer with ID " + id + ", or it has been deleted by another admin";
			// CommonUtility.showMessageBackend(request, response, message);
			CommonUtitlity.showMessageBackend(message, request, response);
			return;
		} 
		else 
		{
			// Assignment 18: Check if there are reviews made by this customer
			reviews = customer.getReviews();

			if (!reviews.isEmpty()) 
			{
				this.customerDAO.closeCurrentSessionWithTransaction();
				message = "Could not delete customer with id " + id + " , there are reviews made by this customer";
				System.out.println(">>CustomerService.deleteCustomer(): " + message);
				CommonUtitlity.showMessageBackend(message, request, response);
			} 
			else 
			{
				message = "Customer with id " + id + " has been deleted succesfully!";
				this.customerDAO.delete(id);
				System.out.println(">>CustomerService.deleteCustomer(): " + message);
				this.customerDAO.closeCurrentSessionWithTransaction();
				request.setAttribute("message", message);
				this.listCustomers(message);
			}

		}
	}

	// Copy and paste the create() for register
	public void registerCustomer() throws ServletException, IOException {
		String email = request.getParameter("email");

		this.customerDAO.openCurrentSession();
		Customer existCustomer = this.customerDAO.findByEmail(email);
		this.customerDAO.closeCurrentSession();

		String message = "";

		if (existCustomer != null) {
			message = "Could not regiser. The email " + email + " is already registred" + "by another customer";
		}

		else {

			Customer newCustomer = new Customer();
			readFormFields(newCustomer);

			this.customerDAO.openCurrentSessionWithTransaction();
			this.customerDAO.create(newCustomer);
			this.customerDAO.closeCurrentSessionWithTransaction();

			message = "You have registered successfully! Thank you";
		}

		// listCustomers(message);
		request.setAttribute("message", message);
		request.getRequestDispatcher("/frontend/message.jsp").forward(request, response);
	}

	public void showLogin() throws ServletException, IOException {
		System.out.println(">>CustomerService.showLogin():the URI is: \n" + request.getRequestURI());
		request.getRequestDispatcher("frontend/login.jsp").forward(request, response);
	}

	public void doLogin() throws ServletException, IOException {
		// fetch the email and address from the request
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String message = "";

		// invoke the checkLogin() method of customerDAO and save in isLogin
		this.customerDAO.openCurrentSession();
		Customer customer = customerDAO.checkLogin(email, password);
		this.customerDAO.closeCurrentSession();
		System.out.println(">>CustomerService.doLogin():the URI is: \n" + request.getRequestURI());

		/*
		 * if there is a customer with these email and password : 1.add this customer to
		 * the session 2.forward the request to "forntend/customer_profile.jsp" page
		 */
		if (customer != null) {
			// OK
			System.out.println(">>CustomerService.login():The Customer " + customer.getFullname() + " is Login!");
			request.getSession().setAttribute("loggedCustomer", customer);
			/*
			 * message = "Welcome customer: " + email; request.setAttribute("message",
			 * message);
			 */
			showCustomerProfile();

		}
		// if isLogin == false forword to login.jsp page
		else {
			// OK
			System.out.println(">>CustomerService.login():Customer is NOT log!");

			message = "email or password are not correct.Please try again";
			request.setAttribute("message", message);

			// request.getRequestDispatcher("login.jsp").forward(request, response);
			showLogin();

		}
	}

	public void showCustomerProfile() throws ServletException, IOException {

		request.getRequestDispatcher("frontend/customer_profile.jsp").forward(request, response);

	}

	public void showCustomerProfileEditForm() throws ServletException, IOException {
		/*
		 * Customer loggedCustomer =
		 * (Customer)request.getSession().getAttribute("loggedCustomer");
		 * System.out.println(">>CustomerService.showCustomerProfileEditForm():" +
		 * "customer full name: " + loggedCustomer.getFullname())
		 */;

		request.getRequestDispatcher("frontend/edit_profile.jsp").forward(request, response);

		/*
		 * String email = request.getParameter("email");
		 * System.out.println("CustomerService.editCustomer():email = " +email);
		 * 
		 * this.customerDAO.openCurrentSession(); Customer customer =
		 * customerDAO.findByEmail(email); this.customerDAO.closeCurrentSession();
		 * 
		 * System.out.
		 * println("CustomerService.editCustomer():customer fullname from db = " +
		 * customer.getFullname());
		 * 
		 * request.setAttribute("customer", customer);
		 */

	}

	public void updateCustomerProfile() throws ServletException, IOException {
		// get the session
		HttpSession session = request.getSession();

		// save the session attribute 'loggedCustomer' in 'customer
		Customer customer = (Customer) session.getAttribute("loggedCustomer");

		// update the Customer object in memory
		readFormFields(customer);

		// update the Customer in database
		this.customerDAO.openCurrentSessionWithTransaction();
		this.customerDAO.update(customer);
		this.customerDAO.closeCurrentSessionWithTransaction();

		// forward the request to the customer_profile.jsp
		this.showCustomerProfile();
	}

	// Util Methods
	private void readFormFields(Customer newCustomer) {

		String email = request.getParameter("email");
		String fullName = request.getParameter("fullName");
		String password = request.getParameter("password");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String zipCode = request.getParameter("zipCode");
		String country = request.getParameter("country");

		// In the case of request from edit_profile.jsp - this condition is not
		// satisfied!
		if (email != null && !email.equals(""))
			newCustomer.setEmail(email);

		// In the case of request from edit_profile.jsp - this condition is not
		// satisfied!
		newCustomer.setFullname(fullName);
		if (password != null && !password.equals(""))
			newCustomer.setPassword(password);

		newCustomer.setPassword(password);
		newCustomer.setPhone(phone);
		newCustomer.setAddress(address);
		newCustomer.setCity(city);
		newCustomer.setZipcode(zipCode);
		newCustomer.setCountry(country);
	}

}