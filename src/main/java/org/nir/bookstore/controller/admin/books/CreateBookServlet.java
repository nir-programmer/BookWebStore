package org.nir.bookstore.controller.admin.books;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.BookService;

/**
 * Servlet implementation class CreateBookServlet
 */
@WebServlet("/admin/create_book")
@MultipartConfig(
	fileSizeThreshold = 1024 * 10, //10 KB
	maxFileSize = 1024 * 300  ,//300KB
	maxRequestSize = 1024 * 1024 //1MB
		)
public class CreateBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CreateBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		BookService bookService = new BookService(request, response); 
		
		bookService.createBook();
	}

}
