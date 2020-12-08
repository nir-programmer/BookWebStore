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
	
	private SessionFactory sessionFactory; 
	private Session session;
	private UsersDAO usersDAO; 
	
	
	public UsersService()
	{
		this.sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(Users.class)
				.buildSessionFactory();
		
		this.session = this.sessionFactory.getCurrentSession();
		
		this.usersDAO = new UsersDAO(session);
	}


	public void listUsers(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		List<Users> users = this.usersDAO.listAll();
		
		request.setAttribute("users", users);
		
		request.getRequestDispatcher("/admin/users_list.jsp").forward(request, response);
	}


	public void createUser(String email, String fullName, String password) 
	{
		Users users = new Users(email, password, fullName);
		
		this.usersDAO.create(users);
		
		
		
	}


	

}
