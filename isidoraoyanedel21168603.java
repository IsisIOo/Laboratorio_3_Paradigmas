package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class isidoraoyanedel21168603 implements Interfaz_IOyanedel_21168603{
    //esta cosa es sistema
    private String nombre;
    private Date fechaCreacrion;
    private List<Drive> drives;
    private List<Usuario> usuarios;
    private List<String> logueados;
    private List<String> actual;

    //List<Chapter> carpeta; //lo estoy usando en path

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
        var tamano = ruta.size()-1;
        if(tamano > 1){

            var currentChapters =
                    ruta.get(tamano).carpeta.stream()
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
        else{
           ruta1.rutaSTRING = actual.get(0).toLowerCase() + ":/";
           ruta1.usuariocarpeta = getLogueados().get(0);
           //var carpetas = new ArrayList<>();
           //var archivos = new ArrayList<>();
           //ruta1.carpeta = carpetas;
           ruta.add(ruta1);
        }



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
            rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
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
            rutaruta.archivo = ruta.get(tamano).archivo;
            ruta.add(rutaruta);
        }

        //caso normal
        if (currentChapters.contains(camino_seleccionado)){
            //AGREGAR ARCHIVOS
            var filtro_buscandoRutasNORMAL =
                    ruta.stream()
                            .filter(rutas -> rutas.rutaSTRING.equals(ruta.get(tamano).rutaSTRING + camino_seleccionado + "/"))
                            .collect(Collectors.toList());
            if (filtro_buscandoRutasNORMAL.size() >= 1) {
                var tamano3 = filtro_buscandoRutasNORMAL.size() - 1;
                rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING + camino_seleccionado + "/";
                rutaruta.archivo.addAll((filtro_buscandoRutasNORMAL.get(tamano3).archivo));
                rutaruta.carpeta.addAll((filtro_buscandoRutasNORMAL.get(tamano3).carpeta));
                rutaruta.usuariocarpeta = getLogueados().get(0);
                ruta.add(rutaruta);
            }
            else{
                rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING + camino_seleccionado + "/";
                rutaruta.usuariocarpeta = getLogueados().get(0);
                ruta.add(rutaruta);
            }
        }

//caso /.folder1
        if(camino_seleccionado.contains("./")) {
            String[] titulo_texto_sinbarra = camino_seleccionado.split("./"); //quita el ./
            var tituloreal = titulo_texto_sinbarra[1]; //obtiene el titulo string sin ./ desde la lista
            String total = String.join("", tituloreal); //convierte la lista en string

            if (currentChapters.contains(total)) { //busca el titulo string
                var filtro_buscandoRutasBARRA =
                        ruta.stream()
                                .filter(rutas -> rutas.rutaSTRING.equals(ruta.get(tamano).rutaSTRING + total + "/"))
                                .collect(Collectors.toList());
                if (filtro_buscandoRutasBARRA.size() >= 1) {
                    var tamano3 = filtro_buscandoRutasBARRA.size() - 1;
                    rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING + total + "/";
                    rutaruta.archivo.addAll((filtro_buscandoRutasBARRA.get(tamano3).archivo));
                    rutaruta.carpeta.addAll((filtro_buscandoRutasBARRA.get(tamano3).carpeta));
                    rutaruta.usuariocarpeta = getLogueados().get(0);
                    ruta.add(rutaruta);
                }
            }
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
                rutaruta.archivo.addAll((filtro_buscandoRutas.get(tamano3).archivo));
                rutaruta.carpeta.addAll((filtro_buscandoRutas.get(tamano3).carpeta));
                rutaruta.usuariocarpeta = getLogueados().get(0);
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

        var newFile = new File(nombre_de_archivo, tipode1, content_de_archivo, seg2, seg1);
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
        var newFile = new File(nombre_de_archivo, tipode1, content_de_archivo, seg2, seg1);
        addFile(newFile);
    }



    public void addFile(File archivo){
        var lecturas = archivo.getAtributo_lect();
        var seguridad = archivo.getAtributo_Seg();
        var contenidos= archivo.getContenido();
        var rutaruta = new Path();
        var tamano = ruta.size()-1;
        var currentFiles =
                ruta.get(tamano).archivo.stream()
                        .map(File::getNombre)
                        .collect(Collectors.toList());
        if (!currentFiles.contains(archivo.getNombre())){
            var archivos_actuales = getRuta().get(tamano).getArchivo();
            rutaruta.archivo.add(archivo);
            rutaruta.archivo.addAll(archivos_actuales);
            rutaruta.carpeta = ruta.get(tamano).carpeta;
            rutaruta.usuariocarpeta= getLogueados().get(0);
            rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
            //AGREGAR ARCHIVOS
            ruta.add(rutaruta);
        }
        else{
            System.out.println("ya existe un archivo con ese Titulo, ingrese otro titulo y extensión.\n");
            crea_fileCaso2(contenidos, seguridad, lecturas);

        }
    }

    public void del(String path){
        //caso archivo
        //if(path.contains('.')) {
            var rutaruta = new Path();
            var tamano = ruta.size() - 1;
            var currentFiles =
                    ruta.get(tamano).archivo.stream()
                            .map(File::getNombre)
                            .collect(Collectors.toList());

            if (currentFiles.contains(path)) {
                var filtro_buscandoTITULOSRutas =
                        ruta.get(tamano).archivo.stream()
                                .filter(rutas -> !rutas.getNombre().equals(path))
                                .collect(Collectors.toList());
                rutaruta.carpeta = ruta.get(tamano).carpeta;
                rutaruta.usuariocarpeta = getLogueados().get(0);
                rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
                rutaruta.archivo = filtro_buscandoTITULOSRutas;
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
            if(path == "." || path == "*"){
                rutaruta.carpeta = ruta.get(tamano).carpeta;
                rutaruta.usuariocarpeta = getLogueados().get(0);
                rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
                rutaruta.archivo.clear();
                ruta.add(rutaruta);
            }

            String[] titulo_separado = path.split("\\.");
            var largo = titulo_separado.length;
            var tituloTitulo = titulo_separado[0];


            //caso de titulo.extension
            if(largo == 2 && !tituloTitulo.equals("")) {
                var extensionTitulo = titulo_separado[1];
                if (currentFiles.contains(tituloTitulo)) {
                    var filtro_buscandoTITULOSRutas =
                            ruta.get(tamano).archivo.stream()
                                    .filter(rutas -> !rutas.getNombre().equals(tituloTitulo) && !rutas.getExtension().equals(extensionTitulo))
                                    .collect(Collectors.toList());
                    rutaruta.carpeta = ruta.get(tamano).carpeta;
                    rutaruta.usuariocarpeta = getLogueados().get(0);
                    rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
                    rutaruta.archivo = filtro_buscandoTITULOSRutas;
                    ruta.add(rutaruta);
                }
            }

            //caso extension sola
            if(largo == 2 && tituloTitulo.equals("")){
                var filtro_buscandoEXTENSIONRutas =
                        ruta.get(tamano).archivo.stream()
                                .filter(rutas -> !rutas.getExtension().equals(path))
                                .collect(Collectors.toList());
                rutaruta.carpeta = ruta.get(tamano).carpeta;
                rutaruta.usuariocarpeta = getLogueados().get(0);
                rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
                rutaruta.archivo = filtro_buscandoEXTENSIONRutas;
                ruta.add(rutaruta);
            }

//caso carpeta simple
            var currentChapters =
                ruta.get(tamano).carpeta.stream()
                        .map(Chapter::getNombre)
                        .collect(Collectors.toList());
            if(currentChapters.contains(path)){
                var filtro_borrarUNAcarpeta =
                        ruta.get(tamano).carpeta.stream()
                                .filter((rutas -> !rutas.getNombre().equals(path)))
                                .collect(Collectors.toList());
                rutaruta.rutaSTRING = ruta.get(tamano).rutaSTRING;
                rutaruta.carpeta.addAll(ruta.get(tamano).carpeta);
                rutaruta.carpeta = filtro_borrarUNAcarpeta;
                rutaruta.usuariocarpeta = getLogueados().get(0);
                ruta.add(rutaruta);
            }

            //falta el caso de carpeta con archivos


            //else{
             //   System.out.println("No hay ningun archivo que cumpla esas condiciones.");
            //}

        }

    public void copy (String file, String target){
        var rutaruta = new Path();
        //caso de mover a otro drive
        String[] existedrive1 = target.split(":/");
        String[] existedrive = target.split("/");
        var drivev= existedrive1[0];


        var currentDrivesletter = //obtiene las letras de todos los drives
                drives.stream()
                        .map(Drive::getLetter)
                        .collect(Collectors.toList());

        var driveamover = //busca las rutas que partan con la letra del drive del string entregado d:/ asi obtienes la ultima actualizacion de esa y revisa las carpetas
                ruta.stream()
                        .filter(rutas->rutas.rutaSTRING.equals(drivev.toLowerCase() + ":" + "/"))
                        .collect(Collectors.toList());

        var tamano2 = driveamover.size()-1;
        var tamano = ruta.size()-1;

        String[] tituloyextension = file.split("\\.");
        var titulo = tituloyextension[0];
        var extension = tituloyextension[1];

        var obtenerarchivocosas =
                ruta.get(tamano).archivo.stream()
                        .filter(File -> File.getNombre().equals(titulo) && File.getExtension().equals("." + extension))
                        .collect(Collectors.toList());

        var recuperardatosderuta =
                ruta.stream()
                        .filter(rutas->rutas.rutaSTRING.equals(target.toLowerCase()))
                        .collect(Collectors.toList());

        var currentChapters =
                driveamover.get(tamano2).carpeta.stream()
                        .map(Chapter::getNombre)
                        .collect(Collectors.toList());
        
//caso nunca abri esa ruta, por lo tanto debo saber si existe el drive letra y si existe la carpeta
        //cuando hay drive y una carpeta
        if(currentDrivesletter.contains(drivev) && existedrive.length>1){

            var carpetadestino = existedrive[1];


            //ruta1.carpeta.addAll(rutasderutas.get(tamano2).carpeta); //si d:/ contuene la carpeta de destino
            if(currentChapters.contains(carpetadestino)) {
                //caso donde nunca se trabajo con d:/carpeta por lo tanto todos sus listas son vacias
                if (recuperardatosderuta.isEmpty()) {
                    rutaruta.rutaSTRING = target.toLowerCase();
                    rutaruta.archivo.addAll(obtenerarchivocosas);
                    //rutaruta.archivo.addAll(recuperardatosderuta.get(tamano2).getArchivo());
                    rutaruta.usuariocarpeta = getLogueados().get(0);
                    //rutaruta.carpeta = new ArrayList<>();
                    ruta.add(rutaruta);
                }
                else { //caso cuando existe una ruta en la que se trabajo el d:/carpeta
                    rutaruta.rutaSTRING = target.toLowerCase();
                    rutaruta.archivo.addAll(obtenerarchivocosas);
                    rutaruta.archivo.addAll(recuperardatosderuta.get(tamano2).getArchivo());
                    rutaruta.usuariocarpeta = getLogueados().get(0);
                    rutaruta.carpeta = recuperardatosderuta.get(tamano2).getCarpeta();
                    ruta.add(rutaruta);
                }

            }


 //caso donde quiero mover a d:/ sin carpeta
            else if(currentDrivesletter.contains(drivev) && existedrive.length==1){
                rutaruta.rutaSTRING = target.toLowerCase();
                rutaruta.archivo.addAll(obtenerarchivocosas);
                rutaruta.archivo.addAll(driveamover.get(tamano2).getArchivo());
                rutaruta.usuariocarpeta= getLogueados().get(0);
                rutaruta.carpeta = driveamover.get(tamano2).carpeta;
                ruta.add(rutaruta);
            }

            else{
                rutaruta.rutaSTRING = target.toLowerCase();
                rutaruta.archivo.addAll(obtenerarchivocosas);
                rutaruta.archivo.addAll(driveamover.get(tamano2).getArchivo());
                rutaruta.usuariocarpeta= getLogueados().get(0);
                rutaruta.carpeta = driveamover.get(tamano2).getCarpeta();
                ruta.add(rutaruta);
            }

        }

        else {
            rutaruta.rutaSTRING = target.toLowerCase();
            rutaruta.archivo.addAll(obtenerarchivocosas);
            rutaruta.archivo.addAll(driveamover.get(tamano2).getArchivo());
            rutaruta.usuariocarpeta= getLogueados().get(0);
            rutaruta.carpeta = driveamover.get(tamano2).carpeta;
            ruta.add(rutaruta);
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

    public List<Path> getRuta() {
        return ruta;
    }
}

