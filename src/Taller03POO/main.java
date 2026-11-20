package Taller03POO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
	
	public static ArrayList<String> monstruos = new ArrayList<>();
	public static ArrayList<String> registros = new ArrayList<>();

	public static void main(String[] args) {

		cargarAchivos();
		
		Scanner teclado = new Scanner(System.in);
		int opcion = -1;

		while (opcion != 6) {
			
			String nombre = verificarNombre(teclado);
			
			while (nombre == "" || nombre.contains(";")) {
				
				nombre = verificarNombre(teclado);
				
			}
			
			int record = buscarRecord(nombre);
			
			if (record == -1) {
				
				System.out.println("¡Bienvenido, " + nombre + "! Es tu primera vez jugando.");
				System.out.println("");
				
			} else {
				
				System.out.println("¡Bienvenido de vuelta, " + nombre + "! Tu récord actual es de " + record + " puntos.");
				System.out.println("");
				
			}
			
			opcion = -1;
			
			int[] puntajesSesion = {-1, -1, -1, -1};

			while (opcion != 5 && opcion != 6) {
				
				mostrarMenu(nombre);
				
				if (!teclado.hasNextLine()) {
					
					opcion = 6;
					break;
					
				}
				
				try {
					
					opcion = Integer.parseInt(teclado.nextLine().trim());
					
				} catch (NumberFormatException e) {
					
					System.out.println("");
					System.out.println("Ingresa un numero del 1 al 6");
					System.out.println("");
					continue;
					
				}
				
				System.out.println("");
				
				switch (opcion) {

				case 1:
					jugarMinijuego(teclado, puntajesSesion);
					break;
				case 2:
					catalogoMonstruos();
					break;
				case 3:
					historialSesion();
					break;
				case 4:
					rankingJugadores();
					break;
				case 5:
					terminarSesion();
					break;
				case 6:
					break;
				}
				
			}
			
		}

	}

	private static int buscarRecord(String nombre) {
		
		int record = -1;
		
		for (String registro : registros) {
			
			String[] datos = registro.split(";");
			
			if (datos.length == 2 && datos[0].trim().equals(nombre)) {
				
				try {
					
					int puntaje = Integer.parseInt(datos[1].trim());
					
				} catch (NumberFormatException e) {
					
					
				}
				
			}
			
		}
		
		return record;
		
	}

	private static String verificarNombre(Scanner teclado) {
		
		while (true) {
			
			System.out.print("Nombre de usuario: ");
			
			if (!teclado.hasNextLine()) {
				
				return null;
				
			}
			
			String nombre = teclado.nextLine().trim();
			
			if (nombre.isEmpty()) {
				
				System.out.println("[!] El nombre no puede estar vacío.");
				
			}
			
			if (nombre.contains(";")) {
				
				System.out.println("[!] El nombre no puede llevar ';'.");
				
			}
			
			else {
				
				return nombre;
				
			}
			
		}
		
	}

	private static void terminarSesion() {
		// TODO Auto-generated method stub
		
	}

	private static void rankingJugadores() {
		// TODO Auto-generated method stub
		
	}

	private static void historialSesion() {
		// TODO Auto-generated method stub
		
	}

	private static void catalogoMonstruos() {
		// TODO Auto-generated method stub
		
	}

	private static void jugarMinijuego(Scanner teclado, int[] puntajesSesion) {
		
	    while (true) {
	    	
	        mostrarMenuMinijuegos(puntajesSesion);

	        if (!teclado.hasNextLine()) {
	        	
	            return;
	            
	        }

	        int opcion;
	        
	        try {
	        	
	            opcion = Integer.parseInt(teclado.nextLine().trim());
	            
	        } catch (NumberFormatException e) {
	        	
	            System.out.println("Ingresa un número del 0 al 4.");
	            continue;
	            
	        }

	        if (opcion == 0) {
	            return;
	        }

	        if (opcion < 1 || opcion > MINIJUEGOS.length) {
	        	
	            System.out.println("Ingresa un número del 0 al 4.");
	            continue;
	            
	        }

	        int indice = opcion - 1;

	        if (puntajesSesion[indice] != -1) {
	        	
	            System.out.println("[!] Ya jugaste " + MINIJUEGOS[indice] + " en esta sesión.");
	            continue;
	            
	        }

	        System.out.println("Elegiste: " + MINIJUEGOS[indice]);
	        
	        return;
	    }
	}

	private static void mostrarMenuMinijuegos(int[] puntajesSesion) {

		System.out.println("------ MINIJUEGOS ------");

	    for (int i = 0; i < MINIJUEGOS.length; i++) {
	    	
	        String estado = "Pendiente";

	        if (puntajesSesion[i] != -1) {
	        	
	            estado = "Jugado, " + puntajesSesion[i] + " puntos";
	            
	        }

	        System.out.println((i + 1) + ") " + MINIJUEGOS[i] + " [" + estado + "]");
	        
	    }

	    System.out.println("0) Volver");
	    System.out.print("> ");
	    
	}
	
	private static final String[] MINIJUEGOS = {
		    "Carrera del Cementerio",
		    "Caza de Calabazas",
		    "Lanzamiento de Lápidas",
		    "Sobrevivir al Amanecer"
		};

	private static void cargarAchivos() {
		
		System.out.println("Cargando archivos...");

		File archivosMonstruos = new File("Monstruos.txt");

		try {

			Scanner lector = new Scanner(archivosMonstruos);

			while (lector.hasNextLine()) {
				
				monstruos.add(lector.nextLine());

			}

			lector.close();

		} catch (FileNotFoundException e) {

			System.out.println("No se pudo abrir Monstruos.txt");

		}

		if (monstruos.isEmpty()) {

			System.out.println("Monstruos.txt esta vacio");

		}

		File archivosRegistros = new File("Registros.txt");

		try {

			Scanner lector = new Scanner(archivosRegistros);

			while (lector.hasNextLine()) {

				registros.add(lector.nextLine());

			}

			lector.close();

		} catch (FileNotFoundException e) {

			System.out.println("Registros.txt empezara vacia");

		}
		
		System.out.println("> " + monstruos.size() + " monstruos cargados desde Monstruos.txt");
		System.out.println("> " + registros.size() + " jugadores cargados desde Registros.txt");
		System.out.println("");
		System.out.println("=========================================\r\n"
				+ "             SPOOKY SEASON\r\n"
				+ "=========================================");

	}

	private static void mostrarMenu(String nombre) {
		
		System.out.println("------ MENÚ PRINCIPAL (" + nombre + ") ------");
		System.out.println("1) Jugar un minijuego");
		System.out.println("2) Ver catálogo de monstruos");
		System.out.println("3) Ver historial de la sesión");
		System.out.println("4) Ver ranking de jugadores");
		System.out.println("5) Terminar sesión");
		System.out.println("6) Salir");
		System.out.print("> ");

	}

}
