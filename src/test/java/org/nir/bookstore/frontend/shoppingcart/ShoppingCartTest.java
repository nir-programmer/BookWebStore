package org.nir.bookstore.frontend.shoppingcart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
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
	private static ShoppingCart cart;

	@BeforeAll
	@DisplayName("when creating the shopping cart")
	static void init()
	{

		cart = new ShoppingCart();
		Book book1 = new Book(1);
		Book book2 = new Book(2);

		book1.setTitle("Title of Book1");
		book2.setTitle("Title of Book2");
		
		book1.setPrice(30);
		book2.setPrice(50f);
		
		  cart.addItem(book1);
		  cart.addItem(book1);
		  
		  cart.addItem(book2);
		  cart.addItem(book1);
		  cart.addItem(book2);
		 
		//System.out.println("init():#Books = " + cart.getItems().keySet().size());
		/*
		 * Book book1 = new Book(1); Book book2 = new Book(2); Book book3 = new Book(3);
		 * 
		 * book1.setTitle("AAA"); book2.setTitle("BBB"); book3.setTitle("CCC");
		 * 
		 * book1.setPrice(20); book2.setPrice(50); book3.setPrice(40);
		 * 
		 * cart.addItem(book1); cart.addItem(book2); cart.addItem(book2);
		 * cart.addItem(book3);
		 */

	}

	// OK
	@Test
	@DisplayName("when calling addItem() ")
	void testAddNewItem()
	{
		Map<Book, Integer> items = cart.getItems();
		System.out.println(">>testAddItem():books id and quantitis BEFORE adding a new book:");
		// System.out.println(">>testAddItem():number of books: " +
		// items.keySet().size());
		items.entrySet().forEach(
				e -> System.out.println("Book id = " + e.getKey().getBookId() + " quantity = " + e.getValue()));

		Integer bookId = 3;
		cart.addItem(new Book(bookId));
		System.out.println(">>testAddItem():books id and quantitis AFTER adding a new book with id = " + bookId);
		
		items.entrySet().forEach(
				e -> System.out.println("Book id = " + e.getKey().getBookId() + " quantity = " + e.getValue()));
	}
	
	
	//OK
	@Test
	@DisplayName("when calling addItem() ")
	void testAddExistItem()
	{
		Map<Book, Integer> items = cart.getItems();
		System.out.println(">>testAddItem():books id and quantitis BEFORE adding a new book:");
		// System.out.println(">>testAddItem():number of books: " +
		// items.keySet().size());
		items.entrySet().forEach(
				e -> System.out.println("Book id = " + e.getKey().getBookId() + " quantity = " + e.getValue()));

		Integer bookId = 2 ;
		cart.addItem(new Book(bookId));
		System.out.println(">>testAddItem():books id and quantitis AFTER adding a new book with id :" + bookId);
		
		items.entrySet().forEach(
				e -> System.out.println("Book id = " + e.getKey().getBookId() + " quantity = " + e.getValue()));
		
		
	}
	
	

	// OK
	@Test
	@DisplayName("when calling removeItem() on exists item")
	void testRemoveExistItem()
	{
		Map<Book, Integer> items = cart.getItems();
		
		Integer bookId = 1; 
		System.out.println(">>testAddItem():books id and quantitis BEFORE removing a book with id :" + bookId);
		
		items.entrySet().forEach(
				e -> System.out.println("Book id = " + e.getKey().getBookId() + " quantity = " + e.getValue()));
		
		
		cart.removeItem(new Book(bookId)); 
		System.out.println(">>testAddItem():books id and quantitis AFTER remofing a book with id :" + bookId);
		
		assertTrue(cart.getItems().isEmpty());
		items.entrySet().forEach(
				e -> System.out.println("Book id = " + e.getKey().getBookId() + " quantity = " + e.getValue()));
		
	
	}
	
	//OK
	@Test
	@DisplayName("when adding an item and remove it")
	public void testAddRemoveNewItem()
	{
		Book book2 = new Book(2);
		cart.addItem(book2);
		
		cart.removeItem(book2);
		
		assertNull(cart.getItems().get(book2)); 
		
	}
	
	

	// OK
	@Test
	@DisplayName("when calling getTotalQuantity")
	void testGetTotalQuantity()
	{
		Book book3 = new Book(3);
		cart.addItem(book3);
		cart.addItem(book3);
		cart.addItem(book3);

		int total = cart.getTotalQuantity();

		assertEquals(8, total);

		System.out.println(">>testGetTotalQuantity(): total = " + total);

	}

	@Test
	@DisplayName("when printing titles of books in the cart")
	void testPrintAllBooksPrices()
	{
		Map<Book, Integer> map = cart.getItems();

		for (Map.Entry m : map.entrySet())
			;

		map.entrySet().forEach(e -> System.out.println(e.getKey().getPrice()));

	}

	@Test
	@DisplayName("when calling getTotalAmount()")
	void testGetTotalAmountEmptyCart()
	{

		double total = cart.getTotalAmount();

		assertEquals(0.0f, total);

		System.out.println(">>testTotalAmount():total = " + total);

	}
	@Test
	@DisplayName("when calling getTotalAmount()")
	void testGetTotalAmountNonEmptyCart()
	{

		double total = cart.getTotalAmount();

		assertEquals(190.0f, total);

		System.out.println(">>testTotalAmount():total = " + total);

	}

	@Test
	@DisplayName("when calling getTotalAmount()")
	void testGetTotalAmount2()
	{
		Book book = new Book(3);
		book.setPrice(30);

		double total = cart.getTotalAmount();

		// System.out.println(total);
		// assertEquals(50, total );

		System.out.println(">>testTotalAmount():total = " + total);

	}

	// OK
	@Test
	@DisplayName("when calling getTotalItems()")
	void testGetTotalItems()
	{
		
		//cart.addItem(new Book(2));

		int totalItems = cart.getTotalItems();
		assertEquals(2, totalItems);

		System.out.println("totalItems  " + totalItems);
		
		cart.getItems().entrySet().forEach(b -> 
		System.out.println("Book id = " + b.getKey().getBookId() + ", quanitites = " + b.getValue()));

	}

	//OK
	@Test
	@DisplayName("when calling clear()")
	void testClear()
	{
		int numberOfItems = cart.getTotalItems();
		System.out.println(">>testClear():number of items BEFORE clear(): " + numberOfItems);
		
		assertEquals(2, numberOfItems);
		
		
		cart.clear();
		
		numberOfItems = cart.getTotalItems();
		System.out.println(">>testClear():number of items BEFORE clear(): " + numberOfItems);
		
		assertEquals(0, numberOfItems);
		assertEquals(0.0, cart.getTotalAmount());
		
	}
	
	
	@Test
	@DisplayName("when calling updateCart()")
	public void testUpdateCart()
	{
		ShoppingCart cart  = new ShoppingCart();
		Set<Entry<Book, Integer>> entries = cart.getItems().entrySet();
		
		Book book1 = new Book(1); 
		Book book2 = new Book(2); 
		
		cart.addItem(book1);
		cart.addItem(book2);
		cart.addItem(book2);
		
		System.out.println(">>testUpdateCart():cart BEFORE updste():"); 
		cart.printIdAndQuantites(entries);
		
		
		int [] bookIds = {1, 2}; 
		int [] quantities = {3, 4}; 
		
		cart.updateCart(bookIds, quantities);
		
		assertEquals(7, cart.getTotalQuantity());
		
		System.out.println(">>testUpdateCart():cart AFTER updste():"); 
		cart.printIdAndQuantites(entries);
		
		
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

		Map<Book, Integer> myMap = cart.getItems();

		for (Map.Entry m : myMap.entrySet()) {
			System.out.println(m.getKey() + " " + m.getValue());
		}
	}
	
	
}
