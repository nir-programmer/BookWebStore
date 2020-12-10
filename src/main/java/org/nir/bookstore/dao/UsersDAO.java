package org.nir.bookstore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.nir.bookstore.entities.Users;

public class UsersDAO extends HibernateDAO<Users> implements GenericeDAO<Users> 
{
	
	public UsersDAO(Session session) 
	{
		super(session);
		// TODO Auto-generated constructor stub
	}

	public UsersDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public Users create(Users user)
	{
		return super.create(user);
	}
	
	@Override
	public Users get( Object id) {
		return super.find(Users.class, id);
	}

	@Override
	public void delete(Object id) 
	{
		super.delete(Users.class, id);
		// TODO Auto-generated method stub
		
	}

	@Override
	public Users update(Users user) 
	{
		return super.update(user); 
		// TODO Auto-generated method stub
		//return null;
	}
	
	@Override
	public List<Users> listAll() 
	{
		String query = "Users.findAll"; 
		return super.findWithNamedQuery(query);
	}

	@Override
	public long count() 
	{
		
		String queryName = "Users.countAll";
		return super.countWithNamedQuery(queryName);
		
	}

	public Users getUser(Object id)
	{
		
		return super.find(Users.class, id);
	}
	
	public Users findByEmail(String email)
	{
		List<Users> users = super.findWithNamedQuery("Users.findByEmail", "email", email);
		
		if(users != null && users.size() == 1)
			return users.get(0);
		
		return null; 
		
		
	}

		
}
