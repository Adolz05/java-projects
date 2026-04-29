package MotoGpAv;

import java.util.Objects;
import java.util.Random;

public class Ingeniero extends MiembroEquipo implements Competidor,Comparable<Ingeniero>{

    private String especialidad;
    private String escuderia;
    private int experiencia;


    @Override
    public int hashCode() {
        return Objects.hash(nombre, escuderia);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())return false;
        Ingeniero ingeniero = (Ingeniero) obj;
        return Objects.equals(nombre, ingeniero.nombre) && Objects.equals(escuderia,ingeniero.escuderia);
    }

    public int aportarMejora(){
        Random random = new Random();
        int numAleatorio = random.nextInt(-70,100);
        // (int)(Math.random()*171)-70;

        return numAleatorio + this.experiencia;
    }

    @Override
    public int competir() {
        return 0;
    }

    @Override
    public int compareTo(Ingeniero o) {
        int comparacion = o.experiencia - this.experiencia;
        if (comparacion == 0){
            return this.especialidad.compareTo(o.especialidad);
        }
        return comparacion;
    }

    public Ingeniero(String nombre, int edad, double sueldoBase, String especialidad, String escuderia, int experiencia) {
        super(nombre, edad, sueldoBase);
        this.especialidad = especialidad;
        this.escuderia = escuderia;
        this.experiencia = experiencia;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getEscuderia() {
        return escuderia;
    }

    public void setEscuderia(String escuderia) {
        this.escuderia = escuderia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String realizarTarea() {
        return "";
    }
}
