/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Random;

/**
 *
 * @author Adroan
 */
public class Carro extends Thread {
    private int linha;
    private int coluna;
    private int itemPosicao;
    private double velocidade;
    private String nome;

    public Carro(int linha, int coluna, int itemPosicao, double velocidade) {
        this.linha = linha;
        this.coluna = coluna;
        this.itemPosicao=itemPosicao;
        this.velocidade = velocidade;
        batizador();
    }

    @Override
    public void run() {
        
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

    public double getVelocidade() {
        return velocidade;
    }

    public String getNome() {
        return nome;
    }
   
   
    private void batizador(){
        Random rand = new Random();
        
        switch(rand.nextInt(5)){
            case 0:
                this.nome="azul";
                break;
            case 1:
                this.nome="vermelho";
                break;
            case 2:
                this.nome="ciano";
                break;
            case 3:
                this.nome="verde";
                break;
            case 4:
                this.nome="roxo";
                break;
        }
    }
    
}
