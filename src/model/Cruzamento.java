/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Adroan
 */
public class Cruzamento {
    private Queue<Carro> filaCruzamento = new LinkedList<>();

    public Cruzamento() {
    }
    
    public void addCarro(Carro carro){
        this.filaCruzamento.add(carro);
    }
    
    public Carro entrarCruzamento(){
        return this.filaCruzamento.poll();
    }
    
    public Carro verProximo(){
        return this.filaCruzamento.peek();
    }
}
