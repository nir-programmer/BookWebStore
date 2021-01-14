package org.nir.bookstore.controller.admin.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.entities.Users;
import org.nir.bookstore.service.UsersService;

/**
 * Servlet implementation class CreateUsersServlet
 */
@WebServlet("/admin/create_users")
public class CreateUsersServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UsersService usersService = new UsersService(request , response);
		usersService.createUser();
		
		/*
		 * I want to put all the flow in the service so I move this
		 	code to the create() method of the service
		 */
		//usersService.getAllUsers("User created succssfully!");
		
		
	}

}
