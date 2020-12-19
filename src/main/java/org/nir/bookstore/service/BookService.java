package org.nir.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.dao.CategoryDAO;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.Category;
import org.xml.sax.InputSource;

public class BookService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private BookDAO bookDao;
	private CategoryDAO categoryDAO;

	public BookService(HttpServletRequest request, HttpServletResponse response) {
		bookDao = new BookDAO();
		this.request = request;
		this.response = response;
		this.categoryDAO = new CategoryDAO();
	}

	
	private void listBooks(String message) throws ServletException, IOException 
	{
		if(message != null)
			request.setAttribute("message", message);
		
		bookDao.openCurrentSessionWithTransaction();
		List<Book> books = this.bookDao.listAll();
		
		List<Category> categories = new ArrayList<>();
		for (Book book : books)
			categories.add(book.getCategory());

		System.out.println(">>BookService.listBooks() List of all categories:");
		categories.forEach(System.out::println);
		
		bookDao.closeCurrentSessionWithTransaction();
		
		request.setAttribute("books", books);
		request.getRequestDispatcher("books_list.jsp").forward(request, response);
		
	}
	public void listBooks() throws ServletException, IOException
	{
		listBooks(null); 

	}

	public void showBookNewForm() throws ServletException, IOException {
		categoryDAO.openCurrentSessionWithTransaction();
		List<Category> categories = this.categoryDAO.listAll();

		System.out.println(">>BookService.showBookNewForm():categores:");
		categories.forEach(System.out::println);
		categoryDAO.closeCurrentSessionWithTransaction();

		this.request.setAttribute("categories", categories);

		request.getRequestDispatcher("book_form.jsp").forward(request, response);

	}

	public void createBook() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");
		
		bookDao.openCurrentSessionWithTransaction();
		Book existBook = this.bookDao.findByTitle(title);
		bookDao.closeCurrentSessionWithTransaction();
		
		if (existBook != null) 
		{
			String message = "could not create a book.A book with a title " + title
					+ " because there is already a book with this title";
			request.setAttribute("message", message);
			/* dont have to set the attirbute ...
			 * request.getRequestDispatcher("message.jsp").forward(request, response); */
			listBooks(message); 
			return; 
		}

		
			String author = request.getParameter("author");
			String isbn = request.getParameter("isbn");
			float price = Float.parseFloat(request.getParameter("price"));
			String description = request.getParameter("description");

			// Need to parse the String to Date
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			Date publishDate = null;
			try 
			{
				publishDate = format.parse(request.getParameter("publishDate"));
			}
			catch (ParseException e)
			{
				e.printStackTrace();
				throw new ServletException(
						"Error Parsing the String of publishDate - the required " + "format is MM/dd/yyyy ");
			}
			System.out.println("forms value except image :");

			System.out.println("categoryId = " + categoryId);
			System.out.println("bookTitle = " + title);
			System.out.println("author = " + author);
			System.out.println("isbn = " + isbn);
			System.out.println("price = " + price);
			System.out.println("description =  " + description);
			System.out.println("publishDate = " + publishDate);

			// Create a Book
			Book book = new Book();
			book.setTitle(title);
			book.setAuthor(author);
			book.setDescription(description);
			book.setIsbn(isbn);
			book.setPublishDate(publishDate);

			/*
			 * Important: in order to create a Category - I need to retrieve it form db
			 */
			this.categoryDAO.openCurrentSessionWithTransaction();
			Category category = categoryDAO.get(categoryId);
			this.categoryDAO.closeCurrentSessionWithTransaction();
			book.setCategory(category);

			book.setPrice(price);

			// Read the image from the post multi-part request
			Part part = request.getPart("bookImage");

			// check if there is a data and if there is - read it into a byte array
			if (part != null && part.getSize() > 0) {
				long size = part.getSize();
				byte[] imageBytes = new byte[(int) size];

				// open an inputStream from the part:
				InputStream inputStream = part.getInputStream();
				inputStream.read(imageBytes);
				// close the input stream
				inputStream.close();

				// set the image to the book
				book.setImage(imageBytes);

			}

			// finally - call the BookDAO.create method to save the book
			bookDao.openCurrentSessionWithTransaction();
			Book createdBook = bookDao.create(book);
			bookDao.closeCurrentSessionWithTransaction();
			if (createdBook.getBookId() > 0) 
			{
				String message = "A new book has been created successfully.";
				request.setAttribute("message", message);
				// refresh the book list
				listBooks(message);
			}
		}
	
}
