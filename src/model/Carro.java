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
    private int itemProximaPosicao;
    private double velocidade;
    private String nome;
    private Matriz matriz;
    private boolean spawnou;
    private CriadorDeCarros cdc;
    private double intervaloInsercao;
    private Random rand = new Random();
    private boolean vivo = true;
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
        while (vivo) {
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
    
    public void verificarProxPosicao(int linha, int coluna){
        try{
            itemProximaPosicao = matriz.getValorMatriz(linha, coluna).getItem();
        }catch(ArrayIndexOutOfBoundsException ex){
            matriz.getValorMatriz(this.linha, this.coluna).retirarCarroEstrada();
            this.vivo = false;
            Gerenciador ger = Gerenciador.getInstance();
            ger.verificarFim();
        }
    }

    public void andar() {
        switch (itemPosicao) {
            case 1:
                verificarProxPosicao(linha - 1, coluna);
                if(vivo){
                if (matriz.getValorMatriz(linha - 1, coluna).getItem() <= 4 && !matriz.getValorMatriz(linha - 1, coluna).estaOcupado()) {
                    matriz.getValorMatriz(linha - 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {                   
                        cruzamento();
                    }                   
                }
                break;
            case 2:
                verificarProxPosicao(linha, coluna + 1);
                if (vivo) {
                    if (matriz.getValorMatriz(linha, coluna + 1).getItem() <= 4 && !matriz.getValorMatriz(linha, coluna + 1).estaOcupado()) {
                        matriz.getValorMatriz(linha, coluna + 1).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                    } else {
                        cruzamento();
                    }
                }
                break;
            case 3:
                verificarProxPosicao(linha + 1, coluna);
                if(vivo){
                if (matriz.getValorMatriz(linha + 1, coluna).getItem() <= 4 && !matriz.getValorMatriz(linha + 1, coluna).estaOcupado()) {
                    matriz.getValorMatriz(linha + 1, coluna).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {
                        cruzamento();
                }
                }
                break;
            case 4:
                verificarProxPosicao(linha, coluna-1);
                if(vivo){
                if (matriz.getValorMatriz(linha, coluna - 1).getItem() <= 4 && !matriz.getValorMatriz(linha, coluna - 1).estaOcupado()) {
                    matriz.getValorMatriz(linha, coluna - 1).addCarroEstrada(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                } else {                                      
                    cruzamento();                   
                }
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
        Estrada selecionada;
        switch(itemProximaPosicao){
            case 5:
                estradas.add(matriz.getValorMatriz(linha-2, coluna-2)); // ir pra esquerda
                estradas.add(matriz.getValorMatriz(linha-3, coluna)); // ir pra direita
                selecionada = estradas.get(rand.nextInt(2));
                if(selecionada.getItem() == estradas.get(0).getItem()){
                    atravessarCruzamentoEsquerda(3); //3 = numero de casas que vai andar
                }else{
                    atravessarCruzamentoCima(2);
                }
                break;
            case 7:
                estradas.add(matriz.getValorMatriz(linha+3, coluna)); // ir pra baixo
                estradas.add(matriz.getValorMatriz(linha+2, coluna+2)); // ir pra direita
                selecionada = estradas.get(rand.nextInt(2));
                if(selecionada.getItem() == estradas.get(0).getItem()){
                    atravessarCruzamentoBaixo(2); //3 = numero de casas que vai andar
                }else{
                    atravessarCruzamentoDireita(3);
                }
                break;
            case 8:
                estradas.add(matriz.getValorMatriz(linha, coluna-3)); // ir pra esquerda
                estradas.add(matriz.getValorMatriz(linha+2, coluna-2)); // ir pra baixo
                selecionada = estradas.get(rand.nextInt(2));
                if(selecionada.getItem() == estradas.get(0).getItem()){
                    atravessarCruzamentoEsquerda(2); //3 = numero de casas que vai andar
                }else{
                    atravessarCruzamentoBaixo(3);
                }
                break;
            case 9:
                estradas.add(matriz.getValorMatriz(linha-2, coluna-2)); // ir pra esquerda
                estradas.add(matriz.getValorMatriz(linha-3, coluna)); // ir pra cima
                estradas.add(matriz.getValorMatriz(linha-1, coluna+1)); // ir pra direita
                selecionada = estradas.get(rand.nextInt(3));
                if(selecionada.getItem() == estradas.get(0).getItem()){
                    if(selecionada.getItem() != 0){
                        atravessarCruzamentoEsquerda(3); //3 = numero de casas que vai andar
                    }
                }else if(selecionada.getItem() == estradas.get(1).getItem()){
                    if(selecionada.getItem() != 0){
                    atravessarCruzamentoCima(2);
                    }
                }else{
                    atravessarCruzamentoDireita(1);
                }
                break;
            case 10:
                estradas.add(matriz.getValorMatriz(linha+2, coluna-2)); // ir pra baixo
                estradas.add(matriz.getValorMatriz(linha, coluna-3)); // ir pra esquerda
                estradas.add(matriz.getValorMatriz(linha-1, coluna-1)); // ir pra cima
                selecionada = estradas.get(rand.nextInt(3));
                if(selecionada.getItem() == estradas.get(0).getItem()){
                    atravessarCruzamentoBaixo(3); //3 = numero de casas que vai andar
                }else if(selecionada.getItem() == estradas.get(1).getItem()){
                    if(selecionada.getItem() != 0){
                      atravessarCruzamentoEsquerda(2);  
                    }                    
                }else{
                    atravessarCruzamentoCima(1);
                }
                break;
                           
            case 11:
                estradas.add(matriz.getValorMatriz(linha-2, coluna+2)); // ir pra cima
                estradas.add(matriz.getValorMatriz(linha, coluna+3)); // ir pra direita
                estradas.add(matriz.getValorMatriz(linha+1, coluna+1)); // ir pra baixo
                selecionada = estradas.get(rand.nextInt(3));
                if(selecionada.getItem() == estradas.get(0).getItem()){
                    if(selecionada.getItem() != 0){
                    atravessarCruzamentoCima(3); //3 = numero de casas que vai andar
                    }
                }else if(selecionada.getItem() == estradas.get(1).getItem()){
                    if(selecionada.getItem() != 0){
                        atravessarCruzamentoDireita(2);
                    }                    
                }else{
                    atravessarCruzamentoBaixo(1);
                }
                break;
            case 12:
                estradas.add(matriz.getValorMatriz(linha+1, coluna-1)); // ir pra esquerda
                estradas.add(matriz.getValorMatriz(linha+3, coluna)); // ir pra baixo
                estradas.add(matriz.getValorMatriz(linha+2, coluna+2)); // ir pra direita
                selecionada = estradas.get(rand.nextInt(3));
                if(selecionada.getItem() == estradas.get(0).getItem()){
                    atravessarCruzamentoEsquerda(1); //3 = numero de casas que vai andar
                }else if(selecionada.getItem() == estradas.get(1).getItem()){
                    atravessarCruzamentoBaixo(2);
                }else{
                    if(selecionada.getItem() != 0){
                        atravessarCruzamentoDireita(3);
                    }                   
                }
                break;
            
        }              
    }
    
    private void atravessarCruzamentoEsquerda(int qtdCasas){
        List<Estrada> estradasParaMover = new ArrayList<>();
        switch(qtdCasas){
            case 1: 
                estradasParaMover.add(matriz.getValorMatriz(linha+1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha+1, coluna-1)); break;
            case 2:
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna-1));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna-2));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna-3)); break;

            case 3:
                estradasParaMover.add(matriz.getValorMatriz(linha-1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha-2, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha-2, coluna-1));
                estradasParaMover.add(matriz.getValorMatriz(linha-2, coluna-2)); break;                
        }
        boolean atravessou = false;
        while(!atravessou){
            boolean reservou = false;
            List<Estrada> estradasReservadas = new ArrayList<>();
            for(Estrada estrada : estradasParaMover){
                try{
                    reservou = estrada.reservar();
                }catch(Exception ex){
                    
                }
                if(!reservou){
                    //Liberar geral em for
                    for(Estrada estradaRemover : estradasReservadas){
                        estradaRemover.liberar();
                    }
                    try {
                        sleep((long) velocidade * 4);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }else{
                    estradasReservadas.add(estrada);
                    reservou = false;
                }
                
            }
            if (estradasReservadas.size() == estradasParaMover.size()) {
                for (Estrada estradaAdicionar : estradasReservadas) {
                    matriz.getValorMatriz(estradaAdicionar.getLinha(), estradaAdicionar.getColuna()).addCarroCruzamento(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                    try {
                        sleep((long) velocidade);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Gerenciador ger = Gerenciador.getInstance();
                    ger.notificarEstradaAlterada();
                }
            }
           atravessou = true;
        }
    }
    
    private void atravessarCruzamentoDireita(int qtdCasas){
        List<Estrada> estradasParaMover = new ArrayList<>();
        switch(qtdCasas){
            case 1: 
                estradasParaMover.add(matriz.getValorMatriz(linha-1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha-1, coluna+1)); break;
            case 2:
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna+1));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna+2));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna+3)); break;
            case 3:
                estradasParaMover.add(matriz.getValorMatriz(linha+1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha+2, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha+2, coluna+1));
                estradasParaMover.add(matriz.getValorMatriz(linha+2, coluna+2)); break;
        }
        boolean atravessou = false;
        while(!atravessou){
            boolean reservou = false;
            List<Estrada> estradasReservadas = new ArrayList<>();
            for(Estrada estrada : estradasParaMover){
                try{
                    reservou = estrada.reservar();
                }catch(Exception ex){
                    
                }
                if(!reservou){
                    //Liberar geral em for
                    for(Estrada estradaRemover : estradasReservadas){
                        estradaRemover.liberar();
                    }
                    try {
                        sleep((long) velocidade * 3);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }else{
                    estradasReservadas.add(estrada);
                    reservou = false;
                }
                
            }
            if (estradasReservadas.size() == estradasParaMover.size()) {
                for (Estrada estradaAdicionar : estradasReservadas) {
                    matriz.getValorMatriz(estradaAdicionar.getLinha(), estradaAdicionar.getColuna()).addCarroCruzamento(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                    try {
                        sleep((long) velocidade);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Gerenciador ger = Gerenciador.getInstance();
                    ger.notificarEstradaAlterada();
                }
            }
           atravessou = true;
        }
    }
    
    private void atravessarCruzamentoBaixo(int qtdCasas){
        List<Estrada> estradasParaMover = new ArrayList<>();
        switch(qtdCasas){
            case 1: 
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna+1)); 
                estradasParaMover.add(matriz.getValorMatriz(linha+1, coluna+1)); break;
            case 2:
                estradasParaMover.add(matriz.getValorMatriz(linha+1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha+2, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha+3, coluna)); break;
            case 3:
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna-1));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna-2));
                estradasParaMover.add(matriz.getValorMatriz(linha+1, coluna-2));
                estradasParaMover.add(matriz.getValorMatriz(linha+2, coluna-2)); break;
        }
        boolean atravessou = false;
        while(!atravessou){
            boolean reservou = false;
            List<Estrada> estradasReservadas = new ArrayList<>();
            for(Estrada estrada : estradasParaMover){
                try{
                    reservou = estrada.reservar();
                }catch(Exception ex){
                    
                }
                if(!reservou){
                    //Liberar geral em for
                    for(Estrada estradaRemover : estradasReservadas){
                        estradaRemover.liberar();
                    }
                    try {
                        sleep((long) velocidade * 2);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }else{
                    estradasReservadas.add(estrada);
                    reservou = false;
                }
                
            }
            if (estradasReservadas.size() == estradasParaMover.size()) {
                for (Estrada estradaAdicionar : estradasReservadas) {
                    matriz.getValorMatriz(estradaAdicionar.getLinha(), estradaAdicionar.getColuna()).addCarroCruzamento(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                    try {
                        sleep((long) velocidade);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Gerenciador ger = Gerenciador.getInstance();
                    ger.notificarEstradaAlterada();
                }
            }
           atravessou = true;
        }
    }
    
    private void atravessarCruzamentoCima(int qtdCasas){
        List<Estrada> estradasParaMover = new ArrayList<>();
        switch(qtdCasas){
            case 1: 
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna-1)); 
                estradasParaMover.add(matriz.getValorMatriz(linha-1, coluna-1)); break;
            case 2:
                estradasParaMover.add(matriz.getValorMatriz(linha-1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha-2, coluna)); 
                estradasParaMover.add(matriz.getValorMatriz(linha-3, coluna)); break;
            case 3:
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna+1));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna+2));
                estradasParaMover.add(matriz.getValorMatriz(linha-1, coluna+2)); 
                estradasParaMover.add(matriz.getValorMatriz(linha-2, coluna+2)); break;                
        }
        boolean atravessou = false;
        while(!atravessou){
            boolean reservou = false;
            List<Estrada> estradasReservadas = new ArrayList<>();
            for(Estrada estrada : estradasParaMover){
                try{
                    reservou = estrada.reservar();
                }catch(Exception ex){
                    
                }
                if(!reservou){
                    //Liberar geral em for
                    for(Estrada estradaRemover : estradasReservadas){
                        estradaRemover.liberar();
                    }
                    try {
                        sleep((long) velocidade * 4);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }else{
                    estradasReservadas.add(estrada);
                    reservou = false;
                }
                
            }
            if (estradasReservadas.size() == estradasParaMover.size()) {
                for (Estrada estradaAdicionar : estradasReservadas) {
                    matriz.getValorMatriz(estradaAdicionar.getLinha(), estradaAdicionar.getColuna()).addCarroCruzamento(matriz.getValorMatriz(linha, coluna).retirarCarroEstrada());
                    try {
                        sleep((long) velocidade);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Carro.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Gerenciador ger = Gerenciador.getInstance();
                    ger.notificarEstradaAlterada();
                }
            }
           atravessou = true;
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
        Gerenciador ger = Gerenciador.getInstance();
        ger.spawnarCarros();
    }

}
