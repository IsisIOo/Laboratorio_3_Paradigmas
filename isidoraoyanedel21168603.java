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
    List<String> logueados;
    List<String> actual;


    //List<String> carpeta;

    List<Path> ruta;



    //RF3 CONSTRUCTOR DE FILESYSTEM
    //Dom: nombre
    //Rec: filesystem
    public isidoraoyanedel21168603(String nombre) {
        this.nombre = nombre;
        this.fechaCreacrion = new Date();
        this.drives = new ArrayList<>(); //asumir que es asi
        this.usuarios = new ArrayList<>();
        this.logueados = new ArrayList<>();
        this.actual = new ArrayList<>();
        this.ruta = new ArrayList<>();
    }

    //RF4 TDA SYSTEM - ADD DRIVE
    //Dom: letter, name, capacity
    //no puedo tener drives repetidos
    public void addDrive(String letter, String name, int capacity) {
        var Drive = new Drive(letter, name, capacity);
        List<String> currentLetters = new ArrayList<>();
        for (Drive disco : drives) {
            currentLetters.add(disco.getLetter());
        }
        if (!currentLetters.contains(letter)) {
            drives.add(Drive);
        }

    }

    //RF5 TDA SYSTEM - REGISTER
    //Dom: userName (String)
    //no puedo tener usuarios repetidos
    public void register(String nombre) {
        var user = new Usuario(nombre);
        var currentUsers =
                usuarios.stream()
                        .map(Usuario::getNombre)
                        .collect(Collectors.toList());
        if (!currentUsers.contains(nombre)) {
            usuarios.add(user);
        }
    }

    public List<String> getLogueados() {
        return logueados;
    }

    //RF6 TDA SYSTEM - LOGIN
    //Dom: userName (String)
    //no puedo tener mas de un usuario
    public void login(String nombre) {
        var currentUsers =
                usuarios.stream()
                        .map(Usuario::getNombre)
                        .collect(Collectors.toList());
        if (currentUsers.contains(nombre) && logueados.isEmpty()) {
            logueados.add(nombre);
        }
    }

    //RF7 TDA SYSTEM - LOGOUT
    //Dom: void

    public void logout (){

        logueados.clear();
    }

    //RF8 TDA SYSTEM - LOGIN
    //Dom: userName (String)
    //no puedo tener mas de un drive
    public void switchDrive(String letter){
        var currentLetters =
                drives.stream()
                        .map(Drive::getLetter)
                        .collect(Collectors.toList());

        if (currentLetters.contains(letter) && !logueados.isEmpty()) {
            if(actual.isEmpty()){
                actual.add(letter);
            }
            if(!actual.isEmpty()){
                actual.clear();
                actual.add(letter);
            }
        }
        var ruta1 = new Path();
        var currentChapters =
                ruta1.carpeta.stream()
                .map(Chapter::getNombre)
                .collect(Collectors.toList());
        ruta1.usuariocarpeta = (getLogueados().get(0)); //obtiene solo el string
        ruta1.rutaSTRING = actual.get(0) + ":/"; //con get0 agarra solo la letra
        var rutasderutas =
                ruta.stream()
                        .filter(rutas->rutas.rutaSTRING.equals(actual.get(0) + ":/"))
                        .collect(Collectors.toList());

        if(rutasderutas.size()>1){
            var tamano2 = rutasderutas.size()-1;
            ruta1.carpeta.addAll(rutasderutas.get(tamano2).carpeta);
        }

        //ruta1.carpeta= ;
        ruta.add(ruta1);

    }

    //RF9 TDA SYSTEM - mkDir
    //Dom: userName (String)
    //no puedo tener mas de un drive
    public void mkdir(String chaptername){
        var rutaruta = new Path();
        var carpeta1 = new Chapter(chaptername);
        var currentChapters =
                rutaruta.carpeta.stream()
                        .map(Chapter::getNombre)
                        .collect(Collectors.toList());
        if (!currentChapters.contains(chaptername)) {
            var tamano = ruta.size()-1;

            rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING; //pq cuando agrego adops se devuelve a c?
            rutaruta.carpeta.addAll(ruta.get(tamano).carpeta);
            rutaruta.carpeta.add(carpeta1);//agrega la carpeta a las carpetas, quiero que se agregue a las que habian antes
            rutaruta.usuariocarpeta = getLogueados().get(0);
            ruta.add(rutaruta);
        }
    }






    @Override
    public String toString() {
        return "isidoraoyanedel21168603{" +
                "nombre='" + nombre + '\'' +
                ", fechaCreacrion=" + fechaCreacrion +
                ", drives=" + drives +
                ", usuarios=" + usuarios +
                ", logueados=" + logueados +
                ", actual=" + actual +
                ", ruta=" + ruta +
                '}';
    }

    public List<String> getActual() {
        return actual;
    }
}

