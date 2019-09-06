/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.CriadorDeCarros;
import controler.Gerenciador;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private Matriz matriz;
    private boolean spawnou;
    private CriadorDeCarros cdc;
    private double intervaloInsercao;

    public Carro(int linha, int coluna, int itemPosicao, double velocidade, int capacidadeBuffer, double intervaloInsercao) {
        this.linha = linha;
        this.coluna = coluna;
        this.itemPosicao = itemPosicao;
        this.velocidade = velocidade;
        this.matriz = Matriz.getInstance();
        spawnou = false;
        cdc = new CriadorDeCarros(capacidadeBuffer);
        this.intervaloInsercao = intervaloInsercao;
        batizador();
    }

    @Override
    public void run() {
        while (true) {
            if(!spawnou){
                spawnar();
            }else{
                andar();
            }
            
            try {
                sleep((long) velocidade);
            } catch (InterruptedException ex) {
                Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
            }
                    Gerenciador ger = Gerenciador.getInstance();
                    ger.notificarEstradaAlterada();
        }

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

    public void setVelocidade(double velocidade) {
        this.velocidade = velocidade;
    }
    
    

    public void andar() {
        switch (itemPosicao) {
            case 1:
                if (matriz.getValorMatriz(linha - 1, coluna).getItem() <= 4 && !matriz.getValorMatriz(linha - 1, coluna).estaOcupado()) {
                    matriz.getValorMatriz(linha - 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {
                    cruzamento(matriz.getValorMatriz(linha - 1, coluna).getItem());
                }
                break;
            case 2:
                if (matriz.getValorMatriz(linha, coluna + 1).getItem() <= 4 && !matriz.getValorMatriz(linha, coluna + 1).estaOcupado()) {
                    matriz.getValorMatriz(linha, coluna + 1).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {
                    cruzamento(matriz.getValorMatriz(linha, coluna + 1).getItem());
                }
                break;
            case 3:
                if (matriz.getValorMatriz(linha + 1, coluna).getItem() <= 4 && !matriz.getValorMatriz(linha + 1, coluna).estaOcupado()) {
                    matriz.getValorMatriz(linha + 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {
                    cruzamento(matriz.getValorMatriz(linha + 1, coluna).getItem());
                }
                break;
            case 4:
                if (matriz.getValorMatriz(linha, coluna - 1).getItem() <= 4 && !matriz.getValorMatriz(linha, coluna - 1).estaOcupado()) {
                    matriz.getValorMatriz(linha, coluna - 1).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {
                    cruzamento(matriz.getValorMatriz(linha, coluna - 1).getItem());
                }
                break;
                
        }
    }

    private void batizador() {
        Random rand = new Random();

        switch (rand.nextInt(5)) {
            case 0:
                this.nome = "azul";
                break;
            case 1:
                this.nome = "vermelho";
                break;
            case 2:
                this.nome = "ciano";
                break;
            case 3:
                this.nome = "verde";
                break;
            case 4:
                this.nome = "roxo";
                break;
        }
    }

    private void cruzamento(int item) {
        switch(item){
            case 5: 
                matriz.getValorMatriz(linha - 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                break;
            case 7: 
                matriz.getValorMatriz(linha - 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                break;
            case 8: 
                matriz.getValorMatriz(linha - 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                break;
            case 9: 
                matriz.getValorMatriz(linha - 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                break;
            case 10: 
                matriz.getValorMatriz(linha, coluna - 1).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                break;
            case 11: 
                matriz.getValorMatriz(linha, coluna + 1).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                break;
            case 12: 
                matriz.getValorMatriz(linha + 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                break;
        }
    }

    private void spawnar() {
        boolean adicionou = false;
        while(!adicionou){
        try {
            adicionou = cdc.spawn(this);
            sleep((long) this.intervaloInsercao);
        } catch (Exception ex) {
            Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
        }           
        }
        spawnou = true;
    }

}
