package org.example;

public class Drive_21168603_ioyanedelalvarez implements Interfaz_drive_21168603_ioyanedelalvarez{
    private String letter;
    private String nombre;

    private int capacity;

    /**
     * Funcion que arma estructura drive
     * @param letter letra del drive
     * @param nombre nombre del drive
     * @param capacity capacidad del drive
     */
    public Drive_21168603_ioyanedelalvarez(String letter, String nombre, int capacity) {
        this.letter = letter;
        this.nombre = nombre;
        this.capacity = capacity;
    }

    /**
     * Funcion que muestra los datos actuales
     * @return los datos actuales de drive
     */
    @Override
    public String toString() {
        return "Drive{" +
                "letter='" + letter + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    /**
     * Funcion que obitne la letra de cierto drive
     * @return letra de drives
     */
    public String getLetter() {
        return letter;
    }
}
