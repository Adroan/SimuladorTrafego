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
    private int[][] matriz;
    

    public Matriz(int linha, int coluna) {
        this.linha = linha;
        this.coluna = coluna;
        this.matriz = new int[linha][coluna];
    }

    public Matriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public Matriz(int linha, int coluna, int[][] matriz) {
        this.linha = linha;
        this.coluna = coluna;
        this.matriz = matriz;
    }
    
    

    public int[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(int[][] matriz) {
        this.matriz = matriz;
    }

    public int getLinha() {
        return linha;
    }

    public int getColuna() {
        return coluna;
    }
    public int getValorMatriz(int linha, int coluna){
        return matriz[linha][coluna];
    }
    
    
    
}
