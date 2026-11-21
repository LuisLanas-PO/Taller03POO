package Taller03POO;

public class LanzamientoDeLapidas implements Minijuego {
    @Override
    public String getNombre() {
        return "Lanzamiento de Lápidas";
    }

    @Override
    public String getDescripcion() {
        return "Lanza una lápida lo más lejos posible.";
    }

    @Override
    public String getHabilidadPremiada() {
        return Habilidad.ENFURECERSE;
    }

    @Override
    public double visitar(Zombi zombi) {
        return (double) zombi.getFuerza() * zombi.getResistencia() / 100;
    }

    @Override
    public double visitar(Vampiro vampiro) {
        return vampiro.getFuerza() + vampiro.getVelocidadDeVuelo() / 4.0;
    }

    @Override
    public double visitar(Momia momia) {
        return momia.getFuerza() + momia.getLongitudVendajes() / 10.0;
    }

    @Override
    public double visitar(HombreLobo hombreLobo) {
        return hombreLobo.getFuerza()
                + (double) hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 8;
    }
}