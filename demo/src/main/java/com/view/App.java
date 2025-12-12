package com.view;

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

        ControladorBosque contrBosque = new ControladorBosque();
        contrBosque.crearPartida(nombreMago, nombreMonstruo, nombreBosque);

        System.out.println();
        System.out.println("Te enfrentaras al monstruo " + nombreMonstruo + " de tipo " + contrBosque.getTipoMons() + " por el control del bosque " + nombreBosque);

        System.out.println();
        System.out.println("Perfecto, vamos a empezar a pelear");
        System.out.println();

        try {
            Thread.sleep(3000); // 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Empiezas atacando: ");
        while (contrBosque.finPartida()) {
            System.out.println("Lanzas un hechizo de " + contrBosque.getNivelMagia() + " de poder mágico");
            System.out.println();

            try {
                Thread.sleep(3000); // 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            contrBosque.atacaMago();
            System.out.println("El monstruo queda con " + contrBosque.getVidaMons() + " puntos de vida");
            System.out.println();

            try {
                Thread.sleep(3000); // 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!contrBosque.finPartida()) {
                break;
            }

            System.out.println();
            System.out.println("Ahora ataca el monstruo,");
            System.out.println();

            try {
                Thread.sleep(3000); // 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            contrBosque.atacaMons();
            System.out.println("Te quedas con " + contrBosque.getVidaMago() + " puntos de vida");

            System.out.println();

            try {
                Thread.sleep(3000); // 1 segundo
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (contrBosque.ganaMago()) {
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
