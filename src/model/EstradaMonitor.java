package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Adroan
 */
public class EstradaMonitor implements Estrada {
    private final String imagemBase;
    private int linha;
    private int coluna;
    private int item;
    private boolean ehCruzamento;
    private Carro carro;
    private ImageIcon imagem;
    private boolean reservou;

    public EstradaMonitor(int linha, int coluna, int item,Carro carro, boolean ehCruzamento, String imagem) {
        this.imagemBase = imagem;
        this.linha = linha;
        this.coluna = coluna;
        this.item = item;
        this.ehCruzamento = ehCruzamento;
        this.carro = carro;
        this.reservou= false;
        
    }

    
    

    
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
            System.out.println("Monitor interrompido, abortado");
            e.printStackTrace();
        } 
        return adicionou;
    }
    
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
            System.out.println("Semaforo mutex ou livre interrompido, abortado");
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
            System.out.println("Semaforo mutex ou livre interrompido, abortado");
            e.printStackTrace();
        }
            return adicionou;
        }
    


    public synchronized Carro retirarCarroEstrada() {
        Carro aux = null;
        try {
            
            aux = carro;
            this.carro = null;
            this.imagem = new ImageIcon(imagemBase);
        } catch (Exception e) {
            System.out.println("Semaforo mutex ou livre interrompidos, abortado");
            e.printStackTrace();
            return null;
        }
        return aux;
    }

    public  int getLinha(){
        return linha;
    }

    public int getColuna() {
        return coluna;
    }

    public int getItem() {
        return item;
    }

    public ImageIcon getImagem() {
        return imagem;
    }

    public void setImagem(ImageIcon imagem) {
        this.imagem = imagem;
    }

    public Carro getCarro() {
        return carro;
    }

    public boolean estaOcupado() {
        return carro != null;
    }

    public boolean isEhCruzamento() {
        return ehCruzamento;
    }

    public void setEhCruzamento(boolean ehCruzamento) {
        this.ehCruzamento = ehCruzamento;
    }

    @Override
    public String toString() {
        return "L=" + linha + "C=" + coluna + "Ca=" + carro + "I=" + item;
    }
    
    public synchronized boolean reservar(){
        System.out.println("Tentou reservar");
        if(!estaOcupado()){
            this.carro = new Carro(linha, coluna, item, linha, coluna, linha);
            return true;
        }
        return false;                     
    }
    public synchronized void liberar(){
        carro = null;
    }

}
