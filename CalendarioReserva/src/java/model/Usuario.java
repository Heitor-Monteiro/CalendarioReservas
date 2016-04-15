/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
/**
 *
 * @author Sandio
 */
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Usuario implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column (name = "senha")
    private String senha;
    
    @Column (name = "nome")
    private  String nome;
    
    @Column (name = "s_nome")
    private  String SobreNome;
    
    @Column (name = "dataNasc")
    private  Date dataNasc;
    
    @Column (name = "cpf")
    private  long cpf;
    
    @Column (name = "status")
    private  boolean status;
    
    @Column (name = "login")
    private String login;
    
    @Column (name = "t_usuario")
    private  boolean tipo_usuario;
    
     /*
     * GETTERS AND SETTERS
     */
    
    public String getNome() {
        return nome;
    }
    
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobreNome() {
        return SobreNome;
    }

    public void setSobreNome(String SobreNome) {
        this.SobreNome = SobreNome;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(boolean tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
    
    
}
