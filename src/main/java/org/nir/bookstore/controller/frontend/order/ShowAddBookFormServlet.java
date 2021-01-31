package org.nir.bookstore.controller.frontend.order;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.entities.Book;


@WebServlet("/admin/add_book_form")
public class ShowAddBookFormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ShowAddBookFormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    /*
     * IMPORTANT:No delegation to the OrderService. 
     * Instead , directly delegate to the BookDAO 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		BookDAO bookDAO;
		List<Book> books;
		String addFormPage ;
		//1.Create the BookDAO bookDAO object
		bookDAO  = new BookDAO(); 
		bookDAO.openCurrentSession();
		//2.read list of all books in the database
		books = bookDAO.listAll();
		bookDAO.closeCurrentSession();
		
		
		//3.Set this list in the request
		request.setAttribute("books", books);
		
		//4.Forward this list to the add_form_page.jsp
		addFormPage = "add_book_form.jsp";
		request.getRequestDispatcher(addFormPage).forward(request, response);
		
	}

}
