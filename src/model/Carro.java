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
public class Carro {
    private int linha;
    private int coluna;
    private int itemPosicao;

    public Carro(int linha, int coluna, int itemPosicao) {
        this.linha = linha;
        this.coluna = coluna;
        this.itemPosicao=itemPosicao;
    }
    

    

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public int getItemPosicao() {
        return itemPosicao;
    }

    public void setItemPosicao(int itemPosicao) {
        this.itemPosicao = itemPosicao;
    }
   
    
}
