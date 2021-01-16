package org.nir.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.dao.ReviewDAO;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.Review;

public class ReviewService
{
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ReviewDAO reviewDAO;

	public ReviewService(HttpServletRequest request, HttpServletResponse response)
	{
		this.request = request;
		this.response = response;
		this.reviewDAO = new ReviewDAO();
	}

	public void listAllReviews() throws ServletException, IOException
	{
		listAllReviews(null);

	}

	public void listAllReviews(String message) throws ServletException, IOException
	{
		if (message != null)
			request.setAttribute("message", message);

		this.reviewDAO.openCurrentSession();
		List<Review> reviews = reviewDAO.listAll();
		this.reviewDAO.closeCurrentSession();

		System.out.println(">>ReviewService.listAllReviews():reviews:");
		reviews.forEach(r -> System.out.println(r.getBook()));

		request.setAttribute("reviews", reviews);
		request.getRequestDispatcher("reviews_list.jsp").forward(request, response);
	}

	public void editReview() throws ServletException, IOException
	{

		// Fetch the review id from the request -OK
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		System.out.println(">>ReviewService.editReview():id = " + reviewId);

		// Find the Review in the database - OK
		this.reviewDAO.openCurrentSession();

		Review review = this.reviewDAO.get(reviewId);

		// Assingment 15 - check if the reviewId exists before editing!
		if (review == null) {
			this.reviewDAO.closeCurrentSession();
			CommonUtitlity.showMessageBackend("Could not find review with ID " + reviewId + ".", request, response);
			return;
		}

		this.reviewDAO.closeCurrentSession();
		System.out.println(">>ReviewService.editReview():headline = " + review.getHeadline());

		// set the review in the request
		request.setAttribute("review", review);

		// forward the request to the edit_review.jsp page
		// request.getRequestDispatcher("edit_review.jsp").forward(request, response);

		CommonUtitlity.forwardToPage("edit_review.jsp", request, response);

	}

	public void updateReview() throws ServletException, IOException
	{
		String message = "";
		Integer reviewId;
		String headline;
		String comment;

		// fetch the reviewId from the hidden field of the form -> OK
		reviewId = Integer.parseInt(request.getParameter("reviewId"));
		System.out.println(">>ReviewService.updateReview():reviewId = " + reviewId);

		// fetch the fields : comment , headline
		headline = request.getParameter("headline");
		comment = request.getParameter("comment");
		System.out.println(">>ReviewService.updateReview():headline " + reviewId + " , comment " + comment);

		// read the review with this id from the database into review object
		this.reviewDAO.openCurrentSessionWithTransaction();
		Review review = this.reviewDAO.get(reviewId);

		// read and set the form fields into the review object
		review.setHeadline(headline);
		review.setComment(comment);

		// update the review in the data base
		this.reviewDAO.update(review);
		this.reviewDAO.closeCurrentSessionWithTransaction();

		// forward to the reviews_list.jsp page with a success message
		message = "The review has been updated successfully!";
		this.listAllReviews(message);
	}

	public void deleteReview() throws ServletException, IOException
	{

		// fetch the reviewId from the hidden field of the form -> OK
		Integer reviewId = Integer.parseInt(request.getParameter("id"));
		System.out.println(">>ReviewService.updateReview():reviewId = " + reviewId);
		String message = "";

		this.reviewDAO.openCurrentSessionWithTransaction();
		Review review = this.reviewDAO.get(reviewId);

		// Assignment 16: check if there is reviewId . if not invoke the
		// showMessageBackend()
		if (review == null) {
			this.reviewDAO.closeCurrentSessionWithTransaction();
			message = "Could not find a review with ID " + reviewId + ",\n"
					+ "	 or it might have been deleted by another admin";
			System.out.println(">>ReviewService.updateReview():the review = " + review);
			request.setAttribute("message", message);
			CommonUtitlity.showMessageBackend(message, request, response);
			return;
		}

		message = "Review has been deleted";
		this.reviewDAO.delete(reviewId);

		this.reviewDAO.closeCurrentSessionWithTransaction();
		request.setAttribute("message", message);
		this.listAllReviews(message);

	}

	public void showReviewForm() throws ServletException, IOException
	{
		Integer bookId;
		BookDAO bookDAO;
		String targetPage;
		Book book;
		HttpSession session; 

		// target page - review_form.jsp
		targetPage = "frontend/review_form.jsp";

		// fetch the id of the book from the request
		bookId = Integer.parseInt(request.getParameter("book_id"));

		// fetch the Book from the data base
		bookDAO = new BookDAO();
		bookDAO.openCurrentSession();
		book = bookDAO.get(bookId);
		bookDAO.closeCurrentSession();

		// set the book as an attribute in the request
		request.setAttribute("book", book);
		
		
		// forward the request to the review_form.jsp page
		request.getRequestDispatcher(targetPage).forward(request, response);

	}

	public void submitReview() throws ServletException, IOException
	{
		  ReviewDAO reviewDAO;
		  Review review; 
		  Book book; 
		  Customer customer; 
		  String  targetPage;
		 
		  //form fields
		  Integer bookId ; 
		  String headline ;
		  Integer rating ; 
		  String comment; 
		  
		  //Ok
		  headline = request.getParameter("headline");
		  System.out.println(">>ReviewService.submitReview():headline = " + headline); 
		   //OK
		  comment = request.getParameter("comment");
		  System.out.println(">>ReviewService.submitReview():comment = " + comment); 
		  
		  //OK
		  bookId = Integer.parseInt(request.getParameter("bookId")); 
		  System.out.println(">>ReviewService.submitReview():bookId = " + bookId); 
			
		  //OK
		  rating = Integer.parseInt(request.getParameter("rating"));
		  System.out.println(">>ReviewService.submitReview():rating = " + rating);
			 
		 //Create a new Book with the bookId value
		  book = new Book();
		  book.setBookId(bookId);
		  
		  //Refer to the customer in the session
		  customer = (Customer)request.getSession().getAttribute("loggedCustomer");
		  
		  /*
		   * Check if the customer has already posted on this book and implemnet the 2 
		   * branched in the activity diagram accordingly 
		   */
		  Integer customerId = customer.getCustomerId();
		  
		  
		  reviewDAO = new ReviewDAO();
		  reviewDAO.openCurrentSession();
		  Review existReview = reviewDAO.findByCustomerAndBook(customerId, bookId);
		  reviewDAO.closeCurrentSession();
		  
		  targetPage = "frontend/review_form.jsp"; 
		  //if there is a review for this book by this customer: forward to info_review.jsp
		  if(existReview != null)
		  {
			  System.out.println(">>ReviewService.showReviewForm(): there is a review with bookId = "+ bookId
					  +" and customerId =  "  + customerId);
			  request.setAttribute("review", existReview);
			  targetPage = "frontend/review_info.jsp";
		  }
		  
		  //The customer has not posted to this book yet -save review and forward to review_done.jsp
		  else
		  {
			  System.out.println(">>ReviewService.showReviewForm(): there is no review with bookId = "+ bookId
					  +" and customerId =  "  + customerId);
		  //Create a new Review object
		  review = new Review();
		  review.setBook(book);
		  review.setCustomer(customer);
		  review.setHeadline(headline);
		  review.setComment(comment);
		  review.setRating(rating);
		  
		  
		  //Save the Review in the database
		 reviewDAO = new ReviewDAO();
		 reviewDAO.openCurrentSessionWithTransaction();
		 reviewDAO.create(review);
		 reviewDAO.closeCurrentSessionWithTransaction();
		 
		 //forward the request to the review_done.jsp page
		 targetPage = "frontend/review_done.jsp";
		  }
		  request.getRequestDispatcher(targetPage).forward(request, response);
	}

}
