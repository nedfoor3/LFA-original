package com.proyectofootball.titanes.lfa.model;

/**
 * Created by Ricardo Rodriguez on 9/14/2016.
 */
public class Equipo {

    private String nombre;

    private String ganados;

    private String perdidos;

    private String empates;

    private String colorPrincipal;

    private String colorSecundario;

    private double porcentaje;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGanados() {
        return ganados;
    }

    public void setGanados(String ganados) {
        this.ganados = ganados;
    }

    public String getPerdidos() {
        return perdidos;
    }

    public void setPerdidos(String perdidos) {
        this.perdidos = perdidos;
    }

    public String getEmpates() {
        return empates;
    }

    public void setEmpates(String empates) {
        this.empates = empates;
    }

    public String getColorPrincipal() {
        return colorPrincipal;
    }

    public void setColorPrincipal(String colorPrincipal) {
        this.colorPrincipal = colorPrincipal;
    }

    public String getColorSecundario() {
        return colorSecundario;
    }

    public void setColorSecundario(String colorSecundario) {
        this.colorSecundario = colorSecundario;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
}
