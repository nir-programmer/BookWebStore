package org.nir.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nir.bookstore.entities.Customer;

public class CustomerDAO extends HibernateDAO<Customer> implements GenericeDAO<Customer> {

	@Override
	public Customer create(Customer customer) 
	{
		customer.setRegisterDate(new Date());
		return super.create(customer); 
	}

	@Override
	public Customer update(Customer customer) 
	{
		return super.update(customer); 
	}

	/*
	 * @Override public Customer get(Object id) { return super.find(Customer.class,
	 * id); }
	 */

	@Override
	public void delete(Object id) 
	{
		super.delete(Customer.class, id);
		
	}
	
	@Override
	public Customer get(Object id)
	{
		return super.find(Customer.class, id);
	}

	@Override
	public List<Customer> listAll() 
	{
		return super.findWithNamedQuery("Customer.findAll"); 
	}

	@Override
	public long count() 
	{
		return super.countWithNamedQuery("Customer.countAll");
	}

	public Customer findByEmail(String email)
	{
		Customer customer = null; 
		List<Customer> customers = super.findWithNamedQuery("Customer.findByEmail", "email" , email); 
		
		if(customers != null && customers.size() == 1)
			customer = customers.get(0); 
		
		return customer;
	}
	
	public Customer checkLogin(String email , String password)
	{
		
		Map<String, Object> parameters = new HashMap<>(); 
		parameters.put("email", email);
		parameters.put("password" ,password); 
		
		List<Customer> customers = super.findWithNamedQuery("Customer.checkLogin", parameters);
		
		if(!customers.isEmpty())
			return customers.get(0);
		
		return null;  
		
		//return customer;
	}
	

	


	

}
