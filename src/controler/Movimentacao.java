/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import model.Carro;
import model.Matriz;

/**
 *
 * @author Adroan
 */
public class Movimentacao {

    private Matriz matriz = Matriz.getInstance();

    public Movimentacao() {
    }

    public void andarCima(Carro carro) {
//      Verificação redundante para checar se vai mesmo subir
        if (carro.getItemPosicao() == 1) {
//            verifica se a próxima estrada esta ocupada
            if (matriz.getValorMatriz(carro.getLinha() - 1, carro.getColuna()).estaOcupado() == false) {
//                verifica se não é um cruzamento
                if (matriz.getValorMatriz(carro.getLinha() - 1, carro.getColuna()).isEhCruzamento()==false) {
//                    verifica se não chegou ao final da rua
                    if (matriz.getValorMatriz(carro.getLinha() - 1, carro.getColuna()).getLinha() >= 0) {
                        matriz.getValorMatriz(carro.getLinha() - 1, carro.getColuna()).addCarroEstrada(matriz.getValorMatriz(carro.getLinha(), carro.getColuna()).retirarCarroEstrada());
                        andarCima(matriz.getValorMatriz(carro.getLinha() - 1, carro.getColuna()).getCarro());
                    }
                }else if(matriz.getValorMatriz(carro.getLinha() - 1, carro.getColuna()).isEhCruzamento()==true){
                    
                }
            }
        }
    }

    public void andarBaixo(Carro carro) {
        if (carro.getItemPosicao() == 3) {
            if (matriz.getValorMatriz(carro.getLinha() + 1, carro.getColuna()).estaOcupado() == false) {
                if (matriz.getValorMatriz(carro.getLinha() + 1, carro.getColuna()).isEhCruzamento()==false) {
                    if (matriz.getValorMatriz(carro.getLinha() + 1, carro.getColuna()).getLinha() < matriz.getLinha()) {
                        matriz.getValorMatriz(carro.getLinha() + 1, carro.getColuna()).addCarroEstrada(matriz.getValorMatriz(carro.getLinha(), carro.getColuna()).retirarCarroEstrada());
                        andarBaixo(matriz.getValorMatriz(carro.getLinha() + 1, carro.getColuna()).getCarro());
                    }
                }
            }
        }
    }

    public void andarDireita(Carro carro) {
        if (carro.getItemPosicao() == 2) {
            if (matriz.getValorMatriz(carro.getLinha(), carro.getColuna() + 1).estaOcupado() == false) {
                if (matriz.getValorMatriz(carro.getLinha(), carro.getColuna() + 1).isEhCruzamento()==false) {
                    if (matriz.getValorMatriz(carro.getLinha(), carro.getColuna() + 1).getLinha() < matriz.getColuna()) {
                        matriz.getValorMatriz(carro.getLinha(), carro.getColuna() + 1).addCarroEstrada(matriz.getValorMatriz(carro.getLinha(), carro.getColuna()).retirarCarroEstrada());
                        andarDireita(matriz.getValorMatriz(carro.getLinha(), carro.getColuna() + 1).getCarro());
                    }
                }
            }
        }
    }

    public void andarEsquerda(Carro carro) {
        if (carro.getItemPosicao() == 4) {
            if (matriz.getValorMatriz(carro.getLinha(), carro.getColuna() - 1).estaOcupado() == false) {
                if (matriz.getValorMatriz(carro.getLinha(), carro.getColuna() - 1).isEhCruzamento()==false) {
                    if (matriz.getValorMatriz(carro.getLinha(), carro.getColuna() - 1).getLinha() >= 0) {
                        matriz.getValorMatriz(carro.getLinha(), carro.getColuna() - 1).addCarroEstrada(matriz.getValorMatriz(carro.getLinha(), carro.getColuna()).retirarCarroEstrada());
                        andarEsquerda(matriz.getValorMatriz(carro.getLinha(), carro.getColuna() - 1).getCarro());
                    }
                }
            }
        }
    }
}
