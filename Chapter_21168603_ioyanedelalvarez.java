package org.example;

public class Chapter_21168603_ioyanedelalvarez implements Interfaz_chapter_21168603_ioyanedel {
    private String nombre;

    /**
     * Funcion que crea la estructura carpeta
     * @param nombre nombre de la carpeta
     */
    public Chapter_21168603_ioyanedelalvarez(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Funcion que obtiene nombre de la carpeta
     * @return retorna nombre de la carpeta
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Funcion para modificar el nombre de una carpeta
     * @param nombre nuevo nombre de la carpeta
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Funcion que muestra los datos actuales de las carpetas
     * @return datos actuales
     */
    @Override
    public String toString() {
        return "Chapter{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
