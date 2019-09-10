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
public interface Estrada {

    boolean addCarroEstrada(Carro carro);
    
    boolean spawnarCarroEstrada(Carro carro);
    
    boolean addCarroCruzamento(Carro carro);
    
    Carro retirarCarroEstrada();

    int getLinha();

    int getColuna();

    int getItem();

    ImageIcon getImagem();

    void setImagem(ImageIcon imagem);

    Carro getCarro();

    boolean estaOcupado();

    boolean isEhCruzamento();
    
    void setImagemBase(String imagem);

    void setEhCruzamento(boolean ehCruzamento);
    
    public boolean reservar();
    
    public void liberar();
}
