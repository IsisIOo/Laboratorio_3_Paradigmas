package org.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu_21168603_ioyanedelalvarez {
        private boolean exitmenu;
        private System_21168603_ioyanedelalvarez sistema;

        public Menu_21168603_ioyanedelalvarez(){
            this.exitmenu = false;
            this.sistema = new System_21168603_ioyanedelalvarez(null);
        }

    public System_21168603_ioyanedelalvarez getSistema() {
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
        this.sistema = new System_21168603_ioyanedelalvarez(nombre);
    }

    private void menu_modificar(){
        System.out.println(" --- Escoja una opción: --- ");
        System.out.println("1.- Agregar un drive ");
        System.out.println("2.- Crear un usuario ");
        System.out.println("3.- Login usuario ");
        System.out.println("4.- Desconectar un usuario ");
        System.out.println("5.- Fijar un drive ");
        System.out.println("6.- Crear un directorio: "); //mkdir
        System.out.println("7.- Cambiar de directorio"); //cd
        System.out.println("8. Agregar archivo");
        System.out.println("9. Eliminar archivo o carpeta");
        System.out.println("10. Copiar archivo en un Directorio");
        System.out.println("11. Mover archivo a un directorio");
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

                case 7:
                    Scanner n_directorio = new Scanner(System.in);
                    System.out.println("Ingrese el nombre del directorio al que desea ir: ");
                    String new_camino = n_directorio.nextLine();
                    sistema.cd(new_camino);
                    break;

                case 8:
                    sistema.crea_file();

                case 9:
                    Scanner nombrearcar = new Scanner(System.in);
                    System.out.print("Ingrese el nombre del archivo o carpeta que desea eliminar: ");
                    String nombre_arcarr = nombrearcar.nextLine();
                    sistema.del(nombre_arcarr);
                    break;

                case 10:
                    Scanner Cosa = new Scanner(System.in);
                    Scanner Destino = new Scanner(System.in);
                    System.out.print("Ingrese el nombre del archivo o carpeta que copiar: ");
                    String Cosamover = Cosa.nextLine();
                    String destinomover = Destino.nextLine();
                    sistema.copy(Cosamover, destinomover);
                    break;

                case 11:
                    Scanner Cosa1 = new Scanner(System.in);
                    Scanner Destino1 = new Scanner(System.in);
                    System.out.print("Ingrese el nombre del archivo o carpeta que mover: ");
                    String Cosamover1 = Cosa1.nextLine();
                    String destinomover1 = Destino1.nextLine();
                    sistema.move(Cosamover1, destinomover1);
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



