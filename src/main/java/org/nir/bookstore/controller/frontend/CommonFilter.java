package org.nir.bookstore.controller.frontend;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.nir.bookstore.dao.CategoryDAO;
import org.nir.bookstore.entities.Category;

/**
 * Servlet Filter implementation class CommonFilter
 */
@WebFilter("/*")
public class CommonFilter implements Filter
{
	private final CategoryDAO categoryDAO ; 

    public CommonFilter() 
    {
        categoryDAO = new CategoryDAO(); 
    }

	
	public void destroy()
	{
		// TODO Auto-generated method stub
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException 
	{
		 
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		String path = httpServletRequest.getRequestURI().substring(httpServletRequest.getContextPath().length());
		
		
		
		System.out.println("CommonFilter->doFilter(): path = " + path) ;
		
		System.out.println("path starts with /admin/ ? "  +  path.startsWith("/admin/")); 
		
		if(!path.startsWith("/admin/"))
		{
			System.out.println(">>CommonFilter.doFilter():Calling categoryDAO.openCurrentSession()...");
			this.categoryDAO.openCurrentSession();
			System.out.println(">>CommonFilter.doFilter():categoryDAO.openCurrentSession() RETURNED!");
			
			List<Category> categories = this.categoryDAO.listAll();
			//this.categoryDAO.closeCurrentSession();
			
			System.out.println(">>CommonFilter.doFilter(): Calling catetoryDAO.closeCurrentSession()...");
			categoryDAO.closeCurrentSession();
			System.out.println("CommonFilter.doFilter():categoryDAO.closeCurrentSession() RETURNED!");
			
			
			//categoriems.forEach(c -> System.out.println(c.getName()));
			httpServletRequest.setAttribute("categories", categories);
		}
		
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException
	{
		
		
	}

}
