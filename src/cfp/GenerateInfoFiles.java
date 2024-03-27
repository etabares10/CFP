package cfp;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateInfoFiles {

		
		public static void crearArchivoVendedor(int randomSalesCount, String name, long id) {
	        try {
	            FileWriter writer = new FileWriter( name + "_" + id + ".txt");
	            Random random = new Random();
	            for (int i = 0; i < randomSalesCount; i++) {
	                int saleAmount = random.nextInt(1000); 
	                writer.write("Venta: " + (i + 1) + ", Cantidad: $" + saleAmount + "\n");
	            }
	            writer.close();
	            System.out.println("Vendedor creado exitosa mente");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		
		public static void CrearArchivoProductos(int productsCount) {
	        String[] products = {"Product A", "Product B", "Product C", "Product D", "Product E"};
	        try {
	            FileWriter writer = new FileWriter("Productos.txt");
	            Random random = new Random();
	            for (int i = 0; i < productsCount; i++) {
	                String productName = products[random.nextInt(products.length)];
	                double price = random.nextDouble() * 100; // Generate pseudo-random price
	                writer.write("Producto: " + productName + ", Precio: $" + price + "\n");
	            }
	            writer.close();
	            System.out.println("productos creados exitosa mente");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		
		 private static final String[] NAMES = {"John", "Emma", "Michael", "Sophia", "William", "Olivia"};

		    public static void ArchivoInfoVendedor(int salesmanCount) {
		        try {
		            FileWriter writer = new FileWriter( "Vendedores.txt");
		            Random random = new Random();
		            for (int i = 0; i < salesmanCount; i++) {
		                String name = NAMES[random.nextInt(NAMES.length)];
		                long id = Math.abs(random.nextLong()); // Generate pseudo-random ID
		                writer.write("Nombre: " + name + ", ID: " + id + "\n");
		            }
		            writer.close();
		            System.out.println("Se creo exitoa mente ");
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		    }


	    public static void main(String[] args) {
	    	crearArchivoVendedor(10, "John", 123456789);
	    	CrearArchivoProductos(5);
	    	ArchivoInfoVendedor(3);
	    }
	}


