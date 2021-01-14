package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.ReviewService;

/**
 * Servlet implementation class WriteReviewServlet
 */
@WebServlet("/write_review")
public class WriteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public WriteReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		ReviewService  reviewService = new ReviewService(request, response);
		reviewService.showReviewForm() ;
		
	}

}
