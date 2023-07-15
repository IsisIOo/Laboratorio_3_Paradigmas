package org.example;

public class Usuario_21168603_ioyanedelalvarez {
    private String nombre;

    /**
     * Funcion que crea la estructura usuario
     * @param nombre nombre del usuario
     */
    public Usuario_21168603_ioyanedelalvarez(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Funcion que retorna el nombre del usuario
     * @return nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Funcion que modifica el nombre del usuario
     * @param nombre NUEVO NOMBRE DEL USUARIO
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Funcion que muestra los datos actuales
     * @return muestra los datos actuales
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
}
