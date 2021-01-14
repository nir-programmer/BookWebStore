package org.nir.bookstore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import org.nir.bookstore.service.ReviewService;

public class TestBookEntity {
	// OK
	@Test
	@DisplayName("when calling getAverageRating()")
	void testGetAverageRating() {
		Book book = new Book();
		Customer customer = new Customer();
		Set<Review> reviews = new HashSet<>();

		Review r1 = new Review(book, customer, 5, "Good", "Very Good", new Date());
		Review r2 = new Review(book, customer, 3, "ok", "Very ok", new Date());

		// I can do this since it is in memory - Hibernate ignore this!
		Review r3 = new Review();
		r3.setRating(5);

		reviews.add(r1);
		reviews.add(r2);
		reviews.add(r3);
		book.setReviews(reviews);

		assertTrue(reviews.size() == 3);

		reviews.forEach(r -> System.out.println(r.getHeadline()));

		float average = book.getAverageRating();

		assertEquals(4.3, average, 0.0);

		System.out.println(">>testGetAverageRating():average = " + average);
	}

	@Test
	@DisplayName("when calling getRatingString")
	void testGetRatingString() {

		Book book = new Book(); 
		float avg = 4.5f; 
		System.out.println("average is = " + avg) ; 

		String actual = book.getRatingString(avg); 
		String expect ="on,on,on,on,half"; 
	
		assertEquals(expect, actual);
		
		System.out.println("actual = " + actual); 

	}

}
