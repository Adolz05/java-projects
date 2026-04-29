package gremioAventuras;

import java.util.Objects;
import java.util.Random;

public class Guerrero extends MiembroGremio implements Combatiente,Comparable<Guerrero>{
     private String idPlaca;
     private int rangoGremio;
     private double fatigaAcumulada = 10;

    @Override
    public String toString() {
        return "Guerrero{" +
                "idPlaca='" + idPlaca + '\'' +
                ", rangoGremio=" + rangoGremio +
                ", fatigaAcumulada=" + fatigaAcumulada +
                '}';
    }

    @Override
    public int calcularPoderAtaque() {
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Guerrero guerrero = (Guerrero) object;
        return Objects.equals(idPlaca, guerrero.idPlaca);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idPlaca);
    }

    @Override
    public int compareTo(Guerrero o) {
        return o.rangoGremio - this.rangoGremio;
    }

    @Override
    public String realizarAccion() {
        this.fatigaAcumulada +=5;
        Random random = new Random();
        int suerte = random.nextInt(0,20);

        if ((suerte + (fatigaAcumulada/2))> 80){
            return "Desmayo";
        } else if (fatigaAcumulada > 60) {
            this.fatigaAcumulada = 0;
            return "Retirada al campamento";
        }else {
            return "Monstruo Derrotado";
        }
    }

    public String getIdPlaca() {
        return idPlaca;
    }

    public void setIdPlaca(String idPlaca) {
        this.idPlaca = idPlaca;
    }

    public int getRangoGremio() {
        return rangoGremio;
    }

    public void setRangoGremio(int rangoGremio) {
        this.rangoGremio = rangoGremio;
    }

    public double getFatigaAcumulada() {
        return fatigaAcumulada;
    }

    public void setFatigaAcumulada(double fatigaAcumulada) {
        this.fatigaAcumulada = fatigaAcumulada;
    }

    public Guerrero(String nombre, int nivel, double salarioOro, String idPlaca, int rangoGremio) {
        super(nombre, nivel, salarioOro);
        this.idPlaca = idPlaca;
        this.rangoGremio = rangoGremio;

    }
}
