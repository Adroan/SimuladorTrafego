/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.util.concurrent.Semaphore;
import javax.swing.ImageIcon;

/**
 *
 * @author Adroan
 */
public class EstradaSemáforo implements Estrada  {
    private final String imagemBase;
    private int linha;
    private int coluna;
    private int item;
    private boolean ehCruzamento;
    private Carro carro;
    private ImageIcon imagem;
    private Semaphore mutex;
    private Semaphore ocupado;
    private Semaphore livre;


    public EstradaSemáforo(int linha, int coluna, int item,Carro carro, boolean ehCruzamento, String imagem) {
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
    


    public void addCarroEstrada(Carro carro) {
        try {
            livre.acquire();
            mutex.acquire();
            this.imagem = new ImageIcon(carro.getNome()+imagemBase);
            carro.setColuna(this.coluna);
            carro.setLinha(this.linha);
            carro.setItemPosicao(this.item);
            this.carro = carro;
        } catch (InterruptedException e) {
            System.out.println("Semaforo mutex ou livre interrompido, abortado");
            e.printStackTrace();
        } finally {
            mutex.release();
            ocupado.release();
        }
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

    @Override
    public String toString() {
        return "L=" + linha + "C=" + coluna + "Ca=" + carro + "I=" + item;
    }

}
