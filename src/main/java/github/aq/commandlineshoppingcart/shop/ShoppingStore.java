package github.aq.commandlineshoppingcart.shop;

import java.io.FileNotFoundException;

import github.aq.commandlineshoppingcart.model.Product;
import github.aq.commandlineshoppingcart.service.ProductCatalog;
import github.aq.commandlineshoppingcart.service.ShoppingCart;

public class ShoppingStore {

	private ProductCatalog catalog;
	private ShoppingCart shoppingCart;
	
	public ShoppingStore() {
		shoppingCart = new ShoppingCart();
		catalog = new ProductCatalog();
	}
	
	public ProductCatalog getCatalog() {
		return catalog;
	}
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	
	/**
	 * Checks the existence of Product p in the shopping cart
	 * @param p	 the product
	 * @return True if Product p is in the cart, False otherwise.
	 */
	public boolean existsInShoppingCart(Product p) {
		return shoppingCart.getContent().containsKey(p);
	}
	
	/**
	 * loads the product catalog from a local file
	 * @throws FileNotFoundException in case the file is missing
	 */
    public void loadProducts() {
    	try {
			catalog.loadProducts();
		} catch (FileNotFoundException ex) {
			System.err.println(ex.getMessage());
			System.exit(0);
		}
    }

    /**
     * List the products of the catalog
     */
    public void listProducts() {
    	System.out.println("List products");
        Long[] productIdArray = catalog.getProducts().keySet().toArray(new Long[catalog.getProducts().size()]);
        for (Long productId: productIdArray) {
        	System.out.println(catalog.getProducts().get(productId));
        }
    }

    /**
     * Add a product to the Basket
     * @param productId The id of the product
     * @throws IllegalArgumentException in case the product id is not in the product catalog
     */
    public void addProductToBasket(int productId) throws IllegalStateException {
        if (catalog.existsInProductCatalogue(productId)) {
        	Product p = catalog.getProduct(productId);
        	if (!shoppingCart.getContent().containsKey(p)) {
        		shoppingCart.addProduct(p, 1);
        	} else {
        		int quantity = shoppingCart.getContent().get(p);
        		shoppingCart.addProduct(p, quantity + 1);
        	}
        } else { 
        	throw new IllegalArgumentException("Product with id: " + productId + " is not in catalog. It cannot be added to the basket. Use List command to list the valid products.");
        }
        Product p = catalog.getProduct(productId);
        System.out.println("The quantity of product with id: " + productId + " is: " + shoppingCart.getContent().get(p));
    }

    /**
     * Remove a product from the Basket
     * @param productId The id of the product
     * @throws IllegalArgumentException in case the product id is not in the product catalog
     */
    public void removeProductFromBasket(int productId) throws IllegalStateException {
        if (catalog.existsInProductCatalogue(productId)) {
        	Product p = catalog.getProduct(productId);
        	if (shoppingCart.getContent().containsKey(p)) {
        		int quantity = shoppingCart.getContent().get(p);
        		if (quantity > 1) {
        			shoppingCart.getContent().put(p, quantity - 1);
        		} else { // quantity == 1 
        			shoppingCart.getContent().remove(p);
        		}
        	} else {
        		throw new IllegalArgumentException("Product with id: " + productId + " is not in the shopping cart. It cannot be removed.");
        	}
        } else {
            throw new IllegalArgumentException("Product with id: " + productId + " is not in catalog. It cannot be removed from the basket. Use List command to list the valid products.");
        }
        Product p = catalog.getProduct(productId);
        if (shoppingCart.getContent().get(p) != null) {
            System.out.println("The quantity of product with id: " + productId + " is: " + shoppingCart.getContent().get(p));
        } else {
            System.out.println("The quantity of product with id: " + productId + " has reached 0. The product was removed from the shopping cart.");
        }
    }

    /**
     * Print the total value of the products in the basket
     * Exercise 2c - feature to show the total value of Products in the basket
     */
    public void getTotal() {
        System.out.println("Shopping cart total price: " + shoppingCart.getTotalPrice() + " GBP");
        System.out.println("Shopping cart content list: \n" + shoppingCart.toString());
    }

}
