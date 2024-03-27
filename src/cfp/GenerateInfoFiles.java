package cfp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

		
		public static void createSalesMenFile(int randomSalesCount, String name, long id) {
	        try {
	            FileWriter writer = new FileWriter( name + "_" + id + "_sales.txt");
	            Random random = new Random();
	            for (int i = 0; i < randomSalesCount; i++) {
	                int saleAmount = random.nextInt(1000); // Generate pseudo-random sales amount
	                writer.write("Sale: " + (i + 1) + ", Amount: $" + saleAmount + "\n");
	            }
	            writer.close();
	            System.out.println("Sales file for " + name + " with ID " + id + " created successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		
		public static void createProductsFile(int productsCount) {
	        String[] products = {"Product A", "Product B", "Product C", "Product D", "Product E"};
	        try {
	            FileWriter writer = new FileWriter("products.txt");
	            Random random = new Random();
	            for (int i = 0; i < productsCount; i++) {
	                String productName = products[random.nextInt(products.length)];
	                double price = random.nextDouble() * 100; // Generate pseudo-random price
	                writer.write("Product: " + productName + ", Price: $" + price + "\n");
	            }
	            writer.close();
	            System.out.println("Products file created successfully.");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		
		 private static final String[] NAMES = {"John", "Emma", "Michael", "Sophia", "William", "Olivia"};

		    public static void createSalesManInfoFile(int salesmanCount) {
		        try {
		            FileWriter writer = new FileWriter( "salesmen.txt");
		            Random random = new Random();
		            for (int i = 0; i < salesmanCount; i++) {
		                String name = NAMES[random.nextInt(NAMES.length)];
		                long id = Math.abs(random.nextLong()); // Generate pseudo-random ID
		                writer.write("Name: " + name + ", ID: " + id + "\n");
		            }
		            writer.close();
		            System.out.println("Salesmen info file created successfully.");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }


	    public static void main(String[] args) {
	        createSalesMenFile(10, "John", 123456789);
	        createProductsFile(5);
	        createSalesManInfoFile(3);
	    }
	}


