package org.nir.bookstore.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.CategoryDAO;

public class BaseService<E>
{
	//protected static CategoryDAO categoryDAO;
	protected  HttpServletRequest request;
	protected HttpServletResponse response;
	public BaseService(HttpServletRequest request, HttpServletResponse response) {
		super();
		this.request = request;
		this.response = response;
	}

	
}
