package org.nir.bookstore.service;

import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.entities.Customer;

public class CommonUtility
{
	public static void forwardToPage(String page, String message,
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		request.setAttribute("message", message);
		request.getRequestDispatcher(page).forward(request, response);		
	}
	
	public static void showMessageFrontend(String message, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		forwardToPage("frontend/message.jsp", message, request, response);
	}
	
	public static void showMessageBackend(String message, 
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		forwardToPage("message.jsp", message, request, response);
	}

	public static void forwardToPage(String page,
			HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException {
		request.getRequestDispatcher(page).forward(request, response);		
	}	
	
	public static void generateCountries(HttpServletRequest request)
	{
		String[] countryCodes;
		Map<String, String> mapCountries;
		// String customerForm;

		countryCodes = Locale.getISOCountries();

		mapCountries = new TreeMap<String, String>();

		for (String countryCode : countryCodes) {
			Locale locale = new Locale("", countryCode);

			String code = locale.getCountry();
			String name = locale.getDisplayCountry();

			// I want to sort on the country name in the page!
			mapCountries.put(name, code);
		}
		request.setAttribute("mapCountries", mapCountries);
	}
	
}
