package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Path_21168603_ioyanedelalvarez {

    private String rutaSTRING;
    private List<Chapter_21168603_ioyanedelalvarez> carpeta;
    private List<File_21168603_ioyanedelalvarez> archivo;
    private String  usuariocarpeta;
    private Date fechaCreacion;

    /**
     *Funcion que crea path
     */
    public Path_21168603_ioyanedelalvarez() {
        this.rutaSTRING = rutaSTRING;
        this.carpeta = new ArrayList<>();
        this.archivo = new ArrayList<>();
        this.usuariocarpeta = usuariocarpeta;
        this.fechaCreacion = new Date();
    }


    /**
     * Funcion retorna la lista de carpetas de la ruta
     * @return Lista de carpetas
     */
    public List<Chapter_21168603_ioyanedelalvarez> getCarpeta() {
        return carpeta;
    }


    /**
     *Funcion que devuelve los valores existentes
     * @return String
     */
    @Override
    public String toString() {
        return "Path{" +
                "rutaSTRING='" + rutaSTRING + '\'' +
                ", carpeta=" + carpeta +
                ", archivo=" + archivo +
                ", usuariocarpeta='" + usuariocarpeta + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

    /**
     * Funcion que recupera la lista de archivos de una ruta
     * @return Lista de archivos
     */
    public List<File_21168603_ioyanedelalvarez> getArchivo() {
        return archivo;
    }

    /**
     *  Funcion que retorna el string de la ruta actual tipo: c:/carpeta1/
     * @return el string que tiene la ruta actual
     */
    public String getRutaSTRING() {
        return rutaSTRING;
    }

    /**
     * Funcion que indica el usuario en carpeta
     * @return el usuario que altero la carpeta
     */
    public String getUsuariocarpeta() {
        return usuariocarpeta;
    }

    /**
     * Funcion que obtiene fecha
     * @return fecha de modificacion
     */
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Funcion que modifica el string de la ruta
     * @param rutaSTRING String de la ruta actual
     */
    public void setRutaSTRING(String rutaSTRING) {
        this.rutaSTRING = rutaSTRING;
    }

    /**
     * Funcion que modifica la lista de carpetas
     * @param carpeta lista de carpetas
     */
    public void setCarpeta(List<Chapter_21168603_ioyanedelalvarez> carpeta) {
        this.carpeta = carpeta;
    }

    /**
     * Funcion que modifica la lista de archivos
     * @param archivo lista de archivos
     */
    public void setArchivo(List<File_21168603_ioyanedelalvarez> archivo) {
        this.archivo = archivo;
    }

    /**
     * Funcion que modifica el usuario en carpeta
     * @param usuariocarpeta usuario en carpeta
     */
    public void setUsuariocarpeta(String usuariocarpeta) {
        this.usuariocarpeta = usuariocarpeta;
    }
}

