package org.nir.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.entities.Book;

public class BookService 
{
	private HttpServletRequest request; 
	private HttpServletResponse response;
	private BookDAO bookDao; 
	
	public BookService(HttpServletRequest request, HttpServletResponse response)
	{
		bookDao = new BookDAO() ;
		this.request = request;
		this.response = response;
	}

	public void listBooks() throws ServletException, IOException
	{
		bookDao.openCurrentSessionWithTransaction();
		List<Book> books = this.bookDao.listAll();
		bookDao.closeCurrentSessionWithTransaction();
		
		
		
		request.setAttribute("books", books);
		request.getRequestDispatcher("books_list.jsp").forward(request, response);
		
	}
	

}
