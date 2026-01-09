package com.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.controller.*;
import com.model.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        /**
         * Inicializamos el controlador principal para guardar los datos a medida que
         * creamos los personajes
         * además creamos los hechizos, ya que son siempre los mismos
         */
        ControladorPrincipal controller = new ControladorPrincipal();
        controller.gardarHechizos();

        System.out.println("BIENVENIDO AL JUEGO");
        System.out.println();
        System.out.println("Primero vamos a crear el bosque en el que van a pelear");
        System.out.println();
        System.out.println("Introduce el nombre del bosque:");
        String nombreBosque = sc.nextLine();
        while (nombreBosque.trim().isEmpty()) {
            System.out.println("Vuelve a introducir el nombre del bosque, por favor: ");
            nombreBosque = sc.nextLine();
        }
        System.out.println();
        System.out.println("La pelea será en el bosque " + nombreBosque);
        System.out.println();

        controller.gardarBosque(nombreBosque);

        System.out.println("Ahora vamos a crear los monstruos, ");
        System.out.println("¿Cuantos monstruos quieres crear?: min 3");
        String n = sc.nextLine();
        int nMons;
        try {
            nMons = (int) Integer.parseInt(n);
            if (nMons < 3) {
                System.out.println("Como elegiste un número menor que 3, vamos a crear 3 monstruos");
                nMons = 3;
            }
        } catch (Exception e) {
            nMons = 0;
            if (nMons < 3) {
                System.out.println("Como no elegiste un número válido, vamos a crear 3 monstruos");
                nMons = 3;
            }
        }

        List<Monstruo> monstruos = new ArrayList<Monstruo>();
        for (int i = 0; i < nMons; i++) {
            System.out.println("Introduce el nombre del monstruo -" + i);
            String nombreMonstruo = sc.nextLine();
            while (nombreMonstruo.trim().isEmpty()) {
                System.out.println("Vuelve a introducir el nombre del monstruo, por favor: ");
                nombreMonstruo = sc.nextLine();
            }
            System.out.println("Bienvenido, " + nombreMonstruo);
            System.out.println();

            monstruos.add(controller.crearMonstruo(nombreMonstruo));
        }

        System.out.println();
        System.out.println("Ahora vamos a crear los magos");
        System.out.println();
        System.out.println("¿Cuantos magos quieres crear?: min 2");
        n = sc.nextLine();
        int nMagos;

        try {
            nMagos = (int) Integer.parseInt(n);
            if (nMagos < 2) {
                System.out.println("Como elegiste un número menor que 2, vamos a crear 2 magos");
                nMagos = 2;
            }
        } catch (Exception e) {
            nMagos = 0;
            if (nMagos < 2) {
                System.out.println("Como no elegiste un número válido, vamos a crear 2 magos");
                nMagos = 2;
            }
        }

        Map<String, List<Integer>> magosYHechizos = new HashMap<>();

        List<Mago> magos = new ArrayList<Mago>();
        for (int i = 0; i < nMagos; i++) {
            System.out.println("Introduce el nombre del mago -" + i);
            String nombreMago = sc.nextLine();
            while (nombreMago.trim().isEmpty()) {
                System.out.println("Vuelve a introducir tu nombre, por favor: ");
                nombreMago = sc.nextLine();
            }
            System.out.println("Bienvenido, " + nombreMago);
            System.out.println();

            List<Integer> conjuros = elegirHechizos(sc, nombreMago);

            magos.add(controller.crearMago(nombreMago, conjuros));
            magosYHechizos.put(nombreMago, conjuros);
        }

        System.out.println();

        System.out.println("Vamos a crear el dragón");
        System.out.println("Introduce el nombre del Dragón");
        String nombreDragon = sc.nextLine();
        while (nombreDragon.trim().isEmpty()) {
            System.out.println("Vuelve a introducir tu nombre, por favor: ");
            nombreDragon = sc.nextLine();
        }
        controller.crearDragon(nombreDragon);
        System.out.println("Dragón " + nombreDragon + " creado");
        System.out.println();

        System.out.println("Vale, ahora asignamos al bosque los Monstruos");

        System.out.println();

        controller.setMontruoJefe(monstruos.get(0));
        System.out.println("El primer monstruo lo añadimos como jefe del bosque");

        monstruos.remove(0);
        controller.setMonstruosBosque(monstruos);
        System.out.println("El resto de monstruos los añadimos al bosque");

        System.out.println();
        System.out.println();

        System.out.println("¡Perfecto! Ya tenemos todo listo para empezar la pelea");

        System.out.println();
        System.out.println("Empieza la pelea...");
        System.out.println();

        try {
            Thread.sleep(3000); // 3 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Empiezas atacando: ");
        while (controller.finPartida()) {
            System.out.println("Lanzas un hechizo de " + controller.getNivelMagia() + " de poder mágico");
            System.out.println();

            try {
                Thread.sleep(3000); // 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            controller.atacaMago();
            System.out.println("El monstruo queda con " + controller.getVidaMons() + " puntos de vida");
            System.out.println();

            try {
                Thread.sleep(3000); // 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!controller.finPartida()) {
                break;
            }

            System.out.println();
            System.out.println("Ahora ataca el monstruo,");
            System.out.println();

            try {
                Thread.sleep(3000); // 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            controller.atacaMons();
            System.out.println("Te quedas con " + controller.getVidaMago() + " puntos de vida");

            System.out.println();

            try {
                Thread.sleep(3000); // 3 segundos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (controller.ganaMago()) {
            System.out.println("¡¡FELICIDADES!!");
            System.out.println("¡¡HAS GANADO!!");
            System.out.println("Ahora eres el dueño del bosque " + nombreBosque);
        } else {
            System.out.println("¡Que pena!");
            System.out.println("Has perdido");
            // System.out.println("El monstruo " + nombreMonstruo + " sigue siendo el dueño
            // del bosque");
        }

        System.out.println();

        /*
         * Prueba para ver si elimina
         */
        System.out.println();
        System.out.println("¿Deseas limpiar la base de datos? (s/n)");
        System.out.println("Esto BORRARÁ TODOS LOS DATOS de la base de datos");
        String limpiar = sc.nextLine();

        if (limpiar.equalsIgnoreCase("s") || limpiar.equalsIgnoreCase("si")) {
            System.out.println("Limpiando base de datos...");
            controller.limpiarBaseDatos();
            System.out.println("Base de datos limpiada correctamente");
        } else {
            System.out.println("Los datos de la partida se han guardado");
        }
        sc.close();
    }

    /**
     * Función para elegir los hechizos de cada mago
     * 
     * @param mago nombre del mago
     * @return map de los hechizos de cada mago, clave nombre del mago y list de
     *         hechizos
     */
    public static List<Integer> elegirHechizos(Scanner sc, String mago) {
        List<Integer> hechizos = new ArrayList<Integer>();
        Map<Integer, String> posibles = new HashMap<>(Map.of(
                1, "Bola de fuego",
                2, "Bola de nieve",
                3, "Rayo",
                4, "Meteorito"));
        String opc = "";

        while (hechizos.size() < 2 || !opc.equals("0")) {
            System.out.println("Elige que hechizos quieres.");
            System.out.println("Opciones: ");
            int i = 1;
            while (i < 5) {
                if (posibles.get(i) != null) {
                    System.out.println(i + ". " + posibles.get(i));
                }
                i++;
            }
            System.out.println("0. Salir | Solo puedes salir si tienes mínimo 2 hechizos");
            System.out.println("Puedes tener un máximo de 4 hechizos: ");
            opc = sc.nextLine();
            while (opc.trim().isEmpty()) {
                System.out.println("No has introducido nada, vuelve a introducir el codigo del hechizo, por favor: ");
                opc = sc.nextLine();
            }

            try {
                int opcInt = (int) Integer.parseInt(opc) - 1;
                if ((int) Integer.parseInt(opc) >= 0 && (int) Integer.parseInt(opc) < 5) {
                    if (hechizos.contains(opcInt)) {
                        System.out.println("Ya has elegido ese hechizo");
                    } else {
                        if (!opc.equals("0")) {
                            posibles.remove((int) Integer.parseInt(opc));
                            hechizos.add(opcInt);
                        } else {
                            continue;
                        }
                    }
                } else {
                    System.out.println("Nos has elegido una opción correcta");
                }
            } catch (Exception e) {
                System.out.println("Introduce el código numérico, no letras");
            }

        }

        System.out.println("Hechizos elegidos");
        hechizos.sort(null); // Ordena alfabeticamente
        for (int h : hechizos) {
            System.out.println(h);
        }

        return hechizos;
    }
}
