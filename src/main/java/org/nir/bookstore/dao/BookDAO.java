package org.nir.bookstore.dao;

import java.util.Date;
import java.util.List;

import org.nir.bookstore.entities.Book;

public class BookDAO extends HibernateDAO<Book> implements GenericeDAO<Book> 
{
	@Override
	public Book create(Book book)
	{
		book.setLastUpdateTime(new Date());
		return super.create(book); 
	}

	@Override
	public Book update(Book book)
	{
		//why he puts this here?
		book.setLastUpdateTime(new Date());
		return super.update(book);
	}

	@Override
	public Book get(Object id)
	{
		return super.find(Book.class, id); 
	}

	@Override
	public void delete(Object bookId) 
	{
		super.delete(Book.class, bookId);
		
	}

	@Override
	public List<Book> listAll() 
	{
		return super.findWithNamedQuery("Book.findAll"); 
	}

	@Override
	public long count() 
	{
		
		return super.countWithNamedQuery("Book.countAll"); 
	}
	

	public Book findByTitle(String title)
	{
		List<Book> books = super.findWithNamedQuery("Book.findByTitle", "title", title); 
		
		if(books.size() == 1)
			return books.get(0); 
		return null; 
	}
	
	public List<Book> listByCategory(int categoryId)
	{
		return super.findWithNamedQuery("Book.findByCategory", "catId", categoryId); 
		
	}
	
}
