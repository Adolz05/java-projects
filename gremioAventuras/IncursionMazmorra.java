package gremioAventuras;


import java.util.*;

public class IncursionMazmorra {
    private String nombreMazmorra;
    Set<MiembroGremio> censoTotal = new HashSet<>();
    TreeSet<Clerigo> consejoSanador = new TreeSet<>(Clerigo::compareTo);
    List<Guerrero> vanguardia = new ArrayList<>();
    Deque<Guerrero> tiendaDescanso = new ArrayDeque<>();
    PriorityQueue<Guerrero> enfermeriaUrgente = new PriorityQueue<>(Comparator.comparingInt(Guerrero::getNivel).reversed());

    public void ordenarVanguardiaBurbuja(){
        int num = vanguardia.size();

        for (int i = 0; i < num -1 ; i++) {
            for (int j = 0; j < num- i - 1; j++){
                Guerrero actual = vanguardia.get(j);
                Guerrero siguiente = vanguardia.get(j+1);
                if(actual.getFatigaAcumulada() > siguiente.getFatigaAcumulada()){
                    vanguardia.set(j,siguiente);
                    vanguardia.set(j+1, actual);
                }

            }
        }

    }

    public void simularCombate() {
        for (int oleada = 1; oleada <= 30; oleada++) {

            System.out.println("\n*** OLEADA " + oleada + " ***");

            Iterator<Guerrero> iterator = vanguardia.iterator();
            String resultado;
            int monstruosDerrotados = 0;

            while (iterator.hasNext()){

                Guerrero guerrero = iterator.next();
                resultado = guerrero.realizarAccion();

                switch (resultado){
                    case "Desmayo":
                        iterator.remove();
                        enfermeriaUrgente.offer(guerrero);
                        System.out.println("- " + guerrero.getNombre() + " se ha desmayado.");
                        break;
                    case "Retirada al campamento":
                        iterator.remove();
                        tiendaDescanso.offerLast(guerrero);
                        System.out.println("- " + guerrero.getNombre() + " se ha retirado a descansar.");
                        break;
                    case "Monstruo Derrotado":
                        monstruosDerrotados++;
                        break;
                }
            }

            if (monstruosDerrotados > 0) {
                System.out.println("> La vanguardia ha derrotado a " + monstruosDerrotados + " monstruos.");
            }

            if (oleada == 15){
                faseBendicionMasiva();
            }

            gestionarCampamentoYEnfermeria();
        }

        System.out.println("\nINCURSIÓN FINALIZADA. SUPERVIVIENTES EN PIE: " + vanguardia.size());
    }

    public void faseBendicionMasiva(){
        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println(" Oleada 15. Fase de Bendición Masiva");
        System.out.println("-------------------------------------------");
        System.out.println();
        ListIterator<Guerrero> listIterator = vanguardia.listIterator();

        while (listIterator.hasNext()){
            Guerrero guerrero = listIterator.next();
            System.out.println("Aplicando escudo a: " + guerrero.nombre);
            System.out.println();
        }

        while (listIterator.hasPrevious()){
            Guerrero guerrero = listIterator.previous();
            guerrero.setFatigaAcumulada(guerrero.getFatigaAcumulada()/2);
            System.out.println("Sanación Pasiva a:" + guerrero.nombre);
            System.out.println();
        }
    }
    public void cargarDatosIniciales() {


        // CARGA DE 15 GUERREROS
        reclutar(new Guerrero("Arthur Pendragon", 60, 500.0, "G-001", 99));
        reclutar(new Guerrero("Lancelot", 58, 450.0, "G-002", 95));
        reclutar(new Guerrero("Gawain", 55, 400.0, "G-003", 90));
        reclutar(new Guerrero("Percival", 50, 350.0, "G-004", 85));
        reclutar(new Guerrero("Galahad", 65, 600.0, "G-005", 100));
        reclutar(new Guerrero("Tristan", 52, 380.0, "G-006", 88));
        reclutar(new Guerrero("Bors", 48, 300.0, "G-007", 80));
        reclutar(new Guerrero("Gareth", 45, 280.0, "G-008", 75));
        reclutar(new Guerrero("Bedivere", 49, 310.0, "G-009", 82));
        reclutar(new Guerrero("Gaheris", 42, 250.0, "G-010", 70));
        reclutar(new Guerrero("Lamorak", 54, 390.0, "G-011", 91));
        reclutar(new Guerrero("Kay", 40, 220.0, "G-012", 65));
        reclutar(new Guerrero("Mordred", 56, 420.0, "G-013", 93));
        reclutar(new Guerrero("Hector", 44, 260.0, "G-014", 72));
        reclutar(new Guerrero("Dagonet", 20, 100.0, "G-015", 30));

        // CARGA DE 5 CLÉRIGOS
        reclutar(new Clerigo("Elara", 60, 400.0, "Luz", 95));
        reclutar(new Clerigo("Thorin", 45, 250.0, "Forja", 70));
        reclutar(new Clerigo("Seraphina", 55, 350.0, "Vida", 85));
        reclutar(new Clerigo("Kael", 35, 180.0, "Naturaleza", 50));
        reclutar(new Clerigo("Morgana", 50, 300.0, "Sombras", 80));
    }

    public void reclutar(MiembroGremio miembro) {
        censoTotal.add(miembro);

        if (miembro instanceof Guerrero) {
            vanguardia.add((Guerrero) miembro);
        } else if (miembro instanceof Clerigo) {
            consejoSanador.add((Clerigo) miembro);
        }
    }

    public void gestionarCampamentoYEnfermeria(){
        Guerrero guerreroDescansado = tiendaDescanso.pollFirst();

        if (guerreroDescansado != null && !consejoSanador.isEmpty()){
            Clerigo mejorClerigo = consejoSanador.first();

            int puntosCurados = mejorClerigo.lanzarCuracion();

            System.out.println(mejorClerigo.nombre + " atiende a " + guerreroDescansado.getNombre() + " curandole " + puntosCurados + " puntos de vida");

            if (puntosCurados > 40){
                vanguardia.add(guerreroDescansado);
            }else {
                tiendaDescanso.offerFirst(guerreroDescansado);
            }
        }

        Guerrero guerreroPaciente = enfermeriaUrgente.poll();

        if (guerreroPaciente != null){
            System.out.println("Salvando la vida in extremis a: " + guerreroPaciente.getNombre() + ", Nivel " + guerreroPaciente.getNivel());
        }

    }

}
