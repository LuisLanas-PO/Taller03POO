package Taller03POO;

public class Vampiro extends Monstruo implements Mordedor, Volador {
	private final int velocidadDeVuelo;
	private final int resistenciaAlSol;

	public Vampiro(int vida, int velocidad, int fuerza, int resistencia, int velocidadDeVuelo, int resistenciaAlSol) {
		super(vida, velocidad, fuerza, resistencia);
		this.velocidadDeVuelo = velocidadDeVuelo;
		this.resistenciaAlSol = resistenciaAlSol;
	}

	public int getVelocidadDeVuelo() {
		return velocidadDeVuelo;
	}

	public int getResistenciaAlSol() {
		return resistenciaAlSol;
	}

	@Override
	public String getTipo() {
		return "Vampiro";
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}

	@Override
	public String morder() {
		return "El vampiro clava sus colmillos.";
	}

	@Override
	public String volar() {
		return "El vampiro despliega sus alas y alza vuelo.";
	}

	@Override
	protected String describirAtributosPropios() {
		return "Vuelo " + velocidadDeVuelo + " | Sol " + resistenciaAlSol;
	}
	
}