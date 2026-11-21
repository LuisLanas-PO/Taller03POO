package Taller03POO;

public class Momia extends Monstruo implements Regenerable, Furioso {
    private final int longitudVendajes;

    public Momia(int vida, int velocidad, int fuerza, int resistencia, int longitudVendajes) {
        super(vida, velocidad, fuerza, resistencia);
        this.longitudVendajes = longitudVendajes;
    }

    public int getLongitudVendajes() {
        return longitudVendajes;
    }

    @Override
    public String getTipo() {
        return "Momia";
    }

    @Override
    public double aceptar(VisitorMinijuego visitor) {
        return visitor.visitar(this);
    }

    @Override
    public String regenerarse() {
        return "La momia se envuelve en sus vendajes y recupera fuerzas.";
    }

    @Override
    public String enfurecerse() {
        return "La momia aprieta los puños y ruge.";
    }

    @Override
    protected String describirAtributosPropios() {
        return "Vendajes " + longitudVendajes;
    }
    
}