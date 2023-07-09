package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Path {

    String rutaSTRING;
    List<Chapter> carpeta;
    List<File> archivo;
    String  usuariocarpeta;
    Date fechaCreacion;

    public Path() {
        this.rutaSTRING = rutaSTRING;
        this.carpeta = new ArrayList<>();
        this.archivo = new ArrayList<>();
        this.usuariocarpeta = usuariocarpeta;
        this.fechaCreacion = new Date();
    }


    public List<Chapter> getCarpeta() {
        return carpeta;
    }


    @Override
    public String toString() {
        return "Path{" +
                "rutaSTRING='" + rutaSTRING + '\'' +
                ", carpeta=" + carpeta +
                ", archivo=" + archivo +
                ", usuariocarpeta='" + usuariocarpeta + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }

    public List<File> getArchivo() {
        return archivo;
    }

    public String getRutaSTRING() {
        return rutaSTRING;
    }

    public String getUsuariocarpeta() {
        return usuariocarpeta;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }
}

