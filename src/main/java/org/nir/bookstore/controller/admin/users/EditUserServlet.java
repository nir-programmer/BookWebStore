package org.nir.bookstore.controller.admin.users;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.service.UsersService;

/**
 * Servlet implementation class EditUserServlet
 */
@WebServlet("/admin/edit_user")
public class EditUserServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		UsersService usersService = new UsersService(request, response); 
		usersService.editUser();
		
		
	}

	

}
