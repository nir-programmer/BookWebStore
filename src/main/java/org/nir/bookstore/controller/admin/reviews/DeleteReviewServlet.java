package org.nir.bookstore.controller.admin.reviews;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.entities.Review;
import org.nir.bookstore.service.ReviewService;

/**
 * Servlet implementation class DeleteReviewServlet
 */
@WebServlet("/admin/delete_review")
public class DeleteReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public DeleteReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ReviewService reviewService = new ReviewService(request, response);
		reviewService.deleteReview();
	}

}
