package github.aq.commandlineshoppingcart.service;

import static org.junit.Assert.*;

import org.junit.Test;

import github.aq.commandlineshoppingcart.model.Product;
import github.aq.commandlineshoppingcart.service.ShoppingCart;

public class CartServiceTest {

	@Test
	public void testGetCart() {
		ShoppingCart service = new ShoppingCart();
		assertNotNull(service.getContent());
	}

	@Test
	public void testGetTotalPrice() {
		ShoppingCart service = new ShoppingCart();
		assertNotNull(service.getContent());
		assertEquals(service.getTotalPrice(), 0.0f, 0.0f);
	}

	@Test
	public void testRound() {
		assertEquals(3.99f, ShoppingCart.round(3.991f, 2), 0.0f);
		assertEquals(4.0f, ShoppingCart.round(3.999f, 2), 0.0f);
	}

	@Test
	public void testComputePriceForProductByQuantity() {
		ShoppingCart service = new ShoppingCart();
		Product p = new Product(1, "Red pen", 1.99f);
		service.addProduct(p, 2);
		assertEquals(service.getContent().get(p), 2, 0.0f);
		float total = service.computePriceForProductByQuantity(p);
		assertEquals(total, 3.98f, 0.0f);
	}

	@Test
	public void testAddProduct() {
		ShoppingCart service = new ShoppingCart();
		Product p = new Product(1, "Red pen", 1.99f);
		service.addProduct(p, 2);
		assertEquals(service.getContent().get(p), 2, 0.0f);
		
		Product p2 = new Product(1, "Red pen", 1.99f);
		service.addProduct(p2, 3);
		assertEquals(service.getContent().get(p2), 3, 0);
	}

}
