package org.nir.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;
import javax.print.attribute.HashAttributeSet;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.entities.Review;
import org.nir.bookstore.entities.Users;

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
		Category category = new Category("Advanced Java"); 
		category.setCategoryId(129);
		book.setCategory(category);
		
		book.setTitle("Effective Perl (2nd Edition)");
		book.setAuthor("Nir");
		book.setDescription("New coverage of genereic , enums, annotations");
		book.setPrice(34.99f);
		book.setIsbn("0123456"); 
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
		Date publishDate = dateFormat.parse("05/28/2008"); 
		book.setPublishDate(publishDate);
		
		String  imagePath = "/home/nir/Desktop/MyStudies/Udemy/Java-Servlet-JSP-Hibernate/BookStoreWebSite/src/main/webapp/images/AdvancedJava.png"; 
		
		
		//This method reads all bytes and returns an array of bytes
		byte[] imageByte = Files.readAllBytes(Paths.get(imagePath)); 
		
		book.setImage(imageByte);
		
		
		Book createdBook = bookDAO.create(book); 
		
		
		
		
	}
	
}