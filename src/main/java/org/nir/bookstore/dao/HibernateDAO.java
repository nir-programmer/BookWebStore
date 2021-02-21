package org.nir.bookstore.dao;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.nir.bookstore.entities.Article;
import org.nir.bookstore.entities.Book;
import org.nir.bookstore.entities.BookOrder;
import org.nir.bookstore.entities.Category;
import org.nir.bookstore.entities.Customer;
import org.nir.bookstore.entities.OrderDetail;
import org.nir.bookstore.entities.OrderDetailId;
import org.nir.bookstore.entities.Review;
import org.nir.bookstore.entities.Users;

public class HibernateDAO<E>
{	
	//Tests..
	private static int numberOfOpenSessions = 0 ;
	private static  int numberOfClosedSessions = 0 ;
	private static int  numberOfBeginTransactions  = 0 ;
	private static int numberOfCommitedTransactions  = 0 ;
	private static int numberOfClosedSessionFactory = 0 ; 
	///
	
	
	private  Session currentSession;  
	private  Transaction currentTransaction; 
	
	//new code
	private static SessionFactory sessionFactory; 
	
	static 
	{
		System.out.println(">>HibernateDAO inside the static block , numberOfOpenSessionFactory =  "
						+ numberOfClosedSessionFactory);
		
		System.out.println(">>HibernateDAO inside the static block - try to create session factory");
		
		sessionFactory = new Configuration().configure("hibernate.cfg.xml")
				 .addAnnotatedClass(Category.class)
				 .addAnnotatedClass(Book.class)
				 .addAnnotatedClass(Customer.class)
				 .addAnnotatedClass(OrderDetailId.class)
				 .addAnnotatedClass(OrderDetail.class)
				 .addAnnotatedClass(Review.class)
				 .addAnnotatedClass(Users.class)
				 .addAnnotatedClass(BookOrder.class)
				 .addAnnotatedClass(Article.class)
				 .buildSessionFactory();
		
		numberOfClosedSessionFactory ++ ;
		
		System.out.println(">>HibernateDAO inside the static block - sessionFactory created!");
		System.out.println(">>HibernateDAO inside the static block , numberOfOpenSessionFactory =  "
				+ numberOfClosedSessionFactory);
	}
	///////////////////////////////////////////////////////
	public HibernateDAO() {}
	
/*****************************************************************************
 * 									Interface Methods Implementations
 * *********************************************** ***************************/

	public HibernateDAO(Session session) 
	{
		super();
		this.currentSession = session;
	} 
	
	//OK
	protected E create(E entity)
	{
		getCurrentSession().save(entity);
		
		
		return entity; 
	}
	
	//OK
	protected E find (Class<E> entity ,Object id)
	{
		Session session = getCurrentSession();
		
		//find method can return null but get doesn't
		E e = session.find(entity, id);
		if(e != null)
			session.refresh(e);
		return e; 
	}
	
	protected void delete(Class<E> entity, Object id) 
	{
		Session session = getCurrentSession();
		Object reference = session.getReference(entity, id);
		/*
		 * Have to invoke inside a transaction, else will not work
		 * it will not throw exception -just does not effect the db
		 */
		session.remove(reference);
		
	}

	protected E update(E entity) 
	{
		Session session = getCurrentSession();
		/*
		 * The merge method will create an entity if 
		 * there isn't
		 */
		
		E e = (E)session.merge(entity);
		return entity;
		
	}

	protected List<E> findWithNamedQuery(String queryName) 
	{
		Session session = getCurrentSession();
		Query<E> query = session.createNamedQuery(queryName);
		List<E> entities = query.getResultList();
		return entities;
	}


	public List<E> findWithNamedQuery(String queryName, String paramName , Object id)
	{	
		Session session = getCurrentSession();
		Query<E> query = session.createNamedQuery(queryName);
		query.setParameter(paramName, id);
		return query.getResultList();
	}
	
	protected List<E> findWithNamedQuery(String queryName ,Map<String , Object> parameters)
	{
		Query query = getCurrentSession().createNamedQuery(queryName);  
		
		Set<Entry<String, Object>> setParameters = parameters.entrySet();
		
		//Copy each (key , value) pair from the map into the (name, value) pair of the parameter name
		for(Entry<String, Object> entry: setParameters)
		{
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		return query.getResultList();
	}
	
	protected List<E> findWithNamedQuery(String queryName , int firstResult , int maxResult) 
	{
		Session session = getCurrentSession();
		Query<E> query = session.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult); 
		
		List<E> entities = query.getResultList();
		return entities;
	}
	
	/*
	 * This method is to fix the problem with retrieving the List<Book> most favored ..
	 * 
	 */
	protected List<Object[]> findWithNamedQueryObjects(String queryName , int firstResult , int maxResult) 
	{
		Session session = getCurrentSession();
		Query<Object[]> query = session.createNamedQuery(queryName);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResult); 
		
		List<Object[]> entities = query.getResultList();
		return entities;
	}

	
	protected long countWithNamedQuery(String queryName) 
	{
		Session session = getCurrentSession();
		Query<E> query = session.createNamedQuery(queryName);
		long n = (long)query.getSingleResult(); 
		return n;
	}
	
	public long countWithNamedQuery(String queryName, String paramName, Integer paramValue) 
	{
		Session session = getCurrentSession();
		Query query = session.createNamedQuery(queryName);
		query.setParameter(paramName, paramValue);
		long n = (long)query.getSingleResult(); 
		
		//session.close();
		return n;
		
		/*
		 * Query query = this.currentSession.createNamedQuery(queryName);
		 * query.setParameter(paramName, paramValue); long res =
		 * (long)query.getSingleResult(); return res;
		 */
	}
	
	/******************************************************
	 * METHODS FOR SESSIONS AND TRANSACTIONS HANDLINGS
	 ***************************************************/
	public Session openCurrentSession()
	{
		System.out.println(">>HibernateDAO.openCurrentSession():numberOfOpenSessions = "
					+ numberOfOpenSessions);
		
		System.out.println(">>HibernateDAO.openCurrentSession():Trying to open a new Session...");
		
		currentSession = getSessionFactory().openSession();
		
		System.out.println(">>HibernateDAO.openCurrentSession():A new Session has opened successfully");
		
		numberOfOpenSessions++; 
		
		System.out.println(">>HibernateDAO.openCurrentSession():numberOfOpenSessions = "
				+ numberOfOpenSessions);
	

		return currentSession;
	}
	
	public void openCurrentSessionWithTransaction() 
	{
		System.out.println(">>HibernateDAO.openCurrentSessionWithTransaction():numberOfOpenSessions = "
				+ numberOfOpenSessions);
		
		System.out.println(">>HibernateDAO.openCurrentSessionWithTransaction():Trying to open a new Session...");
		
		currentSession = getSessionFactory().openSession();
		
		System.out.println(">>HibernateDAO.openCurrentSessionWithTransaction():A "
				+ "	new Session has opened successfully");
		
		//update number of open sessions
		numberOfOpenSessions++;
		
		System.out.println(">>HibernateDAO.openCurrentSessionWithTransaction():numberOfOpenSessions = "
				+ numberOfOpenSessions);
		
		
		System.out.println(">>HibernateDAO.openCurrentSessionWithTransaction():numberOfOpenBegunTransactions = "
				+ numberOfBeginTransactions);
		
		System.out.println(">>HibernateDAO.openCurrentSessionWithTransaction():Trying to begin a  newTransaction...");
		
		currentTransaction = currentSession.beginTransaction();
		
		//update number of begun transactions
		numberOfBeginTransactions++;
		
		
		System.out.println(">>HibernateDAO.openCurrentSessionWithTransaction():numberOfOpenBegunTransactions = "
				+ numberOfBeginTransactions);
		
		
		System.out.println(">>HibernateDAO.openCurrentSessionWithTransaction():A new Transaction begun successfully!");
	}
	
	
	public void closeCurrentSession()
	{
		System.out.println(">>HibernateDAO.closeCurrentSession():numberOfOpenSessions = "
				+ numberOfOpenSessions);
	
		System.out.println(">>HibernateDAO.openCurrentSession():Trying to close a Session");
	
		currentSession.close();
		
		//update number of open sessions
		numberOfOpenSessions--; 
		
		System.out.println(">>HibernateDAO.closeCurrentSession():A session closed successfylly!");
		
		System.out.println(">>HibernateDAO.closeCurrentSession():numberOfOpenSessions = "
				+ numberOfOpenSessions);
	}
	
	public void closeCurrentSessionWithTransaction()
	{
		System.out.println(">>HibernateDAO.closeCurrentSessionWithTransaction():"
				+ "Trying to commit a Transaction...");
		
		currentTransaction.commit();
		
		System.out.println(">>HibernateDAO.closeCurrentSessionWithTransaction():"
				+ "Transaction has been commited successfully!");
		
		//update number of begun transactions
		numberOfBeginTransactions--;
		
		System.out.println(">>HibernateDAO.closeCurrentSessionWithTransaction():numberOfOpenBegunTransactions = "
				+ numberOfBeginTransactions);

		
		
		
		System.out.println(">>HibernateDAO.closeCurrentSessionWithTransaction():"
				+ "numberOfOpenSessions = "+ numberOfOpenSessions);
	
		System.out.println(">>HibernateDAO.closeCurrentSessionWithTruansaction()"
					+ ":Trying to close a Session");
	
		currentSession.close();
		
		//update number of open sessions
		numberOfOpenSessions--; 
		
		System.out.println(">>HibernateDAO.closeCurrentSessionWithTransaction():A session closed successfylly!");
		
		System.out.println(">>HibernateDAO.closeCurrentSessionWithTransaction():numberOfOpenSessions = "
				+ numberOfOpenSessions);

	}
	
	protected static SessionFactory getSessionFactory()
	{
		
		boolean isSessionFactoryOpen = sessionFactory.isOpen();
		
		System.out.println(">>HibernateDAO.getSessionFactory(): The SessionFactory is open: " + isSessionFactoryOpen);
		
		/*
		 * System.out.
		 * println(">>HibernateDAO.getSessionFactory():Trying to create the SessionFactory..."
		 * );
		 * 
		 * SessionFactory sessionFactory = new
		 * Configuration().configure("hibernate.cfg.xml")
		 * .addAnnotatedClass(Category.class) .addAnnotatedClass(Book.class)
		 * .addAnnotatedClass(Customer.class) .addAnnotatedClass(OrderDetailId.class)
		 * .addAnnotatedClass(OrderDetail.class) .addAnnotatedClass(Review.class)
		 * .addAnnotatedClass(Users.class) .addAnnotatedClass(BookOrder.class)
		 * .buildSessionFactory();
		 * 
		 * System.out.
		 * println(">>HibernateDAO.getSessionFactory():A new SessionFactory has been created successfully!"
		 * );
		 */
		return sessionFactory;
	}
	
	/*
	 * **************************Getters and setters***********************************************
	 */
	public Session getCurrentSession()
	{
		return currentSession;
	}
	
	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}
	
	public Transaction getCurrentTransaction() {
		return currentTransaction;
	}

	public void setCurrentTransaction(Transaction currentTransaction) 
	{
		this.currentTransaction = currentTransaction;
	}

	
	
	/*******************************************************
	 *
	 ****************************************************/
	
	
}
