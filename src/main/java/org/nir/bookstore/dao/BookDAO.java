package org.nir.bookstore.dao;

import java.util.List;

import org.nir.bookstore.entities.Book;

public class BookDAO extends HibernateDAO<Book> implements GenericeDAO<Book> 
{
	@Override
	public Book create(Book book)
	{
		return super.create(book); 
	}

	@Override
	public Book update(Book book)
	{
		return super.update(book);
	}

	@Override
	public Book get(Object id)
	{
		return super.find(Book.class, id); 
	}

	@Override
	public void delete(Object id) 
	{
		
		
	}

	@Override
	public List<Book> listAll() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}
	

	
}
