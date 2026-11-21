package Taller03POO;

public interface Minijuego extends VisitorMinijuego {
    String getNombre();

    String getDescripcion();

    String getHabilidadPremiada();

    default double calcularPotencial(Monstruo monstruo) {
    	
        return monstruo.aceptar(this);
        
    }
    
}