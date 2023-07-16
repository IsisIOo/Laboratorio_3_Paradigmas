package org.example;

import java.util.Date;
import java.util.List;

public interface Interfaz_path_21168603_ioyanedelalvarez {
    /**
     * Funcion retorna la lista de carpetas de la ruta
     * @return Lista de carpetas
     */
    List<Chapter_21168603_ioyanedelalvarez> getCarpeta();

    /**
     * Funcion que recupera la lista de archivos de una ruta
     * @return Lista de archivos
     */
    List<File_21168603_ioyanedelalvarez> getArchivo();

    /**
     *  Funcion que retorna el string de la ruta actual tipo: c:/carpeta1/
     * @return el string que tiene la ruta actual
     */
    String getRutaSTRING();

    /**
     * Funcion que indica el usuario en carpeta
     * @return el usuario que altero la carpeta
     */
    String getUsuariocarpeta();

    /**
     * Funcion que obtiene fecha
     * @return fecha de modificacion
     */
    Date getFechaCreacion();

    /**
     * Funcion que modifica el string de la ruta
     * @param rutaSTRING String de la ruta actual
     */
    void setRutaSTRING(String rutaSTRING);

    /**
     * Funcion que modifica la lista de carpetas
     * @param carpeta lista de carpetas
     */
    void setCarpeta(List<Chapter_21168603_ioyanedelalvarez> carpeta);

    /**
     * Funcion que modifica la lista de archivos
     * @param archivo lista de archivos
     */
    void setArchivo(List<File_21168603_ioyanedelalvarez> archivo);

    /**
     * Funcion que modifica el usuario en carpeta
     * @param usuariocarpeta usuario en carpeta
     */
    void setUsuariocarpeta(String usuariocarpeta);
}

