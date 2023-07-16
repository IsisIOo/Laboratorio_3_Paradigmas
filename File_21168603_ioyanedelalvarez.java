package org.example;

public class File_21168603_ioyanedelalvarez extends Herencia_de_Archivo21168603_ioyanedelalvarez_ implements Interfaz_file_21168603_ioyanedelalvarez{


    /**
     * Funcion que muestran los archivos actuales
     * @return lo que contiene actualmente los archivos
     */
    @Override
    public String toString() {
        return "File{" +
                "nombre='" + getNombre() + '\'' +
                ", extension='" + getExtension() + '\'' +
                ", contenido='" + getContenido() + '\'' +
                ", atributo_Seg='" + getAtributo_Seg() + '\'' +
                ", atributo_lect='" + getAtributo_lect() + '\'' +
                '}';
    }

    /**
     * Funcion que crea un archivo file
     * @param nombre nombre del archivo
     * @param extension Extension del archivo
     * @param contenido contenido del archivo
     * @param atributo_Seg atributo de seguridad del archivo (oculto o no oculto)
     * @param atributo_lect atributo de lectura (solo lectura o normal editable)
     */
    public File_21168603_ioyanedelalvarez(String nombre, String extension, String contenido, String atributo_Seg, String atributo_lect) {
        super(nombre,  extension, contenido, atributo_lect, atributo_Seg);
    }





}
