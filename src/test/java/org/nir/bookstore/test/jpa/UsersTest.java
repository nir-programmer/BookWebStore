package org.nir.bookstore.test.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.nir.bookstore.entity.Users;

public class UsersTest 
{
	
	public static void main(String[] args)
	{
		
		//Step 0 -create Users object
		Users users = new Users(); 
		users.setEmail("niritzhak10@gmail.com");
		users.setFullName("Nir Itzhak");
		users.setPassword("hello world");
		
		//Step 1
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebSite");
		
		//step 2: 
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		
		
		//step 3: 
		entityManager.getTransaction().begin();
		
		//step 4
		entityManager.persist(users);
		
		//step 5
		entityManager.getTransaction().commit();
		
		
		//step 6
		entityManager.close();
		entityManagerFactory.close();
		
		//step 7
		System.out.println(">>main():A Users object was persisted");
		
		
	}
	
	
}
