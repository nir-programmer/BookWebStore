package org.nir.bookstore.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.OrderDetail;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.ShippingAddress;
import com.paypal.api.payments.ShippingInfo;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

import net.bytebuddy.utility.privilege.GetSystemPropertyAction;

public class PaymentService
{
	// These values are taken from the PayPal's dashboard and they will be used by
	// the Auth2.0 authentication
	private static final String CLIENT_ID = "AbdfyUrNzAKdoCt-aqVBoImFoJMHo4Uv56pN5vLnYSFBF1utdNcIio5gn6yFmVc7iq-RCmsp6YQE0AOP";
	private static final String CLIENT_SECRET = "EGsqHDiaa9vqUGIBkIuJyOsh65ljdLDjkWux8NthqW0C_yXFIIBH_0c8VdaPgprkCMxdlI8QBfpnk3bY";

	// current mode is set to sandbox
	private String mode = "sandbox";

	private HttpServletRequest request;
	private HttpServletResponse response;

	public PaymentService(HttpServletRequest request, HttpServletResponse response)
	{
		super();
		this.request = request;
		this.response = response;
	}

	public void authorizePayment(BookOrder order) throws ServletException, IOException
	{
		// 1.get payer information(using the paypal jars from maven)
		Payer payer = getPayerInformation(order);

		// 2.get redirect URL's information
		RedirectUrls redirectUrls = getRedirectsURLs();

		// 3.get amount details(subtotal , tax , shipping fee, total)
		// Dont need this here anymore - I will create this object in the
		// getTransaction() method...
		// Amount amount = getAmountDetails(order);

		// 4.shipping address (recipient info)

		// 5.get transaction details()
		List<Transaction> transactions = getTransactoinInformation(order);

		// 6.request payment (verification)
		Payment requestPayment = new Payment();
		requestPayment.setPayer(payer).setRedirectUrls(redirectUrls).setIntent("authorize")
				.setTransactions(transactions);

		// For testing purpose - print the content of the the Payment object(in JSON)
		// BEFORE the request to PayPal
		System.out.println("====== REQUEST PAYMENT ======");
		System.out.println(requestPayment);

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);

		try {
			Payment authorizedPayment = requestPayment.create(apiContext);
			// For testing purpose - print the content of the the Payment object(in JSON)
			// AFTER the request to PayPal
			System.out.println("====== AUTHORIZED PAYMENT ======");
			System.out.println(authorizedPayment);

			// extract approval URL's
			String approvalURL = getApprovalURL(authorizedPayment);

			// Redirect the customer to the PayPal's page
			response.sendRedirect(approvalURL);
		} catch (PayPalRESTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("Error in authorizing payment.");
		}

		// 7.get approval link(

		// if the last approval link was 'valid')
		// 8.Redirect to PayPal's payment page( in the approval link)

	}

	private String getApprovalURL(Payment authorizedPayment)
	{
		String approvalURL = null;

		List<Links> links = authorizedPayment.getLinks();

		for (Links link : links) {
			// the "approval_url" is from the JSON response! (rel: "approval_url")
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalURL = link.getHref();
				break;
			}
		}

		return approvalURL;
	}

	// Util method to get the payer information based on the order
	private Payer getPayerInformation(BookOrder order)
	{
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		PayerInfo payerInfo = new PayerInfo();
		Customer customer = order.getCustomer();
		payerInfo.setFirstName(customer.getFirstname());
		payerInfo.setLastName(customer.getLastname());
		payerInfo.setEmail(customer.getEmail());
		payer.setPayerInfo(payerInfo);

		return payer;
	}

	// Util method to construct the 2 links required by PayPal
	private RedirectUrls getRedirectsURLs()
	{
		String requestURL = request.getRequestURL().toString();
		String requestURI = request.getRequestURI();

		// The base URL of the application
		String baseURL = requestURL.replace(requestURI, "").concat(request.getContextPath());

		RedirectUrls redirectUrls = new RedirectUrls();

		// The cancel URL points to the Servlet that forward to the shopping cart jsp
		// page
		String cancelUrl = baseURL.concat("/view_cart");

		// The returnUrl points to the Servlet that forward to the review payment.jsp
		// page
		String returnUrl = baseURL.concat("/review_payment");

		// For testing purpose:
		System.out.println("Return URL: " + returnUrl);
		System.out.println("Cancel URL: " + cancelUrl);

		redirectUrls.setCancelUrl(cancelUrl);
		redirectUrls.setReturnUrl(returnUrl);

		return redirectUrls;
	}

	// Util method to get the amount information required by PayPal
	private Amount getAmountDetails(BookOrder order)
	{
		Details details = new Details();

		// These values need to be converted to String!
		/*
		 * details.setShipping(String.valueOf(order.getShippingFee()));
		 * details.setTax(String.valueOf(order.getTax()));
		 * details.setSubtotal(String.valueOf(order.getSubtotal()));
		 */
		// PayPal response : set the format to 2 digits after the dot
		details.setShipping(String.format("%.2f", order.getShippingFee()));
		details.setTax(String.format("%.2f", order.getTax()));
		details.setSubtotal(String.format("%.2f", order.getSubtotal()));

		Amount amount = new Amount();
		// Fix
		amount.setCurrency("USD");
		amount.setDetails(details);
		amount.setTotal(String.format("%.2f", order.getTotal()));

		return amount;
	}

	// Util method to get the Shipping Address information required by PayPal
	private ShippingAddress getRecipientInformation(BookOrder order)
	{
		ShippingAddress shippingAddress = new ShippingAddress();

		/*
		 * Read the recipient information from the BookOrder object into the
		 * shippingAddress object by chaining code
		 */
		shippingAddress.setRecipientName(order.getFirstname() + " " + order.getLastname()).setPhone(order.getPhone())
				.setLine1(order.getAddressLine1()).setLine2(order.getAddressLine2()).setCity(order.getCity())
				.setState(order.getState()).setCountryCode(order.getCountry()).setPostalCode(order.getZipcode());

		return shippingAddress;

	}

	// Util method to get the Shipping Transaction infomation required by PayPal
	private List<Transaction> getTransactoinInformation(BookOrder order)
	{
		Transaction transaction = new Transaction();
		transaction.setDescription("Books ordered on Evergreen Books");

		Amount amount = getAmountDetails(order);
		transaction.setAmount(amount);

		ItemList itemList = new ItemList();
		ShippingAddress shippingAddress = getRecipientInformation(order);
		itemList.setShippingAddress(shippingAddress);

		List<Item> paypalItems = new ArrayList<>();

		// iterate over the items in the OrderDetail Set of the BookOrder object
		Iterator<OrderDetail> iterator = order.getOrderDetails().iterator();

		while (iterator.hasNext()) {
			OrderDetail orderDetail = iterator.next();
			Book book = orderDetail.getBook();
			// number of items of this book in the order
			Integer quantity = orderDetail.getQuantity();

			// Create a new PayPal Item(Chaining)
			Item paypalItem = new Item();
			paypalItem.setCurrency("USD").setName(book.getTitle()).setQuantity(String.valueOf(quantity))
					.setPrice(String.format("%.2f", book.getPrice()));

			// add the paypal item to the paypals list of item
			paypalItems.add(paypalItem);
		}

		// set the List<Item> into the itemList object
		itemList.setItems(paypalItems);

		// Set the list of items in the transaction object
		transaction.setItemList(itemList);

		// Create a list of PayPal Transaction objects
		List<Transaction> listTransactions = new ArrayList<>();

		// add the transaction to the list
		listTransactions.add(transaction);

		return listTransactions;

	}

	public void reviewPayment() throws ServletException, IOException
	{
		// read the 2 query parameters from the PayPal response:
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");

		if (paymentId == null || payerId == null) {
			throw new ServletException("Error in display payment review");
		}

		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode);

		// Try get a payment object(connect to the PayPal server)
		try {
			Payment payment = Payment.get(apiContext, paymentId);

			// for displaying in the first section(payer information) of the
			// review_payment.jsp
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			request.setAttribute("payer", payerInfo);

			// for displaying in the second section(recipient information) of the
			// review_payment.jsp
			Transaction transaction = payment.getTransactions().get(0);
			ShippingAddress shippingAddress = transaction.getItemList().getShippingAddress();
			request.setAttribute("recipient", shippingAddress);

			// for displaying in the third section(transaction information) of the
			// review_payment.jsp
			request.setAttribute("transaction", transaction);
			
			
			

			// forward to review_payment.jsp page with the 2 query parameters
			String reviewPage = "frontend/review_payment.jsp?paymentId=" + 
								paymentId +"&PayerID="+ payerId; 
			request.getRequestDispatcher(reviewPage).forward(request, response);

		} catch (PayPalRESTException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new ServletException("Error in  getting payment details from PayPal");
		}

		// Test these values are not null = OK
		  System.out.println(">>PaymernServcie.reviewPayement():\npaymentId = " +
		  paymentId + "\nPayerID = " + payerId);
		 

	}

	public Payment executePayment() throws PayPalRESTException
	{
		Payment  resPayment= null; 
		// read the 2 request's parameters:paymentId , PayerID(hidden inputs (${param.attriute})
		String paymentId = request.getParameter("paymentId");
		String payerId = request.getParameter("PayerID");
		
		
		//Test the values
		 System.out.println(">>PaymernServcie.executePayement():\npaymentId = " +
				  paymentId + "\nPayerID = " + payerId);
		 
		 
		 PaymentExecution paymentExecution = new PaymentExecution().setPayerId(payerId);
		 
		 Payment payment = new Payment().setId(paymentId); 
		 
		 //conect the PayPal Server
		 APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, mode); 
		 
		 
		 
		 //execute the payment and return it's return  Payment object 
		resPayment = payment.execute(apiContext, paymentExecution);
		
		System.out.println(">>PaymernServcie.executePayement():\n\n\nThe return Payment Id from the "
				+ "payment.execute() method is: " + resPayment.getId() );
		
		return resPayment;
		 

	}

}
