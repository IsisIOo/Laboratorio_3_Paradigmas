package org.example;

public class File extends Ioyanedel_21168603_Herencia_de_Archivo{


    @Override
    public String toString() {
        return "File{" +
                "nombre='" + nombre + '\'' +
                ", extension='" + extension + '\'' +
                ", contenido='" + contenido + '\'' +
                ", atributo_Seg='" + atributo_Seg + '\'' +
                ", atributo_lect='" + atributo_lect + '\'' +
                '}';
    }

    public File(String nombre, String extension, String contenido, String atributo_Seg, String atributo_lect) {
        super(nombre,  extension, contenido, atributo_lect, atributo_Seg);
    }





}
