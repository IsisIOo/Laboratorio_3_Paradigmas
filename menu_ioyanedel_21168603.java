package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class menu_ioyanedel_21168603 {
        private boolean exitmenu;
        private isidoraoyanedel21168603 sistema;

        public menu_ioyanedel_21168603(){
            this.exitmenu = false;
            this.sistema = new isidoraoyanedel21168603(null);
        }

    public isidoraoyanedel21168603 getSistema() {
        return sistema;
    }

    public boolean isSalirmenu() {
        return exitmenu;
    }

    public void executedemenu(){
        menu_principal();
        escanear_opciones();
    }

    private void menu_principal(){
        System.out.println("-----Escoja una opcion------ ");
        System.out.println("1. Crear Sistema");
        System.out.println("2. Modificar el Sistema");
        System.out.println("3. Ver el Sistema");
        System.out.println("4. Salir");
    }

    private void escanear_opciones(){
        Scanner entrada = new Scanner(System.in);
        try{
            System.out.println("ingrese su opción: " );
            int eleccion = entrada.nextInt();
            switch(eleccion){
                case 1:
                    escaner_NameSistem();
                    break;
                case 2:
                    menu_modificar();
                    escanear_elemento();
                    break;
                case 3:
                    ver_sistema();
                    break;
                case 4:
                    System.out.println("\nFin del proceso.");
                    exitmenu = true;
                    break;}
        } catch (InputMismatchException e) {
            System.out.println("\nSolo se admite de entrada enteros positivos");
            entrada.next();
        }
    }
    private void escaner_NameSistem(){
        Scanner entrada = new Scanner(System.in);
        System.out.print("Ingrese el nombre : ");
        String nombre = entrada.nextLine();
        this.sistema = new isidoraoyanedel21168603(nombre);
    }

    private void menu_modificar(){
        System.out.println(" --- Escoja una opción: --- ");
        System.out.println("1.- Agregar un drive ");
        System.out.println("2.- Crear un usuario ");
        System.out.println("3.- Login usuario ");
        System.out.println("4.- Desconectar un usuario ");
        System.out.println("5.- Fijar un drive ");
        System.out.println("6.- Crear un directorio: ");
    }

    private void escanear_elemento(){
        Scanner entrada = new Scanner(System.in);
        try{
            System.out.println("ingrese su elección: " );
            int eleccion = entrada.nextInt();
            switch(eleccion){
                case 1:
                    Scanner datos = new Scanner(System.in);
                    System.out.print("Ingrese la letra del drive: ");
                    String letra_drive = datos.nextLine();
                    System.out.print("Ingrese el nombre del drive: ");
                    String nombre_drive = datos.nextLine();
                    System.out.print("Ingrese el almacenamiento del drive: ");
                    int capacidad = datos.nextInt();
                    sistema.addDrive(letra_drive, nombre_drive, capacidad);
                    break;

                case 2:
                    Scanner id = new Scanner(System.in);
                    System.out.print("Ingrese su nombre de usuario: ");
                    String nombre_user = id.nextLine();
                    sistema.register(nombre_user);
                    break;
                case 3:
                    Scanner current_u = new Scanner(System.in);
                    System.out.print("Ingrese el nombre del usuario que quiere conectar: ");
                    String current_user = current_u.nextLine();
                    sistema.login(current_user);
                    break;
                case 4:
                    sistema.logout();
                    break;
                case 5:
                    Scanner current_d = new Scanner(System.in);
                    System.out.print("Ingrese la letra del drive con el que quiere trabajar: ");
                    String current_drive = current_d.nextLine();
                    sistema.switchDrive(current_drive);
                    break;

                case 6:
                    Scanner n_folder = new Scanner(System.in);
                    System.out.println("Ingrese el nombre de la carpeta que quiere crear: ");
                    String new_folder = n_folder.nextLine();
                    sistema.mkdir(new_folder);
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("\nSolo se admite de entrada enteros positivos");
            entrada.next();
        }
    }

    private void ver_sistema(){
        System.out.println(sistema);
    }
}



