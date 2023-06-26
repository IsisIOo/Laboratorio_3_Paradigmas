package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class isidoraoyanedel21168603 {
    //esta cosa es sistema
    String nombre;

    Date fechaCreacrion;

    List<Drive> drives;
    List<Usuario> usuarios;


    //RF3 CONSTRUCTOR DE FILESYSTEM
    //Dom: nombre
    //Rec: filesystem
    public isidoraoyanedel21168603(String nombre) {
        this.nombre = nombre;
        this.fechaCreacrion = new Date();
        this.drives = new ArrayList<>(); //asumir que es asi
        this.usuarios = new ArrayList<>();
    }

    //RF4 TDA SYSTEM - ADD DRIVE
    //Dom: letter, name, capacity
    //no puedo tener drives repetidos
    public void addDrive(String letter, String name, int capacity){
        var Drive = new Drive(letter, name, capacity);
        List<String> currentLetters = new ArrayList<>();
        for (Drive disco : drives){
            currentLetters.add(disco.getLetter());
        }
        if (!currentLetters.contains(letter)){
            drives.add(Drive);
        }

    }


    public void register(String nombre){
        var user = new Usuario(nombre);
        var currentUsers =
                usuarios.stream()
                        .map(Usuario::getNombre)
                        .collect(Collectors.toList());
        if (!currentUsers.contains(nombre)){
            usuarios.add(user);
        }
    }

    @Override
    public String toString() {
        return "isidoraoyanedel21168603{" +
                "nombre='" + nombre + '\'' +
                ", fechaCreacrion=" + fechaCreacrion +
                ", drives=" + drives +
                ", usuarios=" + usuarios +
                '}';
    }
}
