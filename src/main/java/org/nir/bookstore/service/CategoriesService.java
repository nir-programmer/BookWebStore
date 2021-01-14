package org.nir.bookstore.service;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nir.bookstore.dao.BookDAO;
import org.nir.bookstore.dao.CategoryDAO;
import org.nir.bookstore.dao.UsersDAO;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Users;

public class CategoriesService {
	private static CategoryDAO categoryDAO;
	private HttpServletRequest request;
	private HttpServletResponse response;

	/************************** CONSTRUCTORS ****************************/
	// OK: used By the Servlets
	public CategoriesService(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		categoryDAO = new CategoryDAO();

	}

	/******************************************************
	 * METODS USED BY THE SERVLETS
	 * 
	 * @throws IOException
	 * @throws ServletException
	 ******************************************************/

	// OK
	public void listAll() throws ServletException, IOException {
		this.listAll(null);

	}

	// OK
	public void listAll(String message) throws ServletException, IOException {
		if (message != null) {
			request.setAttribute("message", message);
		}

		categoryDAO.openCurrentSession();

		List<Category> categories = categoryDAO.listAll();

		categoryDAO.closeCurrentSession();

		request.setAttribute("categories", categories);
		request.getRequestDispatcher("categories_list.jsp").forward(request, response);
	}

	// ok
	public void createCategory() throws ServletException, IOException {
		categoryDAO.openCurrentSessionWithTransaction();
		String name = request.getParameter("categoryName");

		Category existCategory = categoryDAO.findByName(name);

		if (existCategory != null) {
			String message = "Could not create category with name : " + name
					+ " There is a category with this name already!";

			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			;
		}

		else {
			Category category = new Category(name);

			categoryDAO.create(category);

			categoryDAO.closeCurrentSessionWithTransaction();

			listAll("Category Created Seccussfully!");
		}

	}

	// ok
	public void editCategory() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String message = ""; 
		categoryDAO.openCurrentSessionWithTransaction();
		Category category = categoryDAO.get(id);
		//Assignment 4: check if there is a category with this id
		if(category == null)
		{
			categoryDAO.closeCurrentSessionWithTransaction();
			message = "could not find the category :" + id + ".";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
			
		}
		//The normal case
		else
		{
		categoryDAO.closeCurrentSessionWithTransaction();
		request.setAttribute("category", category);
		request.getRequestDispatcher("categories_form.jsp").forward(request, response);
		}
	}

	public void updateCategory() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("categoryId"));
		String name = request.getParameter("categoryName");

		categoryDAO.openCurrentSessionWithTransaction();
		Category categoryById = categoryDAO.get(id);
		Category categoryByName = categoryDAO.findByName(name);

		if (categoryByName != null && (categoryById.getCategoryId() != categoryByName.getCategoryId())) {
			categoryDAO.closeCurrentSessionWithTransaction();
			String message = "could not update category. " + "Category with name : " + name + " Exists!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
			Category category = new Category(id, name);
			categoryDAO.update(category);
			categoryDAO.closeCurrentSessionWithTransaction();
			listAll("Category updated seccussfully");
		}
	}

	public void deleteCategory() throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		long numberOfBooks = 0;
		String message = "";
		

		BookDAO bookDAO = new BookDAO();
		bookDAO.openCurrentSession();
		numberOfBooks = bookDAO.countByCategoryId(id);
		bookDAO.closeCurrentSession();

		System.out.println(">>CategoryService.deleteCategory(): number of books with " + "with categoryId = " + id
				+ " is " + numberOfBooks);

		//if there are books for this category - dont delete this category!
		if (numberOfBooks > 0) {

			message = "could not delete the category :" + id + "  because it contains some books.";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} 
		else 
		{
			categoryDAO.openCurrentSessionWithTransaction();
			
			Category category = categoryDAO.get(id);
			
			// Assignment 5: Check if there is a category with this id before deleting
			if(category == null)
			{
				categoryDAO.closeCurrentSessionWithTransaction();
				message = "Could not find category with ID " + id+ " ,or it might have been deleted." ;
				request.setAttribute("message", message);
				request.getRequestDispatcher("message.jsp").forward(request, response);
			}
			//The Normal case - delete the Category 
			else
			{
			categoryDAO.delete(id);
			categoryDAO.closeCurrentSessionWithTransaction();
			message = "Category deleted successfully";
			listAll(message);
			}
		}

		

	}

	/*********************************************************************************
	 * METHODS USED FOR TESTS
	 *********************************************************************************
	 *
	 **
	 * public List<Users> listUsers() { usersDAO.openCurrentSession(); List<Users>
	 * users = this.usersDAO.listAll(); usersDAO.closeCurrentSession(); return
	 * users; }
	 * 
	 * public Users findUserById(Object id) { usersDAO.openCurrentSession(); Users
	 * user = usersDAO.get(id); usersDAO.closeCurrentSession(); return user; }
	 * 
	 * public void createUser(String email, String fullName, String password) {
	 * usersDAO.openCurrentSessionWithTransaction(); Users user = new Users(email,
	 * password, fullName); usersDAO.create(user);
	 * usersDAO.closeCurrentSessionWithTransaction();
	 * 
	 * }
	 * 
	 * public void createUser(Users user) {
	 * usersDAO.openCurrentSessionWithTransaction(); usersDAO.create(user);
	 * usersDAO.closeCurrentSessionWithTransaction();
	 * 
	 * }
	 * 
	 * public List<Users> findAll() { usersDAO.openCurrentSession(); List<Users>
	 * users = usersDAO.listAll();
	 * 
	 * usersDAO.closeCurrentSession();
	 * 
	 * return users; }
	 * 
	 * public void updateUser(Users user) {
	 * usersDAO.openCurrentSessionWithTransaction();
	 * 
	 * Users emailUser = usersDAO.findByEmail(user.getEmail()); Users idUser =
	 * usersDAO.get(user.getUserId());
	 * 
	 * 
	 * case 1: the email does not exists(normal case) OR the email exists - but it
	 * belongs to the current User: update the current user including email
	 * 
	 * if (emailUser == null || (emailUser != null) && (emailUser.getUserId() ==
	 * idUser.getUserId())) usersDAO.update(user);
	 * 
	 * else System.out. println(">>UsersService.updateUser(User user): " + "There is
	 * another user with email = " + user.getEmail());
	 * 
	 * usersDAO.closeCurrentSessionWithTransaction(); }
	 * 
	 * public void deleteUser(Object id) {
	 * usersDAO.openCurrentSessionWithTransaction(); usersDAO.delete(id);
	 * usersDAO.closeCurrentSessionWithTransaction(); }
	 */

}
