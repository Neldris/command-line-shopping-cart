package github.aq.commandlineshoppingcart.model;

import static org.junit.Assert.*;

import org.junit.Test;

import github.aq.commandlineshoppingcart.model.Product;

public class ProductTest {

	@Test
	public void testProduct() {
		Product p = new Product(1, "my product", 1.25f);
		assertTrue(p.getPrice() == 1.25f);
		assertTrue(p.getId() == 1);
		assertTrue(p.getName().equals("my product"));
	}

}
