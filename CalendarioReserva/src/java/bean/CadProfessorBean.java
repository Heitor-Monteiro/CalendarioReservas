/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Professor;
import dao.ProfessorDAO;
import javax.inject.Inject;

/**
 *
 * @author Sandio
 */
@ManagedBean(name = "cadProfessor")
@SessionScoped
public class CadProfessorBean extends AbstractMB{
    
    @Inject
    private ProfessorDAO pdao;
    
    private Professor professor = new Professor();
    
    private String mensagem;

    public ProfessorDAO getPdao() {
        return pdao;
    }

    public void setPdao(ProfessorDAO pdao) {
        this.pdao = pdao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
    
    public void salvarProf (){
        pdao.inserir(professor);
        
        super.displayInfoMessageToUser("Cadastro Realizado com Sucesso!");
    }
}
