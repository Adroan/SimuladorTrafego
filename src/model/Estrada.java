/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javax.swing.ImageIcon;

/**
 *
 * @author Adroan
 */
public interface Estrada {

    boolean addCarroEstrada(Carro carro);

    Carro retirarCarroEstrada();

    int getLinha();

    int getColuna();

    int getItem();

    ImageIcon getImagem();

    void setImagem(ImageIcon imagem);

    Carro getCarro();

    boolean estaOcupado();

    boolean isEhCruzamento();

    void setEhCruzamento(boolean ehCruzamento);
}
