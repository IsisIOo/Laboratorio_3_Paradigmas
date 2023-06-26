package org.example;

import java.nio.file.FileSystem;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        var filesystem = new isidoraoyanedel21168603("mi sistema");
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

    }
}