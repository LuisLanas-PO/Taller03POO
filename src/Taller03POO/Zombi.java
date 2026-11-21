package Taller03POO;

public class Zombi extends Monstruo implements Mordedor, Regenerable {
	private final int fuerzaDeMordida;

	public Zombi(int vida, int velocidad, int fuerza, int resistencia, int fuerzaDeMordida) {
		super(vida, velocidad, fuerza, resistencia);
		this.fuerzaDeMordida = fuerzaDeMordida;
	}

	public int getFuerzaDeMordida() {
		return fuerzaDeMordida;
	}

	@Override
	public String getTipo() {
		return "Zombi";
	}

	@Override
	public double aceptar(VisitorMinijuego visitor) {
		return visitor.visitar(this);
	}

	@Override
	public String morder() {
		return "El zombi hunde los dientes de un solo mordisco.";
	}

	@Override
	public String regenerarse() {
		return "El zombi recompone sus heridas.";
	}

	@Override
	protected String describirAtributosPropios() {
		return "Mordida " + fuerzaDeMordida;
	}
	
}