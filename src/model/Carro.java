/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controler.CriadorDeCarros;
import controler.Gerenciador;
import java.util.ArrayList;
import java.util.List;
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
    private Random rand = new Random();
    private List<Estrada> caminho = new ArrayList<>();

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
                    cruzamento();
                }
                break;
            case 2:
                if (matriz.getValorMatriz(linha, coluna + 1).getItem() <= 4 && !matriz.getValorMatriz(linha, coluna + 1).estaOcupado()) {
                    matriz.getValorMatriz(linha, coluna + 1).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {
                    cruzamento();
                }
                break;
            case 3:
                if (matriz.getValorMatriz(linha + 1, coluna).getItem() <= 4 && !matriz.getValorMatriz(linha + 1, coluna).estaOcupado()) {
                    matriz.getValorMatriz(linha + 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {
                    cruzamento();
                }
                break;
            case 4:
                if (matriz.getValorMatriz(linha, coluna - 1).getItem() <= 4 && !matriz.getValorMatriz(linha, coluna - 1).estaOcupado()) {
                    matriz.getValorMatriz(linha, coluna - 1).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {
                    cruzamento();
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
//tentativa 
    private void cruzamento() {
        List<Estrada> estradas = new ArrayList<>();
        switch(itemPosicao){
            case 5:
                estradas.add(matriz.getValorMatriz(linha-2, coluna-2)); // ir pra esquerda
                estradas.add(matriz.getValorMatriz(linha-3, coluna)); // ir pra direita
                Estrada selecionada = estradas.get(rand.nextInt(2));
                if(selecionada.getClass() == estradas.get(0).getClass()){
                    atravessarCruzamentoEsquerda(3); //3 = numero de casas que vai andar
                }else{
                    atravessarCruzamentoCima(2);
                }
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            
        }              
    }
    
    private void atravessarCruzamentoEsquerda(int qtdCasas){
        List<Estrada> estradasParaMover = new ArrayList<>();
        switch(qtdCasas){
            case 1: 
                estradasParaMover.add(matriz.getValorMatriz(linha+1, coluna)); break;
            case 2:
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna-1));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna-2)); break;
            case 3:
                estradasParaMover.add(matriz.getValorMatriz(linha-1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha-2, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha-2, coluna-1)); break;                
        }
        boolean atravessou = false;
        while(!atravessou){
            boolean reservou = false;
            int reservados = 0;
            for(Estrada estrada : estradasParaMover){
                try{
                    reservou = estrada.reservar();
                }catch(Exception ex){
                    
                }
                if(!reservou){
                    //Liberar geral em for
                    break;
                }
                reservados++;
            }
            if(reservados == estradasParaMover.size()){
                //Chama método para andar as 3 casas
            }
        }
    }
    
    private void atravessarCruzamentoDireita(int qtdCasas){
        
    }
    
    private void atravessarCruzamentoBaixo(int qtdCasas){
        
    }
    
    private void atravessarCruzamentoCima(int qtdCasas){
        
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
