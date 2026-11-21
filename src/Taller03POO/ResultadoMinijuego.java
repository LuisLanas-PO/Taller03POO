package Taller03POO;

public class ResultadoMinijuego {
	private final Minijuego minijuego;
	private final Monstruo monstruo;
	private final String habilidad;
	private final int puntaje;
	private final String mensajeHabilidad;
	private final boolean habilidadEfectiva;

	public ResultadoMinijuego(Minijuego minijuego, Monstruo monstruo, String habilidad, int puntaje, String mensajeHabilidad, boolean habilidadEfectiva) {
		this.minijuego = minijuego;
		this.monstruo = monstruo;
		this.habilidad = habilidad;
		this.puntaje = puntaje;
		this.mensajeHabilidad = mensajeHabilidad;
		this.habilidadEfectiva = habilidadEfectiva;
	}

	public Minijuego getMinijuego() {
		return minijuego;
	}

	public Monstruo getMonstruo() {
		return monstruo;
	}

	public String getHabilidad() {
		return habilidad;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public String getMensajeHabilidad() {
		return mensajeHabilidad;
	}

	public boolean fueHabilidadEfectiva() {
		return habilidadEfectiva;
	}
}
