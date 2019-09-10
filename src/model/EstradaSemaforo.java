/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Adroan
 */
public class EstradaSemaforo implements Estrada  {
    private String imagemBase;
    private int linha;
    private int coluna;
    private int item;
    private boolean ehCruzamento;
    private Carro carro;
    private ImageIcon imagem;
    private Semaphore mutex;
    private Semaphore ocupado;
    private Semaphore livre;


    public EstradaSemaforo(int linha, int coluna, int item,Carro carro, boolean ehCruzamento, String imagem) {
        this.linha = linha;
        this.coluna = coluna;
        this.item = item;
        this.ehCruzamento = ehCruzamento;
        this.imagem = new ImageIcon(imagem);
        this.imagemBase = imagem;
        mutex = new Semaphore(1);
        livre = new Semaphore(1);
        ocupado = new Semaphore(0);
    }
    


    public boolean addCarroEstrada(Carro carro) {
        boolean adicionou  = false;
        try {
            
            livre.acquire();
            mutex.acquire();
            this.imagem = new ImageIcon("assets/" + carro.getNome()+imagemBase.replace("assets/", ""));
            
            carro.setColuna(this.coluna);
            carro.setLinha(this.linha);
            carro.setItemPosicao(this.item);
            this.carro = carro;
            adicionou = true;
            
        } catch (InterruptedException e) {
            System.out.println("Semaforo mutex ou livre interrompido, abortado");
            e.printStackTrace();
        } finally {
            mutex.release();
            ocupado.release();
        }
        return adicionou;
    }
    
    public boolean addCarroCruzamento(Carro carro) {
        boolean adicionou  = false;
        try {
            mutex.acquire();
            this.imagem = new ImageIcon("assets/" + carro.getNome()+imagemBase.replace("assets/", ""));
            carro.setColuna(this.coluna);
            carro.setLinha(this.linha);
            carro.setItemPosicao(this.item);
            this.carro = carro;
            adicionou = true;
            
        } catch (Exception e) {
            System.out.println("Semaforo mutex ou livre interrompido, abortado");
            e.printStackTrace();
        } finally {
            mutex.release();
            ocupado.release();
        }
        return adicionou;
    }
    
    @Override
    public boolean spawnarCarroEstrada(Carro carro){
        boolean adicionou  = false;        
            try {
                livre.acquire();
                mutex.acquire();
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
        } finally {
            mutex.release();
            ocupado.release();
        }
            return adicionou;
        }
    


    public Carro retirarCarroEstrada() {
        Carro aux = null;
        try {
            ocupado.acquire();
            mutex.acquire();
            aux = carro;
            this.carro = null;
            this.imagem = new ImageIcon(imagemBase);
        } catch (InterruptedException e) {
            System.out.println("Semaforo mutex ou livre interrompidos, abortado");
            e.printStackTrace();
            return null;
        } finally {
            mutex.release();
            livre.release();
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

    public void setImagemBase(String imagemBase) {
        this.imagemBase = imagemBase;
    }
    

    @Override
    public String toString() {
        return "L=" + linha + "C=" + coluna + "Ca=" + carro + "I=" + item;
    }
    
    public boolean reservar(){
        try {
            return livre.tryAcquire(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(EstradaSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    public void liberar(){
        livre.release();
    }

}
