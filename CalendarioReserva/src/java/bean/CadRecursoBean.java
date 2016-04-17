/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import javax.inject.Inject;
import dao.RecursoDAO;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.Recurso;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Sandio
 */
@ManagedBean(name = "cadrecurso")
@SessionScoped
public class CadRecursoBean extends AbstractMB{
   
    @Inject
    private RecursoDAO rdao;
    
    private Recurso recurso = new Recurso();
    
    private String mensagem;
    
    private List<Recurso> recursoBuscados;

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<Recurso> getRecursoBuscados() {
        return recursoBuscados;
    }

    public void setRecursoBuscados(List<Recurso> recursoBuscados) {
        this.recursoBuscados = recursoBuscados;
    }
    
     public void salvarRecurso (){
        rdao.inserir(recurso);
        super.displayInfoMessageToUser("Cadastro Realizado com Sucesso!");
    }
     
    public void atualizaRecurso(RowEditEvent event){
        try {
            this.rdao.atualizar((Recurso)event.getObject());
            super.displayInfoMessageToUser("Professor Alterado com Sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            super.mensagemErro(mensagem);
        }
    }
    
    private void listaVazia (List list){
        if (list.isEmpty() == true) {
            super.mensagemVazia(mensagem);
        }
    }
     
    public  void consultarrecurso(){
        recursoBuscados = rdao.listar();
    }
    
    
}
