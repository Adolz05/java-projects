package gremioAventuras;

public class Main {

    public static void main(String[] args) {

            IncursionMazmorra incursion = new IncursionMazmorra();

            incursion.cargarDatosIniciales();

            incursion.ordenarVanguardiaBurbuja();

            incursion.simularCombate();
    }
}
