package Taller03POO;

public class SobrevivirAlAmanecer implements Minijuego {
    @Override
    public String getNombre() {
        return "Sobrevivir al Amanecer";
    }

    @Override
    public String getDescripcion() {
        return "Aguanta en pie cuando salgan los primeros rayos de sol.";
    }

    @Override
    public String getHabilidadPremiada() {
        return Habilidad.REGENERARSE;
    }

    @Override
    public double visitar(Zombi zombi) {
        return (double) zombi.getResistencia() * zombi.getVida() / 100;
    }

    @Override
    public double visitar(Vampiro vampiro) {
        return (double) vampiro.getResistencia() * vampiro.getResistenciaAlSol() / 100;
    }

    @Override
    public double visitar(Momia momia) {
        return ((double) momia.getResistencia() + momia.getVida()) / 2.0
                * (1 + momia.getLongitudVendajes() / 300.0);
    }

    @Override
    public double visitar(HombreLobo hombreLobo) {
        return ((double) hombreLobo.getResistencia() + hombreLobo.getVida()) / 2.0
                * (5.0 - hombreLobo.getFaseLunar()) / 4;
    }
}