package org.nir.bookstore.controller.admin; 

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.OrderDAO;
import org.nir.bookstore.dao.ReviewDAO;
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
		List<BookOrder> listRecentSales ; 
		List<Review> listMostRecent; 
		
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
		
		
		//add the lists to the request attributes
		request.setAttribute("listRecentSales", listRecentSales);
		request.setAttribute("listMostRecent", listMostRecent);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	 @Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}


		

}
