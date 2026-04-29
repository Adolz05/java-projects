package gremioAventuras;

import java.util.Random;

public class Clerigo  extends MiembroGremio implements Combatiente,Comparable<Clerigo>{
    private String diosVenerado;
    private int poderSanacion;

    @Override
    public int compareTo(Clerigo o) {
        int comparacionPoder = o.poderSanacion - this.poderSanacion;

        if (comparacionPoder == 0) {
            return this.diosVenerado.compareTo(o.diosVenerado);
        }

        return comparacionPoder;
    }

    public int lanzarCuracion(){
        Random random = new Random();
        int aleatorio= random.nextInt(10,50);

        return  (aleatorio + this.poderSanacion);
    }

    public Clerigo(String nombre, int nivel, double salarioOro, String diosVenerado, int poderSanacion) {
        super(nombre, nivel, salarioOro);
        this.diosVenerado = diosVenerado;
        this.poderSanacion = poderSanacion;
    }

    public String getDiosVenerado() {
        return diosVenerado;
    }

    public void setDiosVenerado(String diosVenerado) {
        this.diosVenerado = diosVenerado;
    }

    public int getPoderSanacion() {
        return poderSanacion;
    }

    public void setPoderSanacion(int poderSanacion) {
        this.poderSanacion = poderSanacion;
    }

    @Override
    public int calcularPoderAtaque() {
        return 0;
    }

    @Override
    public String realizarAccion() {
        return "";
    }
}
