package MotoGpAv;

import java.util.Objects;
import java.util.Random;

public class Piloto extends MiembroEquipo implements Competidor, Comparable<Piloto>{

    private int dorsal;
    private String escuderia;
    private int puntosCampeonato;
    private double desgasteNeumaticos = 20;


    @Override
    public int hashCode() {
        return Objects.hash(dorsal);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piloto piloto = (Piloto) o;
        return dorsal == piloto.dorsal;
    }

    @Override
    public String realizarTarea() {
        this.desgasteNeumaticos += 2;
        Random random = new Random();

        int suerte = random.nextInt(0,30);
        //int suerte = (int)(Math.random()*31);

        if ( (suerte+ (this.desgasteNeumaticos/4)) > 95){
            return "Accidente";

        } else if (this.desgasteNeumaticos > 80) {
            this.desgasteNeumaticos = 0;
            return "Entrada a boxes";

        }else if (suerte == 13){
            return "Colisión Leve";

        }else {
            return "Vuelta Limpia";
        }
    }

    @Override
    public int competir() {
        Random random = new Random();
        int aleatorio = random.nextInt(1,100);
        return aleatorio + this.edad;
    }

    @Override
    public int compareTo(Piloto o) {
        //return Integer.compare(o.puntosCampeonato, this.puntosCampeonato);
        return o.puntosCampeonato - this.puntosCampeonato;
    }

    public Piloto(String nombre, int edad, double sueldoBase, int dorsal, String escuderia, int puntosCampeonato) {
        super(nombre, edad, sueldoBase);
        this.dorsal = dorsal;
        this.escuderia = escuderia;
        this.puntosCampeonato = puntosCampeonato;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getEscuderia() {
        return escuderia;
    }

    public void setEscuderia(String escuderia) {
        this.escuderia = escuderia;
    }

    public int getPuntosCampeonato() {
        return puntosCampeonato;
    }

    public void setPuntosCampeonato(int puntosCampeonato) {
        this.puntosCampeonato = puntosCampeonato;
    }

    public double getDesgasteNeumaticos() {
        return desgasteNeumaticos;
    }

    public void setDesgasteNeumaticos(double desgasteNeumaticos) {
        this.desgasteNeumaticos = desgasteNeumaticos;
    }
}
