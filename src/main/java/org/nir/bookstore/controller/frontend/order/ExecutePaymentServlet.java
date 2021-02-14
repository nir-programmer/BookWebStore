package org.nir.bookstore.controller.frontend.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.OrderService;
import org.nir.bookstore.service.PaymentService;

import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;


@WebServlet("/execute_payment")
public class ExecutePaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public ExecutePaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		PaymentService paymentService = new PaymentService(request, response);
		try 
		{
			//IF the executePayment return - then the payment was OK 
			System.out.println(">>doPost():One line before executePayment()...");
			Payment payment = paymentService.executePayment();
			
			System.out.println("\n\n>>The return payment id from executePayment() "
					+ "method: " + payment.getId());
			//store the BookOrder into the DATABASE
			OrderService orderService = new OrderService(request, response);
			
			Integer orderId = orderService.placeOrderPaypal(payment); 
			
			//Display a successfull message to the client
			response.getWriter().println("Payment Succussful. Order ID: " + orderId);
			
		} 
		catch (PayPalRESTException e) 
		{
			e.printStackTrace();
			throw new ServletException("Error in executing payment");
		} 
		
	}

}
