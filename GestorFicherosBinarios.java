package Ficheros;

import javax.swing.*;
import java.io.*;

public class Ejercicio5 {
    public static void main(String[] args) {
        String cantidadStr = JOptionPane.showInputDialog("¿Cuántos números aleatorios quieres generar?");
        String rutaFichero = JOptionPane.showInputDialog("Introduce la ruta y nombre del fichero (ej: numeros.dat):");

        if (cantidadStr == null || rutaFichero == null) {
            System.out.println("Operación cancelada por el usuario.");
            return;
        }

        int cantidad;
        try {
            cantidad = Integer.parseInt(cantidadStr);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debes introducir un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        escribirNumeros(rutaFichero, cantidad);
        leerYMostrarNumeros(rutaFichero);
    }

    public static void escribirNumeros(String ruta, int cantidad) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta, true))) {

            for (int i = 0; i < cantidad; i++) {
                int numeroAleatorio = (int) (Math.random() * 101);
                dos.writeInt(numeroAleatorio);
            }

            dos.flush();
            System.out.println("Se han guardado " + cantidad + " números nuevos en el fichero.");

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al escribir: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void leerYMostrarNumeros(String ruta) {
        System.out.println("\n--- CONTENIDO DEL FICHERO ---");

        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
            while (true) {
                int numeroLeido = dis.readInt();
                System.out.print(numeroLeido + " - ");
            }
        } catch (EOFException e) {
            System.out.println("\n--- FIN DEL FICHERO ---");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
