package org.nir.bookstore.dao;

import java.util.Date;
import java.util.List;

import org.nir.bookstore.entities.BookOrder;

public class OrderDAO extends HibernateDAO<BookOrder> implements GenericeDAO<BookOrder>
{

	//OK
	@Override
	public BookOrder get(Object id)
	{
		return super.find(BookOrder.class, id);
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
	
	//OK
	public BookOrder create(BookOrder bookOrder)
	{
		//Default values
		bookOrder.setOrderDate(new Date());
		bookOrder.setPaymentMethod("Cash on Delivery");
		bookOrder.setStatus("Processing");
		
		return super.create(bookOrder);
	}

	
	@Override
	public List<BookOrder> listAll()
	{
		return super.findWithNamedQuery("BookOrder.findAll");
	}

	@Override
	public long count()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	

}
