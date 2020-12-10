package org.nir.bookstore.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.entities.Users;
import org.nir.bookstore.service.UsersService;

/**
 * Servlet implementation class ListUsersServlet
 */
@WebServlet("/admin/list_users")
public class ListUsersServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ListUsersServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		UsersService usersService = new UsersService(request, response);
		usersService.getAllUsers();
	
	}

	
	
	
	

}
