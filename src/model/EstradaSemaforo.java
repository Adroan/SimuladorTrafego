/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Adroan Covari Heinen , Vinicius Tome Vieira
 * @since 10/09/2019
 * @version 1.0
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

    @Override
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
            e.printStackTrace();
        } finally {
            mutex.release();
            ocupado.release();
        }
        return adicionou;
    }
    
    @Override
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
            e.printStackTrace();
        } finally {
            mutex.release();
            ocupado.release();
        }
            return adicionou;
        }

    @Override
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
    public boolean reservar(){
        try {
            return livre.tryAcquire(500, TimeUnit.MILLISECONDS);
        } catch (InterruptedException ex) {
            Logger.getLogger(EstradaSemaforo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; //Caso seja interrempido       
    }
    @Override
    public void liberar(){
        livre.release();
    }
}
