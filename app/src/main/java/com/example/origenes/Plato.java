package com.example.origenes;

import java.io.Serializable;

public class Plato implements Serializable {
    private String nombre = "";
    private float precio = 0;
    private String descripcion ="";
    private String rutaImagen;


    public Plato(String nombre, float precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}

