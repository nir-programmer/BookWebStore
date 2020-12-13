package org.nir.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.CategoryDAO;
import org.nir.bookstore.entities.Category;

public class CategoryService 
{
	private static CategoryDAO categoryDAO; 
	
	private HttpServletRequest request;
	private HttpServletResponse response;
	
	public CategoryService(HttpServletRequest request , HttpServletResponse response)
	{
		categoryDAO = new CategoryDAO(); 
		
		this.request = request;
		this.response = response; 
	}
	
	public void listCategory() throws ServletException, IOException
	{
		List<Category> categories = categoryDAO.listAll();
		
		request.setAttribute("categories", categories);
		
		request.getRequestDispatcher("categories_list.jsp").forward(request, response);
		
	}

}
