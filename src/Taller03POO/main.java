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

		int opcion = -1;

		while (opcion != 6) {

			mostrarMenu();

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

	}

	private static void mostrarMenu() {
		// TODO Auto-generated method stub

	}

}
