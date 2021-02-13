package org.nir.bookstore.controller.frontend.order;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.OrderService;
import org.nir.bookstore.service.PaymentService;


@WebServlet("/review_payment")
public class ReviewPaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReviewPaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		PaymentService paymentService = new PaymentService(request, response);
		
		paymentService.reviewPayment();
	}
		

}
