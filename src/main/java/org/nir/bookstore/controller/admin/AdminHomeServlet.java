package org.nir.bookstore.controller.admin; 

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.dao.CustomerDAO;
import org.nir.bookstore.dao.OrderDAO;
import org.nir.bookstore.dao.ReviewDAO;
import org.nir.bookstore.dao.UsersDAO;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Review;

/**
 * Servlet implementation class AdminHomeServlet
 */
@WebServlet("/admin/")
public class AdminHomeServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
   
    public AdminHomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		OrderDAO orderDAO;
		ReviewDAO reviewDAO;
		UsersDAO usersDAO;
		BookDAO bookDAO;
		CustomerDAO customerDAO;
		List<BookOrder> listRecentSales ; 
		List<Review> listMostRecent; 
		
		long numberOfUsers, numberOfCustomers , numberOfOrders , numberOfReviews , numberOfBooks;
		
		
		
		//get the list of list recent sales from the data base
		orderDAO = new OrderDAO(); 
		orderDAO.openCurrentSession();
		listRecentSales = orderDAO.listRecentSales();
		orderDAO.closeCurrentSession();
		
		//get the list of most recent reviews form the data base
		reviewDAO = new ReviewDAO() ;
		reviewDAO.openCurrentSession();
		listMostRecent = reviewDAO.listmostRecent();
		reviewDAO.closeCurrentSession();
		
		
		
		
		//////////////update statistics///////////////////
		
		//Count books
		bookDAO = new BookDAO();
		bookDAO.openCurrentSession();
		numberOfBooks = bookDAO.count();
		bookDAO.closeCurrentSession();
		
		
		//count users
		usersDAO = new UsersDAO();
		usersDAO.openCurrentSession();
		numberOfUsers = usersDAO.count();
		usersDAO.closeCurrentSession();
		
		
		//count reviews
		reviewDAO = new ReviewDAO();
		reviewDAO.openCurrentSession();
		numberOfReviews = reviewDAO.count();
		reviewDAO.closeCurrentSession();
		
		
		//count customers
		customerDAO = new CustomerDAO();
		customerDAO.openCurrentSession();
		numberOfCustomers = customerDAO.count();
		customerDAO.closeCurrentSession();
		
		//count orders
		
		  orderDAO = new OrderDAO(); orderDAO.openCurrentSession(); numberOfOrders=
		  orderDAO.count(); orderDAO.closeCurrentSession();
		 
		
		//add the lists and statistics values to request attirbutes
				request.setAttribute("listRecentSales", listRecentSales);
				request.setAttribute("listMostRecent", listMostRecent);
				request.setAttribute("users", numberOfUsers);
				request.setAttribute("customers", numberOfCustomers);
				request.setAttribute("reviews", numberOfReviews);
				request.setAttribute("orders", numberOfOrders);
				request.setAttribute("books", numberOfBooks);
				
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	 @Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}


		

}
