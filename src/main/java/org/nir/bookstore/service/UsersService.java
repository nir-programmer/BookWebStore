package org.nir.bookstore.service;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nir.bookstore.dao.UsersDAO;
import org.nir.bookstore.entities.Users;

public class UsersService 
{
	private static UsersDAO usersDAO; 
	
	private HttpServletRequest request ; 
	private HttpServletResponse response;
	
	public UsersService(HttpServletRequest request, HttpServletResponse response)
	{
		usersDAO = new UsersDAO();
		this.request = request;
		this.response = response;
	}
	
	public void getAllUsers() throws ServletException, IOException
	{
		usersDAO.openCurrentSession();
		List<Users> users = usersDAO.listAll();
		usersDAO.closeCurrentSession();
		
		request.setAttribute("users", users);
		request.getRequestDispatcher("users_list.jsp").forward(request, response);
	}
	
	public UsersService()
	{
		usersDAO = new UsersDAO();
		
	}
	
	public List<Users> listUsers() 
	{
		usersDAO.openCurrentSession();
		List<Users> users = this.usersDAO.listAll();
		usersDAO.closeCurrentSession();
		return users; 
	}
	
	public Users findUserById(Object id)
	{
		usersDAO.openCurrentSession();
		Users user =  usersDAO.get(id);
		usersDAO.closeCurrentSession();
		return user ; 
	}


	public void createUser(String email, String fullName, String password) 
	{
		usersDAO.openCurrentSessionWithTransaction();
		Users user = new Users(email, password, fullName);
		usersDAO.create(user);
		usersDAO.closeCurrentSessionWithTransaction();
		
		/*
		 * usersDAO.openCurrentSessionWithTransaction(); Users users = new Users(email,
		 * password, fullName); this.usersDAO.create(users);
		 * usersDAO.closeCurrentSessionWithTransaction();
		 */
		
		
	}

	public void createUser(Users user) 
	{
		usersDAO.openCurrentSessionWithTransaction();
		usersDAO.create(user);
		usersDAO.closeCurrentSessionWithTransaction();
		
	}


	public List<Users> findAll() 
	{
		usersDAO.openCurrentSession();
		List<Users> users = usersDAO.listAll();
		
		usersDAO.closeCurrentSession();
		
		return users;
		
	}


	

}
