/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Sandio
 */
@Entity
@Table(name = "professor")
public class Professor extends Usuario{

    public Professor() {
    
    }
    
    @Column(name = "usuario",unique = true)
    private Usuario usuario;
    
    @Column(name = "faculdade")
    private String faculdade;
    
     /*
     * GETTERS AND SETTERS
     */

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(String faculdade) {
        this.faculdade = faculdade;
    }
    
    
    
}
