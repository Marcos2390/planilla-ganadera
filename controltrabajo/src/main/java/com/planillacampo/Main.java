package com.planillacampo;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Crear tablas si no existen
        DatabaseInit.crearTablas();

        Scanner sc = new Scanner(System.in);
        RegistroService registro = new RegistroService();
        int opcion = -1;

        do {
            System.out.println("\n=== SISTEMA DE REGISTRO DE ANIMALES ===");
            System.out.println("1. Agregar animal");
            System.out.println("2. Eliminar animal");
            System.out.println("3. Registrar vacuna");
            System.out.println("4. Registrar movimiento");
            System.out.println("5. Listar animales");
            System.out.println("6. Listar sanidad");
            System.out.println("7. Listar movimientos");
            System.out.println("0. Salir");
            System.out.print("Opción: ");

            if (sc.hasNextInt()) {
                opcion = sc.nextInt();
            } else {
                sc.nextLine();
                opcion = -1;
            }
            sc.nextLine(); // limpiar buffer

            switch (opcion) {

                case 1 -> {
                    // INGRESAR NUMERO
                    System.out.print("Número de caravana: ");
                    int nuevoNumero = sc.nextInt();
                    sc.nextLine();

                    // OBTENER TODOS LOS ANIMALES
                    List<Animales> listaAnimales = registro.listarAnimales();

                    // VERIFICAR DUPLICADO
                    boolean existe = listaAnimales.stream()
                            .anyMatch(a -> a.getNumerocaravana() == nuevoNumero);

                    if (existe) {
                        System.out.println("❌ Error: Ya existe un animal con ese número de caravana.");
                        break; // salir sin registrar
                    }

                    // RESTO DE DATOS
                    System.out.print("Categoria: ");
                    String categoria = sc.nextLine();

                    System.out.print("Raza: ");
                    String raza = sc.nextLine();

                    System.out.print("Edad: ");
                    int edad = sc.nextInt();
                    sc.nextLine();

                    registro.agregarAnimal(new Animales(nuevoNumero, categoria, raza, edad));
                }

                case 2 -> {
                    System.out.print("Numero de caravana: ");
                    int num = sc.nextInt();
                    sc.nextLine();
                    registro.eliminarAnimal(num);
                }

                case 3 -> {
                    System.out.print("ID animal (id): ");
                    int idAnimal = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Vacuna: ");
                    String vacuna = sc.nextLine();
                    System.out.print("Fecha (dd/mm/aaaa): ");
                    String fecha = sc.nextLine();
                    registro.registrarVacuna(new Sanidad(idAnimal, vacuna, fecha));
                }

                case 4 -> {
                    System.out.print("ID animal (id): ");
                    int idMov = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Tipo (Ingreso/Salida): ");
                    String tipo = sc.nextLine();
                    System.out.print("Destino: ");
                    String destino = sc.nextLine();
                    System.out.print("Fecha (dd/mm/aaaa): ");
                    String fechaMov = sc.nextLine();
                    registro.registrarMovimiento(new Movimientos(idMov, tipo, destino, fechaMov));
                }

                case 5 -> {
                    var lista = registro.listarAnimales();
                    if (lista.isEmpty())
                        System.out.println("No hay animales registrados.");
                    else
                        lista.forEach(System.out::println);
                }

                case 6 -> registro.listarSanidad();
                case 7 -> registro.listarMovimientos();

                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida.");
            }

        } while (opcion != 0);

        sc.close();
    }
}
