package Taller03POO;

public abstract class Monstruo {
    private final int vida;
    private final int velocidad;
    private final int fuerza;
    private final int resistencia;

    protected Monstruo(int vida, int velocidad, int fuerza, int resistencia) {
        this.vida = vida;
        this.velocidad = velocidad;
        this.fuerza = fuerza;
        this.resistencia = resistencia;
    }

    public int getVida() {
        return vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getResistencia() {
        return resistencia;
    }

    public abstract String getTipo();
    
    public abstract double aceptar(VisitorMinijuego visitor);

    protected abstract String describirAtributosPropios();

    @Override
    public String toString() {
        return getTipo() + " | Vida " + vida
                + " | Vel " + velocidad
                + " | Fue " + fuerza
                + " | Res " + resistencia
                + " | " + describirAtributosPropios();
    }
    
}