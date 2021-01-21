package org.nir.bookstore.dao;

import java.util.List;

import org.nir.bookstore.entities.BookOrder;

public class OrderDAO extends HibernateDAO<BookOrder> implements GenericeDAO<BookOrder>
{

	@Override
	public BookOrder get(Object id)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Object id)
	{
		// TODO Auto-generated method stub
		
	}
	
	public BookOrder update(BookOrder bookOrder)
	{
		return super.update(bookOrder);
	}
	
	public BookOrder create(BookOrder bookOrder)
	{
		return super.create(bookOrder);
	}

	
	@Override
	public List<BookOrder> listAll()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	

}
