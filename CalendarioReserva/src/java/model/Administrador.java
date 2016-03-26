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
@Table(name = "admin")
public class Administrador extends Usuario{
    
    @Column(name = "mat")
    private int matricula;
    
    @OneToMany(mappedBy = "admin_id")
    private List<Reserva> reserva; 
    
     /*
     * GETTERS AND SETTERS
     */

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

}
