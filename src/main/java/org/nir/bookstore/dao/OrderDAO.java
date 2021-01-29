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
		super.delete(BookOrder.class, id);
	}
	
	//OK
	public BookOrder update(BookOrder bookOrder)
	{
		return super.update(bookOrder);
	}
	
	//OK
	public BookOrder create(BookOrder bookOrder)
	{
		//Default values
		bookOrder.setOrderDate(new Date());
		//paymentMethod is set by the OrderService.placeOrder() method - reading from the drop down list
		//bookOrder.setPaymentMethod("Cash on Delivery");
		bookOrder.setStatus("Processing");
		
		return super.create(bookOrder);
	}

	
	@Override
	public List<BookOrder> listAll()
	{
		return super.findWithNamedQuery("BookOrder.findAll");
	}

	//OK
	@Override
	public long count()
	{
		return super.countWithNamedQuery("BookOrder.countAll"); 
	}
	
	//Assignment 22 WRONG
	/*
	 * public long countOrderDetailByBook(int bookId) { return
	 * super.countWithNamedQuery("OrderDetail.countByBook", "bookId", bookId); }
	 */	
	
	//Assignment 23
	public long countOrderDetailByBook(int customerId) 
	{
		return super.countWithNamedQuery("BookOrder.countByCustomer", "customerId", customerId);
	}
	

	

}
