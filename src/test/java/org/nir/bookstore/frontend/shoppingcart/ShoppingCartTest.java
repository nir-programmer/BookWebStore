package org.nir.bookstore.frontend.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
		Book book1 = new Book(1); 
		Book book2 = new Book(2);
		Book book3 = new Book(3); 
		
		book1.setTitle("AAA");
		book2.setTitle("BBB");
		book3.setTitle("CCC");
		
		book1.setPrice(20);
		book2.setPrice(50);
		book3.setPrice(40);
		
		cart.addItem(book1);
		cart.addItem(book2);
		cart.addItem(book2);
		cart.addItem(book3); 
	}
	
	//OK
	@Test
	@DisplayName("when calling addItem() ")
	void testAddItem()
	{
		Map<Book , Integer> items = cart.getItems(); 
		
		int quantity = items.get(new Book(1)); 
		assertEquals(2, quantity);
		
		//System.out.println(">>testAddItem(): quantity of a book with id " + bookId + " in the cart is " + items.get(book) );
		
	}
	
	//OK
	@Test
	@DisplayName("when calling removeItem()")
	void testRemoveItem()
	{
		Book book2 = new Book(2); 
		cart.addItem(book2);
		
		cart.removeItem(book2);
		assertNull(cart.getItems().get(book2));	
	}
	
	//OK
	@Test
	@DisplayName("when calling getTotalQuantity")
	void testGetTotalQuantity()
	{
		Book book3 = new Book(3); 
		cart.addItem(book3);
		cart.addItem(book3);
		cart.addItem(book3);
		
		int total = cart.getTotalQuantity();
		
		assertEquals(5, total); 
		
		System.out.println(">>testGetTotalQuantity(): total = " + total); 
		
	}
	
	@Test
	@DisplayName("when printing titles of books in the cart")
	void testPrintAllBooksTitles()
	{
		Set<Book> books = cart.getItems().keySet();
		
		int numberOfBooksInCart = books.size();
		
		//assertEquals(2, numberOfBooksInCart);
		
		books.forEach(b -> System.out.println(b.getTitle()));
		
		System.out.println(cart.getTotalQuantity());
	}
	
	@Test
	@DisplayName("when calling getTotalAmount()")
	void testGetTotalAmount1()
	{
		
		double total = cart.getTotalAmount();
		
		assertEquals(20.15f, total );
		
		System.out.println(">>testTotalAmount():total = " + total);
		
		
		
	}
	
	@Test
	@DisplayName("when calling getTotalAmount()")
	void testGetTotalAmount2()
	{
		Book book =new Book(3); 
		book.setPrice(30);
		
		double total = cart.getTotalAmount();
		
		//System.out.println(total);
		//assertEquals(50, total );
		
		System.out.println(">>testTotalAmount():total = " + total);
		
	}
	
	//OK
	@Test
	@DisplayName("when calling getTotalItems()")
	void testGetTotalItems()
	{
		Book book4 = new Book();
		cart.addItem(book4);
		
		int totalItems = cart.getTotalItems();
		assertEquals(4, totalItems);
		
		System.out.println("totalItems  " + totalItems); 
		/*
		 * for(Map.Entry m: cart.getItems().entrySet()) {
		 * //System.out.println(m.getKey() + " " + m.getValue()); Book book =
		 * (Book)m.getKey(); Integer quantity = (Integer)m.getValue();
		 * System.out.println("Book Title: " + book.getTitle());
		 * System.out.println("Book copies: " +quantity);
		 * 
		 * }
		 */
		/*
		 * int totalItems = cart.getTotalItems(); int quantity =
		 * cart.getTotalQuantity();
		 * 
		 * 
		 * System.out.println("Number of different books in the cart:" + totalItems);
		 */
		
		
	}
	
	@Test
	void testHashMap()
	{
		Map<Integer, String> map = new HashMap<Integer, String>(); 
		map.put(1, "Mango"); 
		map.put(2, "Apple"); 
		map.put(3, "Banana"); 
		map.put(1, "Grapes"); 
		
		/*
		 * for(Map.Entry m: map.entrySet()) { System.out.println(m.getKey() + " " +
		 * m.getValue()); }
		 */
		
		Map<Book , Integer> myMap  = cart.getItems();
		
		for(Map.Entry m : myMap.entrySet())
		{
			System.out.println(m.getKey() + " " + m.getValue()); 
		}
	}
}
