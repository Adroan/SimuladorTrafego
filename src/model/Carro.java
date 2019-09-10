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
 * @author Adroan Covari Heinen , Vinicius Tome Vieira
 * @since 10/09/2019
 * @version 1.0
 */
public class Carro extends Thread {

    private int linha;
    private int coluna;
    private int itemPosicao; //Seu valor numerico referente a malha
    private int itemProximaPosicao; //Valor numerico referente ao proximo item da malha
    private double velocidade;
    private String nome;
    private Matriz matriz;
    private boolean spawnou;
    private CriadorDeCarros cdc;
    private double intervaloInsercao;
    private Random rand;
    private boolean vivo;
    private List<Estrada> caminho;

    public Carro(int linha, int coluna, int itemPosicao, double velocidade, int capacidadeBuffer, double intervaloInsercao) {
        this.linha = linha;
        this.coluna = coluna;
        this.itemPosicao = itemPosicao;
        this.velocidade = velocidade;
        this.matriz = Matriz.getInstance();
        spawnou = false;
        vivo = true;
        cdc = new CriadorDeCarros(capacidadeBuffer);
        this.intervaloInsercao = intervaloInsercao;
        rand = new Random();
        caminho = new ArrayList<>();
        batizador();
    }

    @Override
    public void run() {
        while (vivo) {
            if (!spawnou) {
                spawnar();
            } else {
                andar();
            }
            try {
                sleep(rand.nextInt(1000));
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

    private void cruzamento() {
        List<Estrada> estradas = new ArrayList<>();
        Estrada selecionada;
        switch(itemProximaPosicao){
            case 5:
                estradas.add(matriz.getValorMatriz(linha-2, coluna-2)); // ir pra esquerda
                estradas.add(matriz.getValorMatriz(linha-3, coluna)); // ir pra direita
                selecionada = estradas.get(rand.nextInt(2)); //escolhe aleatoriamente
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
                    atravessarCruzamentoBaixo(2);
                }else{
                    atravessarCruzamentoDireita(3);
                }
                break;
            case 8:
                estradas.add(matriz.getValorMatriz(linha, coluna-3)); // ir pra esquerda
                estradas.add(matriz.getValorMatriz(linha+2, coluna-2)); // ir pra baixo
                selecionada = estradas.get(rand.nextInt(2));
                if(selecionada.getItem() == estradas.get(0).getItem()){
                    atravessarCruzamentoEsquerda(2);
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
                        atravessarCruzamentoEsquerda(3);
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
                    atravessarCruzamentoBaixo(3);
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
                    atravessarCruzamentoCima(3);
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
                    atravessarCruzamentoEsquerda(1);
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
    
    private void atravessarCruzamentoEsquerda(int qtdCasas) {
        List<Estrada> estradasParaMover = new ArrayList<>(); //Casas que ele vai tentar solicitar acesso
        switch (qtdCasas) {
            case 1:
                estradasParaMover.add(matriz.getValorMatriz(linha + 1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha + 1, coluna - 1));
                break;
            case 2:
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna - 1));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna - 2));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna - 3));
                break;

            case 3:
                estradasParaMover.add(matriz.getValorMatriz(linha - 1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha - 2, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha - 2, coluna - 1));
                estradasParaMover.add(matriz.getValorMatriz(linha - 2, coluna - 2));
                break;
        }
        List<Estrada> estradasReservadas = new ArrayList<>();
        estradasReservadas = solicitarAcessoCruzamento(estradasParaMover);
        if (estradasReservadas.size() == estradasParaMover.size()) {
            andarCruzamento(estradasReservadas);
        }
    }
    
    private void atravessarCruzamentoDireita(int qtdCasas) {
        List<Estrada> estradasParaMover = new ArrayList<>();
        switch (qtdCasas) {
            case 1:
                estradasParaMover.add(matriz.getValorMatriz(linha - 1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha - 1, coluna + 1));
                break;
            case 2:
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna + 1));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna + 2));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna + 3));
                break;
            case 3:
                estradasParaMover.add(matriz.getValorMatriz(linha + 1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha + 2, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha + 2, coluna + 1));
                estradasParaMover.add(matriz.getValorMatriz(linha + 2, coluna + 2));
                break;
        }
        List<Estrada> estradasReservadas = new ArrayList<>();
        estradasReservadas = solicitarAcessoCruzamento(estradasParaMover);
        if (estradasReservadas.size() == estradasParaMover.size()) {
            andarCruzamento(estradasReservadas);
        }
    }
    
    private void atravessarCruzamentoBaixo(int qtdCasas) {
        List<Estrada> estradasParaMover = new ArrayList<>();
        switch (qtdCasas) {
            case 1:
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna + 1));
                estradasParaMover.add(matriz.getValorMatriz(linha + 1, coluna + 1));
                break;
            case 2:
                estradasParaMover.add(matriz.getValorMatriz(linha + 1, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha + 2, coluna));
                estradasParaMover.add(matriz.getValorMatriz(linha + 3, coluna));
                break;
            case 3:
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna - 1));
                estradasParaMover.add(matriz.getValorMatriz(linha, coluna - 2));
                estradasParaMover.add(matriz.getValorMatriz(linha + 1, coluna - 2));
                estradasParaMover.add(matriz.getValorMatriz(linha + 2, coluna - 2));
                break;
        }
        List<Estrada> estradasReservadas = new ArrayList<>();
        estradasReservadas = solicitarAcessoCruzamento(estradasParaMover);
        if (estradasReservadas.size() == estradasParaMover.size()) {
            andarCruzamento(estradasReservadas);
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
            List<Estrada> estradasReservadas = new ArrayList<>();
            estradasReservadas = solicitarAcessoCruzamento(estradasParaMover);
            if (estradasReservadas.size() == estradasParaMover.size()) {
                andarCruzamento(estradasReservadas);
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

    private void andarCruzamento(List<Estrada> estradasReservadas) {
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

    private List<Estrada> solicitarAcessoCruzamento(List<Estrada> estradasParaMover) {
        boolean reservou = false;
        List<Estrada> estradasReservadas = new ArrayList<>();
        for (Estrada estrada : estradasParaMover) {
            try {
                reservou = estrada.reservar();
            } catch (Exception ex) {

            }
            if (!reservou) {
                //Liberar todos que tentou acessar
                for (Estrada estradaRemover : estradasReservadas) {
                    estradaRemover.liberar();
                }
                estradasReservadas.clear();
                break;
            } else {
                estradasReservadas.add(estrada);
                reservou = false;
            }
        }
        return estradasReservadas;
    }
}