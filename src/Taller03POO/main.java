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
			mostrarMenu(nombre);

			switch (opcion) {

			case 1:
				jugarMinijuego();
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

	private static String verificarNombre(Scanner teclado) {
		
		while (true) {
			
			System.out.println("Nombre de usuario: ");
			
			if (!teclado.hasNextLine()) {
				
				return null;
				
			}
			
			String nombre = teclado.nextLine().trim();
			
			if (nombre.isEmpty()); {
				
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

	private static void jugarMinijuego() {
		// TODO Auto-generated method stub
		
	}

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
		System.out.println("> ");

	}

}
