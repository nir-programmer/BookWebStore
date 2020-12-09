package org.nir.bookstore.dao;

import java.io.Serializable;
import java.util.List;

import org.nir.bookstore.entities.Users;

public interface GenericeDAO<T, Id extends Serializable> 
{
	public T create(T t);
	
	public T update(T t);
	
	public T get(Id id );
	
	public void delete(Id id);
	
	public List<T> listAll(); 
	
	public long count(); 
	

}
