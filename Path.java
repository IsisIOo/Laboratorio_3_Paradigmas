package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Path {

    String rutaSTRING;
    List<Chapter> carpeta;
    //List<String> archivo;
    String  usuariocarpeta;
    Date fechaCreacion;

    public Path() {
        this.fechaCreacion = new Date();
        this.rutaSTRING =  rutaSTRING;
        this.carpeta = new ArrayList<>();
        this.usuariocarpeta = usuariocarpeta;

    }


    @Override
    public String toString() {
        return "Path{" +
                "rutaSTRING='" + rutaSTRING + '\'' +
                ", carpeta=" + carpeta +
                ", usuariocarpeta=" + usuariocarpeta +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

    public List<Chapter> getCarpeta() {
        return carpeta;
    }
}

