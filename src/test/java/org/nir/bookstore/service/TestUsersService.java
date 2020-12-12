package org.nir.bookstore.service;

import java.util.List;

import org.apache.catalina.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Users;

public class TestUsersService 
{
	private static UsersService usersService;
	
	//OK
	@BeforeAll
	@DisplayName("create UsersService")
	public static void init()
	{
		usersService = new UsersService();
	}
	
	
	//OK
	@Test
	@DisplayName("when creating a new user by the UsersService")
	void testCreateUsers()
	{
		Users user1 = new Users("YYY", "YYY", "YYY");
		Users user2 = new Users("AAA", "BBB", "CCC");
		
		System.out.println("*** TestUsersService:Persist - Start ***");
		usersService.createUser(user1);
		usersService.createUser(user2);
		List<Users> users1 = usersService.findAll();
		System.out.println("Users Persisted are "); 
			users1.stream().forEach(System.out::println);
	}
	
	//OK
	@Test
	@DisplayName("when calling findAll()")
	void testFindAll()
	{
		List<Users>users = usersService.findAll();
		System.out.println(">>testFindAll():List of users:");
		users.stream().forEach(System.out::println);
	}
	
	//OK
	@Test
	@DisplayName("when calling to get(id) method")
	void testGetFound()
	{
		Integer id = 1; 
		Users user = usersService.findUserById(id);
		System.out.println("testGetFound():User with id = " + id);
		System.out.println(user); 
		
	}
	
	@Test
	@DisplayName("when calling updateUser()")
	void testUpdateUser()
	{
		
		Users user = usersService.findUserById(1);
		System.out.println(">>testUpdateUser():the User BEFORE update: " + user); 
		user.setEmail("D");
		user.setFullName("E");
		user.setPassword("E");
		usersService.updateUser(user); 
		
		user = usersService.findUserById(1);
		usersService.updateUser(user); 
		System.out.println(">>testUpdateUser():the User AFTER update: " + user); 
		//System.out.println(">>testUpdateUser():the User BEFORE update: " + user); 
		
	}
	
	@Test
	@DisplayName("when calling deleteUser(Objcet id)")
	void testDeleteUser()
	{
		Integer id = 1; 
		Users user = usersService.findUserById(id); 
		System.out.println(">>testDeleteUser():the  db user retrieved BEFORE with id: " + id); 
		System.out.println(user); 
		
		usersService.deleteUser(id);
		
		 user = usersService.findUserById(id); 
		System.out.println(">>testDeleteUser():the user retrieved AFTER with id " + id); 
		System.out.println(user); 
		
	}
	
	
	
	
	
	

}
