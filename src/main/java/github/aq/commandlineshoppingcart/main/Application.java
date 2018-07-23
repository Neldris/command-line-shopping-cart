package github.aq.commandlineshoppingcart.main;

import java.util.Scanner;

import github.aq.commandlineshoppingcart.shop.ShoppingStore;

/**
 * Entry to the Shopping Application.
 */
public class Application {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("************************************");
        System.out.println("* Welcome to the Shopping Store *");
        System.out.println("************************************");
        help();

        ShoppingStore shop = new ShoppingStore();
        shop.loadProducts();

        while (true) {
            String inputValue = scanner.next();

            if (inputValue.startsWith("add")) {
            	int productId = -1; 
            	if (scanner.hasNextInt()) {
            		productId = scanner.nextInt();
            	
	            	try {
	                	shop.addProductToBasket(productId);
	                } catch (IllegalArgumentException ex) {
	                	System.err.println(ex);
	                }
	            } else {
	            	System.out.println("Add command expects an int argument");
	            }

            } else if (inputValue.startsWith("remove")) {
            	int productId = -1;
            	if (scanner.hasNext()) {
            		productId = scanner.nextInt();
	            	try {
	            		shop.removeProductFromBasket(productId);
	            	} catch (IllegalArgumentException ex) {
	                	System.err.println(ex);
	                }
                } else {
                	System.out.println("Remove command expects an int argument");
	            }
            	
            } else if (inputValue.startsWith("list")) {
                shop.listProducts();

            } else if (inputValue.startsWith("total")) {
                shop.getTotal();
                
            } else if ("H".equalsIgnoreCase(inputValue)) {
            	help();
            	
            } else if ("Q".equalsIgnoreCase(inputValue)) {
                System.out.println("Thanks for shopping at Net-a-Porter!");
                scanner.close();
                System.exit(0);
                
            } 
        }
    }
    
    public static void help() {
        System.out.println("Enter \"Q\" to Quit");
        System.out.println("Enter \"H\" for help to display the list of commands");
        System.out.println("Enter \"add <ProductId>\" to add to basket");
        System.out.println("Enter \"remove <ProductId>\" to remove from basket");
        System.out.println("Enter \"list\" to show a list of products in the inventory");
        System.out.println("Enter \"total\" to show the total price of the basket and its content");
    }

}
