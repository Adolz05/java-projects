package gremioAventuras;

public abstract class MiembroGremio {
    protected String nombre;
    protected int nivel;
    protected double salarioOro;


    public abstract String realizarAccion();

    public MiembroGremio(String nombre, int nivel, double salarioOro) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.salarioOro = salarioOro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public double getSalarioOro() {
        return salarioOro;
    }

    public void setSalarioOro(double salarioOro) {
        this.salarioOro = salarioOro;
    }
}
