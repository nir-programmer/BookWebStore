package org.nir.bookstore.controller.frontend.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nir.bookstore.service.OrderService;
import org.nir.bookstore.service.PaymentService;

import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.Transaction;
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
			
			Payment payment = paymentService.executePayment();
			
			//store the BookOrder into the DATABASE
			OrderService orderService = new OrderService(request, response);
			
			Integer orderId = orderService.placeOrderPaypal(payment); 
			
			//add the order id of the newly create order in the databaseto the session
			HttpSession session = request.getSession();
			session.setAttribute("orderId" , orderId);
			
			PayerInfo payerInfo = payment.getPayer().getPayerInfo();
			Transaction transaction =  payment.getTransactions().get(0); 
			//No need shipping address here!
			
			/*
			 * WRONG:I need to set this into the session to be used in 
			 * receipt pop up window!!!!
			 * request.setAttribute("payer", payerInfo);
				request.setAttribute("transaction", transaction);
			 */
			session.setAttribute("payer", payerInfo);
			session.setAttribute("transaction", transaction);
			
			
			
			//Forward to the payment_reciept.jsp page
			String receiptPage = "frontend/payment_receipt.jsp"; 
			request.getRequestDispatcher(receiptPage).forward(request, response);
			
		} 
		catch (PayPalRESTException e) 
		{
			e.printStackTrace();
			throw new ServletException("Error in executing payment");
		} 
		
	}

}
