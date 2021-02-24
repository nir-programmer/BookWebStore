package org.nir.bookstore.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nir.bookstore.dao.ArticleDAO;
import org.nir.bookstore.entities.Article;

public class ArticleService {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ArticleDAO articleDAO;

	public ArticleService(HttpServletRequest request, HttpServletResponse response) {

		this.request = request;
		this.response = response;
		this.articleDAO = new ArticleDAO();
	}

	public void listArticles() throws ServletException, IOException {
		listArticles(null);

	}

	private void listArticles(String message) throws ServletException, IOException {
		String articlesPage = "articles_list.jsp";
		List<Article> articles;
		// open a session and transaction
		this.articleDAO.openCurrentSessionWithTransaction();

		// read list of articles
		articles = this.articleDAO.listAll();

		// commit transaction and close session
		this.articleDAO.closeCurrentSessionWithTransaction();

		// set the articles into the request
		request.setAttribute("articles", articles);

		// set the message into the request
		if (message != null)
			request.setAttribute("message", message);

		// forward to articles_list.jspbook_form.jsp
		request.getRequestDispatcher("articles_list.jsp").forward(request, response);

	}

	public void newArticle() throws ServletException, IOException {
		String articleFormPage = "article_form.jsp";

		request.getRequestDispatcher(articleFormPage).forward(request, response);

	}

	public void createArticle() throws IOException, ServletException {
		String title;
		String content;
		Article article;
		String message;
		// 1.Read the form input :title , content:OK
		title = request.getParameter("title");
		content = request.getParameter("content");

		// response.getWriter().println(">>createArticle(): title = " + title + "
		// \ncontent = " + content);

		// 2. Check if there is an article with this title - if there is - forward to
		// message.jsp
		this.articleDAO.openCurrentSessionWithTransaction();
		Article existArticle = this.articleDAO.findByTitle(title);
		if (existArticle != null) {
			this.articleDAO.closeCurrentSessionWithTransaction();
			message = "Could not create a new article!\nThere is already an article with title: " + title;
			CommonUtitlity.forwardToPage("message.jsp", message, request, response);

			return;
		}

		// 2.Create a new Article Object from these values
		article = new Article(title, content);

		// 3.Persist the object to the database: OK!!!

		Article savedArticle = this.articleDAO.create(article);
		this.articleDAO.closeCurrentSessionWithTransaction();

		// response.getWriter().println(">>createArticle(): The article: " +
		// savedArticle + " has been saved!");

		// 4. Call listArticles() method with a success message parameter
		message = "A new article has been saved successfully!";
		this.listArticles(message);

	}

	public void editArticle() throws IOException, ServletException {
		Integer articleId;
		Article article;
		String message;
		// 1.Read the id of the article form the request : OK
		articleId = Integer.parseInt(request.getParameter("id"));

		// response.getWriter().println(">>editArticle(): The id: " + articleId);

		// 2.get the Article from the database: OK!!
		this.articleDAO.openCurrentSessionWithTransaction();
		article = this.articleDAO.get(articleId);

		// For concurrent access: If does not exist - error message
		if (article == null) {
			this.articleDAO.closeCurrentSessionWithTransaction();
			message = "Could not edit the article.The article does not exists!";
			CommonUtility.forwardToPage("message.jsp", message, request, response);
			return;
		}
		this.articleDAO.closeCurrentSessionWithTransaction();

		// response.getWriter().println(">>editArticle(): The article from db: " +
		// article);

		// 3.Add the article to the request
		request.setAttribute("article", article);

		// 4.Forward the request to the article_form.jsp page
		request.getRequestDispatcher("article_form.jsp").forward(request, response);

	}

	public void updateArticle() throws IOException, ServletException {
		Integer articleId;
		String title;
		String content;
		Article existArticle, articleByTitle;
		String message;

		// 1.Read the form data for the title and content from the request
		articleId = Integer.parseInt(request.getParameter("id"));

		title = request.getParameter("title");
		content = request.getParameter("content");

		// 3 articleByTitle <- findByTitle(title);
		this.articleDAO.openCurrentSessionWithTransaction();
		existArticle = this.articleDAO.get(articleId);

		articleByTitle = this.articleDAO.findByTitle(title);
		// 4 if the articleByTitle exists AND articleByTitle not equals to
		// existedArticle
		// Send an error message!!! and return
		if (articleByTitle != null && !articleByTitle.equals(existArticle)) {
			this.articleDAO.closeCurrentSessionWithTransaction();
			message = "Could not update the article!\nThere is already an article with title: " + title;
			CommonUtility.forwardToPage("message.jsp", message, request, response);
			return;
		}

		// 5 else
		// 5.1 Update the object with the request values(title and content)
		existArticle.setTitle(title);
		existArticle.setContent(content);

		// 5.2 Update the object into the database
		this.articleDAO.update(existArticle);

		this.articleDAO.closeCurrentSessionWithTransaction();

		// 5.3 Call the listAll() methods with a message...
		this.listArticles("The article has been update successfully!");

	}

	public void deleteArticle() throws ServletException, IOException {
		Integer articleId;
		Article existArticle;
		String message;

		// read the id from the request
		articleId = Integer.parseInt(request.getParameter("id"));

		// get the article from the data base
		this.articleDAO.openCurrentSessionWithTransaction();
		existArticle = this.articleDAO.get(articleId);

		// For concurrent access: If does not exist - error message
		if (existArticle == null) {
			this.articleDAO.closeCurrentSessionWithTransaction();
			message = "Could not delete the article.The article does not exists!";
			CommonUtility.forwardToPage("message.jsp", message, request, response);
			return;
		}

		// else call the DAO.delete(id)
		articleDAO.delete(articleId);
		this.articleDAO.closeCurrentSessionWithTransaction();

		// message and forward
		listArticles("The article has been deleged succesffully!");

	}

	/*************************************************
	 * 					Front End
	 ******************************************/
	public void getAbout() throws IOException 
	{
		Article article; 
		
		//read the title of the article form the request
		String title = request.getParameter("title"); 
		response.getWriter().println(">>title = " + title);
		
		
		//get the Article from the db by title
		this.articleDAO.openCurrentSessionWithTransaction();
		article = this.articleDAO.findByTitle(title);
		
		
		response.getWriter().println(">>The article from the db = \n" + title);
		this.articleDAO.closeCurrentSessionWithTransaction();
		
		
	}

}
