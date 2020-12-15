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
		
		boolean loggedInt = session != null && session.getAttribute("userEmail") != null; 
		
		if(loggedInt)
			chain.doFilter(request, response);
		else
			httpRequest.getRequestDispatcher("login.jsp").forward(request, response);
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
