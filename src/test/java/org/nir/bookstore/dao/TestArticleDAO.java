package org.nir.bookstore.dao;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Article;

public class TestArticleDAO {
//
	private static ArticleDAO articleDAO = null; 

	@BeforeAll
	@DisplayName("when create ArticleDAO object")
	public static void init() {
		articleDAO = new ArticleDAO();
	}

	@BeforeEach
	@DisplayName("when calling openCurrentSessionWithTransaction()")
	void testOpenCurrentSessionWithTransaction() {
		articleDAO.openCurrentSessionWithTransaction();
	}

	@AfterEach
	@DisplayName("when calling closeCurrentSessionWithTransaction()")
	void testcloseCurrentSessionWithTransaction() {
		articleDAO.closeCurrentSessionWithTransaction();
	}

	/*************************************************************
	 * 							TESTS
	 ****************************************************/
	
	//OK!!
	@Test
	@DisplayName("When createing a new Article")
	public void testCreateArticle()
	{
		Article article  = new Article();
		article.setTitle("Extra Article");
		article.setContent("You can contact us in each day of the week!!!");
		
		System.out.println(">>testCreateArticle():Trying to persist the article..."); 
		Article savedArticle = articleDAO.create(article);
		
		assertNotNull(savedArticle);
			
	}

	//DONE
	@Test
	@DisplayName("When calling get()")
	public void testGetArticleExists()
	{
		Integer id = 2; 
		Article article ; 
		
		System.out.println("\n\n>>testGetArticleExists():Reaind article with id + " + id); 
		article = articleDAO.get(id); 
		assertNotNull(article);
		
		
		System.out.println(">>testGetArticleExists():The returned article: \n" + article); 
		System.out.println("\n\n");
		
	}
	
	@Test
	@DisplayName("When calling update() method")
	public void testUpdataArticle()
	{
		Integer id = 2; 
		Article article; 
		
		System.out.println("\n\n>>testGetUpdateArticle():Reading article with  id + " + id); 
		
		article = articleDAO.get(id);
		
		
		
		assertNotNull(article);
		
		
		System.out.println(">>testGetArticleExists():article BEFORE update is  " + article); 
		
		
		System.out.println("\n\n>>testGetUpdateArticle():Updating article with  id + " + id); 
		
		article.setTitle("Contact Us");
		article.setContent("You can contact us every day! ");
		
		Article updaArticle = articleDAO.update(article);
		
		
		System.out.println("\n\n>>testGetUpdateArticle():Updating article is : " + updaArticle); 
		
		
	}
	
	//OK
	@Test
	@DisplayName("When trying to update an article with an existing title")
	public void testUpdataArticleWithExistingTitle()
	{
		Integer id = 2; 
		Article article; 
		
		System.out.println("\n\n>>testGetUpdateArticle():Reading article with  id + " + id); 
		
		article = articleDAO.get(id);
		
		
		
		assertNotNull(article);
		
		
		System.out.println(">>testGetArticleExists():article BEFORE update is  " + article); 
		
		
		System.out.println("\n\n>>testGetUpdateArticle():Updating article with  id + " + id); 
		
		article.setTitle("About Us");
		article.setContent("You can contact us every day! ");
		
		Article updaArticle = articleDAO.update(article);
		
		//assertThrows(PersistenceException.class	, articleDAO.update(article));
		
		System.out.println("\n\n>>testGetUpdateArticle():Updating article is : " + updaArticle); 
		
		
	}
	
	@Test
	@DisplayName("When deleging article")
	public void testDeleteArticle()
	{ 
		Integer id = 7; 
		
		System.out.println(">>testDeleteArticle():Trying to delete article with id = " + id); 
		
		articleDAO.delete(id);
		
		Article deletedArticle = articleDAO.get(id);
		assertNull(deletedArticle);
		
		System.out.println(">>testDeleteArticle():Artiecle with  = " + id + 
				" AFTER delete: "+ deletedArticle); 
		
	}
	
	//OK
	@Test 
	@DisplayName("When adding a new article with an existing title")
	public void testAddingArticleWithExistingTitle()
	{
		
		Article article = new Article("Contact Us" , "Just Random content.."); 
		System.out.println("\n\n>>testAddingArticleWithExistingTitle():Adding an article with exiting title");
		articleDAO.create(article);
		
		System.out.println(">>Should not reach here!!"); 
	
	}
	
	//OK
	@Test
	@DisplayName("When calling listAll() method")
	public void testListAll()
	{
		
		List<Article> articles; 
		
		System.out.println("\n\n>>Trying to read all articles from the db.."); 
		
		articles = articleDAO.listAll();
		
		assertEquals(4, articles.size());
		
		articles.forEach(System.out::println);
	
	}
	
	//OK
	@Test
	@DisplayName("When calling count() ")
	public void testCount()
	{
		long expected = 5; 
		System.out.println("\n\n>>Call count().."); 
		long actual = articleDAO.count();
		
		assertEquals(expected, actual);
		
		System.out.println("Number of articles in the database: " + actual);
		
	}
	
	@Test
	@DisplayName("When calling findByTitle()")
	public void testFindByNameExists()
	{
		
		String title = "Contact Us"; 
		Article article =articleDAO.findByTitle(title);
		
		assertNotNull(article);
		
		System.out.println(">>testFindByTitle(): the article is: " +  article);
	}
	
	
	@Test
	@DisplayName("When calling findByTitle() with non exists title")
	public void testFindByNameNotExists()
	{
		
		String title = "Contact Ussss"; 
		Article article =articleDAO.findByTitle(title);
		
		assertNull(article);
		
		System.out.println(">>testFindByTitle(): the article is: " +  article);
	}
	


}