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
@Table(name = "admin")
public class Administrador extends Usuario{
   
    @Column(name = "usuario", unique = true)
    private Usuario usuario;
    
     /*
     * GETTERS AND SETTERS
     */

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
