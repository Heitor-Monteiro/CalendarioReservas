/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dao.ReservaDAO;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import model.Reserva;

/**
 *
 * @author Sandio
 */
@ManagedBean(name = "cadreserva")
@SessionScoped
public class cadReservaBean extends AbstractMB{
    
    @Inject
    private ReservaDAO rdao;
    
    private  Reserva reserva = new Reserva();
    
    private String mensagem;

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
     
    public  void cadreserva (){
        rdao.inserir(reserva);
        super.displayInfoMessageToUser("Cadastro Realizado com Sucesso!");
    }
    
}
