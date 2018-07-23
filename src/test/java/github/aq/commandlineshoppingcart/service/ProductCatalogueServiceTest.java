package github.aq.commandlineshoppingcart.service;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

import github.aq.commandlineshoppingcart.model.Product;
import github.aq.commandlineshoppingcart.service.ProductCatalog;

public class ProductCatalogueServiceTest {


	@Test
	public void testGetProductCatalogue() {
		ProductCatalog service = new ProductCatalog();
		try {
			service.loadProducts();
		} catch (FileNotFoundException e) {
			assertTrue(false);
		}
		assertNotNull(service.getProducts());
	}

	@Test
	public void testExistsInProductCatalogue() {
		ProductCatalog service = new ProductCatalog();
		try {
			service.loadProducts();
		} catch (FileNotFoundException e) {
			assertTrue(false);
		}
		
		assertTrue(service.existsInProductCatalogue(1));
		assertFalse(service.existsInProductCatalogue(-1));
		assertFalse(service.existsInProductCatalogue(0));
		assertFalse(service.existsInProductCatalogue(8));
	}


	@Test
	public void testLoadProducts() {
		ProductCatalog service = new ProductCatalog();
		try {
			service.loadProducts();
		} catch (FileNotFoundException e) {
			assertTrue(false);
		}
		assertEquals(7, service.getSize());
		
	}

	@Test
	public void testGetProduct() {
		ProductCatalog service = new ProductCatalog();
		try {
			service.loadProducts();
		} catch (FileNotFoundException e) {
			assertTrue(false);
		}
		Product p = service.getProduct(1);
		assertNotNull(p);
		assertEquals(1, p.getId());
		assertEquals(9.99f, p.getPrice(), 0.0f);
		assertTrue(p.getName().length() > 0);
	}

}
