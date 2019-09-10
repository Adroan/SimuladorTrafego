/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.ImageIcon;

/**
 *
 * @author Adroan Covari Heinen , Vinicius Tome Vieira
 * @since 10/09/2019
 * @version 1.0
 */
public class Estrada {

    public boolean addCarroEstrada(Carro carro) {
        return false;
    }

    public boolean spawnarCarroEstrada(Carro carro) {
        return false;
    }

    public boolean addCarroCruzamento(Carro carro) {
        return false;
    }

    public Carro retirarCarroEstrada() {
        return null;
    }

    public int getLinha() {
        return 0;
    }

    public int getColuna() {
        return 0;
    }

    public int getItem() {
        return 0;
    }

    public ImageIcon getImagem() {
        return null;
    }

    public void setImagem(ImageIcon imagem) {
    }

    public Carro getCarro() {
        return null;
    }

    public boolean estaOcupado() {
        return false;
    }

    public boolean isEhCruzamento() {
        return false;
    }

    public void setImagemBase(String imagem) {
    }

    public void setEhCruzamento(boolean ehCruzamento) {
    }

    public boolean reservar() {
        return false;
    }

    public void liberar() {
    }

    public Estrada estradaFactory(int modo, int linha, int coluna, int item, Carro carro, boolean ehCruzamento, String imagem) {
        if (modo == 1) {
            return new EstradaSemaforo(linha, coluna, item, carro, ehCruzamento, imagem);
        } else {
            return new EstradaMonitor(linha, coluna, item, carro, ehCruzamento, imagem);
        }
    }
}
