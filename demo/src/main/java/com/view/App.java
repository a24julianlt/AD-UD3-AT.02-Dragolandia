package com.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.controller.*;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("BIENVENIDO AL JUEGO");
        System.out.println();
        System.out.println("¡¡Eres un mago y vas a pelear contra poderosos monstruos!!");
        System.out.println();
        System.out.println("Introduce tu nombre: ");
        String nombreMago = sc.nextLine();
        while (nombreMago.trim().isEmpty()) {
            System.out.println("Vuelve a introducir tu nombre, por favor: ");
            nombreMago = sc.nextLine();
        }

        System.out.println("Bienvenido, " + nombreMago);
        System.out.println();

        List<Integer> hechizos = new ArrayList<Integer>();
        Map<Integer, String> posibles = new HashMap<>(Map.of(
                1, "Bola de fuego",
                2, "Bola de nieve",
                3, "Rayo",
                4, "Meteorito"));
        String opc = "";

        /*
         * 
         * 
         * TERMINAR SELECCIONAR HECHIZO
         * 
         * 
         */

        while (!opc.equals("0")) {
            System.out.println("Elige que hechizos quieres.");
            System.out.println("Opciones: ");
            int i = 1;
            while (i < 5) {
                if (posibles.get(i) != null) {
                    System.out.println(i + ". " + posibles.get(i));
                }
                i++;
            }
            System.out.println("0. Salir");
            System.out.println("Puedes tener un máximo de 4 hechizos");
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

        System.out.println();

        System.out.println("Ahora vas a nombrar a tu enemigo, un monstruo");
        System.out.println("Introduce el nombre del monstruo: ");
        String nombreMonstruo = sc.nextLine();
        while (nombreMonstruo.trim().isEmpty()) {
            System.out.println("Vuelve a introducir el nombre del monstruo, por favor: ");
            nombreMonstruo = sc.nextLine();
        }

        System.out.println();

        System.out.println("Muy bien, ahora introduce el nombre del bosque que quieres conquistar:");
        String nombreBosque = sc.nextLine();
        while (nombreBosque.trim().isEmpty()) {
            System.out.println("Vuelve a introducir el nombre del bosque, por favor: ");
            nombreBosque = sc.nextLine();
        }

        System.out.println();

        System.out.println("Muy bien, ahora introduce el nombre del dragon:");
        String nombreDragon = sc.nextLine();
        while (nombreDragon.trim().isEmpty()) {
            System.out.println("Vuelve a introducir el nombre del dragon, por favor: ");
            nombreDragon = sc.nextLine();
        }

        List<String> monstruos = new ArrayList<>();
        monstruos.add("Monstruo " + nombreBosque);

        ControladorPrincipal controller = new ControladorPrincipal();
        controller.crearPartida(nombreMago, hechizos, nombreMonstruo, monstruos, nombreBosque, nombreDragon);

        controller.gardarMago();
        controller.gardarMonstruo();
        controller.gardarBosque();
        controller.gardarDragon();

        System.out.println();
        System.out.println("Te enfrentaras al monstruo " + nombreMonstruo + " de tipo " + controller.getTipoMons()
                + " por el control del bosque " + nombreBosque);

        System.out.println();
        System.out.println("Perfecto, vamos a empezar a pelear");
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
            System.out.println("El monstruo " + nombreMonstruo + " sigue siendo el dueño del bosque");
        }

        sc.close();
    }
}
