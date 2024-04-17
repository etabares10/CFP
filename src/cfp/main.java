package cfp;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

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
