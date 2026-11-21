package Taller03POO;

public class HombreLobo extends Monstruo implements Mordedor, Furioso {
	private final int ferocidad;
	private final int faseLunar;

	public HombreLobo(int vida, int velocidad, int fuerza, int resistencia, int ferocidad, int faseLunar) {
		super(vida, velocidad, fuerza, resistencia);
		this.ferocidad = ferocidad;
		this.faseLunar = faseLunar;
	}

	public int getFerocidad() {
		return ferocidad;
	}

	public int getFaseLunar() {
		return faseLunar;
	}

	@Override
	public String getTipo() {
		return "HombreLobo";
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}

	@Override
	public String morder() {
		return "El hombre lobo da un mordisco con sus fuertes mandíbulas.";
	}

	@Override
	public String enfurecerse() {
		return "El hombre lobo aúlla y desata su furia.";
	}

	@Override
	protected String describirAtributosPropios() {
		return "Ferocidad " + ferocidad + " | Luna " + faseLunar;
	}
	
}