package org.nir.bookstore.controller.frontend;

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
 * Servlet Filter implementation class CustomerLoginFilter
 */
@WebFilter("/*")
public class CustomerLoginFilter implements Filter
{
	private static final String[] loginRequiredURLs = 
		{"/view_profile", "/edit_profile" , "/update_profile" , "/write_form"};

    public CustomerLoginFilter() {
        // TODO Auto-generated constructor stub
    }

	
	public void destroy() {
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException
	{
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		//Retrieve the URI with out Root Context
		String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
		HttpSession session = httpRequest.getSession(false);
		
		//check if the path starts with /admin/ - if it is: continue the filter chain - doFilter()
		if(path.startsWith("/admin/"))
		{
			/*
			 * System.out.println(">>CustomerLoginFilter.doFilter(): Path=  "+ path);
			 * System.out.println(">>CustomerLoginFilter.doFilter(): isLogin = " +
			 * loggedIn);
			 */
			chain.doFilter(httpRequest, response);
			return; 
		}
		
		
		//check if there is an active session with loggedCustomer attribute
		boolean loggedIn = session != null && session.getAttribute("loggedCustomer") != null; 
		
		//fetch the request URL String 
		String requestURL = httpRequest.getRequestURL().toString();
		
		
		//TEST
		System.out.println(">>CustomerLoginFilter.doFilter(): Path=  "+ path); 
		System.out.println(">>CustomerLoginFilter.doFilter(): isLogin = " + loggedIn);
		
		
		
		
		
		/*
		 * if(!loggedIn && path.startsWith("/view_profile")) {
		 * System.out.println(">>CustomerLoginFilter.doFilter(): Path=  "+ path);
		 * System.out.println(">>CustomerLoginFilter.doFilter(): isLogin = " +
		 * loggedIn);
		 * 
		 * httpRequest.getRequestDispatcher("frontend/login.jsp").forward(httpRequest,
		 * response); }
		 */
		if(!loggedIn && isLoginRequired(requestURL))
		{
		System.out.println(">>CustomerLoginFilter.doFilter(): Path=  "+ path); 
		System.out.println(">>CustomerLoginFilter.doFilter(): isLogin = " + loggedIn);
		
		httpRequest.getRequestDispatcher("frontend/login.jsp").forward(httpRequest, response);
		}
		else
		{
		chain.doFilter(request, response);
		}
	}

	private boolean isLoginRequired(String requestURL)
	{
		for( String loginRequiredURL : loginRequiredURLs)
			if(requestURL.contains(loginRequiredURL))
				return true;
		
		return false; 
	}
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
