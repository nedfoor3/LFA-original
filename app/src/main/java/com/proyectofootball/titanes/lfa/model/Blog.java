package com.proyectofootball.titanes.lfa.model;

/**
 * Created by Tonatiuh on 12/09/2016.
 */
public class Blog {
    private String imagenNota, encabezadoNota, textoNota, fechaNota, videoNota;

    public Blog() {

    }

    public Blog(String imagenNota, String encabezadoNota, String textoNota, String fechaNota, String videoNota) {
        this.imagenNota = imagenNota;
        this.encabezadoNota = encabezadoNota;
        this.textoNota = textoNota;
        this.fechaNota = fechaNota;
        this.videoNota = videoNota;
    }

    public String getImagenNota() {
        return imagenNota;
    }

    public void setImagenNota(String imagenNota) {
        this.imagenNota = imagenNota;
    }

    public String getEncabezadoNota() {
        return encabezadoNota;
    }

    public void setEncabezadoNota(String encabezadoNota) {
        this.encabezadoNota = encabezadoNota;
    }

    public String getTextoNota() {
        return textoNota;
    }

    public void setTextoNota(String textoNota) {
        this.textoNota = textoNota;
    }

    public String getFechaNota() {
        return fechaNota;
    }

    public void setFechaNota(String fechaNota) {
        this.fechaNota = fechaNota;
    }

    public String getVideoNota() {
        return videoNota;
    }

    public void setVideoNota(String videoNota) {
        this.videoNota = videoNota;
    }
}