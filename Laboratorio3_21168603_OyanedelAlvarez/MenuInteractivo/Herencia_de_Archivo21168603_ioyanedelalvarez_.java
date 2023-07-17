package MenuInteractivo;

public class Herencia_de_Archivo21168603_ioyanedelalvarez_ implements Interfaz_herencia_21168603_ioyanedelalvarez{
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

    /**
     *
     * @param nombre nombre del archivo
     * @param extension extension del archivo
     * @param contenido contenido del archivo
     * @param atributo_Seg atributo de seguridad del archivo (oculto o no oculto)
     * @param atributo_lect atributo de lectura (solo lectura o normal editable)
     */
    public Herencia_de_Archivo21168603_ioyanedelalvarez_(String nombre, String extension, String contenido, String atributo_Seg, String atributo_lect) {
        this.nombre = nombre;
        this.extension = extension;
        this.contenido = contenido;
        this.atributo_Seg = atributo_Seg;
        this.atributo_lect = atributo_lect;
    }


    /**
     * Funcion que recupera el nombre de un archivo
     * @return nombre archivo
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Funcion que recupera el atributo de seguridad que tiene cierto archivo
     * @return atributo de seguridad del archivo
     */
    public String getAtributo_Seg() {
        return atributo_Seg;
    }

    /**
     * Funcion que recupera el atributo de seguridad de cierto archivo
     * @return retorna atributo de lectura de cierto archivo
     */
    public String getAtributo_lect() {
        return atributo_lect;
    }

    /**
     * Funcion que entrega el contenido de cierto archivo
     * @return contenido de un archivo
     */
    public String getContenido() {
        return contenido;
    }

    /**
     * Funcion que recupera la extension de cierto archivo
     * @return extension de archivo
     */
    public String getExtension() {
        return extension;
    }
}
