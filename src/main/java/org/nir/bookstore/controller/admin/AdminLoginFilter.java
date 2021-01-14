
package org.nir.bookstore.controller.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class AdminLoginFilter
 */
@WebFilter("/admin/*")
public class AdminLoginFilter implements Filter {

    /**
     * Default constructor. 
     */
    public AdminLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	public void destroy() 
	{
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		System.out.println("AdminLoginFilter is invoked"); 
		
		HttpServletRequest httpRequest = (HttpServletRequest)request; 
		
		HttpSession session = httpRequest.getSession(false);
		
		boolean loggedIn = session != null && session.getAttribute("userEmail") != null; 
		
		String loginURI = httpRequest.getContextPath() + "/admin/login"; 
		
		boolean loginRequest = httpRequest.getRequestURI().equals(loginURI);
		
		boolean loginPage = httpRequest.getRequestURI().endsWith("login.jsp"); 
		
		/*
		 * If logged in and wants the to login or just want login.jsp
		 * -> forward to admin home page -
		 */
		if(loggedIn  && ( loginRequest ||  loginPage) )
		{
			request.getRequestDispatcher("/admin/").forward(httpRequest, response);
		}
		
		else if(loggedIn || loginRequest)
		{
			System.out.println("User Logged in!"); 
			chain.doFilter(request, response);
		}
		else
		{
			System.out.println("User Not Logged in!"); 
			httpRequest.getRequestDispatcher("login.jsp").forward(httpRequest, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
