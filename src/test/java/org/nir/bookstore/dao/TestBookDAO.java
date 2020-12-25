package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.Category;

public class TestBookDAO 
{
	
	private static BookDAO bookDAO = null;

	@BeforeAll
	@DisplayName("when create BookDAO object")
	public static void init() {
		bookDAO = new BookDAO();
	}

	@BeforeEach
	@DisplayName("when calling openCurrentSessionWithTransaction()")
	void testOpenCurrentSessionWithTransaction() {
		bookDAO.openCurrentSessionWithTransaction();
	}

	@AfterEach
	@DisplayName("when calling closeCurrentSessionWithTransaction()")
	void testcloseCurrentSessionWithTransaction() {
		bookDAO.closeCurrentSessionWithTransaction();
	}
	
	/**********************************************************
	 * 					TESTS
	 * @throws ParseException 
	 * @throws IOException 
	 * ************************************************************/
	
	//OK
	@Test
	@DisplayName("when create a Book by BookDAO")
	void testCreateBook() throws ParseException, IOException
	{
		Book book  = new Book();
		book.setLastUpdateTime(new Date());
		Category category = new Category(); 
		category.setCategoryId(11);
		book.setCategory(category);
		
		book.setTitle("Java 8 - Lambda Exprssions");
		book.setAuthor("Nir");
		book.setDescription("comprehnsive guide to Java 8");
		book.setPrice(34.99f);
		book.setIsbn("0123456"); 
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
		Date publishDate = dateFormat.parse("05/28/2008"); 
		book.setPublishDate(publishDate);
		
		String  imagePath = "/home/nir/Desktop/MyStudies/Udemy/Java-Servlet-JSP-Hibernate/BookStoreWebSite/src/main/webapp/images/smaller.png"; 
		
		
		//This method reads all bytes and returns an array of bytes
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath)); 
		
		book.setImage(imageByte);
		
		
		Book createdBook = bookDAO.create(book); 
		

	}
	
	@Test
	@DisplayName("when update a Book by BookDAO")
	void testUpdateBook() throws ParseException, IOException
	{
		Book existBook  = new Book();
		Integer id = 7; 
		existBook.setBookId(id);
		
		//book.setLastUpdateTime(new Date());
		//Create a new Category 
		Category category = new Category("Java Core"); 
		category.setCategoryId(129);
		existBook.setCategory(category);
		
		/*
		 * update the book edition to 3 nd from 2 nd. This column is a UNIQUE KEY in the data base
		 */
		existBook.setTitle(" Java  8(10 nd Edition)");
		existBook.setAuthor("Shalom");
		existBook.setDescription("New coverage of genereic , enums, annotations");
		existBook.setPrice(159.99f);
		existBook.setIsbn("0123456"); 
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
		Date publishDate = dateFormat.parse("05/28/2008"); 
		existBook.setPublishDate(publishDate);
		
		String  imagePath = "/home/nir/Desktop/MyStudies/Udemy/Java-Servlet-JSP-Hibernate/BookStoreWebSite/src/main/webapp/images/smaller.png"; 
		
		
		//This method reads all bytes and returns an array of bytes
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath)); 
		
		existBook.setImage(imageByte);
		
		
		Book updatedBook = bookDAO.update(existBook); 
		
		assertEquals(existBook.getCategory().getCategoryId(), updatedBook.getCategory().getCategoryId());
		
		System.out.println("The updated Book is "); 
		System.out.println(updatedBook) ;

	}
	
	//OK
	@Test
	@DisplayName("when delete a non exists book from db")
	void testDeleteBookFail()
	{
		Integer id = 80; 
		
		//bookDAO.delete(id);
		
		assertThrows(EntityNotFoundException.class, () -> bookDAO.delete(id));
	}
	
	//OK
	@Test
	@DisplayName("when delete an exists book")
	void testDeleteBookPass()
	{
		Integer id = 3 ;
		
		assertDoesNotThrow(() -> bookDAO.delete(id));
		
		System.out.println(">>testDeleteBookPass():Sucess Delete"); 
		
	}
	
	//OK
	@Test
	@DisplayName("when call get() on exist book")
	void testGetBookFound()
	{
		Integer id = 1; 
		Book book = bookDAO.get(id); 
		
		assertNotNull(book);
		
		System.out.println(">>testGetBookFound():The book title"); 
		System.out.println(book.getTitle());
		
	}
	
	//OK
	@Test
	@DisplayName("when call get on non existed book")
	void testGetBookNotFound()
	{
		Integer id = 4; 
		Book book = bookDAO.get(id);
		
		assertNull(book); 
		
		System.out.println(">>testGetBookNotFound");
		
		
		assertNull(book); 
		
	}
	
	//OK
	@Test
	@DisplayName("when calling listAll() method")
	void testListAll()
	{
		List<Book> books = bookDAO.listAll();
		
		assertEquals(0, books.size());
		
		System.out.println(">>testListAll():list of books in db:"); 
		books.forEach(c -> System.out.println(c.getTitle() + ", "  + c.getAuthor()));
			
	}
	
	//OK
	@Test
	@DisplayName("when calling findByTitle on exist title")
	void testFindByTitleFound()
	{
		String title = "Java 8 - Lambda Exprssions"; 
		Book book = bookDAO.findByTitle(title);
		
		
		assertNotNull(book);
		assertEquals("Java 8 - Lambda Exprssions", book.getTitle());
		
		
		System.out.println(">>testFindByTitleFound():Seccuss!");
		
	}
	
	//OK
	@Test
	@DisplayName("when calling findByTitle() on not existing title")
	void testFindByTitleNonExistsTitle()
	{
		String title = "Thinking in Java"; 
		
		Book book = bookDAO.findByTitle(title);
		
		assertNull(book); 
	}
	
	//OK
	@Test
	@DisplayName("when calling count() ")
	void testCount()
	{
		
		long numberOfBooks = bookDAO.count();
		
		assertEquals(3, numberOfBooks);
		
		System.out.println(">>testCount():number of books: "+ numberOfBooks); 
		
	}
	
	@Test
	@DisplayName("when calling listByCategory()")
	void testListByCategory()
	{
		List<Book> books = bookDAO.listByCategory(14); 
		assertNotNull(books); 
		assertEquals(7, books.size());
		
		books.forEach(c -> System.out.println(c.getTitle())); 
			
	}
	
	@Test
	@DisplayName("when calling listNewBooks()")
	void testListNewBooks()
	{
		
		List<Book> books = bookDAO.listNewBooks();
		assertEquals(4, books.size());
		
		books.forEach( c -> System.out.println("Book Title: " + c.getTitle() +
				"Book Publish Date: " + c.getPublishDate() ));
	
	}
	
	@Test
	@DisplayName("when calling search() method on existinb title")
	void testSearchInTitle()
	{
		List<Book> books = bookDAO.search("Java") ;
		assertEquals(3, books.size());
		
		books.forEach(c -> System.out.println(c.getTitle()));
		
	}
	
	@Test
	@DisplayName("when caling serach method on exiting author")
	void testSearchInAuthor()
	{
		List<Book> books = bookDAO.search("Spring basics"); 
		assertEquals(1, books.size());
		books.forEach(c -> System.out.println(c.getTitle() + " , " + c.getAuthor()));
	}
}