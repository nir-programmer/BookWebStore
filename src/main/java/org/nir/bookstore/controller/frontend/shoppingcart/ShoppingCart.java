package org.nir.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.nir.bookstore.entities.Book;

public class ShoppingCart
{

	//The key is a Book instance and the value is number of copies
	private Map<Book, Integer> cart  = new HashMap<Book, Integer>();
	
	public void addItem(Book book)
	{
		//if the book already in the cart - increase the value by one
		if(cart.containsKey(book))
		{
			
			int quantity = cart.get(book) + 1;
			cart.put(book, quantity); 
		}
		//if the book is not the in the cart - add the book with value of 1
		else
		{
			
			cart.put(book, 1);
		}
			
	}

	public Map<Book, Integer> getItems()
	{
		return this.cart;
	}
	
	public void removeItem(Book book)
	{
		this.cart.remove(book);
	}
	
	
	public int getTotalQuantity()
	{
		int total = 0; 
		
		Iterator<Book> iterator = cart.keySet().iterator();
		
		while(iterator.hasNext())
		{
			//fetch the book(the key ) from the iterator and store in next variable
			Book next = iterator.next();
			Integer quantity = cart.get(next);
			total += quantity;
		}
		
		return total; 

	}
	
	// OK
	public double getTotalAmount()
	{
		float total = 0.0f; 
		
		Iterator<Book> iterator = this.cart.keySet().iterator();
		
		
		while(iterator.hasNext())
		{
			Book book = iterator.next();
			Integer quantity = this.cart.get(book); 
			double price = book.getPrice();
			total += price * quantity;
		}
		
		return total ;
	}
	
	//OK
	public int getTotalItems()
	{
		return this.cart.size();
	}
	
	public void clear()
	{
		this.cart.clear();
	}
	
	
	
}
