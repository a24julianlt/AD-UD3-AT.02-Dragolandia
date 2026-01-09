package com.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.controller.*;
import com.model.*;

/**
 * Clase principal del juego Dragolandia.
 * Gestiona el flujo del juego y la interacción con el usuario.
 */
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
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // OBTENER REFERENCIAS A TRAVÉS DE CONTROLADORES
        Bosque bosque = controller.obtenerBosque();
        Dragon dragon = controller.obtenerDragon();
        List<Hechizo> todosHechizos = controller.getTodosLosHechizos();

        int ronda = 1;

        // Mientras haya magos y monstruos vivos
        while (magos.stream().anyMatch(m -> m.getVida() > 0) &&
                controller.getListMonstruos().stream().anyMatch(m -> m.getVida() > 0)) {

            System.out.println("\n========== RONDA " + ronda + " ==========");

            // a) Cada mago lanza un conjuro aleatorio
            System.out.println("\n--- TURNO DE MAGOS ---");
            for (Mago mago : magos) {
                if (mago.getVida() > 0) {
                    // Seleccionar hechizo aleatorio de TODOS los disponibles
                    Hechizo hechizoAleatorio = todosHechizos.get((int) (Math.random() * todosHechizos.size()));

                    // Verificar si el mago conoce este hechizo
                    List<Hechizo> conjurosMago = mago.getConjuros();
                    boolean conoceHechizo = conjurosMago.stream()
                            .anyMatch(h -> h.getNombre().equals(hechizoAleatorio.getNombre()));

                    if (conoceHechizo) {
                        System.out.println(mago.getNombre() + " lanza " + hechizoAleatorio.getNombre());

                        // Usar método del controlador para aplicar hechizo
                        controller.usarHechizo(hechizoAleatorio.getNombre(), controller.getListMonstruos());

                        // Avisar y retirar caídos inmediatamente tras el hechizo
                        List<Monstruo> muertosTrasHechizo = new ArrayList<>();
                        for (Monstruo obj : controller.getListMonstruos()) {
                            if (obj.getVida() <= 0) {
                                System.out.println("Muere " + obj.getNombre());
                                muertosTrasHechizo.add(obj);
                            }
                        }
                        controller.getListMonstruos().removeAll(muertosTrasHechizo);

                        if (bosque.getMonstruoJefe() != null && bosque.getMonstruoJefe().getVida() <= 0) {
                            System.out.println("Muere el jefe " + bosque.getMonstruoJefe().getNombre());
                            List<Monstruo> vivos = controller.getListMonstruos().stream()
                                    .filter(m -> m.getVida() > 0)
                                    .collect(Collectors.toList());
                            if (!vivos.isEmpty()) {
                                Monstruo nuevoJefe = vivos.get(0);
                                controller.setMontruoJefe(nuevoJefe);
                                vivos.remove(0);
                                controller.setMonstruosBosque(vivos);
                                System.out.println(nuevoJefe.getNombre() + " pasa a ser el nuevo jefe del bosque");
                            } else {
                                controller.setMontruoJefe(null);
                                System.out.println("No quedan monstruos para jefe");
                            }
                        }
                    } else {
                        System.out.println(mago.getNombre() + " intenta " + hechizoAleatorio.getNombre() +
                                " pero no lo conoce. ¡Pierde 1 vida!");
                        mago.setVida(mago.getVida() - 1);
                    }
                }
            }

            // b) Cada monstruo ataca a un mago
            System.out.println("\n--- TURNO DE MONSTRUOS ---");
            List<Monstruo> monstruosAtaque = new ArrayList<>();

            // Añadir monstruo jefe (solo una vez)
            Monstruo jefe = bosque.getMonstruoJefe();
            if (jefe != null && jefe.getVida() > 0) {
                monstruosAtaque.add(jefe);
            }

            // Añadir resto de monstruos vivos, evitando duplicar al jefe
            monstruosAtaque.addAll(controller.getListMonstruos().stream()
                    .filter(m -> m.getVida() > 0 && (jefe == null || m.getId() != jefe.getId()))
                    .collect(Collectors.toList()));

            for (Monstruo monstruo : monstruosAtaque) {
                List<Mago> magosVivos = magos.stream()
                        .filter(m -> m.getVida() > 0)
                        .collect(Collectors.toList());

                if (!magosVivos.isEmpty()) {
                    Mago objetivo = magosVivos.get((int) (Math.random() * magosVivos.size()));
                    System.out.println(monstruo.getNombre() + " ataca a " + objetivo.getNombre());
                    monstruo.atacar(objetivo);

                    if (objetivo.getVida() <= 0) {
                        System.out.println("Muere " + objetivo.getNombre());
                        magos.removeIf(m -> m.getNombre().equals(objetivo.getNombre()));
                    }
                }
            }

            // c) El dragón ataca al monstruo jefe
            System.out.println("\n--- TURNO DEL DRAGÓN ---");
            if (dragon.getVida() > 0 && bosque.getMonstruoJefe() != null &&
                    bosque.getMonstruoJefe().getVida() > 0) {
                dragon.atacar(bosque.getMonstruoJefe());

                if (bosque.getMonstruoJefe().getVida() <= 0) {
                    System.out.println("Muere el jefe " + bosque.getMonstruoJefe().getNombre());
                    List<Monstruo> vivos = controller.getListMonstruos().stream()
                            .filter(m -> m.getVida() > 0)
                            .collect(Collectors.toList());
                    if (!vivos.isEmpty()) {
                        Monstruo nuevoJefe = vivos.get(0);
                        controller.setMontruoJefe(nuevoJefe);
                        vivos.remove(0);
                        controller.setMonstruosBosque(vivos);
                        System.out.println(nuevoJefe.getNombre() + " pasa a ser el nuevo jefe del bosque");
                    } else {
                        controller.setMontruoJefe(null);
                        System.out.println("No quedan monstruos para jefe");
                    }
                }
            }

            // 1. Eliminar muertos y reasignar jefe si es necesario
            System.out.println("\n--- LIMPIEZA ---");

            // Eliminar magos muertos
            List<Mago> magosMuertos = magos.stream()
                    .filter(m -> m.getVida() <= 0)
                    .collect(Collectors.toList());

            for (Mago m : magosMuertos) {
                System.out.println(m.getNombre() + " ha muerto");
            }
            magos.removeAll(magosMuertos);

            // Eliminar monstruos muertos
            List<Monstruo> monstruosMuertos = controller.getListMonstruos().stream()
                    .filter(m -> m.getVida() <= 0)
                    .collect(Collectors.toList());

            for (Monstruo m : monstruosMuertos) {
                System.out.println(m.getNombre() + " ha muerto");
            }
            controller.getListMonstruos().removeAll(monstruosMuertos);

            // Si el jefe muere, asignar nuevo jefe
            if (bosque.getMonstruoJefe() != null && bosque.getMonstruoJefe().getVida() <= 0) {
                System.out.println("El jefe " + bosque.getMonstruoJefe().getNombre() + " ha muerto");

                List<Monstruo> monstruosVivos = controller.getListMonstruos().stream()
                        .filter(m -> m.getVida() > 0)
                        .collect(Collectors.toList());

                if (!monstruosVivos.isEmpty()) {
                    Monstruo nuevoJefe = monstruosVivos.get(0);
                    controller.setMontruoJefe(nuevoJefe);
                    monstruosVivos.remove(0);
                    controller.setMonstruosBosque(monstruosVivos);
                    System.out.println(nuevoJefe.getNombre() + " es el nuevo jefe del bosque");
                } else {
                    controller.setMontruoJefe(null);
                    System.out.println("No quedan monstruos para ser jefe");
                }
            }

            // 2. Mostrar estado después de cada ronda
            System.out.println("\n========== ESTADO DEL JUEGO ==========");

            System.out.println("\n--- MAGOS ---");
            for (Mago m : magos) {
                String estado = m.getVida() > 0 ? "VIVO" : "MUERTO";
                System.out.println(m.getNombre() + " - Vida: " + m.getVida() + " - " + estado);
            }

            System.out.println("\n--- BOSQUE: " + bosque.getNombre() + " ---");
            if (bosque.getMonstruoJefe() != null) {
                String estado = bosque.getMonstruoJefe().getVida() > 0 ? "VIVO" : "MUERTO";
                System.out.println("👑 JEFE: " + bosque.getMonstruoJefe().getNombre() +
                        " (" + bosque.getMonstruoJefe().getTipo() + ") - Vida: " +
                        bosque.getMonstruoJefe().getVida() + " - " + estado);
            }

            System.out.println("\nMonstruos en el bosque: " + controller.getListMonstruos().size());
            for (Monstruo m : controller.getListMonstruos()) {
                String estado = m.getVida() > 0 ? "VIVO" : "MUERTO";
                System.out.println("  🧟 " + m.getNombre() + " (" + m.getTipo() + ") - Vida: " +
                        m.getVida() + " - " + estado);
            }

            System.out.println("\n--- DRAGÓN ---");
                String estadoDragon = dragon.getVida() > 0 ? "VIVO" : "MUERTO";
                System.out.println(dragon.getNombre() + " - Resistencia: " +
                    dragon.getResistencia() + " - " + estadoDragon);

            System.out.println("=====================================");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ronda++;
        }

        // 3. Fin de partida
        System.out.println("\n========== FIN DEL JUEGO ==========");
        if (magos.stream().anyMatch(m -> m.getVida() > 0)) {
            System.out.println("🎉 ¡¡FELICIDADES!!");
            System.out.println("¡¡LOS MAGOS HAN GANADO!!");
            System.out.println("Ahora son los dueños del bosque " + bosque.getNombre());
        } else {
            System.out.println("¡Que pena!");
            System.out.println("¡¡LOS MONSTRUOS HAN GANADO!!");
        }
        System.out.println("=====================================");

        System.out.println();

        /*
         * Limpieza de la base de datos
         */
        System.out.println("¿Deseas limpiar la base de datos? (s/n)");
        String limpiar = sc.nextLine();

        if (limpiar.equalsIgnoreCase("s") || limpiar.equalsIgnoreCase("si")) {
            controller.limpiarBaseDatos();
        } else {
            System.out.println("Los datos de la partida se han guardado");
        }

        sc.close();
    }

    /**
     * Permite al usuario elegir los hechizos para un mago.
     * @param sc Scanner para entrada de usuario
     * @param mago Nombre del mago
     * @return Lista de índices de hechizos seleccionados
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
