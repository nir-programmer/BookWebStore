package org.nir.bookstore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestReviewEntity 
{
	@Test
	@DisplayName("when calling getStars()")
	void testGetStars()
	{
		Review review = new Review();
		review.setRating(0);
		
		String actual = review.getStars();
		String expected = "off,off,off,off,off"; 
		
		assertEquals(expected, actual);
		
		System.out.println(">>testGetStars():stars = " + actual); 
	}

}
