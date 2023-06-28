package org.example;

import java.nio.file.FileSystem;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var filesystem = new isidoraoyanedel21168603("mi sistema");
        var pathpath = new Path();
        System.out.println(filesystem);

        filesystem.addDrive("D", "Oveja", 400 );
        System.out.println(filesystem);

        filesystem.addDrive("C", "oLA", 600 );
        filesystem.addDrive("E", "oLA", 600 );
        System.out.println(filesystem);

        filesystem.register("User1");
        System.out.println(filesystem);

        filesystem.register("User1"); //no repite usuario
        System.out.println(filesystem);

        filesystem.login("User1");
        System.out.println(filesystem);

        filesystem.login("User2");
        System.out.println(filesystem);

        filesystem.logout();
        System.out.println(filesystem);

        filesystem.register("User2"); //no repite usuario
        System.out.println(filesystem);

        filesystem.login("User2");
        System.out.println(filesystem);

        //filesystem.logout(); //NO HACE SWITCH DRIVE SI ESTÁ ESTE TROZO
        //System.out.println(filesystem);

        filesystem.switchDrive("C");
        System.out.println(filesystem);

        filesystem.mkdir("hola");
        System.out.println(filesystem);

        //filesystem.logout();
        //System.out.println(filesystem);

        //filesystem.login("User1"); //cambia el usuario que está dentro de la ruta
        //System.out.println(filesystem);

        System.out.println(filesystem);

        filesystem.switchDrive("E");
        System.out.println(filesystem);

        filesystem.mkdir("ADOPS");
        System.out.println(filesystem);

        filesystem.mkdir("EADO");
        System.out.println(filesystem);

        filesystem.switchDrive("C");
        System.out.println(filesystem);

        filesystem.switchDrive("E");
        System.out.println(filesystem);

        filesystem.mkdir("EADO");
        System.out.println(filesystem);

        filesystem.cd("EADO");
        System.out.println(filesystem);

        filesystem.mkdir("gfafa");
        System.out.println(filesystem);

    }
}