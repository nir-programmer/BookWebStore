package org.nir.bookstore.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.nir.bookstore.entities.Users;

public class UsersDAO extends HibernateDAO<Users> implements GenericeDAO<Users> {

	public UsersDAO(Session session) {
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

	
	

	/*
	 * @Override public Users create(Users user) { // TODO Auto-generated method
	 * stub return super.create(user); }
	 * 
	 * @Override public Users update(Users user) { return super.update(user); }
	 * 
	 * @Override public Users get(Object userId) {
	 * 
	 * The Users.class will translate to Class<Users> in supper class
	 * 
	 * return super.find(Users.class, userId); }
	 * 
	 * @Override public void delete(Object id) { super.delete(Users.class, id);
	 * 
	 * }
	 * 
	 * @Override public List<Users> listAll() {
	 * 
	 * String query = "Users.findAll"; return super.findWithNamedQuery(query);
	 * 
	 * }
	 * 
	 * @Override public long count() {
	 * 
	 * return super.countWithNamedQuery("Users.countAll"); }
	 * 
	 * public Users findById(Integer id) { session.getTransaction().begin(); //
	 * Integer id = 1; String hql = "FROM Users U WHERE U.userId =: id"; Query query
	 * = session.createQuery(hql); query.setParameter("id", id); Users users =
	 * (Users) query.getSingleResult(); session.getTransaction().commit(); return
	 * users;
	 * 
	 * }
	 * 
	 * public List<Users> findByEmail(String email) { String queryName =
	 * "Users.findByEmail"; return super.findWithNamedQuery(queryName, "email",
	 * email); }
	 * 
	 * public Users findByEmailFound(String email) {
	 * session.getTransaction().begin(); String hql =
	 * "FROM Users U WHERE U.email =: email"; Query<Users> query =
	 * session.createQuery(hql); query.setParameter("email", email); Users users =
	 * query.getSingleResult(); session.getTransaction().commit(); return users;
	 * 
	 * }
	 */

	
}
