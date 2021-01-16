package org.nir.bookstore.controller.frontend.shoppingcart;

import java.util.HashMap;
import java.util.Map;

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
			cart.put(book, 1);
			
	}

	public Map<Book, Integer> getItems()
	{
		return this.cart;
	}
	
	
}
