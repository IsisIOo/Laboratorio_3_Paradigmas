package org.example;

public class Ioyanedel_21168603_Herencia_de_Archivo {
    private String nombre; //no se si ponerle private, me caga el to string
    private String extension;
    private String contenido;
    private String atributo_Seg;
    private String atributo_lect;


    @Override
    public String toString() {
        return "Ioyanedel_21168603_Herencia_de_Archivo{" +
                "nombre='" + nombre + '\'' +
                ", extension='" + extension + '\'' +
                ", contenido='" + contenido + '\'' +
                ", atributo_Seg='" + atributo_Seg + '\'' +
                ", atributo_lect='" + atributo_lect + '\'' +
                '}';
    }

    public Ioyanedel_21168603_Herencia_de_Archivo(String nombre, String extension, String contenido, String atributo_Seg, String atributo_lect) {
        this.nombre = nombre;
        this.extension = extension;
        this.contenido = contenido;
        this.atributo_Seg = atributo_Seg;
        this.atributo_lect = atributo_lect;
    }


    public String getNombre() {
        return nombre;
    }

    public String getAtributo_Seg() {
        return atributo_Seg;
    }

    public String getAtributo_lect() {
        return atributo_lect;
    }

    public String getContenido() {
        return contenido;
    }

    public String getExtension() {
        return extension;
    }
}
