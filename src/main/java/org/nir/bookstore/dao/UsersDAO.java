package org.nir.bookstore.dao;

import java.util.List;

import org.hibernate.Session;
import org.nir.bookstore.entities.Users;

public class UsersDAO extends HibernateDAO<Users> implements GenericeDAO<Users> 
{
	
	public UsersDAO(Session session) 
	{
		super(session);
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public Users create(Users user) 
	{
		// TODO Auto-generated method stub
		return super.create(user);
	}



	@Override
	public Users update(Users user) 
	{
		
		return super.update(user);
	}

	@Override
	public Users get(Object userId)
	{
		return super.get(userId);
	}

	@Override
	public void delete(Object id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Users> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	

	

}
