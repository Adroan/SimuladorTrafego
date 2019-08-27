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
    private boolean estaOcuoado;

    public Estrada(int linha, int coluna, int item) {
        this.linha = linha;
        this.coluna = coluna;
        this.item = item;
        this.estaOcuoado = false;
    }

    public boolean isEstaOcuoado() {
        return estaOcuoado;
    }

    public void setEstaOcuoado(boolean estaOcuoado) {
        this.estaOcuoado = estaOcuoado;
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

    @Override
    public String toString() {
        return "L="+linha+"C="+coluna+"Oc="+estaOcuoado+"I="+item;
    }
    
    
}
