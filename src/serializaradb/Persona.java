/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializaradb;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Marce
 */
public class Persona implements Serializable{
    
    private String nombre;
    private ArrayList<Gusto>gustos;

    public Persona(String nombre, ArrayList<Gusto> gustos) {
        this.nombre = nombre;
        this.gustos = gustos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Gusto> getGustos() {
        return gustos;
    }

    public void setGustos(ArrayList<Gusto> gustos) {
        this.gustos = gustos;
    }

    @Override
    public String toString() {
        return "Persona{" + "A=" + nombre + ", le gustan los " + gustos + '}';
    }
    
    
}
