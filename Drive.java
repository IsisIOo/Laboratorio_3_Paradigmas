package org.example;

public class Drive {
    String letter;
    String nombre;

    int capacity;

    public Drive(String letter, String nombre, int capacity) {
        this.letter = letter;
        this.nombre = nombre;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Drive{" +
                "letter='" + letter + '\'' +
                ", nombre='" + nombre + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    public String getLetter() {
        return letter;
    }
}
