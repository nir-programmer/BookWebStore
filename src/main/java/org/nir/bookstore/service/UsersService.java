package org.nir.bookstore.service;

import java.io.IOException;
import java.net.HttpRetryException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.nir.bookstore.dao.UsersDAO;
import org.nir.bookstore.entities.Users;

public class UsersService {
	private static UsersDAO usersDAO;

	private HttpServletRequest request;
	private HttpServletResponse response;

	/************************** CONSTRUCTORS ****************************/
	// OK: used By the Servlets
	public UsersService(HttpServletRequest request, HttpServletResponse response) {
		usersDAO = new UsersDAO();
		this.request = request;
		this.response = response;
	}

	public UsersService() {
		usersDAO = new UsersDAO();

	}

	/******************************************************
	 * METODS USED BY THE SERVLETS
	 ******************************************************/
	// OK
	public void getAllUsers(String message) throws ServletException, IOException {
		if (message != null)
			request.setAttribute("message", message);

		usersDAO.openCurrentSession();
		List<Users> users = usersDAO.listAll();
		usersDAO.closeCurrentSession();

		request.setAttribute("users", users);
		request.getRequestDispatcher("users_list.jsp").forward(request, response);

	}

	// OK
	public void getAllUsers() throws ServletException, IOException {
		getAllUsers(null);
	}

	// OK
	public void createUser() throws ServletException, IOException {
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");

		usersDAO.openCurrentSession();
		Users existUser = usersDAO.findByEmail(email);
		usersDAO.closeCurrentSession();

		if (existUser != null) {
			System.out.println(">>UsersService.createUser():The User " + existUser + " Exists!");
			String message = "Couldn't create a user. " + "A user with email " + email + " already exists!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		} else {
			Users newUser = new Users(email, password, fullName);

			usersDAO.openCurrentSessionWithTransaction();
			usersDAO.create(newUser);
			usersDAO.closeCurrentSessionWithTransaction();
			getAllUsers("User created succssfully!");
		}

	}

	public void editUser() throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("id"));

		usersDAO.openCurrentSession();
		Users user = usersDAO.get(userId);
		usersDAO.closeCurrentSession();

		// System.out.println("THE User is: " + user.getFullName());

		request.setAttribute("user", user);

		request.getRequestDispatcher("users_form.jsp").forward(request, response);

	}

	public void updateUser() throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("userId"));
		String email = request.getParameter("email");
		String fullName = request.getParameter("fullname");
		String password = request.getParameter("password");

		System.out.println(">>UsersService.update()" + "\n" + id + " , " + email + " , " + fullName + " , " + password);

		Users user = new Users(id, email, password, fullName);

		usersDAO.openCurrentSessionWithTransaction();
		Users emailUser = usersDAO.findByEmail(user.getEmail());
		Users idUser = usersDAO.get(user.getUserId());

		/*
		 * case 1: the email does not exists(normal case) OR the email exists - but it
		 * belongs to the current User: update the current user including email
		 */
		if (emailUser == null || (emailUser != null) && (emailUser.getUserId() == idUser.getUserId())) {
			usersDAO.update(user);
			usersDAO.closeCurrentSessionWithTransaction();
			getAllUsers("User updated successfully");
		} else {
			usersDAO.closeCurrentSessionWithTransaction();
			String message = "Could not update user. User with email " + user.getEmail() + " already exists";
			request.setAttribute("message", message);
			request.getRequestDispatcher("message.jsp").forward(request, response);
		}

	}

	public void deleteUser() throws ServletException, IOException {
		Integer userID = Integer.parseInt(request.getParameter("id"));

		// System.out.println(">>UserService.deleteUser(): id = " + userID);

		usersDAO.openCurrentSessionWithTransaction();
		usersDAO.delete(userID);
		System.out.println(">>UsersService.delteUser:User with id = " + userID + " Dleted");
		usersDAO.closeCurrentSessionWithTransaction();

		getAllUsers("User Deleted Succussfully");

	}

	/******************************************************
	 * METHODS USED FOR TESTS
	 ******************************************************/
	public List<Users> listUsers() {
		usersDAO.openCurrentSession();
		List<Users> users = this.usersDAO.listAll();
		usersDAO.closeCurrentSession();
		return users;
	}

	public Users findUserById(Object id) {
		usersDAO.openCurrentSession();
		Users user = usersDAO.get(id);
		usersDAO.closeCurrentSession();
		return user;
	}

	public void createUser(String email, String fullName, String password) {
		usersDAO.openCurrentSessionWithTransaction();
		Users user = new Users(email, password, fullName);
		usersDAO.create(user);
		usersDAO.closeCurrentSessionWithTransaction();

	}

	public void createUser(Users user) {
		usersDAO.openCurrentSessionWithTransaction();
		usersDAO.create(user);
		usersDAO.closeCurrentSessionWithTransaction();

	}

	public List<Users> findAll() {
		usersDAO.openCurrentSession();
		List<Users> users = usersDAO.listAll();

		usersDAO.closeCurrentSession();

		return users;
	}

	public void updateUser(Users user) {
		usersDAO.openCurrentSessionWithTransaction();

		Users emailUser = usersDAO.findByEmail(user.getEmail());
		Users idUser = usersDAO.get(user.getUserId());

		/*
		 * case 1: the email does not exists(normal case) OR the email exists - but it
		 * belongs to the current User: update the current user including email
		 */
		if (emailUser == null || (emailUser != null) && (emailUser.getUserId() == idUser.getUserId()))
			usersDAO.update(user);

		else
			System.out.println(
					">>UsersService.updateUser(User user): " + "There is another user with email = " + user.getEmail());

		usersDAO.closeCurrentSessionWithTransaction();
	}

	public void deleteUser(Object id) {
		usersDAO.openCurrentSessionWithTransaction();
		usersDAO.delete(id);
		usersDAO.closeCurrentSessionWithTransaction();
	}

	public void login() throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		usersDAO.openCurrentSessionWithTransaction();
		boolean loginResult = usersDAO.checkLogin(email, password);
		usersDAO.closeCurrentSessionWithTransaction();

		if (loginResult) 
		{
			System.out.println("User is authenticated");
			
			request.getSession().setAttribute("userEmail", email);
			
			request.getRequestDispatcher("/admin/").forward(request, response);
		
		}
		else
		{
			System.out.println("Login Failed"); 
			String message = "Login Failed"; 
			request.setAttribute("message", message);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
