package org.nir.bookstore.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nir.bookstore.entities.Review;

public class ReviewDAO extends HibernateDAO<Review> implements GenericeDAO<Review> {

	@Override
	public Review get(Object reviewId) {
		return super.find(Review.class, reviewId);
	}

	
	/*
	 * It is OK to delete Review because it is the 'many' in the relationshop with
	 * Customer and Book!
	 */
	@Override
	public void delete(Object reviewId) 
	{
		super.delete(Review.class, reviewId);
		
	}
	
	//OK
	@Override
	public Review create(Review review)
	{
		review.setReviewTime(new Date());
		return super.create(review);
	}
	
	@Override
	public Review update(Review review)
	{
		return super.update(review);
	}

	@Override
	public List<Review> listAll() 
	{
		
		List<Review> reviews = super.findWithNamedQuery("Review.listAll"); 
		return reviews;
	}

	@Override
	public long count() {
		return super.countWithNamedQuery("Review.countAll") ;
	}


	public long countByBook(Integer bookId) 
	{
		
		return super.countWithNamedQuery("Review.countByBook", "bookId", bookId);
	}


	public long countByCustomer(Integer customerId) 
	{
		return super.countWithNamedQuery("Review.countByCustomer", "customerId" , customerId); 
	}


	public Review findByCustomerAndBook(Integer customerId , Integer bookId)
	{
		Map<String , Object> parameters = new HashMap<>(); 
		parameters.put("customerId" , customerId); 
		parameters.put("bookId", bookId);
		
		List<Review> reviews= super.findWithNamedQuery("Review.findByCustomerAndBook", parameters);
		
		//if the reviews is not emtpty - return the first (and only) review
		if(!reviews.isEmpty())
			return reviews.get(0);
		else
			return null; 
				
	}
	
	public List<Review> listmostRecent()
	{
		return super.findWithNamedQuery("Review.listNew" , 0 , 3);
	}

	
}
