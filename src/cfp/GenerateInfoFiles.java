package cfp;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GenerateInfoFiles {

		
	
	 public static void crearArchivoVendedor(int randomSalesCount, String name, long id) {
	        String[] idProductos = {"1112", "2221", "3331", "4441", "5551"};
	        try {
	            FileWriter writer = new FileWriter(name + ";" + id + ".txt");
	            Random random = new Random();
	            for (int i = 0; i < randomSalesCount; i++) {
	                int saleAmount = random.nextInt(1000);
	                String idProducto = idProductos[random.nextInt(idProductos.length)];
	                writer.write(idProducto + ";" + saleAmount + "\n");
	            }
	            writer.close();
	            System.out.println("Vendedor creado exitosamente");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		
		
		public static void CrearArchivoProductos(int productsCount) {
	        String[] idProductos = {"1112", "2221", "3331", "4441", "5551"};
	        String[] nombreProductos = {"Memoria ram", "SSD", "HMI", "USB", "Portatil"};
	        Random random = new Random();
	        try (FileWriter writer = new FileWriter("Productos.txt")) {
	            for (int i = 0; i < productsCount; i++) {
	                int index = random.nextInt(idProductos.length);
	                String idProducto = idProductos[index];
	                String nombreProducto = nombreProductos[index];
	                double precio = random.nextDouble() * 100;
	                writer.write(idProducto + ";" + nombreProducto + ";" + precio + "\n");
	                // Eliminar el producto ya seleccionado para que no se repita
	                String[] tempIdProductos = new String[idProductos.length - 1];
	                String[] tempNombreProductos = new String[nombreProductos.length - 1];
	                int count = 0;
	                for (int j = 0; j < idProductos.length; j++) {
	                    if (j != index) {
	                        tempIdProductos[count] = idProductos[j];
	                        tempNombreProductos[count] = nombreProductos[j];
	                        count++;
	                    }
	                }
	                idProductos = tempIdProductos;
	                nombreProductos = tempNombreProductos;
	            }
	            System.out.println("Productos creados exitosamente");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		

		private static final String[] NAMES = {"John Moncada", "Emma Coronel", "Michael Jason","Erick Tabares"};
	    private static final String[] IDS = {"1033342539", "8460611", "1033338402", "4376550"};
		
	    public static void ArchivoInfoVendedor(int salesmanCount) {
	        try {
	            FileWriter writer = new FileWriter("Vendedores.txt");
	            Random random = new Random();
	            List<String> idList = new ArrayList<>(List.of(IDS));
	            List<String> nameList = new ArrayList<>(List.of(NAMES));
	            for (int i = 0; i < salesmanCount; i++) {
	                if (nameList.isEmpty() || idList.isEmpty()) {
	                    System.out.println("No hay suficientes nombres o identificaciones para continuar.");
	                    break;
	                }
	                int nameIndex = random.nextInt(nameList.size());
	                String name = nameList.get(nameIndex);
	                nameList.remove(nameIndex);
	                int idIndex = random.nextInt(idList.size());
	                String id = idList.get(idIndex);
	                idList.remove(idIndex);
	                writer.write(id+";CC" + ";" + name + "\n");
	            }
	            writer.close();
	            System.out.println("Se crearon exitosamente los vendedores");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
		 
		 public static void menu() {
			 Scanner scanner = new Scanner(System.in);
			 int opcion;
			 
			 do {
				System.out.println("-----------Menu---------");
				System.out.println("1: Generar archivos planos");
				System.out.println("2: Generar informes (Leer archivos)");
				System.out.println("3: Salir");
				System.out.println("-------Selecciones una obcion--------");
				opcion = scanner.nextInt();
				
				switch(opcion){
				case 1:
					crearArchivoVendedor(10, "cc", 1033342539);
			    	CrearArchivoProductos(5);
			    	ArchivoInfoVendedor(4);
			    	break;
				case 2:
					main immain =  new main();
			    	immain.generarinforme();
					break;
					
				case 3:
					System.out.println("Saliendo del programa");
					break;
				
				}
				 
			 }while(opcion !=3);
			 
			 scanner.close();
			  
		 }


	    public static void main(String[] args) {
	    	menu();
	    	
	    }
	}


