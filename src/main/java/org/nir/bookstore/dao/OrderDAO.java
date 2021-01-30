package org.nir.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nir.bookstore.entities.BookOrder;

public class OrderDAO extends HibernateDAO<BookOrder> implements GenericeDAO<BookOrder>
{

	//OK
	@Override
	public BookOrder get(Object id)
	{
		return super.find(BookOrder.class, id);
	}
	
	//For Security : I want this method to return the order only if the order belong to caller customer!!
	public BookOrder get(Integer orderId , Integer customerId)
	{
		List<BookOrder> bookOrders;
		Map<String,Object> parameters ; 
		BookOrder bookOrder;
		
		//create a map and add pairs for orderIde , customerId
		parameters = new HashMap<String, Object>();
		parameters.put("orderId", orderId);
		parameters.put("customerId" , customerId); 
		
		bookOrders = super.findWithNamedQuery("BookOrder.findByIdAndCustomer", parameters);
		bookOrder = null ;
		
		//If the list is not empty , take the first element
		if(!bookOrders.isEmpty())
			bookOrder = bookOrders.get(0); 
		
		return bookOrder;
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
		return super.countWithNamedQuery("Bo)okOrder.countAll"); 
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
	
	public List<BookOrder> listByCustomer(Integer customerId)
	{
		 
		//Integer customerId = 11;  
		List<BookOrder> bookOrders ; 
		
		bookOrders = this.findWithNamedQuery("BookOrder.findByCustomer", "customerId", customerId);
		return bookOrders;
		
	}

	

}
