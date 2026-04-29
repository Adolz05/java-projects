package MotoGpAv;

import java.util.*;

public class GranPremio {

    private String nombreCircuito;
    private Set<MiembroEquipo> participantesRegistrados = new HashSet<>();
    private Map<String, List<MiembroEquipo>> agrupacionEquipos = new HashMap<>();
    private PriorityQueue<Piloto> parrillaSalida = new PriorityQueue<>();
    private Deque<Piloto> pitLane = new ArrayDeque<>();
    private Map<String,Integer> rendimientoTotalAcumulado = new HashMap<>();
    private List<Piloto> abandonos = new ArrayList<>();
    private List<Piloto> pilotosEnPista = new ArrayList<>();


    public void registrarParticipantes(){

        this.participantesRegistrados = new HashSet<>();

        // --- PILOTOS ---
        participantesRegistrados.add(new Piloto("Jorge Martín", 26, 5000000.0, 89, "Pramac Racing", 508));
        participantesRegistrados.add(new Piloto("Francesco Bagnaia", 27, 6000000.0, 1, "Ducati Lenovo", 498));
        participantesRegistrados.add(new Piloto("Marc Márquez", 31, 10000000.0, 93, "Gresini Racing", 392));
        participantesRegistrados.add(new Piloto("Enea Bastianini", 26, 3000000.0, 23, "Ducati Lenovo", 386));
        participantesRegistrados.add(new Piloto("Brad Binder", 28, 2500000.0, 33, "KTM Factory", 217));
        participantesRegistrados.add(new Piloto("Pedro Acosta", 19, 1200000.0, 31, "GasGas Tech3", 215));
        participantesRegistrados.add(new Piloto("Maverick Viñales", 29, 4000000.0, 12, "Aprilia Racing", 190));
        participantesRegistrados.add(new Piloto("Aleix Espargaró", 34, 3500000.0, 41, "Aprilia Racing", 163));
        participantesRegistrados.add(new Piloto("Fabio Di Giannantonio", 25, 1200000.0, 49, "VR46 Racing", 165));
        participantesRegistrados.add(new Piloto("Franco Morbidelli", 29, 1500000.0, 21, "Pramac Racing", 173));
        participantesRegistrados.add(new Piloto("Marco Bezzecchi", 25, 2000000.0, 72, "VR46 Racing", 153));
        participantesRegistrados.add(new Piloto("Alex Márquez", 28, 1800000.0, 73, "Gresini Racing", 173));
        participantesRegistrados.add(new Piloto("Fabio Quartararo", 25, 8000000.0, 20, "Yamaha Factory", 113));
        participantesRegistrados.add(new Piloto("Jack Miller", 29, 2000000.0, 43, "KTM Factory", 87));
        participantesRegistrados.add(new Piloto("Miguel Oliveira", 29, 1500000.0, 88, "Trackhouse Racing", 75));

        // --- 5 INGENIEROS ---
        participantesRegistrados.add(new Ingeniero("Gigi Dall'Igna", 58, 2500000.0, "Aerodinámica", "Ducati Lenovo", 35));
        participantesRegistrados.add(new Ingeniero("Santi Hernández", 48, 950000.0, "Telemetría", "Gresini Racing", 20));
        participantesRegistrados.add(new Ingeniero("Christian Gabarrini", 51, 980000.0, "Electrónica", "Ducati Lenovo", 25));
        participantesRegistrados.add(new Ingeniero("Alberto Puig", 57, 1100000.0, "Estrategia", "Honda", 30));
        participantesRegistrados.add(new Ingeniero("Massimo Rivola", 52, 1050000.0, "Chasis", "Aprilia Racing", 22));
        participantesRegistrados.add(new Ingeniero("Gino Borsoi", 50, 1100000.0, "Dirección", "Pramac Racing", 25));
        participantesRegistrados.add(new Ingeniero("Francesco Guidotti", 51, 900000.0, "Mecánica", "KTM Factory", 20));
        participantesRegistrados.add(new Ingeniero("Massimo Meregalli", 53, 950000.0, "Estrategia", "Yamaha Factory", 22));
        participantesRegistrados.add(new Ingeniero("Pablo Nieto", 43, 800000.0, "Telemetría", "VR46 Racing", 15));
        participantesRegistrados.add(new Ingeniero("Nicolas Goyon", 45, 750000.0, "Chasis", "GasGas Tech3", 18));
        participantesRegistrados.add(new Ingeniero("Davide Brivio", 59, 1200000.0, "Dirección", "Trackhouse Racing", 30));

    }

    public void organizarEscuderia(){

        for (MiembroEquipo miembro: participantesRegistrados){
            String escuderia = "";
            if (miembro instanceof Piloto){
                escuderia = ((Piloto)miembro).getEscuderia();
            } else if (miembro instanceof  Ingeniero) {
                escuderia = ((Ingeniero)miembro).getEscuderia();
            }

            if(!agrupacionEquipos.containsKey(escuderia)){
                agrupacionEquipos.put(escuderia,new ArrayList<>());
            }

            //agrupacionEquipos.putIfAbsent(escuderia, new ArrayList<>());

            agrupacionEquipos.get(escuderia).add(miembro);


        }


    }
    public void formarParrilla(){
        for(MiembroEquipo miembro : participantesRegistrados){
            if (miembro instanceof Piloto){

                parrillaSalida.add((Piloto) miembro);

                rendimientoTotalAcumulado.put(miembro.getNombre(), 0);
            }

        }

    }

    public void simularCarrera(){
        System.out.println("---INICIANDO SIMULACIÓN DE CARRERA A 50 VUELTAS---");

        while (!parrillaSalida.isEmpty()) {
            pilotosEnPista.add(parrillaSalida.poll());
        }

        for (int i = 0; i < 50; i++) {

            Iterator<Piloto> iterator = pilotosEnPista.iterator();
            while (iterator.hasNext()){
                Piloto piloto = iterator.next();
                String resultado = piloto.realizarTarea();

                switch (resultado){
                    case "Accidente":
                        iterator.remove();
                        abandonos.add(piloto);
                        System.out.println("Nombre: "+ piloto.getNombre() + "ha sido eliminado (Accidente)");
                        System.out.println();
                        break;
                    case "Entrada a boxes":
                        iterator.remove();
                        pitLane.offerLast(piloto);
                        System.out.println("Nombre: " + piloto.getNombre() + "ha entrado a boxes en la vuelta "+ i);
                        System.out.println();
                        break;
                    case "Colisión Leve":
                        iterator.remove();
                        pitLane.offerFirst(piloto);
                        System.out.println("Nombre: " + piloto.getNombre() + " es tonto y se ha dado un choque en la vuelta "+i);
                        break;
                    case "Vuelta Limpia":
                        int puntosVuelta = piloto.competir();
                        rendimientoTotalAcumulado.put(piloto.getNombre(), rendimientoTotalAcumulado.get(piloto.getNombre()) + puntosVuelta);

                        break;
                }
            }

            while (!pitLane.isEmpty()){
                Piloto pilotoEnBoxes = pitLane.poll();

                if (agrupacionEquipos.containsKey(pilotoEnBoxes.getEscuderia())){

                    List<MiembroEquipo> equipo = agrupacionEquipos.get(pilotoEnBoxes.getEscuderia());
                    Ingeniero ingenieroMecanico = null;

                    for (MiembroEquipo miembro : equipo) {
                        if (miembro instanceof Ingeniero) {
                            ingenieroMecanico = (Ingeniero) miembro;
                            break;
                        }
                    }

                    if (ingenieroMecanico != null) {
                        int bonus = ingenieroMecanico.aportarMejora();
                        if (bonus > 0){
                            pilotoEnBoxes.setDesgasteNeumaticos(0);
                            pilotosEnPista.add(pilotoEnBoxes);
                            System.out.println(pilotoEnBoxes.getNombre() + " ha regresado a la pista gracias a " + ingenieroMecanico.getNombre());
                            System.out.println();
                        } else {
                            abandonos.add(pilotoEnBoxes);
                            System.out.println(pilotoEnBoxes.getNombre() + " no puede continuar (La mejora falló)");
                            System.out.println();
                        }
                    } else {
                        abandonos.add(pilotoEnBoxes);
                        System.out.println(pilotoEnBoxes.getNombre() + " ha abandonado porque su equipo no tiene ingeniero en boxes.");
                    }
                }
            }

        }
    }

    public void mostrarResultados(){


        System.out.println("---RESULTADOS FINALES---");
        System.out.println("Abandonos");
        if (abandonos.isEmpty()){
            System.out.println("No hay abandonos");
        }else{
            for (Piloto piloto : abandonos){
                System.out.println("- " + piloto.getNombre());
            }
        }

        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println("--- MAPA DE AGRUPACIÓN POR EQUIPOS ---");
        for (Map.Entry<String, List<MiembroEquipo>> entrada : agrupacionEquipos.entrySet()){
            System.out.println("Escuderia: " + entrada.getKey());

            for (MiembroEquipo miembro: entrada.getValue()){
                if (miembro instanceof Piloto){
                    System.out.println(" (Piloto) "+ miembro.getNombre());
                }else if (miembro instanceof Ingeniero) {
                    System.out.println(" (Ingeniero)" + miembro.getNombre());
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("--------------------------------------");
        System.out.println();
        System.out.println("Rendimiento Total Acumulado (Ranking):");
        rendimientoTotalAcumulado.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByValue().reversed()).forEach(entry -> System.out.println(entry.getKey() + ": "+ entry.getValue() + " puntos"));


    }













    public GranPremio(String nombreCircuito) {
        this.nombreCircuito = nombreCircuito;
        registrarParticipantes();
    }

    public String getNombreCircuito() {
        return nombreCircuito;
    }

    public void setNombreCircuito(String nombreCircuito) {
        this.nombreCircuito = nombreCircuito;
    }

    public Set<MiembroEquipo> getParticipantesRegistrados() {
        return participantesRegistrados;
    }

    public void setParticipantesRegistrados(Set<MiembroEquipo> participantesRegistrados) {
        this.participantesRegistrados = participantesRegistrados;
    }

    public Map<String, List<MiembroEquipo>> getAgrupacionEquipos() {
        return agrupacionEquipos;
    }

    public void setAgrupacionEquipos(Map<String, List<MiembroEquipo>> agrupacionEquipos) {
        this.agrupacionEquipos = agrupacionEquipos;
    }

    public PriorityQueue<Piloto> getParrillaSalida() {
        return parrillaSalida;
    }

    public void setParrillaSalida(PriorityQueue<Piloto> parrillaSalida) {
        this.parrillaSalida = parrillaSalida;
    }

    public Deque<Piloto> getPitLane() {
        return pitLane;
    }

    public void setPitLane(Deque<Piloto> pitLane) {
        this.pitLane = pitLane;
    }

    public Map<String, Integer> getRendimientoTotalAcumulado() {
        return rendimientoTotalAcumulado;
    }

    public void setRendimientoTotalAcumulado(Map<String, Integer> rendimientoTotalAcumulado) {
        this.rendimientoTotalAcumulado = rendimientoTotalAcumulado;
    }

    public List<Piloto> getAbandonos() {
        return abandonos;
    }

    public void setAbandonos(List<Piloto> abandonos) {
        this.abandonos = abandonos;
    }

    public List<Piloto> getPilotosEnPista() {
        return pilotosEnPista;
    }

    public void setPilotosEnPista(List<Piloto> pilotosEnPista) {
        this.pilotosEnPista = pilotosEnPista;
    }


}
