package org.nir.bookstore.frontend.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import org.nir.bookstore.entities.Book;

public class ShoppingCartTest
{
	private static ShoppingCart cart ; 
	@BeforeAll
	@DisplayName("when creating the shopping cart")
	static void init()
	{
		
		cart = new ShoppingCart();
		Book book = new Book(); 
		//I NEED TO SET THIS PROPERTY BECAUSE OF THE equals() of Book
		Integer bookId = 1; 
		book.setBookId(bookId);
		cart.addItem(book);
	}
	
	@Test
	@DisplayName("when calling addItem() ")
	void testAddItem()
	{
	
		Book book = new Book(); 
		//I NEED TO SET THIS PROPERTY BECAUSE OF THE equals() of Book
		Integer bookId = 1; 
		book.setBookId(bookId);
		book.setTitle("B1");
		//add the book to the cart
		cart.addItem(book);
		//cart.addItem(book);
		
		Map<Book , Integer> items = cart.getItems(); 
		
		int actual = items.get(book);
		int expected = 2; 
		
		assertEquals(expected, actual);
		
		System.out.println(">>testAddItem():the book id in the cart is " + items.containsKey(book));
		
	
		System.out.println(">>testAddItem(): quantity of a book with id " + bookId + " in the cart is " + items.get(book) );
		
		
	}
}
