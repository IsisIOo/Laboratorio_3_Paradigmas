package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class System_21168603_ioyanedelalvarez implements Interfaz_system_21168603_ioyanedel {
    //esta cosa es sistema
    private String nombre;
    private Date fechaCreacrion;
    private List<Drive_21168603_ioyanedelalvarez> drives;
    private List<Usuario_21168603_ioyanedelalvarez> usuarios;
    private List<String> logueados;
    private List<String> actual;

    //List<Chapter> carpeta; //lo estoy usando en path

    List<Path_21168603_ioyanedelalvarez> ruta;



    //RF3 CONSTRUCTOR DE FILESYSTEM
    //Dom: nombre
    //Rec: filesystem

    /**
     * FUNCION 1 TDA system - constructor  Método constructor de un sistema. Deja registro de la fecha de creación.
     * @param nombre nombre del sistema
     */
    public System_21168603_ioyanedelalvarez(String nombre) {
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

    /**
     * Funcion 2 ADD-DRIVE  Método que permite añadir una unidad a un sistema. La letra de la unidad debe ser única.
     * @param letter letra del drive
     * @param name nombre del drive
     * @param capacity capcidad del dirve
     */
    public void addDrive(String letter, String name, int capacity) {
        var Drive = new Drive_21168603_ioyanedelalvarez(letter, name, capacity);
        List<String> currentLetters = new ArrayList<>();
        for (Drive_21168603_ioyanedelalvarez disco : drives) {
            currentLetters.add(disco.getLetter());
        }
        if (!currentLetters.contains(letter)) {
            drives.add(Drive);
        }

    }

    //RF5 TDA SYSTEM - REGISTER
    //Dom: userName (String)
    //no puedo tener usuarios repetidos

    /**
     * FUNCION 3 REGISTER  Método que permite registrar un nuevo usuario al sistema. El nombre de usuario es único y no puede ser duplicado.
     * @param nombre NOMBRE DEL USUARIO A REGISTRAR
     */
    public void register(String nombre) {
        var user = new Usuario_21168603_ioyanedelalvarez(nombre);
        var currentUsers =
                usuarios.stream()
                        .map(Usuario_21168603_ioyanedelalvarez::getNombre)
                        .collect(Collectors.toList());
        if (!currentUsers.contains(nombre)) {
            usuarios.add(user);
        }
    }

    /**
     * Funcion que retorna el usuario logueado en el sistema
     * @return usuario logueado
     */
    public List<String> getLogueados() {
        return logueados;
    }

    /**
     * FUNCION 4 LOGIN Método que permite iniciar sesión con un usuario del sistema, solo si éste existe.
     * @param nombre nombre del usuario a loguear
     */
    public void login(String nombre) {
        var currentUsers =
                usuarios.stream()
                        .map(Usuario_21168603_ioyanedelalvarez::getNombre)
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

    /**
     * FUNCION 5 LOGOUT Método que permite cerrar la sesión de un usuario en el sistema.
     */

    public void logout (){

        logueados.clear();
    }


    /**
     * FUNCION 6 SWITCHDRIVE Permite fijar la unidad en la que el usuario realizará acciones.
     * El método solo debe funcionar cuando hay un usuario con sesión iniciada en el sistema a partir del método de login.
     * @param letter letra del drive al que se va a cambiar
     */
    public void switchDrive(String letter){
        var currentLetters =
                drives.stream()
                        .map(Drive_21168603_ioyanedelalvarez::getLetter)
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
        var ruta1 = new Path_21168603_ioyanedelalvarez();
        var tamano = ruta.size()-1;
        if(tamano > 1){

            var currentChapters =
                    ruta.get(tamano).getCarpeta().stream()
                            .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                            .collect(Collectors.toList());
            ruta1.setUsuariocarpeta((getLogueados().get(0))); //obtiene solo el string
            var drive_minuscula = actual.get(0).toLowerCase();
            ruta1.setRutaSTRING(drive_minuscula + ":/");//con get0 agarra solo la letra
            var rutasderutas =
                    ruta.stream()
                            .filter(rutas->rutas.getRutaSTRING().equals(actual.get(0).toLowerCase() + ":/"))
                            .collect(Collectors.toList());

            if(rutasderutas.size()>1){
                var tamano2 = rutasderutas.size()-1;
                ruta1.getCarpeta().addAll(rutasderutas.get(tamano2).getCarpeta());
            }

            //ruta1.carpeta= ;
            ruta.add(ruta1);
        }
        else{
            ruta1.setRutaSTRING(actual.get(0).toLowerCase() + ":/");
            ruta1.setUsuariocarpeta(getLogueados().get(0));
            //var carpetas = new ArrayList<>();
            //var archivos = new ArrayList<>();
            //ruta1.carpeta = carpetas;
            ruta.add(ruta1);
        }



    }

    //RF9 TDA SYSTEM - mkDir
    //Dom: userName (String)
    //no puedo tener mas de un drive

    /**
     * FUNCION 7 MKDIR Método que permite crear directorio dentro de una unidad a partir del nombre especificado.
     * Internamente el método añade datos relativos a usuario creador, fecha de creación, fecha de última modificación y
     * atributos de seguridad como los señalados en el enunciado general
     * @param chaptername nombre de la carpeta que se creará
     */
    public void mkdir(String chaptername){
        var rutaruta = new Path_21168603_ioyanedelalvarez();
        var tamano = ruta.size()-1;
        var carpeta1 = new Chapter_21168603_ioyanedelalvarez(chaptername);
        var currentChapters =
                ruta.get(tamano).getCarpeta().stream()
                        .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                        .collect(Collectors.toList());
        if (!currentChapters.contains(chaptername) && !logueados.isEmpty()) {
            rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
            rutaruta.getCarpeta().addAll(ruta.get(tamano).getCarpeta());
            rutaruta.getCarpeta().add(carpeta1);//agrega la carpeta a las carpetas, quiero que se agregue a las que habian antes
            rutaruta.getArchivo().addAll(ruta.get(tamano).getArchivo());
            rutaruta.setUsuariocarpeta(getLogueados().get(0));
            ruta.add(rutaruta);
        }
    }

    /**
     * FUNCION 8 CD Método que permite cambiar la ruta (path) donde se realizarán las próximas operaciones. cd permite cambiarse a un directorio especificado a partir de la ruta señalada en un String. Además,
     * contará con los comodines especiales: “.”, “..” y “/” (se usa slash en lugar de backslash) que permitirán referirse a la carpeta actual,
     * regresar a la carpeta del nivel anterior siguiendo la ruta actual del usuario y volver a la raíz de la unidad respectivamente.
     * @param camino_seleccionado carpeta seleccionada para avanzar
     */
    public void cd (String camino_seleccionado){
        var rutaruta = new Path_21168603_ioyanedelalvarez();
        var tamano = ruta.size()-1;
        var currentChapters =
                ruta.get(tamano).getCarpeta().stream()
                        .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                        .collect(Collectors.toList());

        //otros if de los comodines LSITOS
        if ((camino_seleccionado == "././././" || camino_seleccionado == "." || camino_seleccionado == "./") && !logueados.isEmpty()){
            rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
            rutaruta.setUsuariocarpeta(getLogueados().get(0));
            rutaruta.setCarpeta(ruta.get(tamano).getCarpeta());
            rutaruta.setArchivo(ruta.get(tamano).getArchivo());
            ruta.add(rutaruta);
        }

        //caso normal
        if (currentChapters.contains(camino_seleccionado) && !logueados.isEmpty()){
            //AGREGAR ARCHIVOS
            var filtro_buscandoRutasNORMAL =
                    ruta.stream()
                            .filter(rutas -> rutas.getRutaSTRING().equals(ruta.get(tamano).getRutaSTRING() + camino_seleccionado + "/"))
                            .collect(Collectors.toList());
            if (filtro_buscandoRutasNORMAL.size() >= 1 && !logueados.isEmpty()) {
                var tamano3 = filtro_buscandoRutasNORMAL.size() - 1;
                rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING() + camino_seleccionado + "/");
                rutaruta.getArchivo().addAll((filtro_buscandoRutasNORMAL.get(tamano3).getArchivo()));
                rutaruta.getCarpeta().addAll((filtro_buscandoRutasNORMAL.get(tamano3).getCarpeta()));
                rutaruta.setUsuariocarpeta(getLogueados().get(0));
                ruta.add(rutaruta);
            }
            else{
                rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING() + camino_seleccionado + "/");
                rutaruta.setUsuariocarpeta(getLogueados().get(0));
                ruta.add(rutaruta);
            }
        }

//caso /.folder1
        if(camino_seleccionado.contains("./") && !logueados.isEmpty()) {
            String[] titulo_texto_sinbarra = camino_seleccionado.split("./"); //quita el ./
            var tituloreal = titulo_texto_sinbarra[1]; //obtiene el titulo string sin ./ desde la lista
            String total = String.join("", tituloreal); //convierte la lista en string

            if (currentChapters.contains(total) && !logueados.isEmpty()) { //busca el titulo string
                var filtro_buscandoRutasBARRA =
                        ruta.stream()
                                .filter(rutas -> rutas.getRutaSTRING().equals(ruta.get(tamano).getRutaSTRING() + total + "/"))
                                .collect(Collectors.toList());
                if (filtro_buscandoRutasBARRA.size() >= 1 && !logueados.isEmpty()) {
                    var tamano3 = filtro_buscandoRutasBARRA.size() - 1;
                    rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING() + total + "/");
                    rutaruta.getArchivo().addAll((filtro_buscandoRutasBARRA.get(tamano3).getArchivo()));
                    rutaruta.getCarpeta().addAll((filtro_buscandoRutasBARRA.get(tamano3).getCarpeta()));
                    rutaruta.setUsuariocarpeta(getLogueados().get(0));
                    ruta.add(rutaruta);
                }
            }
        }

        //caso de cuando quiere devolverse a la raiz recupera las cosas de la raiz
        if(camino_seleccionado == "/" && !logueados.isEmpty()) { //regresa a la raiz de la unidad
            String[] raiz_unidad = ruta.get(tamano).getRutaSTRING().split("/");
            var raiz_unidad1 = raiz_unidad[0];
            //hacer if que si raiz_ unidad0 = actual con / obtenga las carpetas o algo asi
            var filtro_raicesUnidad =
                    ruta.stream()
                            .filter(rutas -> rutas.getRutaSTRING().equals(actual.get(0).toLowerCase() + ":/")) //rutas=creado aqui
                            .collect(Collectors.toList());

            if (filtro_raicesUnidad.size() > 1 && !logueados.isEmpty()) { //recupera y asigna luego del filtro
                var tamano2 = filtro_raicesUnidad.size() - 1;
                rutaruta.getCarpeta().addAll(filtro_raicesUnidad.get(tamano2).getCarpeta());
                rutaruta.setRutaSTRING(raiz_unidad1 + "/");
                rutaruta.setUsuariocarpeta(getLogueados().get(0));
                ruta.add(rutaruta);
            }
        }

            //caso de regresar a la anterior

        if(camino_seleccionado == ".." && !logueados.isEmpty()) {
            var borrar_ultimo = ruta.get(tamano).getRutaSTRING().split("/"); //hace lista de string[]
            var largototal = borrar_ultimo.length - 1;
            String[] copiaderutasinultimoelem = Arrays.copyOf(borrar_ultimo, largototal);
            String nuevaruta = String.join("/", copiaderutasinultimoelem);
            String nuevarutaReArmada = nuevaruta + "/";

            var filtro_buscandoRutas =
                    ruta.stream()
                            .filter(rutas -> rutas.getRutaSTRING().equals(nuevarutaReArmada))
                            .collect(Collectors.toList());
            if (filtro_buscandoRutas.size() >= 1 && !logueados.isEmpty()) {
                var tamano3 = filtro_buscandoRutas.size() - 1;
                rutaruta.setRutaSTRING(nuevarutaReArmada);
                rutaruta.getArchivo().addAll((filtro_buscandoRutas.get(tamano3).getArchivo()));
                rutaruta.getCarpeta().addAll((filtro_buscandoRutas.get(tamano3).getCarpeta()));
                rutaruta.setUsuariocarpeta(getLogueados().get(0));
                ruta.add(rutaruta);

            }
            else {
            System.out.println("Ya ha llegado a la raiz de la unidad");
            }
        }
    }

    //recopila datos de file y cuando recupile los junte coon var y que entre

    public void crea_file(){
        Scanner extension_texto = new Scanner(System.in);
        System.out.println("------Elija el tipo de archivo que desea crear------");
        System.out.println("1. Archivo de texto.");
        System.out.println("2. Documento.");
        System.out.println("3. Codigo fuente.");
        int archivo_elegido = extension_texto.nextInt(); //cuando opcion elegida es integer
        String tipode1 = "";

        switch (archivo_elegido){
            case 1:
                //Scanner datos = new Scanner(System.in);
                //System.out.print("");
                //System.out.print("Ingrese el nombre del drive: ");
                //System.out.print("Ingrese el almacenamiento del drive: ");
                //int txt = datos.nextInt();
                //sistema.addDrive(letra_drive, nombre_drive, capacidad);
                tipode1 = ".txt";
                break;

            case 2:
                Scanner documentos = new Scanner(System.in);
                System.out.print("---Seleccione que tipo de documento que desea---\n");
                System.out.print("1. .docx");
                System.out.print("\n2. .pdf");
                int tipoDoc = documentos.nextInt();
                //sistema.addDrive(letra_drive, nombre_drive, capacidad);
                if(tipoDoc == 1){
                    tipode1 = ".docx";
                }
                else if(tipoDoc == 2){
                    tipode1 = ".pdf";
                }
                else{
                    System.out.println("Esa opción no está disponible.");
                    //agregar que se reinicie la pregunta
                }
                break;

            case 3:
                Scanner codfuente = new Scanner(System.in);
                System.out.print("---Seleccione que tipo de codigo fuente que desea---\n");
                System.out.print("1. .python");
                System.out.print("\n2. .java");
                System.out.print("\n3. .pl");
                int tipoCodfuente = codfuente.nextInt();
                if(tipoCodfuente == 1){
                    tipode1 = ".python";
                }
                else if(tipoCodfuente == 2){
                    tipode1 = ".java";
                }
                else if(tipoCodfuente == 3){
                    tipode1 = ".pl";
                }
                else{
                    System.out.println("Esa opción no está disponible");
                }
                break;}

        Scanner titulo_Archivo = new Scanner(System.in);
        System.out.print("Ingrese el titulo de su archivo:");
        String nombre_de_archivo =titulo_Archivo.nextLine();

        Scanner contenido_archivo = new Scanner(System.in);
        System.out.print("Ingrese el contenido de su archivo:");
        String content_de_archivo =contenido_archivo.nextLine();

        Scanner seguridad = new Scanner(System.in);
        System.out.println("¿Desea que su archivo esté oculto?");
        System.out.println("1. Si.");
        System.out.println("2. No.");
        int tiposeg = seguridad.nextInt();
        String seg1="";
        if(tiposeg == 1){
            seg1 = "oculto";
        }
        if(tiposeg == 2){
            seg1 = "";
        }
        else{
            System.out.println("Esa opción no está disponible.");
        }

        //int oculto = seguridad.nextInt(); //cuando opcion elegida es integer

        Scanner lectura = new Scanner(System.in);
        System.out.println("¿Desea que su archivo sea de solo lectura?");
        System.out.println("1. Si.");
        System.out.println("2. No.");
        int lecture = lectura.nextInt();
        String seg2 = "";
        if (lecture == 1){
             seg2 = "solo lectura";
        }
        else if(lecture ==2){
             seg2 = "";
        }
        else{
            System.out.println("Esa opción no está disponible.");
        }

        var newFile = new File_21168603_ioyanedelalvarez(nombre_de_archivo, tipode1, content_de_archivo, seg2, seg1);
        addFile(newFile);
    }

    public void crea_fileCaso2(String content_de_archivo, String seg1, String seg2){
        Scanner extension_texto = new Scanner(System.in);
        System.out.println("------Elija el tipo de archivo que desea crear------");
        System.out.println("1. Archivo de texto.");
        System.out.println("2. Documento.");
        System.out.println("3. Codigo fuente.");
        int archivo_elegido = extension_texto.nextInt(); //cuando opcion elegida es integer
        String tipode1 = "";

        switch (archivo_elegido){
            case 1:
                //Scanner datos = new Scanner(System.in);
                //System.out.print("");
                //System.out.print("Ingrese el nombre del drive: ");
                //System.out.print("Ingrese el almacenamiento del drive: ");
                //int txt = datos.nextInt();
                //sistema.addDrive(letra_drive, nombre_drive, capacidad);
                tipode1 = ".txt";
                break;

            case 2:
                Scanner documentos = new Scanner(System.in);
                System.out.print("---Seleccione que tipo de documento que desea---\n");
                System.out.print("1. .docx");
                System.out.print("\n2. .pdf");
                int tipoDoc = documentos.nextInt();
                //sistema.addDrive(letra_drive, nombre_drive, capacidad);
                if(tipoDoc ==1){
                    tipode1 = ".docx";
                }
                else if(tipoDoc ==2){
                    tipode1 = ".pdf";
                }
                else{
                    System.out.println("Esa opción no está disponible.");
                }
                break;

            case 3:
                Scanner codfuente = new Scanner(System.in);
                System.out.print("---Seleccione que tipo de codigo fuente que desea---\n");
                System.out.print("1. .python");
                System.out.print("\n2. .java");
                System.out.print("\n3. .pl");
                int tipoCodfuente = codfuente.nextInt();
                //sistema.addDrive(letra_drive, nombre_drive, capacidad);
                if(tipoCodfuente == 1){
                    tipode1 = ".python";
                }
                else if(tipoCodfuente == 2){
                    tipode1 = ".java";
                }
                else if(tipoCodfuente ==3 ){
                    tipode1 = ".pl";
                }
                else{
                    System.out.println("Esa opción no está disponible.");
                }
                break;
        }

        Scanner titulo_Archivo = new Scanner(System.in);
        System.out.print("Ingrese el titulo de su archivo:");
        String nombre_de_archivo =titulo_Archivo.nextLine();
        var newFile = new File_21168603_ioyanedelalvarez(nombre_de_archivo, tipode1, content_de_archivo, seg2, seg1);
        addFile(newFile);
    }


    /**
     * FUNCION 9 ADDFILE Método que permite añadir un archivo en la ruta actual.
     * @param archivo DATOS DEL ARCHIVO PARA AGREGAR
     */
    public void addFile(File_21168603_ioyanedelalvarez archivo){
        var lecturas = archivo.getAtributo_lect();
        var seguridad = archivo.getAtributo_Seg();
        var contenidos= archivo.getContenido();
        var rutaruta = new Path_21168603_ioyanedelalvarez();
        var tamano = ruta.size()-1;
        var currentFiles =
                ruta.get(tamano).getArchivo().stream()
                        .map(File_21168603_ioyanedelalvarez::getNombre)
                        .collect(Collectors.toList());
        if (!currentFiles.contains(archivo.getNombre()) && !logueados.isEmpty() ){
            var archivos_actuales = getRuta().get(tamano).getArchivo();
            rutaruta.getArchivo().add(archivo);
            rutaruta.getArchivo().addAll(archivos_actuales);
            rutaruta.setCarpeta(ruta.get(tamano).getCarpeta());
            rutaruta.setUsuariocarpeta(getLogueados().get(0));
            rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
            //AGREGAR ARCHIVOS
            ruta.add(rutaruta);
        }
        else{
            System.out.println("ya existe un archivo con ese Titulo, ingrese otro titulo y extensión.\n");
            crea_fileCaso2(contenidos, seguridad, lecturas);

        }
    }

    /**
     * FUNCION 10 DEL Método para eliminar un archivo o varios archivos en base a un patrón determinado.
     * Esta versión también puede eliminar una carpeta completa con todos sus subdirectorios. El contenido eliminado se va a la papelera.
     * @param path Cosa a borrar YA SEA ARCHIVO O CARPETA
     */
    public void del(String path){

        //caso archivo
        //if(path.contains('.')) {
            var rutaruta = new Path_21168603_ioyanedelalvarez();
            var tamano = ruta.size() - 1;
            var currentFiles =
                    ruta.get(tamano).getArchivo().stream()
                            .map(File_21168603_ioyanedelalvarez::getNombre)
                            .collect(Collectors.toList());

            if (currentFiles.contains(path) && !logueados.isEmpty()) {
                var filtro_buscandoTITULOSRutas =
                        ruta.get(tamano).getArchivo().stream()
                                .filter(rutas -> !rutas.getNombre().equals(path))
                                .collect(Collectors.toList());

                rutaruta.setCarpeta(ruta.get(tamano).getCarpeta());
                rutaruta.setUsuariocarpeta(getLogueados().get(0));
                rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                rutaruta.setArchivo(filtro_buscandoTITULOSRutas);
                ruta.add(rutaruta);
            }

            //caso extensión
            /*if(!path.isEmpty() && path.contains("."))  {
                var filtro_buscandoEXTENSIONRutas =
                        ruta.get(tamano).archivo.stream()
                                .filter(rutas -> !rutas.getExtension().equals(path))
                                .collect(Collectors.toList());
                rutaruta.carpeta = ruta.get(tamano).carpeta;
                rutaruta.usuariocarpeta = getLogueados().get(0);
                rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
                rutaruta.archivo = filtro_buscandoEXTENSIONRutas;
                ruta.add(rutaruta);

            }*/
            if((path == "." || path == "*") && !logueados.isEmpty()){
                rutaruta.setCarpeta(ruta.get(tamano).getCarpeta());
                rutaruta.setUsuariocarpeta(getLogueados().get(0));
                rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                rutaruta.getArchivo().clear();
                ruta.add(rutaruta);
            }

            String[] titulo_separado = path.split("\\.");
            var largo = titulo_separado.length;
            var tituloTitulo = titulo_separado[0];


            //caso de titulo.extension
            if(largo == 2 && !tituloTitulo.equals("") && !logueados.isEmpty()) {
                var extensionTitulo = titulo_separado[1];
                if (currentFiles.contains(tituloTitulo)) {
                    var filtro_buscandoTITULOSRutas =
                            ruta.get(tamano).getArchivo().stream()
                                    .filter(rutas -> !rutas.getNombre().equals(tituloTitulo) && !rutas.getExtension().equals(extensionTitulo))
                                    .collect(Collectors.toList());
                    rutaruta.setCarpeta(ruta.get(tamano).getCarpeta());
                    rutaruta.setUsuariocarpeta(getLogueados().get(0));
                    rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                    rutaruta.setArchivo(filtro_buscandoTITULOSRutas);
                    ruta.add(rutaruta);
                }
            }

            //caso extension sola
            if(largo == 2 && tituloTitulo.equals("") && !logueados.isEmpty()){
                var filtro_buscandoEXTENSIONRutas =
                        ruta.get(tamano).getArchivo().stream()
                                .filter(rutas -> !rutas.getExtension().equals(path))
                                .collect(Collectors.toList());
                rutaruta.setCarpeta(ruta.get(tamano).getCarpeta());
                rutaruta.setUsuariocarpeta(getLogueados().get(0));
                rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                rutaruta.setArchivo(filtro_buscandoEXTENSIONRutas);
                ruta.add(rutaruta);
            }

//caso carpeta simple
            var currentChapters =
                ruta.get(tamano).getCarpeta().stream()
                        .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                        .collect(Collectors.toList());

            if(currentChapters.contains(path) && !logueados.isEmpty()){
                var filtro_borrarUNAcarpeta =
                        ruta.get(tamano).getCarpeta().stream()
                                .filter((rutas -> !rutas.getNombre().equals(path)))
                                .collect(Collectors.toList());

                rutaruta.setRutaSTRING(ruta.get(tamano).getRutaSTRING()); //rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
                rutaruta.getCarpeta().addAll(ruta.get(tamano).getCarpeta());
                rutaruta.setCarpeta(filtro_borrarUNAcarpeta);
                rutaruta.setUsuariocarpeta(getLogueados().get(0));
                ruta.add(rutaruta);
            }

            //falta el caso de carpeta con archivos


            //else{
             //   System.out.println("No hay ningun archivo que cumpla esas condiciones.");
            //}

        }

    /**
     * FUNCION 10 COPY Método para copiar un archivo o carpeta desde una ruta origen a una ruta destino.
     * @param file archivo o carpeta para mover
     * @param target destino del archivo o carpeta
     */
    public void copy (String file, String target){
        //si es un archivo file podrá separarse por punto puesto que lo recibe como entrada como foo.txt, en cambio si recibe carpeta, solo será el nombre


        if(file.contains(".") && !logueados.isEmpty()){
            String[] file1 = file.split("\\.");
            var rutaruta = new Path_21168603_ioyanedelalvarez();
            var ruta1 = new Path_21168603_ioyanedelalvarez();
            //caso de mover a otro drive
            String[] existedrive1 = target.split(":/");
            String[] existedrive = target.split("/");
            var drivev= existedrive1[0];

            //conseguimos la penultima posición del registro de ruta
            var penultima_posicion = ruta.size() - 2;



            var currentDrivesletter = //obtiene las letras de todos los drives
                    drives.stream()
                            .map(Drive_21168603_ioyanedelalvarez::getLetter)
                            .collect(Collectors.toList());

            var driveamover = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                    ruta.stream()
                            .filter(rutas->rutas.getRutaSTRING().equals(drivev.toLowerCase() + ":" + "/"))
                            .collect(Collectors.toList());

            var tamano2 = driveamover.size()-1;
            var tamano = ruta.size()-1;

            String[] tituloyextension = file.split("\\.");
            var titulo = tituloyextension[0];//titulo del archivo
            var extension = tituloyextension[1]; //extension del archivo

                //Consigue los datos del archivo
            var obtenerarchivocosas =
                    ruta.get(tamano).getArchivo().stream()
                            .filter(File -> File.getNombre().equals(titulo) && File.getExtension().equals("." + extension))
                            .collect(Collectors.toList());

            //recupera los datos de la ruta de destino
            var recuperardatosderuta =
                    ruta.stream()
                            .filter(rutas->rutas.getRutaSTRING().equals(target.toLowerCase()))
                            .collect(Collectors.toList());

            //obtiene las carpetas del destino
            var currentChapters =
                    driveamover.get(tamano2).getCarpeta().stream()
                            .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                            .collect(Collectors.toList());

            //caso nunca abri esa ruta, por lo tanto debo saber si existe el drive letra y si existe la carpeta
            //cuando hay drive y una carpeta, ejemplo: "c:/folder1/"
            if(currentDrivesletter.contains(drivev) && existedrive.length>1){

                var carpetadestino = existedrive[1];


                //ruta1.carpeta.addAll(rutasderutas.get(tamano2).carpeta); //si d:/ contuene la carpeta de destino
                if(currentChapters.contains(carpetadestino)) {
                    //caso donde nunca se trabajo con d:/carpeta por lo tanto todos sus listas son vacias
                    if (recuperardatosderuta.isEmpty()) {
                        rutaruta.setRutaSTRING(target.toLowerCase());
                        rutaruta.getArchivo().addAll(obtenerarchivocosas);
                        //rutaruta.archivo.addAll(recuperardatosderuta.get(tamano2).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        //rutaruta.carpeta = new ArrayList<>();
                        ruta.add(rutaruta);
                        ruta.add(ruta.get(tamano));

                    }

                    else { //caso cuando existe una ruta en la que se trabajo el d:/carpeta
                        rutaruta.setRutaSTRING(target.toLowerCase());
                        rutaruta.getArchivo().addAll(obtenerarchivocosas);
                        rutaruta.getArchivo().addAll(recuperardatosderuta.get(tamano2).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        rutaruta.setCarpeta(recuperardatosderuta.get(tamano2).getCarpeta());
                        ruta.add(rutaruta);
                        ruta.add(ruta.get(tamano));
                    }

                }


     //caso donde quiero mover a d:/ sin carpeta
                else if(currentDrivesletter.contains(drivev) && existedrive.length==1){
                    rutaruta.setRutaSTRING(target.toLowerCase());
                    rutaruta.getArchivo().addAll(obtenerarchivocosas);
                    rutaruta.getArchivo().addAll(driveamover.get(tamano2).getArchivo());
                    rutaruta.setUsuariocarpeta(getLogueados().get(0));
                    rutaruta.setCarpeta(driveamover.get(tamano2).getCarpeta());
                    ruta.add(rutaruta);
                    ruta.add(ruta.get(tamano));
                }


                else{
                    if(recuperardatosderuta.size()>=1){
                        rutaruta.setRutaSTRING(target.toLowerCase());
                        rutaruta.getArchivo().addAll(obtenerarchivocosas);
                        rutaruta.getArchivo().addAll(recuperardatosderuta.get(tamano2).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        rutaruta.setCarpeta(recuperardatosderuta.get(tamano2).getCarpeta());
                        ruta.add(rutaruta);


                        rutaruta.setRutaSTRING(target.toLowerCase());
                        rutaruta.getArchivo().addAll(obtenerarchivocosas);
                        rutaruta.getArchivo().addAll(driveamover.get(tamano2).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        rutaruta.setCarpeta(driveamover.get(tamano2).getCarpeta());
                        ruta.add(rutaruta);
                        ruta.add(ruta.get(tamano));
                    }

                    else {
                        rutaruta.setRutaSTRING(target.toLowerCase());
                        rutaruta.getArchivo().addAll(obtenerarchivocosas);
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);


                        rutaruta.setRutaSTRING(target.toLowerCase());
                        rutaruta.getArchivo().addAll(obtenerarchivocosas);
                        rutaruta.getArchivo().addAll(driveamover.get(tamano2).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        rutaruta.setCarpeta(driveamover.get(tamano2).getCarpeta());
                        ruta.add(rutaruta);
                        ruta.add(ruta.get(tamano));
                    }
                }

            }

            else {
                rutaruta.setRutaSTRING(target.toLowerCase());
                rutaruta.getArchivo().addAll(obtenerarchivocosas);
                rutaruta.getArchivo().addAll(driveamover.get(tamano2).getArchivo());
                rutaruta.setUsuariocarpeta(getLogueados().get(0));
                rutaruta.setCarpeta(driveamover.get(tamano2).getCarpeta());
                ruta.add(rutaruta);
                ruta.add(ruta.get(tamano));
            }
        }

        else { //el largo del archivo a mover separado por / es ==0 (esto ocurre porque cuando muevo carpeta no tengo  .
                //agregar como recueprar los archivos  de la carpeta y agregarlos a los archivos existentes a de la carpeta de destino
                // a las carpetas de la ruta de destino hay que agregarle la carpeta que queremos mover
                var rutaruta = new Path_21168603_ioyanedelalvarez();
                String[] existedrive1 = target.split(":/");
                String[] existedrive = target.split("/");
                var drivev = existedrive1[0];

                var currentDrivesletter = //obtiene las letras de todos los drives
                        drives.stream()
                                .map(Drive_21168603_ioyanedelalvarez::getLetter)
                                .collect(Collectors.toList());
                var driveamover = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                        ruta.stream()
                                .filter(rutas -> rutas.getRutaSTRING().equals(drivev.toLowerCase() + ":" + "/"))
                                .collect(Collectors.toList());

                var tamano2 = driveamover.size() - 1;
                var tamano = ruta.size() - 1;

                var currentChaptersLUGARDESTINO =
                        driveamover.get(tamano2).getCarpeta().stream()
                                .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                                .collect(Collectors.toList());

                var currentChaptersLUGARORIGEN =
                        ruta.get(tamano).getCarpeta().stream()
                                .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                                .collect(Collectors.toList());

                var obteneractualizacioncarpetamover = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                        ruta.stream()
                                .filter(rutas -> rutas.getRutaSTRING().contains(file))
                                .collect(Collectors.toList());

                var tamano22 = obteneractualizacioncarpetamover.size() - 1;

                var obteneractualizacioncarpetaRECIBIR = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                        ruta.stream()
                                .filter(rutas -> rutas.getRutaSTRING().equals(target))
                                .collect(Collectors.toList());
                var tamano3 = obteneractualizacioncarpetaRECIBIR.size() - 1;

                var obtenerarchivocosas = //obtiene la carpeta que tenga el mismo nombre que file para agregarla a su destino
                        ruta.get(tamano).getCarpeta().stream() //no funciona porque en get tamano no existe esa carpeta
                                .filter(Chapter -> Chapter.getNombre().equals(file))
                                .collect(Collectors.toList());

            var penultima_posicion = ruta.size() - 2;

    //se mueve a una carpeta lenght >1 se mueve a un drive (d:/) si el largo es 1
                if (currentDrivesletter.contains(drivev) && existedrive.length > 1) {

                    var carpetadestino = existedrive[1];


                    //ruta1.carpeta.addAll(rutasderutas.get(tamano2).carpeta); //si d:/ contuene la carpeta de destino y si la ultima ruta abierta contiene la carpeta a mover
                    if (currentChaptersLUGARDESTINO.contains(carpetadestino) && (ruta.get(tamano).getCarpeta().contains(file) || ruta.get(tamano).getRutaSTRING().contains(file)) && !obteneractualizacioncarpetaRECIBIR.isEmpty()) {
                        var obtenerdatoscarpeta = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                                ruta.stream()
                                        .filter(rutas -> rutas.getRutaSTRING().equals(actual + ":/" + file.toLowerCase() + "/"))
                                        .collect(Collectors.toList());

                        rutaruta.setRutaSTRING(target.toLowerCase()+ file + "/");
                        rutaruta.getCarpeta().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getCarpeta());
                        rutaruta.getCarpeta().addAll(obtenerarchivocosas);
                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getArchivo());
                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);
                        ruta.add(ruta.get(tamano));


                    }
                    else if (obteneractualizacioncarpetaRECIBIR.isEmpty() && obteneractualizacioncarpetamover.isEmpty()) {
                        rutaruta.setRutaSTRING(target.toLowerCase()+ file + "/");
                        rutaruta.getCarpeta().addAll(obtenerarchivocosas);
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);
                        ruta.add(ruta.get(tamano));


                    }
                    else if (obteneractualizacioncarpetaRECIBIR.isEmpty() && !obteneractualizacioncarpetamover.isEmpty()) { //funciona
                        var recuperardatosderuta =
                                ruta.stream()
                                        .filter(rutas->rutas.getRutaSTRING().equals(target.toLowerCase()))
                                        .collect(Collectors.toList());

                        var tamano5 = recuperardatosderuta.size()-1;



                       /* rutaruta.rutaSTRING = recuperardatosderuta.get(tamano5).rutaSTRING;
                        rutaruta.carpeta.addAll(obtenerarchivocosas);
                        rutaruta.carpeta.addAll(recuperardatosderuta.get(tamano5).getCarpeta());
                        rutaruta.archivo.addAll(recuperardatosderuta.get(tamano5).getArchivo());
                        rutaruta.usuariocarpeta = (recuperardatosderuta.get(tamano5).getUsuariocarpeta());
                        ruta.add(rutaruta);*/


                        rutaruta.setRutaSTRING(target.toLowerCase()+ file + "/");
                        rutaruta.getCarpeta().addAll(obteneractualizacioncarpetamover.get(tamano22).getCarpeta());
                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano22).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);
                        ruta.add(ruta.get(tamano));

                    }
                    else {
                        if (ruta.get(tamano).getCarpeta().contains(file) && !obteneractualizacioncarpetamover.isEmpty()){
                            var obtenerdatoscarpeta = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                                    ruta.stream()
                                            .filter(rutas -> rutas.getRutaSTRING().equals(actual + ":/" + file.toLowerCase() + "/"))
                                            .collect(Collectors.toList());

                            rutaruta.setRutaSTRING(target.toLowerCase()+ file + "/");
                            rutaruta.getCarpeta().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getCarpeta()); //no funciona porque está vacia
                            rutaruta.getCarpeta().addAll(obtenerarchivocosas);
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getArchivo());
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano).getArchivo());
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            ruta.add(rutaruta);
                            ruta.add(ruta.get(tamano));

                        }

                        if((ruta.get(tamano).getRutaSTRING().contains(file) && !obteneractualizacioncarpetamover.isEmpty()) && !obteneractualizacioncarpetaRECIBIR.isEmpty()) {
                            var borrar_ultimo = ruta.get(tamano).getRutaSTRING().split("/"); //hace lista de string[]
                            var largototal = borrar_ultimo.length - 1;
                            String[] copiaderutasinultimoelem = Arrays.copyOf(borrar_ultimo, largototal);
                            String nuevaruta = String.join("/", copiaderutasinultimoelem);
                            String nuevarutaReArmada = nuevaruta + "/";

                            var filtro_buscandoRutas =
                                    ruta.stream()
                                            .filter(rutas -> rutas.getRutaSTRING().equals(nuevarutaReArmada)) //buscan las rutas que son iguales a la que se le quito el ultimo elemento
                                            .collect(Collectors.toList());
                            var tamano4 = filtro_buscandoRutas.size()-1;

                            rutaruta.setRutaSTRING(target.toLowerCase()+ file + "/");
                            rutaruta.getCarpeta().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getCarpeta()); //no funciona porque está vacia
                            rutaruta.getCarpeta().addAll(filtro_buscandoRutas.get(tamano4).getCarpeta());
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getArchivo());
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano).getArchivo());
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            ruta.add(rutaruta);
                            ruta.add(ruta.get(tamano));

                        }



                        else {
                            rutaruta.setRutaSTRING(target.toLowerCase()+ file + "/");
                            rutaruta.getCarpeta().addAll(obtenerarchivocosas);//problema con recuperar carpeta
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano22).getArchivo());
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            ruta.add(rutaruta);
                            ruta.add(ruta.get(tamano));

                        }
                    }


                }
                if (currentDrivesletter.contains(drivev) && existedrive.length == 1 && !logueados.isEmpty()){
                    if(!driveamover.isEmpty() && !logueados.isEmpty()){

                        rutaruta.setRutaSTRING(target.toLowerCase()+ file + "/");

                        rutaruta.getCarpeta().addAll(obtenerarchivocosas);//problema con recuperar carpeta
                        rutaruta.getCarpeta().addAll(driveamover.get(tamano2).getCarpeta());

                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano22).getArchivo());
                        rutaruta.getArchivo().addAll(driveamover.get(tamano2).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);
                        ruta.add(ruta.get(tamano));
                    }
                    else{

                        rutaruta.setRutaSTRING(target.toLowerCase()+ file + "/");
                        rutaruta.getCarpeta().addAll(obtenerarchivocosas);//problema con recuperar carpeta
                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano22).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);
                        ruta.add(ruta.get(tamano));

                    }
                }
            }
        }



























    /**
     *  FUNCION 11 MOVE Método para mover un archivo o carpeta desde una ruta origen a una ruta destino. La operación de mover elimina el contenido desde la ruta origen.
     * @param file archivo a mover
     * @param target lugar de destino
     */
    public void move(String file, String target) {
        if (!logueados.isEmpty()) {
            if (file.contains(".")) {
                String[] file1 = file.split("\\.");
                var rutaruta = new Path_21168603_ioyanedelalvarez();
                var ruta1 = new Path_21168603_ioyanedelalvarez();
                //caso de mover a otro drive
                String[] existedrive1 = target.split(":/");
                String[] existedrive = target.split("/");
                var drivev = existedrive1[0];

                //conseguimos la penultima posición del registro de ruta
                int penultima_posicion = ruta.size() - 2;


                var currentDrivesletter = //obtiene las letras de todos los drives
                        drives.stream()
                                .map(Drive_21168603_ioyanedelalvarez::getLetter)
                                .collect(Collectors.toList());

                var driveamover = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                        ruta.stream()
                                .filter(rutas -> rutas.getRutaSTRING().equals(drivev.toLowerCase() + ":" + "/"))
                                .collect(Collectors.toList());

                var tamano2 = driveamover.size() - 1;
                var tamano = ruta.size() - 1;

                String[] tituloyextension = file.split("\\.");
                var titulo = tituloyextension[0];//titulo del archivo
                var extension = tituloyextension[1]; //extension del archivo

                //Consigue los datos del archivo
                var obtenerarchivocosas =
                        ruta.get(tamano).getArchivo().stream()
                                .filter(File -> File.getNombre().equals(titulo) && File.getExtension().equals("." + extension))
                                .collect(Collectors.toList());

                //recupera los datos de la ruta de destino
                var recuperardatosderuta =
                        ruta.stream()
                                .filter(rutas -> rutas.getRutaSTRING().equals(target.toLowerCase()))
                                .collect(Collectors.toList());

                //obtiene las carpetas del destino
                var currentArchivosSINARCHIVOAMOVER = //archivos actuales sin el archivo que se movio
                        ruta.get(tamano).getArchivo().stream()
                                .filter(File -> !File.getNombre().equals(titulo) && !File.getExtension().equals("." + extension))
                                .collect(Collectors.toList());

                var currenChapterSINARCHIVOMOVER =
                        ruta.get(tamano).getCarpeta().stream()
                                .filter(Chapter -> !Chapter.getNombre().equals(file))
                                .collect(Collectors.toList());

                var currentChapters =
                        driveamover.get(tamano2).getCarpeta().stream()
                                .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                                .collect(Collectors.toList());

                var currentFiles =
                        driveamover.get(tamano2).getCarpeta().stream()
                                .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                                .collect(Collectors.toList());

                //caso nunca abri esa ruta, por lo tanto debo saber si existe el drive letra y si existe la carpeta
                //cuando hay drive y una carpeta, ejemplo: "c:/folder1/"
                if (currentDrivesletter.contains(drivev) && existedrive.length > 1) {

                    var carpetadestino = existedrive[1];


                    //ruta1.carpeta.addAll(rutasderutas.get(tamano2).carpeta); //si d:/ contuene la carpeta de destino
                    if (currentChapters.contains(carpetadestino)) {
                        //caso donde nunca se trabajo con d:/carpeta por lo tanto todos sus listas son vacias
                        if (recuperardatosderuta.isEmpty()) {
                            rutaruta.setRutaSTRING(target.toLowerCase());
                            rutaruta.getArchivo().addAll(obtenerarchivocosas);
                            //rutaruta.archivo.addAll(recuperardatosderuta.get(tamano2).getArchivo());
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            //rutaruta.carpeta = new ArrayList<>();

                            ruta.add(rutaruta);



                            ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                            ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                            ruta1.setArchivo(currentArchivosSINARCHIVOAMOVER);
                            ruta1.setCarpeta(ruta.get(tamano).getCarpeta());
                            ruta.add(ruta1);


                        } else { //caso cuando existe una ruta en la que se trabajo el d:/carpeta
                            rutaruta.setRutaSTRING(target.toLowerCase());
                            rutaruta.getArchivo().addAll(obtenerarchivocosas);
                            rutaruta.getArchivo().addAll(recuperardatosderuta.get(tamano2).getArchivo());
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            rutaruta.setCarpeta(recuperardatosderuta.get(tamano2).getCarpeta());
                            ruta.add(rutaruta);



                            ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                            ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                            ruta1.setArchivo(currentArchivosSINARCHIVOAMOVER);
                            ruta1.setCarpeta(ruta.get(tamano).getCarpeta());
                            ruta.add(ruta1);
                        }

                    }


                    //caso donde quiero mover a d:/ sin carpeta
                    else if (currentDrivesletter.contains(drivev) && existedrive.length == 1) {
                        rutaruta.setRutaSTRING(target.toLowerCase());
                        rutaruta.getArchivo().addAll(obtenerarchivocosas);
                        rutaruta.getArchivo().addAll(driveamover.get(tamano2).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        rutaruta.setCarpeta(driveamover.get(tamano2).getCarpeta());
                        ruta.add(rutaruta);


                        ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                        ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                        ruta1.setArchivo(currentArchivosSINARCHIVOAMOVER);
                        ruta1.setCarpeta(ruta.get(tamano).getCarpeta());
                        ruta.add(ruta1);
                    }
                    else { // se mueve a un d:/carpeta1/, osea, que puedo recuperar datos de esta de otra forma que a la que hice arriba

                        if(recuperardatosderuta.size()>=1){
                            rutaruta.setRutaSTRING(target.toLowerCase());
                            rutaruta.getArchivo().addAll(obtenerarchivocosas);
                            rutaruta.getArchivo().addAll(recuperardatosderuta.get(tamano2).getArchivo());
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            rutaruta.setCarpeta(recuperardatosderuta.get(tamano2).getCarpeta());
                            ruta.add(rutaruta);


                            ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                            ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                            ruta1.setArchivo(currentArchivosSINARCHIVOAMOVER);
                            ruta1.setCarpeta(ruta.get(tamano).getCarpeta());
                            ruta.add(ruta1);
                        }

                        else {
                            rutaruta.setRutaSTRING(target.toLowerCase());
                            rutaruta.getArchivo().addAll(obtenerarchivocosas);
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            ruta.add(rutaruta);


                            ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                            ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                            ruta1.setArchivo(currentArchivosSINARCHIVOAMOVER);
                            ruta1.setCarpeta(ruta.get(tamano).getCarpeta());
                            ruta.add(ruta1);
                        }
                    }

                }
                else {

                    rutaruta.setRutaSTRING(target.toLowerCase());
                    rutaruta.setArchivo(driveamover.get(tamano2).getArchivo());
                    rutaruta.setArchivo(obtenerarchivocosas);

                    rutaruta.setUsuariocarpeta(getLogueados().get(0));
                    rutaruta.setCarpeta(driveamover.get(tamano2).getCarpeta());
                    ruta.add(rutaruta);

                    ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                    ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                    ruta1.setArchivo(currentArchivosSINARCHIVOAMOVER);
                    ruta1.setCarpeta(ruta.get(tamano).getCarpeta());
                    ruta.add(ruta1);
                }
            }






            //cuando no contiene punto, o sea se mueve carpeta, por lo tanto hay que borrar las carpetas tambien
            else { //el largo del archivo a mover separado por / es ==0 (esto ocurre porque cuando muevo carpeta no tengo  .
                //agregar como recueprar los archivos  de la carpeta y agregarlos a los archivos existentes a de la carpeta de destino
                // a las carpetas de la ruta de destino hay que agregarle la carpeta que queremos mover
                var rutaruta = new Path_21168603_ioyanedelalvarez();
                var ruta1 = new Path_21168603_ioyanedelalvarez();

                String[] existedrive1 = target.split(":/");
                String[] existedrive = target.split("/");
                var drivev = existedrive1[0];

                var currentDrivesletter = //obtiene las letras de todos los drives
                        drives.stream()
                                .map(Drive_21168603_ioyanedelalvarez::getLetter)
                                .collect(Collectors.toList());
                var driveamover = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                        ruta.stream()
                                .filter(rutas -> rutas.getRutaSTRING().equals(drivev.toLowerCase() + ":" + "/"))
                                .collect(Collectors.toList());

                var tamano2 = driveamover.size() - 1;
                var tamano = ruta.size() - 1;

                var currentChaptersLUGARDESTINO =
                        driveamover.get(tamano2).getCarpeta().stream()
                                .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                                .collect(Collectors.toList());

                var currentChaptersLUGARORIGEN =
                        ruta.get(tamano).getCarpeta().stream()
                                .map(Chapter_21168603_ioyanedelalvarez::getNombre)
                                .collect(Collectors.toList());

                var obteneractualizacioncarpetamover = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                        ruta.stream()
                                .filter(rutas -> rutas.getRutaSTRING().contains(file))
                                .collect(Collectors.toList());

                var tamano22 = obteneractualizacioncarpetamover.size() - 1;

                var obteneractualizacioncarpetaRECIBIR = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                        ruta.stream()
                                .filter(rutas -> rutas.getRutaSTRING().equals(target))
                                .collect(Collectors.toList());

                var tamano3 = obteneractualizacioncarpetaRECIBIR.size() - 1;

                var obtenerarchivocosas = //obtiene la carpeta que tenga el mismo nombre que file para agregarla a su destino
                        ruta.get(tamano).getCarpeta().stream() //no funciona porque en get tamano no existe esa carpeta
                                .filter(Chapter -> Chapter.getNombre().equals(file))
                                .collect(Collectors.toList());


                var currenChapterSINcarpetaMOVER =
                        ruta.get(tamano).getCarpeta().stream()
                                .filter(Chapter -> !Chapter.getNombre().equals(file))
                                .collect(Collectors.toList());

                //se mueve a una carpeta lenght >1 se mueve a un drive (d:/) si el largo es 1
                if (currentDrivesletter.contains(drivev) && existedrive.length > 1) {

                    var carpetadestino = existedrive[1];


                    //ruta1.carpeta.addAll(rutasderutas.get(tamano2).carpeta); //si d:/ contuene la carpeta de destino y si la ultima ruta abierta contiene la carpeta a mover
                    if (currentChaptersLUGARDESTINO.contains(carpetadestino) && ruta.get(tamano).getCarpeta().contains(file)){
                        var obtenerdatoscarpeta = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                                ruta.stream()
                                        .filter(rutas -> rutas.getRutaSTRING().equals(actual + ":/" + file.toLowerCase() + "/"))
                                        .collect(Collectors.toList());

                        rutaruta.setRutaSTRING(target.toLowerCase() + file + "/");
                        rutaruta.getCarpeta().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getCarpeta());
                        rutaruta.getCarpeta().addAll(obtenerarchivocosas);
                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getArchivo());
                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);

                        ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                        ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                        ruta1.setCarpeta(currenChapterSINcarpetaMOVER);
                        ruta1.setArchivo(new ArrayList<>());
                        ruta.add(ruta1);

                    if(ruta.get(tamano).getRutaSTRING().contains(file) && !obteneractualizacioncarpetaRECIBIR.isEmpty()){

                        rutaruta.setRutaSTRING(target.toLowerCase() + file + "/");
                        rutaruta.getCarpeta().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getCarpeta());
                        rutaruta.getCarpeta().addAll(obtenerarchivocosas);
                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getArchivo());
                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);

                        ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                        ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                        ruta1.setCarpeta(currenChapterSINcarpetaMOVER);
                        ruta1.setArchivo(new ArrayList<>());
                        ruta.add(ruta1);



                    }






                    } else if (obteneractualizacioncarpetaRECIBIR.isEmpty() && obteneractualizacioncarpetamover.isEmpty()) {
                        rutaruta.setRutaSTRING(target.toLowerCase() + file + "/");
                        rutaruta.getCarpeta().addAll(obtenerarchivocosas);
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);

                        ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                        ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                        ruta1.setCarpeta(currenChapterSINcarpetaMOVER);
                        ruta1.setArchivo(new ArrayList<>());
                        ruta.add(ruta1);




                    } else {
                        if (ruta.get(tamano).getCarpeta().contains(file) && !obteneractualizacioncarpetamover.isEmpty()) {
                            var obtenerdatoscarpeta = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                                    ruta.stream()
                                            .filter(rutas -> rutas.getRutaSTRING().equals(actual + ":/" + file.toLowerCase() + "/"))
                                            .collect(Collectors.toList());

                            var borrar_ultimo = ruta.get(tamano).getRutaSTRING().split("/"); //hace lista de string[]
                            var largototal = borrar_ultimo.length - 1;
                            String[] copiaderutasinultimoelem = Arrays.copyOf(borrar_ultimo, largototal);
                            String nuevaruta = String.join("/", copiaderutasinultimoelem);
                            String nuevarutaReArmada = nuevaruta + "/";

                            var filtro_buscandoRutas =
                                    ruta.stream()
                                            .filter(rutas -> rutas.getRutaSTRING().equals(nuevarutaReArmada)) //buscan las rutas que son iguales a la que se le quito el ultimo elemento
                                            .collect(Collectors.toList());
                            var tamano4 = filtro_buscandoRutas.size() - 1;

                            var filtro_eliminandoderutarearmada =
                                    filtro_buscandoRutas.get(tamano4).getCarpeta().stream()
                                                    .filter(Chapter -> !Chapter.getNombre().equals(file))
                                                            .collect(Collectors.toList());

                            rutaruta.setRutaSTRING(target.toLowerCase() + file + "/");
                            rutaruta.getCarpeta().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getCarpeta()); //no funciona porque está vacia
                            rutaruta.getCarpeta().addAll(obtenerarchivocosas);
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getArchivo());
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano).getArchivo());
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            ruta.add(rutaruta);

                            ruta1.setRutaSTRING(nuevarutaReArmada);
                            ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                            ruta1.setCarpeta(filtro_eliminandoderutarearmada);
                            ruta1.setArchivo(new ArrayList<>());
                            ruta.add(ruta1);

                        }

                        if ((ruta.get(tamano).getRutaSTRING().contains(file) && !obteneractualizacioncarpetamover.isEmpty()) && !obteneractualizacioncarpetaRECIBIR.isEmpty()) {
                            var borrar_ultimo = ruta.get(tamano).getRutaSTRING().split("/"); //hace lista de string[]
                            var largototal = borrar_ultimo.length - 1;
                            String[] copiaderutasinultimoelem = Arrays.copyOf(borrar_ultimo, largototal);
                            String nuevaruta = String.join("/", copiaderutasinultimoelem);
                            String nuevarutaReArmada = nuevaruta + "/";

                            var filtro_buscandoRutas =
                                    ruta.stream()
                                            .filter(rutas -> rutas.getRutaSTRING().equals(nuevarutaReArmada)) //buscan las rutas que son iguales a la que se le quito el ultimo elemento
                                            .collect(Collectors.toList());
                            var tamano4 = filtro_buscandoRutas.size() - 1;

                            var filtro_eliminandoderutarearmada =
                                    filtro_buscandoRutas.get(tamano4).getCarpeta().stream()
                                            .filter(Chapter -> !Chapter.getNombre().equals(file))
                                            .collect(Collectors.toList());

                            rutaruta.setRutaSTRING(target.toLowerCase() + file + "/");
                            rutaruta.getCarpeta().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getCarpeta()); //no funciona porque está vacia
                            rutaruta.getCarpeta().addAll(filtro_buscandoRutas.get(tamano4).getCarpeta());
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetaRECIBIR.get(tamano3).getArchivo());
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano).getArchivo());
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            ruta.add(rutaruta);

                            ruta1.setRutaSTRING(nuevarutaReArmada);
                            ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                            ruta1.setCarpeta(filtro_eliminandoderutarearmada);
                            ruta1.setArchivo(new ArrayList<>());
                            ruta.add(ruta1);
                        }
                        else {
                            var borrar_ultimo = ruta.get(tamano).getRutaSTRING().split("/"); //hace lista de string[]
                            var largototal = borrar_ultimo.length - 1;
                            String[] copiaderutasinultimoelem = Arrays.copyOf(borrar_ultimo, largototal);
                            String nuevaruta = String.join("/", copiaderutasinultimoelem);
                            String nuevarutaReArmada = nuevaruta + "/";

                            var filtro_buscandoRutas =
                                    ruta.stream()
                                            .filter(rutas -> rutas.getRutaSTRING().equals(nuevarutaReArmada)) //buscan las rutas que son iguales a la que se le quito el ultimo elemento
                                            .collect(Collectors.toList());
                            var tamano4 = filtro_buscandoRutas.size() - 1;

                            var filtro_eliminandoderutarearmada =
                                    filtro_buscandoRutas.get(tamano4).getCarpeta().stream()
                                            .filter(Chapter -> !Chapter.getNombre().equals(file))
                                            .collect(Collectors.toList());

                            rutaruta.setRutaSTRING(target.toLowerCase() + file + "/");
                            rutaruta.getCarpeta().addAll(obteneractualizacioncarpetamover.get(tamano22).getCarpeta());//problema con recuperar carpeta
                            rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano22).getArchivo());
                            rutaruta.setUsuariocarpeta(getLogueados().get(0));
                            ruta.add(rutaruta);

                            ruta1.setRutaSTRING(nuevarutaReArmada);
                            ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                            ruta1.setCarpeta(filtro_eliminandoderutarearmada);
                            ruta1.setArchivo(filtro_buscandoRutas.get(tamano4).getArchivo());
                            ruta.add(ruta1);

                        }
                    }


                }
                if (currentDrivesletter.contains(drivev) && existedrive.length == 1 && !logueados.isEmpty()) {
                    if (!driveamover.isEmpty() && !logueados.isEmpty()) {

                        rutaruta.setRutaSTRING(target.toLowerCase() + file + "/");

                        rutaruta.getCarpeta().addAll(obteneractualizacioncarpetamover.get(tamano22).getCarpeta());//problema con recuperar carpeta
                        rutaruta.getCarpeta().addAll(driveamover.get(tamano2).getCarpeta());

                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano22).getArchivo());
                        //rutaruta.getArchivo().addAll(driveamover.get(tamano2).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);

                        ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                        ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                        ruta1.setCarpeta(currenChapterSINcarpetaMOVER);
                        ruta1.setArchivo(new ArrayList<>());
                        ruta.add(ruta1);
                    }
                    else {

                        rutaruta.setRutaSTRING(target.toLowerCase() + file + "/");
                        rutaruta.getCarpeta().addAll(obtenerarchivocosas);//problema con recuperar carpeta
                        rutaruta.getArchivo().addAll(obteneractualizacioncarpetamover.get(tamano22).getArchivo());
                        rutaruta.setUsuariocarpeta(getLogueados().get(0));
                        ruta.add(rutaruta);

                        ruta1.setRutaSTRING(ruta.get(tamano).getRutaSTRING());
                        ruta1.setUsuariocarpeta(ruta.get(tamano).getUsuariocarpeta());
                        ruta1.setCarpeta(currenChapterSINcarpetaMOVER);
                        ruta1.setArchivo(new ArrayList<>());
                        ruta.add(ruta1);

                    }
                }
            }
        }
    }















    @Override
    public String toString() {
        return "isidoraoyanedel21168603{" +
                "nombre='" + nombre + '\'' + "\n" +
                " fechaCreacrion=" + fechaCreacrion + "\n" +
                " drives=" + drives + "\n" +
                " usuarios=" + usuarios + "\n" +
                " logueados=" + logueados + "\n" +
                " actual=" + actual + "\n" +
                " ruta=" + ruta + "\n" +
                '}';
    }

    public List<String> getActual() {
        return actual;
    }

    public List<Path_21168603_ioyanedelalvarez> getRuta() {
        return ruta;
    }
}

