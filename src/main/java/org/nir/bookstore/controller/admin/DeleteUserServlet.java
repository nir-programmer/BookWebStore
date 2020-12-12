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
 * Servlet implementation class DeleteUserServlet
 */
@WebServlet("/admin/delete_user")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		UsersService service = new UsersService(request, response); 
		
		service.deleteUser();
		/*
		 * response.setContentType("text/html"); PrintWriter out = response.getWriter();
		 * 
		 * out.println("<html><body>"); out.println("<h2>Test DeleteUserServlet</h2>");
		 * out.println("</body></html>");
		 */
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();

		out.println("<html><body>");
		out.println("<h2>Test DeleteUserServlet</h2>");
		out.println("</body></html>");
	}

}
