package org.nir.bookstore.dao;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.nir.bookstore.entities.Article;

public class TestArticleDAO {

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
		article.setTitle("Contact Up");
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
		
		System.out.println(">>testGetArticleExists():Reaind article with id + " + id); 
		article = articleDAO.get(id); 
		assertNotNull(article);
		
		
		System.out.println(">>testGetArticleExists():The returned article: \n" + article); 
		
	}
	
	@Test
	@DisplayName("When calling update() method")
	public void testUpdataArticle()
	{
		Integer id = 3; 
		Article article; 
		
		System.out.println("\n\n>>testGetUpdateArticle():Reading article with  id + " + id); 
		
		article = articleDAO.get(id);
		
		
		
		assertNotNull(article);
		
		
		System.out.println(">>testGetArticleExists():article BEFORE update is  " + article); 
		
		
		System.out.println("\n\n>>testGetUpdateArticle():Updating article with  id + " + id); 
		
		article.setTitle("xxxxxx");
		article.setContent("cccccc");
		
		Article updaArticle = articleDAO.update(article);
		
		
		System.out.println("\n\n>>testGetUpdateArticle():Updating article is : " + updaArticle); 
		
		
	}
	
	@Test
	@DisplayName("When deleging article")
	public void testDeleteArticle()
	{ 
		Integer id = 4; 
		
		System.out.println(">>testDeleteArticle():Trying to delete article with id = " + id); 
		
		articleDAO.delete(id);
		
		Article deletedArticle = articleDAO.get(id);
		assertNull(deletedArticle);
		
		System.out.println(">>testDeleteArticle():Artiecle with  = " + id + 
				" AFTER delete: "+ deletedArticle); 
	
		
	}
	

}