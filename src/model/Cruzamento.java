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
public class Cruzamento implements Caminho{
    private Queue<Carro> filaCruzamento = new LinkedList<>();
    private Estrada[] estradas = new Estrada[4];
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
    
    public Estrada getEstradaCruzamento(int index){
        return estradas[index];
    }
    
    public void setEstradaCruzamento(int index, Estrada estrada){
        this.estradas[index]=estrada;
    }

    @Override
    public int getItem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Carro getCarro() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
