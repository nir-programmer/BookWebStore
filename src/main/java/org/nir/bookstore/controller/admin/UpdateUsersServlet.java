package org.nir.bookstore.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.UsersService;

/**
 * Servlet implementation class UpdateUserServlet
 */
@WebServlet("/admin/update_user")
public class UpdateUsersServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		UsersService usersService = new UsersService(request, response);
		usersService.updateUser(); 
		/*
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * 
		 * out.println("<html><body>"); out.println("<h1>Test Update Servlet</h1>");
		 * out.println("</body></html>");
		 */
	}

}
