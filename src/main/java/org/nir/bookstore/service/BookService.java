package org.nir.bookstore.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.dao.CategoryDAO;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Review;
import org.xml.sax.InputSource;

import net.bytebuddy.asm.Advice.Exit;

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

	private void listBooks(String message) throws ServletException, IOException {
		if (message != null)
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
		// Assignment 11
		/* request.getRequestDispatcher("books_list.jsp").forward(request, response); */

		// CommonUtility.forwardToPage(request, response, "books_list.jsp");
		CommonUtitlity.forwardToPage("books_list.jsp", request, response);
	}

	public void listBooks() throws ServletException, IOException {
		listBooks(null);

	}

	public void showBookNewForm() throws ServletException, IOException {

		this.categoryDAO.openCurrentSessionWithTransaction();
		List<Category> categories = categoryDAO.listAll();
		this.categoryDAO.closeCurrentSessionWithTransaction();

		System.out.println(">>BookService.showBookNewForm(): list of categoires: ");
		categories.forEach(c -> System.out.println(c.getName()));

		request.setAttribute("categories", categories);
		request.getRequestDispatcher("book_form.jsp").forward(request, response);

	}

	public void createBook() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");

		bookDao.openCurrentSessionWithTransaction();
		Book existBook = this.bookDao.findByTitle(title);
		bookDao.closeCurrentSessionWithTransaction();

		if (existBook != null) {
			String message = "could not create a book.A book with a title " + title
					+ " because there is already a book with this title";
			// request.setAttribute("message", message);
			listBooks(message);
			return;
		}

		// Create a Book
		Book newBook = new Book();

		// read the forms fields into newBook
		readBookFields(newBook);

		bookDao.openCurrentSessionWithTransaction();
		Book createdBook = bookDao.create(newBook);
		bookDao.closeCurrentSessionWithTransaction();

		if (createdBook.getBookId() > 0) {

			String message = "A new book has been created successfully.";
			// request.setAttribute("message", message);
			// refresh the book list
			listBooks(message);
		}
	}

	// read the values of the book in the form fields into a given Book
	public void readBookFields(Book book) throws IOException, ServletException {
		/*
		 * Read values {author , isbn price , description , categoryId , title ,
		 * publishDate ,image} from the form into local variables
		 */
		String author = request.getParameter("author");
		String isbn = request.getParameter("isbn");
		float price = Float.parseFloat(request.getParameter("price"));
		String description = request.getParameter("description");
		Integer categoryId = Integer.parseInt(request.getParameter("category"));
		String title = request.getParameter("title");

		// Read the image from the post multi-part request
		Part part = request.getPart("bookImage");

		// read the category
		this.categoryDAO.openCurrentSessionWithTransaction();
		Category category = categoryDAO.get(categoryId);
		this.categoryDAO.closeCurrentSessionWithTransaction();
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy");

		// Read publishDate
		Date publishDate = null;
		try {
			publishDate = format.parse(request.getParameter("publishDate"));
		} catch (ParseException e) {
			e.printStackTrace();
			throw new ServletException(
					"Error Parsing the String of publishDate - the required " + "format is MM/dd/yyyy ");
		}

		System.out.println("VALUES RED FROM THE FORM BEFORE SETTING TO THE LOCAL BOOK :");

		System.out.println("categoryId = " + categoryId);
		System.out.println("bookTitle = " + title);
		System.out.println("author = " + author);
		System.out.println("isbn = " + isbn);
		System.out.println("price = " + price);
		System.out.println("publishDate = " + publishDate);
		System.out.println("description =  " + description);

		/*
		 * READ ALL LOCALES VALUES(except the id) INTO THE BOOK PARAMATER
		 */
		book.setTitle(title);
		book.setAuthor(author);
		book.setDescription(description);
		book.setIsbn(isbn);
		book.setPublishDate(publishDate);
		book.setPrice(price);
		book.setCategory(category);

		/*
		 * check if there is data and in the part variable. If there is . read it by
		 * using inputStream into an array of bytes. Read the array of bytes into the
		 * book
		 */
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

	}

	public void editBook() throws ServletException, IOException {
		Integer bookId = Integer.parseInt(request.getParameter("id"));

		this.categoryDAO.openCurrentSessionWithTransaction();
		List<Category> categories = categoryDAO.listAll();
		this.categoryDAO.closeCurrentSessionWithTransaction();

		System.out.println(">>BookService.showBookNewForm(): list of categoires: ");
		categories.forEach(c -> System.out.println(c.getName()));

		bookDao.openCurrentSessionWithTransaction();
		Book book = bookDao.get(bookId);
		System.out.println(">>BookService.editBook(): the id of the book = " + bookId);
		bookDao.closeCurrentSessionWithTransaction();

		// This is the name of the paramater I test in the book_form.jsp
		request.setAttribute("book", book);
		request.setAttribute("categories", categories);
		// forward to the form
		request.getRequestDispatcher("book_form.jsp").forward(request, response);

	}

	public void updateBook() throws ServletException, IOException {
		System.out.println(">>BookService.updateBook():Book ID: " + request.getParameter("bookId"));
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		String title = request.getParameter("title");

		bookDao.openCurrentSessionWithTransaction();
		// get the book from the db
		Book existBook = bookDao.get(bookId);
		Book bookByTitle = bookDao.findByTitle(title);
		bookDao.closeCurrentSessionWithTransaction();

		System.out.println("BookService.update(): bookByTitle = " + bookByTitle);
		// if the title of the book title belongs to the edited book - save the edited
		// book
		if (bookByTitle != null && !bookByTitle.equals(existBook)) {
			System.out.println("Books are equals");
			String message = "Could not update the book becuase there's another book having " + "the title: '" + title
					+ "' already!";
			System.out.println("BookService.update(): " + message);
			listBooks(message);
			return;
		}

		System.out.println("Books are equals");
		// read the form values into the existing book - it's ok there is only one
		readBookFields(existBook);

		bookDao.openCurrentSessionWithTransaction();
		bookDao.update(existBook);
		bookDao.closeCurrentSessionWithTransaction();

		String message = "Book updated successfully";
		listBooks(message);

	}

	public void deleteBook() throws ServletException, IOException {
		Integer bookId;
		int numberOfReviews;
		Book book;
		Set<Review> reviews;
		String message = "";

		bookId = Integer.parseInt(request.getParameter("id"));
		bookDao.openCurrentSessionWithTransaction();
		book = bookDao.get(bookId);
		// first check if there is a book (or it has been deleted by another admin)
		if (book == null) {
			bookDao.closeCurrentSessionWithTransaction();
			message = "Could not find book with ID " + bookId + ", or it has been deleted by another admin";
			CommonUtitlity.showMessageBackend(message, request, response);
			return;
		}

		// Here I know there is a book with this id
		else {
			// find number of reviews before deleting it
			reviews = book.getReviews();
			numberOfReviews = reviews.size();

			// if there is one or more reviews - dont delete and the message
			if (numberOfReviews >= 1) {
				message = "Couldn't delete book with id :" + bookId + " becuase there are " + numberOfReviews
						+ " reviews for this book";
				System.out.println(message);
				CommonUtitlity.showMessageBackend(message, request, response);
			}

			else 
			{
				this.bookDao.delete(bookId);
				message = "The book was deleted successfully";
				System.out.println(message);
				bookDao.closeCurrentSessionWithTransaction();
				listBooks(message);
			}

		}
	}

	public void listBooksByCategory() throws ServletException, IOException {
		Integer categoryId = Integer.parseInt(request.getParameter("id"));
		System.out.println(">>BookService.listBooksByCategory():category id  = " + categoryId);
		String message = "";
		// Assignment 9 : Check if there is a category with this id
		this.categoryDAO.openCurrentSession();
		Category category = this.categoryDAO.get(categoryId);
		if (category == null) {
			this.categoryDAO.closeCurrentSession();
			/*
			 * message = "Sorry, the category ID " + categoryId +" is not available";
			 * System.out.println(">>BookService.listBooksByCategory():" + message);
			 * request.setAttribute("message", message);
			 * request.getRequestDispatcher("frontend/message.jsp").forward(request,
			 * response);
			 */

			// Assignment 11: invoke the showMessageFrontEnd() method
			message = "Sorry, the category ID " + categoryId + " is not available";
			// CommonUtility.showMessageFrontend(request, response, message);
			CommonUtitlity.showMessageFrontend(message, request, response);
			return;
		}

		this.categoryDAO.closeCurrentSession();

		bookDao.openCurrentSession();
		List<Book> books = bookDao.listByCategory(categoryId);
		bookDao.closeCurrentSession();

		request.setAttribute("books", books);

		// request.getRequestDispatcher("frontend/books_list_by_category.jsp").forward(request,
		// response);
		// Assignment 11 : call the CommonUtiliy.forwaordToPage() method
		// CommonUtility.forwardToPage(request, response,
		// "frontend/books_list_by_category.jsp");
		CommonUtitlity.forwardToPage("frontend/books_list_by_category.jsp", request, response);
	}

	public void viewBookDetails() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		bookDao.openCurrentSession();
		Book book = bookDao.get(id);
		bookDao.closeCurrentSession();

		/*
		 * categoryDAO.openCurrentSession(); List<Category> categories =
		 * categoryDAO.listAll(); // String categoryName = category.getName();
		 * categoryDAO.closeCurrentSession();
		 */

		if (book != null) {
			request.setAttribute("book", book);
		} else {
			String message = "Sorry, the book with ID " + id + " is not available.";
			request.setAttribute("message", message);
		}
		// request.setAttribute("book", book);
		// request.setAttribute("categories", categories);
		request.getRequestDispatcher("frontend/book_details.jsp").forward(request, response);
	}

	public void search() throws ServletException, IOException {

		String keyword = request.getParameter("keyword");
		List<Book> result = null;

		bookDao.openCurrentSession();

		if (keyword.equals(""))
			result = bookDao.listAll();
		else
			result = bookDao.search(keyword);

		bookDao.closeCurrentSession();

		System.out.println("BookService.search() - Results:");
		result.forEach(c -> System.out.println(c.getTitle()));

		request.setAttribute("result", result);
		request.setAttribute("keyword", keyword);
		request.getRequestDispatcher("frontend/search_result.jsp").forward(request, response);
	}

}