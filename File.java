package org.example;

public class File extends Ioyanedel_21168603_Herencia_de_Archivo{


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

    public File(String nombre, String extension, String contenido, String atributo_Seg, String atributo_lect) {
        super(nombre,  extension, contenido, atributo_lect, atributo_Seg);
    }





}
