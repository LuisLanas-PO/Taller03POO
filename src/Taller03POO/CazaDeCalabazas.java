package Taller03POO;

public class CazaDeCalabazas implements Minijuego {
    @Override
    public String getNombre() {
        return "Caza de Calabazas";
    }

    @Override
    public String getDescripcion() {
        return "Muerde la mayor cantidad de calabazas del huerto en 60 segundos.";
    }

    @Override
    public String getHabilidadPremiada() {
        return Habilidad.MORDER;
    }

    @Override
    public double visitar(Zombi zombi) {
        return (double) zombi.getFuerzaDeMordida() * zombi.getVida() / 100
                + zombi.getResistencia() / 2.0;
    }

    @Override
    public double visitar(Vampiro vampiro) {
        return ((double) vampiro.getFuerza() + vampiro.getVelocidad()) / 3.0;
    }

    @Override
    public double visitar(Momia momia) {
        return (double) momia.getLongitudVendajes() * momia.getFuerza() / 200;
    }

    @Override
    public double visitar(HombreLobo hombreLobo) {
        return (double) hombreLobo.getFerocidad() * hombreLobo.getFaseLunar() / 4
                + hombreLobo.getVelocidad() / 4.0;
    }
}