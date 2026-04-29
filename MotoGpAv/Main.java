package MotoGpAv;

public class Main {
    static void main(String[] args) {
        GranPremio granPremio = new GranPremio("Ricardo Tormo");

        System.out.println(" Registrando Participantes...");
        granPremio.registrarParticipantes();
        System.out.println();

        System.out.println("Organizando boxes y agrupando equipos...");
        granPremio.organizarEscuderia();
        System.out.println();

        System.out.println("Formando Parrilla...");
        granPremio.formarParrilla();

        System.out.println("\n==================================================");
        System.out.println("           SEMÁFORO VERDE - COMIENZA LA CARRERA");
        System.out.println("==================================================");
        granPremio.simularCarrera();


        System.out.println("\n==================================================");
        System.out.println("           BANDERA A CUADROS - FIN DE CARRERA");
        System.out.println("==================================================");
        granPremio.mostrarResultados();
    }

}
