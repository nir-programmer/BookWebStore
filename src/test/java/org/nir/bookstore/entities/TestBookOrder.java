package org.nir.bookstore.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.criteria.Order;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestBookOrder
{
	/*
	 * private static BookOrder bookOrder1, bookOrder2;
	 * 
	 * @BeforeAll public static void init() {
	 * 
	 * // Define books , bookOrders , and OrderDeails.. Book book1; Book book2; Book
	 * book3; Book book4; Book book5; Book book6;
	 * 
	 * BookOrder bookOrder1; BookOrder bookOrder2; BookOrder bookOrder3; BookOrder
	 * bookOrder4;
	 * 
	 * OrderDetail orderDetail1; OrderDetail orderDetail2; OrderDetail orderDetail3;
	 * 
	 *//***********************************************
		 * Instantiation
		 *************************************************/
	/*
	 * // Books book1 = new Book(1); book2 = new Book(2); book3 = new Book(3); book4
	 * = new Book(4); book5 = new Book(5); book6 = new Book(6);
	 * 
	 * // BookOrders : set the same orderId to bookOrder1 and bookOrder2 bookOrder1
	 * = new BookOrder(1); bookOrder2 = new BookOrder(1); bookOrder3 = new
	 * BookOrder(3); bookOrder4 = new BookOrder(1);
	 * 
	 * // OrderDetails.. orderDetail1 = new OrderDetail(null, book1, bookOrder1);
	 * orderDetail2 = new OrderDetail(null, book2, bookOrder2); orderDetail3 = new
	 * OrderDetail(null, book1, bookOrder2);
	 * 
	 * Set<OrderDetail> orderDetails1 = new HashSet<OrderDetail>();
	 * orderDetails1.add(orderDetail1); orderDetails1.add(orderDetail3);
	 * 
	 * Set<OrderDetail> orderDetails2 = new HashSet<OrderDetail>();
	 * orderDetails2.add(orderDetail1); orderDetails2.add(orderDetail2);
	 * 
	 * Set<OrderDetail> orderDetails3 = new HashSet<OrderDetail>();
	 * orderDetails3.add(orderDetail1); orderDetails2.add(orderDetail2);
	 * orderDetails2.add(orderDetail3);
	 * 
	 * // Make bookO bookOrder1.setOrderDetails(orderDetails1);
	 * bookOrder2.setOrderDetails(orderDetails1);
	 * bookOrder3.setOrderDetails(orderDetails1);
	 * bookOrder4.setOrderDetails(orderDetails3);
	 * 
	 * Set<OrderDetail> orders1 = bookOrder1.getOrderDetails(); Set<OrderDetail>
	 * orders2 = bookOrder2.getOrderDetails(); Set<OrderDetail> orders3 =
	 * bookOrder3.getOrderDetails();
	 * 
	 * 
	 * System.out.println("---------------------------------------------\n orders1:"
	 * ); orders1.forEach(o -> System.out.println("Book Id:" +
	 * o.getBook().getBookId() + " , Order id: " + o.getBookOrder().getOrderId()) );
	 * 
	 * 
	 * System.out.println("---------------------------------------------\n orders2:"
	 * ); orders2.forEach(o -> System.out.println("Book Id:" +
	 * o.getBook().getBookId() + " , Order id: " + o.getBookOrder().getOrderId()) );
	 * 
	 * 
	 * 
	 * System.out.println("---------------------------------------------\n orders3:"
	 * ); orders3.forEach(o -> System.out.println("Book Id:" +
	 * o.getBook().getBookId() + " , Order id: " + o.getBookOrder().getOrderId()) );
	 * 
	 * // check if bookOrder1 equals bookOrder2
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("when comparing 2 instances of BookOrder with same id and same COPIES of the OrderDetails but different address"
	 * ) void testEqualsWithBasicHashCodeAndEquals() { // Define books , bookOrders
	 * , and OrderDeails.. Book book1; Book book2; Book book3; Book book4; Book
	 * book5; Book book6;
	 * 
	 * BookOrder bookOrder1; BookOrder bookOrder2;
	 * 
	 * BookOrder bookOrder3; BookOrder bookOrder4 ;
	 * 
	 * 
	 * OrderDetail orderDetail1; OrderDetail orderDetail2; OrderDetail orderDetail3;
	 * 
	 * boolean sameBookOrders;
	 *//***********************************************
		 * Instantiation
		 *************************************************/
	/*
	 * // Books book1 = new Book(1); book2 = new Book(2); book3 = new Book(3); book4
	 * = new Book(4); book5 = new Book(5); book6 = new Book(6);
	 * 
	 * // BookOrders: Create 2 different instances of BookOrder with same values
	 * bookOrder1 = new BookOrder(1); bookOrder2 = new BookOrder(1);
	 * 
	 * // OrderDetail: orderDetail1 = new OrderDetail(null, book1, bookOrder1);
	 * orderDetail2 = new OrderDetail(null, book2, bookOrder2); orderDetail3 = new
	 * OrderDetail(null, book1, bookOrder2);
	 * 
	 * // Creating 2 instances of Set<OrderDetail> with same values Set<OrderDetail>
	 * orderDetails1 = new HashSet<OrderDetail>(); orderDetails1.add(orderDetail1);
	 * orderDetails1.add(orderDetail2); orderDetails1.add(orderDetail3);
	 * 
	 * System.out.println(">>orderDetails1: "); orderDetails1.forEach(o ->
	 * System.out .println("order id = " + o.getBookOrder().getOrderId() +
	 * " , book id = " + o.getBook().getBookId())); System.out.println(
	 * "-------------------------------------------------------------------");
	 * 
	 * Set<OrderDetail> orderDetails2 = new HashSet<OrderDetail>();
	 * orderDetails2.add(orderDetail1); orderDetails2.add(orderDetail2);
	 * orderDetails1.add(orderDetail3);
	 * 
	 * System.out.println(">>orderDetails2: "); orderDetails1.forEach(o ->
	 * System.out .println("order id = " + o.getBookOrder().getOrderId() +
	 * " , book id = " + o.getBook().getBookId())); System.out.println(
	 * "-------------------------------------------------------------------");
	 * 
	 * 
	 * bookOrder1.setOrderDetails(orderDetails1);
	 * bookOrder2.setOrderDetails(orderDetails2);
	 * 
	 * sameBookOrders = bookOrder1.equals(bookOrder2);
	 * 
	 * 
	 * assertTrue(sameBookOrders);
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("when comparing 2 instances of BookOrder with same id and same COPIES of the OrderDetails but different address"
	 * ) void testEqualsWithEclipseHashCodeAndEquals() { // Define books ,
	 * bookOrders , and OrderDeails.. Book book1; Book book2; Book book3; Book
	 * book4; Book book5; Book book6;
	 * 
	 * BookOrder bookOrder1; BookOrder bookOrder2;
	 * 
	 * BookOrder bookOrder3; BookOrder bookOrder4 ;
	 * 
	 * 
	 * OrderDetail orderDetail1; OrderDetail orderDetail2; OrderDetail orderDetail3;
	 * 
	 * boolean sameBookOrders;
	 *//***********************************************
		 * Instantiation
		 *************************************************/
	/*
	 * // Books book1 = new Book(1); book2 = new Book(2); book3 = new Book(3); book4
	 * = new Book(4); book5 = new Book(5); book6 = new Book(6);
	 * 
	 * // BookOrders: Create 2 different instances of BookOrder with same values
	 * bookOrder1 = new BookOrder(1); bookOrder2 = new BookOrder(1);
	 * 
	 * // OrderDetail: orderDetail1 = new OrderDetail(null, book1, bookOrder1);
	 * orderDetail2 = new OrderDetail(null, book2, bookOrder2); orderDetail3 = new
	 * OrderDetail(null, book1, bookOrder2);
	 * 
	 * // Creating 2 instances of Set<OrderDetail> with same values Set<OrderDetail>
	 * orderDetails1 = new HashSet<OrderDetail>(); orderDetails1.add(orderDetail1);
	 * orderDetails1.add(orderDetail2); orderDetails1.add(orderDetail3);
	 * 
	 * System.out.println(">>orderDetails1: "); orderDetails1.forEach(o ->
	 * System.out .println("order id = " + o.getBookOrder().getOrderId() +
	 * " , book id = " + o.getBook().getBookId())); System.out.println(
	 * "-------------------------------------------------------------------");
	 * 
	 * Set<OrderDetail> orderDetails2 = new HashSet<OrderDetail>();
	 * orderDetails2.add(orderDetail1); orderDetails2.add(orderDetail2);
	 * orderDetails1.add(orderDetail3);
	 * 
	 * System.out.println(">>orderDetails2: "); orderDetails1.forEach(o ->
	 * System.out .println("order id = " + o.getBookOrder().getOrderId() +
	 * " , book id = " + o.getBook().getBookId())); System.out.println(
	 * "-------------------------------------------------------------------");
	 * 
	 * 
	 * bookOrder1.setOrderDetails(orderDetails1);
	 * bookOrder2.setOrderDetails(orderDetails2);
	 * 
	 * sameBookOrders = bookOrder1.equals(bookOrder2);
	 * 
	 * 
	 * assertTrue(sameBookOrders);
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("when comparing 2 instances of BookOrder with same id and same COPIES of the OrderDetails but different address"
	 * ) void testEqualsFalseWithEclipse() { // Define books , bookOrders , and
	 * OrderDeails.. Book book1; Book book2; Book book3; Book book4; Book book5;
	 * Book book6;
	 * 
	 * BookOrder bookOrder1; BookOrder bookOrder2;
	 * 
	 * BookOrder bookOrder3; BookOrder bookOrder4 ;
	 * 
	 * 
	 * OrderDetail orderDetail1; OrderDetail orderDetail2; OrderDetail orderDetail3;
	 * 
	 * boolean sameBookOrders;
	 *//***********************************************
		 * Instantiation
		 *************************************************/
	/*
	 * // Books book1 = new Book(1); book2 = new Book(2); book3
	 * = new Book(3); book4 = new Book(4); book5 = new Book(5);
	 * book6 = new Book(6);
	 * 
	 * // BookOrders: Create 2 different instances of BookOrder
	 * with same values bookOrder1 = new BookOrder(1);
	 * bookOrder2 = new BookOrder(1);
	 * 
	 * // OrderDetail: orderDetail1 = new OrderDetail(null,
	 * book1, bookOrder1); orderDetail2 = new OrderDetail(null,
	 * book2, bookOrder2); orderDetail3 = new OrderDetail(null,
	 * book1, bookOrder2);
	 * 
	 * // Creating 2 instances of Set<OrderDetail> with same
	 * values Set<OrderDetail> orderDetails1 = new
	 * HashSet<OrderDetail>(); orderDetails1.add(orderDetail1);
	 * orderDetails1.add(orderDetail2);
	 * orderDetails1.add(orderDetail3);
	 * 
	 * System.out.println(">>orderDetails1: ");
	 * orderDetails1.forEach(o -> System.out
	 * .println("order id = " + o.getBookOrder().getOrderId() +
	 * " , book id = " + o.getBook().getBookId()));
	 * System.out.println(
	 * "-------------------------------------------------------------------"
	 * );
	 * 
	 * Set<OrderDetail> orderDetails2 = new
	 * HashSet<OrderDetail>(); orderDetails2.add(orderDetail1);
	 * //orderDetails2.add(orderDetail2);
	 * orderDetails2.add(orderDetail3);
	 * 
	 * System.out.println(
	 * "-------------------------------------------------------------------"
	 * );
	 * 
	 * System.out.println(">>orderDetails2: ");
	 * orderDetails2.forEach(o -> System.out
	 * .println("order id = " + o.getBookOrder().getOrderId() +
	 * " , book id = " + o.getBook().getBookId()));
	 * System.out.println(
	 * "-------------------------------------------------------------------"
	 * );
	 * 
	 * 
	 * bookOrder1.setOrderDetails(orderDetails1);
	 * bookOrder2.setOrderDetails(orderDetails2);
	 * 
	 * 
	 * 
	 * sameBookOrders = bookOrder1.equals(bookOrder2);
	 * 
	 * 
	 * assertTrue(sameBookOrders);
	 * 
	 * System.out.println(">>sameBookOrders = " +
	 * sameBookOrders); }
	 */

}
