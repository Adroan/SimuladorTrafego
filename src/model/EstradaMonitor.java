package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.ImageIcon;

/**
 *
 * @author Adroan Covari Heinen , Vinicius Tome Vieira
 * @since 10/09/2019
 * @version 1.0
 */
public class EstradaMonitor implements Estrada {
    private String imagemBase;
    private int linha;
    private int coluna;
    private int item;
    private boolean ehCruzamento;
    private Carro carro;
    private ImageIcon imagem;
    private boolean reservou;

    public EstradaMonitor(int linha, int coluna, int item,Carro carro, boolean ehCruzamento, String imagem) {
        this.imagem = new ImageIcon(imagem);
        this.linha = linha;
        this.coluna = coluna;
        this.item = item;
        this.ehCruzamento = ehCruzamento;
        this.carro = carro;
        this.reservou= false;
        this.imagemBase = imagem;
    }

    @Override
    public synchronized boolean addCarroEstrada(Carro carro) {
        boolean adicionou  = false;
        try {                     
            if(!estaOcupado()){
            this.imagem = new ImageIcon("assets/" + carro.getNome()+imagemBase.replace("assets/", ""));
            carro.setColuna(this.coluna);
            carro.setLinha(this.linha);
            carro.setItemPosicao(this.item);
            this.carro = carro;
            adicionou = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return adicionou;
    }
    
    @Override
    public synchronized boolean addCarroCruzamento(Carro carro) {
        boolean adicionou  = false;
        try {
            this.imagem = new ImageIcon("assets/" + carro.getNome()+imagemBase.replace("assets/", ""));
            carro.setColuna(this.coluna);
            carro.setLinha(this.linha);
            carro.setItemPosicao(this.item);
            this.carro = carro;
            adicionou = true;            
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return adicionou;
    }
    
    @Override
    public boolean spawnarCarroEstrada(Carro carro){
        boolean adicionou  = false;        
            try {
                if (!estaOcupado()) {
                    this.imagem = new ImageIcon("assets/" + carro.getNome() + imagemBase.replace("assets/", ""));
                    carro.setColuna(this.coluna);
                    carro.setLinha(this.linha);
                    carro.setItemPosicao(this.item);
                    this.carro = carro;
                    adicionou = true;
            }else{
                   Thread.sleep(200);
                }
           
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
            return adicionou;
        }
    
    @Override
    public synchronized Carro retirarCarroEstrada() {
        Carro aux = null;
        try {            
            aux = carro;
            this.carro = null;
            this.imagem = new ImageIcon(imagemBase);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return aux;
    }

    @Override
    public  int getLinha(){
        return linha;
    }

    @Override
    public int getColuna() {
        return coluna;
    }

    @Override
    public int getItem() {
        return item;
    }

    @Override
    public ImageIcon getImagem() {
        return imagem;
    }

    @Override
    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }

    @Override
    public Carro getCarro() {
        return carro;
    }

    @Override
    public boolean estaOcupado() {
        return carro != null;
    }

    @Override
    public boolean isEhCruzamento() {
        return ehCruzamento;
    }

    @Override
    public void setEhCruzamento(boolean ehCruzamento) {
        this.ehCruzamento = ehCruzamento;
    }

    @Override
    public void setImagemBase(String imagemBase) {
        this.imagemBase = imagemBase;
    }
    

    @Override
    public String toString() {
        return "L=" + linha + "C=" + coluna + "Ca=" + carro + "I=" + item;
    }
    
    @Override
    public synchronized boolean reservar(){
        if(!estaOcupado()){
            this.carro = new Carro(linha, coluna, item, linha, coluna, linha);
            return true;
        }
        return false;                     
    }
    
    @Override
    public synchronized void liberar(){
        carro = null;
    }
}
