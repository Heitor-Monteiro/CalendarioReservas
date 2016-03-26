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
     
    public  void consultarrecurso(){
        recursoBuscados = rdao.listar();
         for (int i = 0; i <recursoBuscados.size(); i++) {
             System.out.println(recursoBuscados.get(i).getNome());
             System.out.println(recursoBuscados.get(i).getLocal());
             System.out.println(recursoBuscados.get(i).getId());
         }
    }
}
