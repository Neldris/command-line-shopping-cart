package github.aq.commandlineshoppingcart.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import github.aq.commandlineshoppingcart.model.Product;

/**
 * The CartService class contains the content of the cart (the basket)
 */
public class ShoppingCart {

	private Map<Product, Integer> content;
	private float totalPrice; 
	
	public ShoppingCart() {
		content = new HashMap<Product, Integer>();
		totalPrice = 0;
	}
	
	public Map<Product, Integer> getContent() {
		return content;
	}

	/**
	 * computes the total price value for the cart.
	 * @return	the amount of all the products found in the cart.
	 */
	public float getTotalPrice() {
		totalPrice = 0;
        for (Map.Entry<Product, Integer> entry: content.entrySet()) {
        	Product p = entry.getKey();
        	float productTotalPrice = computePriceForProductByQuantity(p);
        	totalPrice += productTotalPrice;
        }
		return round(totalPrice, 2);
	}
	
	/**
	 * round a float
	 * @param d
	 * @param decimalPlace
	 * @return	the float number rounded by the decimalPlace number
	 */
	public static float round(float d, int decimalPlace) {
		BigDecimal bd = new BigDecimal(Float.toString(d));
		bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
		return bd.floatValue();
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		if (content.size() > 0) {
			for (Map.Entry<Product, Integer> entry: content.entrySet()) {
	        	Product p = entry.getKey();
	        	float productTotalPrice = computePriceForProductByQuantity(p);
	        	sb.append("{\"product\":").append(p.toString())
	        	.append(",\"quantity\": ").append(entry.getValue())
	        	.append(",\"productTotalPrice\":").append(productTotalPrice)
	        	.append("},\n");
	        	
	        }
			//sb.deleteCharAt(sb.length() -1);//remove last ,
		} else {
		    sb.append("[]");
		}
		return sb.toString();
	}
	
	/**
	 * compute the price of a product by its quantity
	 * @param p the Product p
	 * @return the total price of a product by its quantity
	 */
	public float computePriceForProductByQuantity(Product p) {
		return p.getPrice() * content.get(p);
	}
	
	/**
	 * Add the product to the basket with its quantity
	 * @param p	the product
	 * @param quantity	the quantity required of the product p
	 */
	public void addProduct(Product p, int quantity) {
		content.put(p, quantity);
	}
	
}
