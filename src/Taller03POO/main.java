package Taller03POO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class main {

	private static final Sistema sistema = new Sistema();
	public static ArrayList<String> registros = new ArrayList<>();

	public static void main(String[] args) {

		cargarAchivos();

		if (sistema.getCantidadMonstruos() == 0) {

			return;

		}

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

				System.out.println(
						"¡Bienvenido de vuelta, " + nombre + "! Tu récord actual es de " + record + " puntos.");
				System.out.println("");

			}

			opcion = -1;

			sistema.iniciarSesion();

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
					jugarMinijuego(teclado);
					System.out.println("");
					break;
				case 2:
					catalogoMonstruos();
					System.out.println("");
					break;
				case 3:
					historialSesion();
					System.out.println("");
					break;
				case 4:
					rankingJugadores();
					System.out.println("");
					break;
				case 5:
					terminarSesion();
					System.out.println("");
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

				System.out.println("El nombre no puede estar vacío.");

			}

			if (nombre.contains(";")) {

				System.out.println("El nombre no puede llevar ';'.");

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

		ArrayList<Monstruo> catalogo = sistema.getMonstruos();

		System.out.println("------ CATÁLOGO DE MONSTRUOS ------");

		for (int i = 0; i < catalogo.size(); i++) {

			Monstruo monstruo = catalogo.get(i);
			ArrayList<String> habilidades = sistema.getHabilidades(monstruo);
			String textoHabilidades = "";

			for (int j = 0; j < habilidades.size(); j++) {

				if (j > 0) {

					textoHabilidades += ", ";

				}

				textoHabilidades += habilidades.get(j);
				
			}

			String estado = "Disponible";

			if (sistema.monstruoYaUsado(monstruo)) {
				
				estado = "Usado";
				
			}

			System.out.println((i + 1) + ") " + monstruo + " | Habilidades: " + textoHabilidades + " | [" + estado + "]");
			
		}

	}

	private static Monstruo elegirMonstruo(Scanner teclado, ArrayList<Monstruo> disponibles) {

		System.out.println("\nElige el monstruo en el que te transformarás:");

		for (int i = 0; i < disponibles.size(); i++) {

			System.out.println((i + 1) + ") " + disponibles.get(i));

		}

		System.out.println("0) Volver");

		while (true) {

			System.out.print("> ");

			if (!teclado.hasNextLine()) {

				return null;

			}

			try {

				int opcion = Integer.parseInt(teclado.nextLine().trim());

				if (opcion == 0) {

					return null;

				}

				if (opcion >= 1 && opcion <= disponibles.size()) {

					return disponibles.get(opcion - 1);

				}

			} catch (NumberFormatException e) {

			}

			System.out.println("[!] Opción fuera de rango. Intente nuevamente.");

		}

	}

	private static void jugarMinijuego(Scanner teclado) {

		Minijuego[] minijuegos = sistema.getMinijuegos();

		while (true) {

			mostrarMenuMinijuegos(minijuegos);

			if (!teclado.hasNextLine()) {

				return;

			}

			int opcion;

			try {

				opcion = Integer.parseInt(teclado.nextLine().trim());

			} catch (NumberFormatException e) {

				System.out.println("[!] Ingresa un número del 0 al " + minijuegos.length + ".");

				continue;

			}

			if (opcion == 0) {

				return;

			}

			if (opcion < 1 || opcion > minijuegos.length) {

				System.out.println("[!] Ingresa un número del 0 al " + minijuegos.length + ".");
				continue;

			}

			Minijuego minijuego = minijuegos[opcion - 1];

			if (sistema.getResultado(minijuego) != null) {

				System.out.println("[!] Ya jugaste " + minijuego.getNombre() + " en esta sesión.");
				System.out.println("");
				continue;

			}

			ArrayList<Monstruo> disponibles = sistema.getMonstruosDisponibles();

			if (disponibles.isEmpty()) {

				System.out.println("[!] No quedan monstruos disponibles en esta sesión.");
				System.out.println("");
				return;

			}

			String descripcion = minijuego.getDescripcion();
			System.out.println(minijuego.getNombre() + ": " + descripcion.substring(0, 1).toLowerCase()
					+ descripcion.substring(1));

			if (prepararPartida(teclado, minijuego, disponibles)) {

				return;

			}

		}

	}

	private static void mostrarMenuMinijuegos(Minijuego[] minijuegos) {

		System.out.println("------ MINIJUEGOS ------");

		for (int i = 0; i < minijuegos.length; i++) {

			Minijuego minijuego = minijuegos[i];
			ResultadoMinijuego resultado = sistema.getResultado(minijuego);
			String estado = "Pendiente";

			if (resultado != null) {

				estado = "Jugado, " + resultado.getPuntaje() + " puntos";

			}

			System.out.println((i + 1) + ") " + minijuego.getNombre() + " [" + estado + "]");

		}

		System.out.println("0) Volver");
		System.out.print("> ");

	}

	private static boolean prepararPartida(Scanner teclado, Minijuego minijuego, ArrayList<Monstruo> disponibles) {

		while (true) {

			Monstruo monstruo = elegirMonstruo(teclado, disponibles);

			if (monstruo == null) {

				return false;

			}

			System.out.println("\nTe comes un dulce de dudosa procedencia...");
			double potencial = sistema.calcularPotencial(minijuego, monstruo);
			String potencialFormateado = String.format("%.2f", potencial).replace(',', '.');
			System.out.println("Potencial para " + minijuego.getNombre() + ": " + potencialFormateado);

			ArrayList<String> habilidades = sistema.getHabilidades(monstruo);
			int opcion = elegirHabilidad(teclado, habilidades);

			if (opcion == -1) {

				return false;

			}

			if (opcion == habilidades.size() + 1) {

				continue;

			}

			String habilidad = null;

			if (opcion != 0) {

				habilidad = habilidades.get(opcion - 1);

			}

			ResultadoMinijuego resultado = sistema.registrarPartida(minijuego, monstruo, habilidad);

			if (habilidad != null) {

				System.out.println(resultado.getMensajeHabilidad());

				if (resultado.fueHabilidadEfectiva()) {

					System.out.println("¡La habilidad fue efectiva! Puntaje x1.5");

				} else {

					System.out.println("La habilidad no tuvo efecto. Puntaje x1.0");

				}

			}

			System.out.println("\n>> Puntaje obtenido: " + resultado.getPuntaje() + " puntos");
			return true;

		}

	}

	private static int elegirHabilidad(Scanner teclado, ArrayList<String> habilidades) {

		System.out.println("\n¿Quieres usar una habilidad?");

		for (int i = 0; i < habilidades.size(); i++) {

			System.out.println((i + 1) + ") " + habilidades.get(i));

		}

		int opcionVolver = habilidades.size() + 1;
		System.out.println("0) No usar habilidad");
		System.out.println(opcionVolver + ") Volver");

		while (true) {

			System.out.print("> ");

			if (!teclado.hasNextLine()) {

				return -1;

			}

			try {

				int opcion = Integer.parseInt(teclado.nextLine().trim());

				if (opcion >= 0 && opcion <= opcionVolver) {

					return opcion;

				}

			} catch (NumberFormatException e) {

			}

			System.out.println("[!] Opción fuera de rango. Intente nuevamente.");

		}

	}

	private static final String[] DESCRIPCIONES_MINIJUEGOS = {
			"Recorre el cementerio de punta a punta antes de que el sepulturero te vea.",
			"Muerde la mayor cantidad de calabazas del huerto en 60 segundos.",
			"Lanza una lápida lo más lejos posible.", "Aguanta en pie cuando salgan los primeros rayos de sol." };

	private static final String[] MINIJUEGOS = { "Carrera del Cementerio", "Caza de Calabazas",
			"Lanzamiento de Lápidas", "Sobrevivir al Amanecer" };

	private static void cargarAchivos() {

		System.out.println("Cargando archivos...");

		File archivosMonstruos = new File("Monstruos.txt");

		try (Scanner lector = new Scanner(archivosMonstruos)) {

			int numeroLinea = 0;

			while (lector.hasNextLine()) {

				numeroLinea++;
				String linea = lector.nextLine();

				if (numeroLinea == 1 && linea.startsWith("\uFEFF")) {

					linea = linea.substring(1);

				}

				try {

					sistema.agregarMonstruo(linea.split(";", -1));

				} catch (IllegalArgumentException e) {

					System.out.println("Monstruos.txt, línea " + numeroLinea + ": " + e.getMessage());

				}

			}

			if (sistema.getCantidadMonstruos() == 0) {

				System.out.println("Monstruos.txt no tiene monstruos válidos.");

			}

		} catch (FileNotFoundException e) {

			System.out.println("No se pudo abrir Monstruos.txt.");

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

		System.out.println("> " + sistema.getCantidadMonstruos() + " monstruos cargados desde Monstruos.txt");
		System.out.println("> " + registros.size() + " jugadores cargados desde Registros.txt");
		System.out.println("");
		System.out.println("=========================================\r\n" + "             SPOOKY SEASON\r\n"
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
