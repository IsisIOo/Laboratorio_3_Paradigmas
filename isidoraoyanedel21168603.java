package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class isidoraoyanedel21168603 implements Interfaz_IOyanedel_21168603{
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
        System.out.println("Se ha creado el sistema con éxito.");
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
            System.out.println("El usuario ha iniciado sesión correctamente.");
        }
        else{
            System.out.println("No existe el usuario ingresado.");
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
        var drive_minuscula = actual.get(0).toLowerCase();
        ruta1.rutaSTRING = drive_minuscula + ":/"; //con get0 agarra solo la letra
        var rutasderutas =
                ruta.stream()
                        .filter(rutas->rutas.rutaSTRING.equals(actual.get(0).toLowerCase() + ":/"))
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
        var tamano = ruta.size()-1;
        var carpeta1 = new Chapter(chaptername);
        var currentChapters =
                ruta.get(tamano).carpeta.stream()
                        .map(Chapter::getNombre)
                        .collect(Collectors.toList());
        if (!currentChapters.contains(chaptername)) {


            rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING; //pq cuando agrego adops se devuelve a c?
            rutaruta.carpeta.addAll(ruta.get(tamano).carpeta);
            rutaruta.carpeta.add(carpeta1);//agrega la carpeta a las carpetas, quiero que se agregue a las que habian antes
            rutaruta.usuariocarpeta = getLogueados().get(0);
            ruta.add(rutaruta);
        }
    }

    public void cd (String camino_seleccionado){
        var rutaruta = new Path();
        var tamano = ruta.size()-1;
        var currentChapters =
                ruta.get(tamano).getCarpeta().stream()
                        .map(Chapter::getNombre)
                        .collect(Collectors.toList());

        //otros if de los comodines LSITOS
        if (camino_seleccionado == "././././" || camino_seleccionado == "." || camino_seleccionado == "./"){
            rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
            rutaruta.usuariocarpeta = getLogueados().get(0);
            rutaruta.carpeta = ruta.get(tamano).carpeta;
            //agregar archivos, rutaruta.
            ruta.add(rutaruta);
        }
        //caso normal
        if (currentChapters.contains(camino_seleccionado)){
            rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING + camino_seleccionado + "/";
            rutaruta.usuariocarpeta = getLogueados().get(0);
            //AGREGAR ARCHIVOS
            ruta.add(rutaruta);



        }

        //caso de cuando quiere devolverse a la raiz recupera las cosas de la raiz
        if(camino_seleccionado == "/") { //regresa a la raiz de la unidad
            String[] raiz_unidad = ruta.get(tamano).rutaSTRING.split("/");
            var raiz_unidad1 = raiz_unidad[0];
            //hacer if que si raiz_ unidad0 = actual con / obtenga las carpetas o algo asi
            var filtro_raicesUnidad =
                    ruta.stream()
                            .filter(rutas -> rutas.rutaSTRING.equals(actual.get(0).toLowerCase() + ":/")) //rutas=creado aqui
                            .collect(Collectors.toList());

            if (filtro_raicesUnidad.size() > 1) { //recupera y asigna luego del filtro
                var tamano2 = filtro_raicesUnidad.size() - 1;
                rutaruta.carpeta.addAll(filtro_raicesUnidad.get(tamano2).carpeta);
                rutaruta.rutaSTRING = raiz_unidad1 + "/";
                rutaruta.usuariocarpeta = getLogueados().get(0);
                ruta.add(rutaruta);
            }
        }

            //caso de regresar a la anterior

        if(camino_seleccionado == "..") {
            var borrar_ultimo = ruta.get(tamano).rutaSTRING.split("/"); //hace lista de string[]
            var largototal = borrar_ultimo.length - 1;
            String[] copiaderutasinultimoelem = Arrays.copyOf(borrar_ultimo, largototal);
            String nuevaruta = String.join("/", copiaderutasinultimoelem);
            String nuevarutaReArmada = nuevaruta + "/";

            var filtro_buscandoRutas =
                    ruta.stream()
                            .filter(rutas -> rutas.rutaSTRING.equals(nuevarutaReArmada))
                            .collect(Collectors.toList());
            if (filtro_buscandoRutas.size() >= 1) {
                var tamano3 = filtro_buscandoRutas.size() - 1;
                rutaruta.rutaSTRING = nuevarutaReArmada;
                rutaruta.carpeta.addAll((filtro_buscandoRutas.get(tamano3).carpeta));
                rutaruta.usuariocarpeta = getLogueados().get(0);
                ruta.add(rutaruta);
            }
            else {
                rutaruta.rutaSTRING = nuevarutaReArmada;
                rutaruta.carpeta.addAll((filtro_buscandoRutas.get(0).carpeta));
                rutaruta.usuariocarpeta = getLogueados().get(0);
                ruta.add(rutaruta);
            }
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

