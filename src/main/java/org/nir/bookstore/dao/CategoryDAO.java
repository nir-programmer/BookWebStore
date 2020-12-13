package org.nir.bookstore.dao;

import java.util.List;

import org.nir.bookstore.entities.Category;

public class CategoryDAO extends HibernateDAO<Category> implements GenericeDAO<Category> {

	//Ok
	@Override
	public Category create(Category category) 
	{
		return super.create(category);
	}
	//OK
	@Override
	public Category update(Category category) 
	{
		return super.update(category); 
	}

	//Ok
	@Override
	public Category get(Object id) 
	{
		return super.find(Category.class, id);
	}

	//OK
	@Override
	public void delete(Object id) 
	{
		super.delete(Category.class, id);
	
	}

	@Override
	public List<Category> listAll() 
	{
		
		return super.findWithNamedQuery("Category.findAll"); 
	}

	@Override
	public long count() 
	{
		
		return super.countWithNamedQuery("Category.countAll"); 
	}

}
