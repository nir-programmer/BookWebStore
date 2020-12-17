package org.nir.bookstore.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.Category;

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
		
		List<Category> categories  = new ArrayList<>() ;
		for(Book book: books)
			categories.add(book.getCategory());
		
		System.out.println(">>BookService.listBooks() List of all categories:");
		categories.forEach(System.out::println);
		bookDao.closeCurrentSessionWithTransaction();
		
		
		
		request.setAttribute("books", books);
		request.getRequestDispatcher("books_list.jsp").forward(request, response);
		
	}
	
	
	

}
