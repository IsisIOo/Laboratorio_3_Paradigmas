package org.example;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        /*menu_ioyanedel_21168603 menu = new menu_ioyanedel_21168603();
        while (!menu.isSalirmenu()) {
            menu.executedemenu();}*/


            var filesystem = new System_21168603_ioyanedelalvarez("mi sistema");
            var pathpath = new Path_21168603_ioyanedelalvarez();
            System.out.println(filesystem);

            filesystem.addDrive("D", "Oveja", 400);
            System.out.println(filesystem);

            filesystem.addDrive("C", "oLA", 600);
            filesystem.addDrive("E", "oLA", 600);
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

            filesystem.cd("/");
            System.out.println(filesystem);


            //filesystem.cd("/"); //funciona
            //System.out.println(filesystem);

            filesystem.mkdir("feo thomi");
            System.out.println(filesystem);

            filesystem.cd("feo thomi");
            System.out.println(filesystem);

            filesystem.crea_file();
            System.out.println(filesystem);

            filesystem.switchDrive("D");
            System.out.println(filesystem);

            filesystem.mkdir("culo");
            System.out.println(filesystem);

            filesystem.mkdir("figura");
            System.out.println(filesystem);

            filesystem.switchDrive("C");
            System.out.println(filesystem);

            filesystem.cd("feo thomi");
            System.out.println(filesystem);

            filesystem.mkdir("chalala");
            System.out.println(filesystem);
            //filesystem.cd("..");
            //System.out.println(filesystem);

           // filesystem.crea_file();
            //System.out.println(filesystem);

            filesystem.copy("hola.txt", "D:/culo/");
            System.out.println(filesystem);




        }
    }