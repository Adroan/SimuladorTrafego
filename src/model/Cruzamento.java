/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Adroan
 */
public class Cruzamento extends Estrada {

    private Queue<Carro> filaCruzamento = new LinkedList<>();

    public Cruzamento(int linha, int coluna, int item, Carro carro, boolean ehCruzamento) {
        super(linha, coluna, item, carro, ehCruzamento);
    }



    /**
     *Adiciona o carro a fila do cruzamento
     * @param carro
     */
    public void addCarro(Carro carro) {
        this.filaCruzamento.add(carro);
    }
    
    /**
     * retira o carro da fila e manda para dentro do cruzamento
     * @return
     */
    public Carro entrarCruzamento() {
        return this.filaCruzamento.poll();
    }

    /**
     * somente ve quem é o próximo carro
     * @return
     */
    public Carro verProximo() {
        return this.filaCruzamento.peek();
    }
}
