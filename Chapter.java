package org.example;

public class Chapter implements Interfaz_chapter_ioyanedel_21168603{
    String nombre;

    public Chapter(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
