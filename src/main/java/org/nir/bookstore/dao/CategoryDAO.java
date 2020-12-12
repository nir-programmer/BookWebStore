package org.nir.bookstore.dao;

import java.util.List;

import org.nir.bookstore.entities.Category;

public class CategoryDAO extends HibernateDAO<Category> implements GenericeDAO<Category> {

	@Override
	public Category create(Category category) 
	{
		return super.create(category);
	}

	@Override
	public Category update(Category t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Category get(Object id) 
	{
		return super.find(Category.class, id);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Category> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

}
