package com.example.origenes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Categoria implements Serializable {
    private String nombre;
    public String nombreImagen;
    List<Plato>platos;


    public Categoria(String nombre) {
        this.nombre = nombre;
        platos = new ArrayList<Plato>();
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void addPlato(Plato plato){
        platos.add(plato);
    }
}
