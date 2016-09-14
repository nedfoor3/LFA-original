package com.proyectofootball.titanes.lfa.model;

/**
 * Created by Tonatiuh on 12/09/2016.
 */
public class Blog {
    private String imagenNota, encabezadoNota, textoNota;

    public Blog() {

    }

    public Blog(String imagenNota, String encabezadoNota, String textoNota) {
        this.imagenNota = imagenNota;
        this.encabezadoNota = encabezadoNota;
        this.textoNota = textoNota;
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
}