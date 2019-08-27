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
public class Matriz {
    private int linha;
    private int coluna;
    private Estrada[][] matriz;
    
    private Matriz(){};
    
    private static Matriz instance;
    
    public synchronized static Matriz getInstance(){
        if(instance==null)
            instance = new Matriz();
        
        return instance;
    }

    public Estrada[][] getMatriz() {
        return matriz;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
    public Estrada getValorMatriz(int linha, int coluna){
        return matriz[linha][coluna];
    }
    
    public void setValorMatriz(int linha, int coluna, Estrada estrada){
        this.matriz[linha][coluna]=estrada;
    }
    
    public void criarMatriz(int linha, int coluna){
        this.matriz = new Estrada[linha][coluna];
    }
    
}
