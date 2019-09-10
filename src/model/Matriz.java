/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Adroan Covari Heinen , Vinicius Tome Vieira
 * @since 10/09/2019
 * @version 1.0
 */
public class Matriz {
    private int linha;
    private int coluna;
    private Estrada[][] matriz;
    private static Matriz instance;
    
    private Matriz() {
    }

    public synchronized static Matriz getInstance() {
        if (instance == null) {
            instance = new Matriz();
        }
        return instance;
    }

    public Estrada[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Estrada[][] matriz) {
        this.matriz = matriz;
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

    public Estrada getValorMatriz(int linha, int coluna) {
        return matriz[linha][coluna];
    }

    public void setValorMatriz(int linha, int coluna, Estrada estrada) {
        this.matriz[linha][coluna] = estrada;
    }

    public void criarMatriz(int linha, int coluna) {
        this.matriz = new Estrada[linha][coluna];
    }
}
