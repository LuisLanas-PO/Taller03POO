package Taller03POO;

public class Jugador {
	private final String nombre;
	private final int puntajeMaximo;

	public Jugador(String nombre, int puntajeMaximo) {
		this.nombre = nombre;
		this.puntajeMaximo = puntajeMaximo;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntajeMaximo() {
		return puntajeMaximo;
	}
}