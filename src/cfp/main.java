package cfp;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;


public class main {
	
	public static ArrayList<DatosArchivo> leerVentas() {
		
		String rutaProyecto = System.getProperty("user.dir");
        ArrayList<DatosArchivo> datosArchivos = new ArrayList<>();
        
        File carpetaRaiz = new File(rutaProyecto);
        File[] archivos = carpetaRaiz.listFiles();

        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().startsWith("cc;")) {
                try {
                    FileInputStream fis = new FileInputStream(archivo);
                    Scanner scanner = new Scanner(fis);
                    
                    StringBuilder contenido = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        contenido.append(scanner.nextLine()).append("\n");
                    }
                    
                    // Crear un objeto DatosArchivo y agregarlo a la lista
                    DatosArchivo datosArchivo = new DatosArchivo(archivo.getName(), contenido.toString());
                    datosArchivos.add(datosArchivo);
                    
                    scanner.close();
                    fis.close();
                    
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return datosArchivos;
    }
	
	
	public static ArrayList<DatosArchivo> leerProductos() {
		  String rutaProyecto = System.getProperty("user.dir");
	        ArrayList<DatosArchivo> datosArchivos = new ArrayList<>();
	        
	        File carpetaRaiz = new File(rutaProyecto);
	        File[] archivos = carpetaRaiz.listFiles();

	        for (File archivo : archivos) {
	            if (archivo.isFile() && archivo.getName().startsWith("Productos")) {
	                try {
	                    FileInputStream fis = new FileInputStream(archivo);
	                    Scanner scanner = new Scanner(fis);
	                    
	                    StringBuilder contenido = new StringBuilder();
	                    while (scanner.hasNextLine()) {
	                        contenido.append(scanner.nextLine()).append("\n");
	                    }
	                    
	                    // Crear un objeto DatosArchivo y agregarlo a la lista
	                    DatosArchivo datosArchivo = new DatosArchivo(archivo.getName(), contenido.toString());
	                    datosArchivos.add(datosArchivo);
	                    
	                    scanner.close();
	                    fis.close();
	                    
	                } catch (FileNotFoundException e) {
	                    e.printStackTrace();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	        return datosArchivos;
	}
	
	
	public static ArrayList<DatosArchivo> leerVendedores() {
        String rutaProyecto = System.getProperty("user.dir");
        ArrayList<DatosArchivo> datosArchivos = new ArrayList<>();
        
        File carpetaRaiz = new File(rutaProyecto);
        File[] archivos = carpetaRaiz.listFiles();

        for (File archivo : archivos) {
            if (archivo.isFile() && archivo.getName().startsWith("Vendedores")) {
                try {
                    FileInputStream fis = new FileInputStream(archivo);
                    Scanner scanner = new Scanner(fis);
                    
                    StringBuilder contenido = new StringBuilder();
                    while (scanner.hasNextLine()) {
                        contenido.append(scanner.nextLine()).append("\n");
                    }
                    
                    // Crear un objeto DatosArchivo y agregarlo a la lista
                    DatosArchivo datosArchivo = new DatosArchivo(archivo.getName(), contenido.toString());
                    datosArchivos.add(datosArchivo);
                    
                    scanner.close();
                    fis.close();
                    
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return datosArchivos;
    }

	    
	
	
	// en esta funcion vamos a hacer toda la logica para crear los archivos con los informes
	public void generarinforme() {
	
		 ArrayList<DatosArchivo> ventas = leerVentas();
	        ArrayList<DatosArchivo> vendedores = leerVendedores();
	        ArrayList<DatosArchivo> productos = leerProductos();

	        // Crear mapa para almacenar las sumas de ventas
	        HashMap<String, Integer> mapaSumas = new HashMap<>();
	        
	        // Iterar sobre los datos de ventas y sumar valores
	        for (DatosArchivo dato : ventas) {
	            String nombreArchivo = dato.getNombreArchivo().replace("cc;", "").replace(".txt", "");
	            String contenido = dato.getContenidoArchivo();
	            String[] partes = contenido.split("\n");

	            for (String parte : partes) {
	                String[] valores = parte.split(";");
	                String clave = nombreArchivo + ";" + valores[0];
	                int valor = Integer.parseInt(valores[1]);

	                mapaSumas.put(clave, mapaSumas.getOrDefault(clave, 0) + valor);
	            }
	        }

	        // Crear mapa para almacenar los datos de vendedores
	        HashMap<String, String> mapaVendedores = new HashMap<>();
	        for (DatosArchivo dato : vendedores) {
	            String contenido = dato.getContenidoArchivo();
	            String[] lineas = contenido.split("\n");
	            for (String linea : lineas) {
	                String[] campos = linea.split(";");
	                mapaVendedores.put(campos[0], campos[1] + ";" + campos[2]);
	            }
	        }

	        // Crear mapa para almacenar los datos de productos
	        HashMap<String, String> mapaProductos = new HashMap<>();
	        for (DatosArchivo dato : productos) {
	            String contenido = dato.getContenidoArchivo();
	            String[] lineas = contenido.split("\n");
	            for (String linea : lineas) {
	                String[] campos = linea.split(";");
	                mapaProductos.put(campos[0], campos[1] + ";" + campos[2]);
	            }
	        }

	        // Combinar datos de ventas con datos de vendedores y productos y agregar al ArrayList de resultados
	        ArrayList<String> resultados = new ArrayList<>();
	        for (Map.Entry<String, Integer> entry : mapaSumas.entrySet()) {
	            String claveVenta = entry.getKey();
	            int valorVenta = entry.getValue();
	            if (mapaVendedores.containsKey(claveVenta.split(";")[0]) && mapaProductos.containsKey(claveVenta.split(";")[1])) {
	                String[] infoVendedor = mapaVendedores.get(claveVenta.split(";")[0]).split(";");
	                String[] infoProducto = mapaProductos.get(claveVenta.split(";")[1]).split(";");
	                resultados.add("nombre=" + infoVendedor[1] + ";tipod=" + infoVendedor[0] + ";numeroD=" + claveVenta.split(";")[0] + ";idProd=" + claveVenta.split(";")[1] + ";cantidad=" + valorVenta + ";nomPro=" + infoProducto[0] + ";precProd=" + infoProducto[1]);
	            }
	        }

	        // Imprimir el resultado
	        for (String resultado : resultados) {
	            System.out.println(resultado);
	        }
	        String rutaArchivo = "informe_Ventas_PorVendedor.txt";
	        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo))) {

	            // Iterar sobre los resultados y escribir en el archivo
	            for (String resultado : resultados) {
	                // Separar los datos del resultado
	                String[] partes = resultado.split(";");
	                String nombreProducto = "";
	                String nombre = "";
	                String numeroD = "";
	                double cantidadVendida = 0;

	                // Obtener el nombre del vendedor, el número de documento y el nombre del producto
	                for (String parte : partes) {
	                    if (parte.startsWith("nombre=")) {
	                        nombre = parte.substring(7);
	                    } else if (parte.startsWith("numeroD=")) {
	                        numeroD = parte.substring(8);
	                    } else if (parte.startsWith("nomPro=")) {
	                        nombreProducto = parte.substring(7);
	                    } else if (parte.startsWith("cantidad=")) {
	                        // Calcular la cantidad vendida
	                        String[] cantidadParte = parte.split("=");
	                        int cantidad = Integer.parseInt(cantidadParte[1]);
	                        // Encontrar el precio del producto
	                        String[] precioParte = resultado.split("precProd=");
	                        double precio = Double.parseDouble(precioParte[1]);
	                        // Calcular la cantidad vendida multiplicando por el precio del producto
	                        cantidadVendida = cantidad * precio;    
	                    }
	                }

	                // Formatear la cantidad vendida con dos decimales y puntos de mil
	                String cantidadFormateada = String.format("%.2f", cantidadVendida);
	                cantidadFormateada = String.format("%,d", (long) Double.parseDouble(cantidadFormateada.replace(",", "")));

	                // Escribir en el archivo
	                escritor.write("nombre=" + nombre + ";numeroD=" + numeroD + ";producto=" + nombreProducto + ";cantidadV=" + cantidadFormateada + "\n");
	            }

	            System.out.println("Informe generado correctamente en " + rutaArchivo);

	        } catch (IOException e) {
	            System.err.println("Error al escribir en el archivo: " + e.getMessage());
	        }
	        
	        
	        String rutaArchivo2 = "informe_Productos_Vendidos.txt";

	        try (BufferedWriter escritor = new BufferedWriter(new FileWriter(rutaArchivo2))) {
	            Collections.sort(resultados, (a, b) -> {
	                int cantidadA = Integer.parseInt(a.split("cantidad=")[1].split(";")[0]);
	                int cantidadB = Integer.parseInt(b.split("cantidad=")[1].split(";")[0]);
	                return cantidadB - cantidadA;
	            });

	            for (String resultado : resultados) {
	                String[] partes = resultado.split(";");
	                String nombreProducto = "";
	                double precio = 0;
	                int cantidad = 0;

	                for (String parte : partes) {
	                    if (parte.startsWith("nomPro=")) {
	                        nombreProducto = parte.substring(7);
	                    } else if (parte.startsWith("cantidad=")) {
	                        cantidad = Integer.parseInt(parte.substring(9));
	                    } else if (parte.startsWith("precProd=")) {
	                        precio = Double.parseDouble(parte.substring(9));
	                    }
	                }

	                escritor.write(nombreProducto + ";" + precio + "\n");
	            }

	            System.out.println("Informe generado correctamente en " + rutaArchivo);

	        } catch (IOException e) {
	            System.err.println("Error al escribir en el archivo: " + e.getMessage());
	        }


	    }
	         
	    
    
//cierre de clase
}

 
//case para los datos de archivos

class  DatosArchivo {
    private String nombreArchivo;
    private String contenidoArchivo;
    
    public DatosArchivo(String nombreArchivo, String contenidoArchivo) {
        this.nombreArchivo = nombreArchivo;
        this.contenidoArchivo = contenidoArchivo;
    }
    
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    
    public String getContenidoArchivo() {
        return contenidoArchivo;
    }
} 
