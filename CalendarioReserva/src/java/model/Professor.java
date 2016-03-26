/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Sandio
 */
@Entity
@Table(name = "professor")
public class Professor extends Usuario{
    
    @OneToMany(mappedBy = "professor_id")
    private List<Reserva> reserva;
    
    @Column(name = "faculdade")
    private String faculdade;
    
    @Column(name = "siap")
    private String siap;
    
     /*
     * GETTERS AND SETTERS
     */

    public String getSiap() {
        return siap;
    }

    public void setSiap(String siap) {
        this.siap = siap;
    }
    
    public String getFaculdade() {
        return faculdade;
    }

    public void setFaculdade(String faculdade) {
        this.faculdade = faculdade;
    }
    
    
    
}
