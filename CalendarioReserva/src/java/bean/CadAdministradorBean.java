/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import dao.AdministradorDAO;
import dao.ProfessorDAO;
import dao.UsuarioDao;
import java.util.List;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import model.Administrador;
import model.Professor;
import model.Usuario;
import org.primefaces.event.RowEditEvent;
/**
 *
 * @author PAULO
 */
@ManagedBean(name = "cadAdmin")
@ViewScoped
public class CadAdministradorBean extends AbstractMB{
    
    @Inject
    private AdministradorDAO Administradordao;
    
    @Inject
    private ProfessorDAO professordao;
    
    @Inject
    private UsuarioDao usuariodao;
    
    private String campo, sql;
    
    private List<Administrador> adminBuscados;
    
    private List<Professor> profBuscados;
    
    private List<Usuario> usuariobuscados;
  
    private Administrador administrador = new Administrador();
    
    private Professor professor = new Professor();
    
    private String mensagem;
    
     /*
     * GETTERS AND SETTERS
     */
    
    public AdministradorDAO getAdministradordao() {
        return Administradordao;
    }

    public void setAdministradordao(AdministradorDAO Administradordao) {
        this.Administradordao = Administradordao;
    }

    public Professor getProfessoor() {
        return professor;
    }

    public void setProfessoor(Professor professoor) {
        this.professor = professoor;
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

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Administrador> getAdminBuscados() {
        return adminBuscados;
    }

    public void setAdminBuscados(List<Administrador> adminBuscados) {
        this.adminBuscados = adminBuscados;
    }

    public ProfessorDAO getProfessordao() {
        return professordao;
    }

    public void setProfessordao(ProfessorDAO professordao) {
        this.professordao = professordao;
    }

    public UsuarioDao getUsuariodao() {
        return usuariodao;
    }

    public void setUsuariodao(UsuarioDao usuariodao) {
        this.usuariodao = usuariodao;
    }

    public List<Professor> getProfBuscados() {
        return profBuscados;
    }

    public void setProfBuscados(List<Professor> profBuscados) {
        this.profBuscados = profBuscados;
    }

    public List<Usuario> getUsuariobuscados() {
        return usuariobuscados;
    }

    public void setUsuariobuscados(List<Usuario> usuariobuscados) {
        this.usuariobuscados = usuariobuscados;
    }
    
     /*
     * METODOS DO CRUD
     */
    
    public void salvarAdmin (){
       Administradordao.inserir(administrador);
       super.displayInfoMessageToUser("Cadastro Realizado com Sucesso!");
    }
    
    public void atualizarProfessor (RowEditEvent event){
        try {
            this.professordao.atualizar((Professor)event.getObject());
            super.displayInfoMessageToUser("Professor Alterado com Sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            super.mensagemErro(mensagem);
        }
    }
    
    public void atualizarAdmin (RowEditEvent event){
        try {
            this.Administradordao.atualizar((Administrador)event.getObject());
            super.displayInfoMessageToUser("Administrador Alterado com Sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            super.mensagemErro(mensagem);
        }
    }
    
    public void atualizarUsuario (RowEditEvent event){
        try {
           this.usuariodao.atualizar((Usuario)event.getObject());
           super.displayInfoMessageToUser("Usuário Alterado com Sucesso!"); 
        } catch (Exception e){
            e.printStackTrace();
            super.mensagemErro(mensagem);
        }
    }
    
    public void cancelarEdit (RowEditEvent event){
        super.displayInfoMessageToUser("Não Houver Alteração!");
    }
    
    public  void consultarCampo(){
       if(null != campo) 
           switch (campo) {
            case "matricula":
                adminBuscados = Administradordao.consultaUsuario(Administrador.class, campo, sql);
                listaVazia(adminBuscados);
                break;
            case "siap":
                profBuscados = professordao.consultaUsuario(Professor.class, campo,sql);
                listaVazia(profBuscados);
                break;
            case "nome":
                usuariobuscados = usuariodao.consultaUsuario(Usuario.class, campo,sql);
                listaVazia(usuariobuscados);
                break;
            case "cpf":
                usuariobuscados = usuariodao.consultaUsuario(Usuario.class, campo,sql);
                listaVazia(usuariobuscados);
                break;
            default:
                super.displayInfoMessageToUser("Indisponível!"); 
                break;
        }
    }
    
    public void limpar (){
        profBuscados.clear();
        adminBuscados.clear();
        usuariobuscados.clear();
    }
    
    private void listaVazia(List list){
        if (list.isEmpty() == true) {
            super.mensagemVazia(mensagem);
        }
    }
}
