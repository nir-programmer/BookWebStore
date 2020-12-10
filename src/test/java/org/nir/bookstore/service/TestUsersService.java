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
	
	@BeforeAll
	@DisplayName("create UsersService")
	public static void init()
	{
		usersService = new UsersService();
	}
	
	
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
	
	@Test

}
