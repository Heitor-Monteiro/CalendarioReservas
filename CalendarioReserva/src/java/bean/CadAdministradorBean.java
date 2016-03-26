/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import dao.AdministradorDAO;
import javax.inject.Inject;
import model.Administrador;
/**
 *
 * @author PAULO
 */
@ManagedBean(name = "cadAdmin")
@SessionScoped
public class CadAdministradorBean {
    
    @Inject
    private AdministradorDAO Administradordao;
    
    private Administrador administrador = new Administrador();
    
    private String mensagem;

    public AdministradorDAO getAdministradordao() {
        return Administradordao;
    }

    public void setAdministradordao(AdministradorDAO Administradordao) {
        this.Administradordao = Administradordao;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
    
    public void salvarAdmin (){
       Administradordao.inserir(administrador);
    }
}
