/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Adroan
 */
public class Estrada {
    private int linha;
    private int coluna;
    private int item;
    private boolean ehCruzamento;
    private Carro carro;

    public Estrada(int linha, int coluna, int item, boolean ehCruzamento,Carro carro) {
        this.linha = linha;
        this.coluna = coluna;
        this.item = item;
        this.carro=carro;
        this.ehCruzamento = ehCruzamento;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public int getItem() {
        return item;
    }
    
    public Carro retirarCarro(){
        Carro aux=carro;
        this.carro=null;
        return aux;
    }
    public void addCarro(Carro carro){
        this.carro = carro;
    }
    
    public boolean estaOcupado(){
        return carro!=null;
    }

    @Override
    public String toString() {
        return "L="+linha+"C="+coluna+"Ca="+carro+"I="+item+"Cr="+ehCruzamento;
    }
    
    
}
