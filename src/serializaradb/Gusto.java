/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serializaradb;

import java.io.Serializable;

/**
 *
 * @author Marce
 */
public class Gusto implements Serializable{
    private String nombreDeGusto;

    public Gusto(String nombreDeGusto) {
        this.nombreDeGusto = nombreDeGusto;
    }

    public String getNombreDeGusto() {
        return nombreDeGusto;
    }

    public void setNombreDeGusto(String nombreDeGusto) {
        this.nombreDeGusto = nombreDeGusto;
    }

    @Override
    public String toString() {
        return "Gusto{" + "nombreDeGusto=" + nombreDeGusto + '}';
    }
    
    
    
    
    
}
