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
		categoryDAO = new CategoryDAO();
		this.request = request;
		this.response = response;
	}

	/******************************************************
	 * METODS USED BY THE SERVLETS
	 * 
	 * @throws IOException
	 * @throws ServletException
	 ******************************************************/

	//OK
	public void listAll() throws ServletException, IOException 
	{
		this.listAll(null);

	}
	
	//OK
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
	
	public void createCategory() throws ServletException, IOException
	{
		categoryDAO.openCurrentSessionWithTransaction();
		String name = request.getParameter("categoryName"); 
		
		Category category =new Category(name); 
		
		categoryDAO.create(category);
		
		categoryDAO.closeCurrentSessionWithTransaction();
		
		listAll("Category Created Seccussfully!");
		
		
		
	}
	/*
	 * // OK public void getAllUsers(String message) throws ServletException,
	 * IOException { if (message != null) request.setAttribute("message", message);
	 * 
	 * usersDAO.openCurrentSession(); List<Users> users = usersDAO.listAll();
	 * usersDAO.closeCurrentSession();
	 * 
	 * request.setAttribute("users", users);
	 * request.getRequestDispatcher("users_list.jsp").forward(request, response);
	 * 
	 * }
	 * 
	 * // OK public void getAllUsers() throws ServletException, IOException {
	 * getAllUsers(null); }
	 * 
	 * // OK public void createUser() throws ServletException, IOException { String
	 * email = request.getParameter("email"); String fullName =
	 * request.getParameter("fullname"); String password =
	 * request.getParameter("password");
	 * 
	 * usersDAO.openCurrentSession(); Users existUser = usersDAO.findByEmail(email);
	 * usersDAO.closeCurrentSession();
	 * 
	 * if (existUser != null) {
	 * System.out.println(">>UsersService.createUser():The User " + existUser +
	 * " Exists!"); String message = "Couldn't create a user. " +
	 * "A user with email " + email + " already exists!";
	 * request.setAttribute("message", message);
	 * request.getRequestDispatcher("message.jsp").forward(request, response); }
	 * else { Users newUser = new Users(email, password, fullName);
	 * 
	 * usersDAO.openCurrentSessionWithTransaction(); usersDAO.create(newUser);
	 * usersDAO.closeCurrentSessionWithTransaction();
	 * getAllUsers("User created succssfully!"); }
	 * 
	 * }
	 * 
	 * public void editUser() throws ServletException, IOException { int userId =
	 * Integer.parseInt(request.getParameter("id"));
	 * 
	 * usersDAO.openCurrentSession(); Users user = usersDAO.get(userId);
	 * usersDAO.closeCurrentSession();
	 * 
	 * // System.out.println("THE User is: " + user.getFullName());
	 * 
	 * request.setAttribute("user", user);
	 * 
	 * request.getRequestDispatcher("users_form.jsp").forward(request, response);
	 * 
	 * }
	 * 
	 * public void updateUser() throws ServletException, IOException { int id =
	 * Integer.parseInt(request.getParameter("userId")); String email =
	 * request.getParameter("email"); String fullName =
	 * request.getParameter("fullname"); String password =
	 * request.getParameter("password");
	 * 
	 * System.out.println(">>UsersService.update()" + "\n" + id + " , " + email +
	 * " , " + fullName + " , " + password);
	 * 
	 * Users user = new Users(id, email, password, fullName);
	 * 
	 * usersDAO.openCurrentSessionWithTransaction(); Users emailUser =
	 * usersDAO.findByEmail(user.getEmail()); Users idUser =
	 * usersDAO.get(user.getUserId());
	 * 
	 * 
	 * case 1: the email does not exists(normal case) OR the email exists - but it
	 * belongs to the current User: update the current user including email
	 * 
	 * if (emailUser == null || (emailUser != null) && (emailUser.getUserId() ==
	 * idUser.getUserId())) { usersDAO.update(user);
	 * usersDAO.closeCurrentSessionWithTransaction();
	 * getAllUsers("User updated successfully"); } else {
	 * usersDAO.closeCurrentSessionWithTransaction(); String message =
	 * "Could not update user. User with email " + user.getEmail() +
	 * " already exists"; request.setAttribute("message", message);
	 * request.getRequestDispatcher("message.jsp").forward(request, response); }
	 * 
	 * }
	 * 
	 * public void deleteUser() throws ServletException, IOException { Integer
	 * userID = Integer.parseInt(request.getParameter("id"));
	 * 
	 * // System.out.println(">>UserService.deleteUser(): id = " + userID);
	 * 
	 * usersDAO.openCurrentSessionWithTransaction(); usersDAO.delete(userID);
	 * System.out.println(">>UsersService.delteUser:User with id = " + userID +
	 * " Dleted"); usersDAO.closeCurrentSessionWithTransaction();
	 * 
	 * getAllUsers("User Deleted Succussfully");
	 * 
	 * }
	 */

	

	/******************************************************
	 * METHODS USED FOR TESTS
	 ******************************************************
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
		 * else System.out. println(">>UsersService.updateUser(User user): " +
		 * "There is another user with email = " + user.getEmail());
		 * 
		 * usersDAO.closeCurrentSessionWithTransaction(); }
		 * 
		 * public void deleteUser(Object id) {
		 * usersDAO.openCurrentSessionWithTransaction(); usersDAO.delete(id);
		 * usersDAO.closeCurrentSessionWithTransaction(); }
		 */

}
