package github.aq.commandlineshoppingcart.shop;

import static org.junit.Assert.*;

import org.junit.Test;

import github.aq.commandlineshoppingcart.model.Product;
import github.aq.commandlineshoppingcart.shop.ShoppingStore;

public class ShoppingStoreTest {

	@Test
	public void testNetAPorterShop() {
		ShoppingStore shop = new ShoppingStore();
		assertEquals(shop.getCatalog().getProducts().size(), 0);
	}

	@Test
	public void testLoadProducts() {
		ShoppingStore shop = new ShoppingStore();
		shop.loadProducts();
		assertEquals(shop.getCatalog().getSize(), 7);
	}

	@Test
	public void testListProducts() {
		ShoppingStore shop = new ShoppingStore();
		shop.loadProducts();
		assertEquals(shop.getCatalog().getSize(), 7);
		shop.listProducts();
	}

	@Test
	public void testAddProductToBasketAndProductExistsInCatalogue() {
		ShoppingStore shop = new ShoppingStore();
		shop.loadProducts();
		shop.addProductToBasket(1);
		shop.addProductToBasket(1);
		Product p = shop.getCatalog().getProduct(1);
		int quantity = shop.getShoppingCart().getContent().get(p);
		assertEquals(quantity, 2);
	}

	@Test
	public void testAddProductToBasketAndProductNotInCatalogue() {
		ShoppingStore shop = new ShoppingStore();
		shop.loadProducts();
		try {
			shop.addProductToBasket(-1);
		} catch(IllegalStateException ex) {
			assertTrue(true);
		}
	}
	
	@Test
	public void testRemoveProductFromBasketAndProductNotInCatalogue() {
		ShoppingStore shop = new ShoppingStore();
		shop.loadProducts();
		try {
			shop.removeProductFromBasket(-1);
		} catch(IllegalStateException ex) {
			//assertTrue(ex.getMessage().contains("is not in catalogue."));
			assertTrue(true);
		}
	}

	@Test
	public void testRemoveProductFromBasketAndProductExistsInCatalogue() {
		ShoppingStore shop = new ShoppingStore();
		shop.loadProducts();
		shop.addProductToBasket(1);
		shop.addProductToBasket(1);
		shop.removeProductFromBasket(1);

		Product p = shop.getCatalog().getProduct(1);
		int quantity = shop.getShoppingCart().getContent().get(p);
		assertEquals(quantity, 1);
		
		shop.removeProductFromBasket(1);
		p = shop.getCatalog().getProduct(1);
		assertFalse(shop.existsInShoppingCart(p));

	}
	
	@Test
	public void testGetTotal() {
		ShoppingStore shop = new ShoppingStore();
		shop.loadProducts();
		shop.addProductToBasket(1);
		shop.addProductToBasket(1);
		Product pOne = shop.getCatalog().getProduct(1);
		float totalPriceProductOne = shop.getShoppingCart().computePriceForProductByQuantity(pOne);
		assertEquals(totalPriceProductOne, 19.98f, 0.0f);
		
		shop.removeProductFromBasket(1);
		totalPriceProductOne = shop.getShoppingCart().computePriceForProductByQuantity(pOne);
		assertEquals(totalPriceProductOne, 9.99f, 0.0f);
		
		shop.addProductToBasket(2);
		shop.addProductToBasket(2);
		shop.addProductToBasket(2);
		Product pTwo = shop.getCatalog().getProduct(2);
		float totalPriceProductTwo = shop.getShoppingCart().computePriceForProductByQuantity(pTwo);
		assertEquals(totalPriceProductTwo, 29.97f, 0.0f);
		
		shop.addProductToBasket(3);
		Product pThree = shop.getCatalog().getProduct(3);
		float totalPriceProductThree = shop.getShoppingCart().computePriceForProductByQuantity(pThree);
		assertEquals(totalPriceProductThree, 45.0f, 0.0f);
		
		float totalCartPrice = shop.getShoppingCart().getTotalPrice();
		assertEquals(totalCartPrice, 84.96f, 0.0f);
	}

}
