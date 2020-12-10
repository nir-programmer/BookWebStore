package org.nir.bookstore.dao;

import java.io.Serializable;
import java.util.List;

import org.nir.bookstore.entities.Users;

public interface GenericeDAO<T> 
{
	public T create(T t);
	
	public T update(T t);
	
	public T get(Object id );
	
	public void delete(Object id);
	
	public List<T> listAll(); 
	
	public long count(); 
	

}
