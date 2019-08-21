/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import model.Carro;
import model.Matriz;

/**
 *
 * @author Adroan
 */
public class Gerenciador {

    private List<Carro> carros = new ArrayList<>();
    private Random rand = new Random();
    private Matriz matriz;

    public Gerenciador(Matriz matriz) {
        this.matriz = matriz;
    }

    public void nascer() {
        int orientacao = rand.nextInt(4);
//        int orientacao =  4;
        switch (orientacao) {
            case 0:
//                System.out.println("sul");
                nascerSul();
                break;
            case 1:
//                System.out.println("oeste");
                nascerOeste();
                break;
            case 2:
//                System.out.println("norte");
                nascerNorte();
                break;
            case 3:
//                System.out.println("leste");
                nascerLeste();
                break;
        }
    }

    private void nascerSul() {
        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < matriz.getColuna(); i++) {
            if (matriz.getValorMatriz(matriz.getLinha() - 1, i) == 1) {
                posicoes.add(i);
            }
        }
        int colunaNascer = rand.nextInt(posicoes.size());
        Carro carro = new Carro(matriz.getLinha() - 1, posicoes.get(colunaNascer));
        carros.add(carro);
    }

    private void nascerOeste() {
        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < matriz.getLinha(); i++) {
            if (matriz.getValorMatriz(i, 0) == 2) {
                posicoes.add(i);
            }
        }
        int colunaNascer = rand.nextInt(posicoes.size());
        Carro carro = new Carro(posicoes.get(colunaNascer), 0);
        carros.add(carro);
    }

    private void nascerNorte() {
        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < matriz.getColuna(); i++) {
            if (matriz.getValorMatriz(0, i) == 3) {
                posicoes.add(i);
            }
        }
        int colunaNascer = rand.nextInt(posicoes.size());
        Carro carro = new Carro(0, posicoes.get(colunaNascer));
        carros.add(carro);
    }

    private void nascerLeste() {
        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < matriz.getLinha(); i++) {
            if (matriz.getValorMatriz(i, matriz.getColuna() - 1) == 4) {
                posicoes.add(i);
            }
        }
        int colunaNascer = rand.nextInt(posicoes.size());
        Carro carro = new Carro(posicoes.get(colunaNascer), matriz.getColuna() - 1);
        carros.add(carro);
    }

    public void imprimirCarros() {
        for (Carro carro : carros) {
            System.out.println("nasceu linha: " + carro.getLinha() + " Coluna:" + carro.getColuna());
        }
    }

    private void andar(Carro carro, int proxLinha, int proxColuna) {
        carro.setLinha(proxLinha);
        carro.setColuna(proxColuna);
    }

    public Carro dirigir(Carro carro) {
        int linhaCarro = carro.getLinha();
        int colunaCarro = carro.getColuna();
        int itemMatriz = matriz.getValorMatriz(linhaCarro, colunaCarro);
        switch (itemMatriz) {
            case 1:
                andar(carro,carro.getLinha()-1,carro.getColuna());
                break;
            case 2:
                andar(carro,carro.getLinha(),carro.getColuna()+1);
                break;
            case 3:
                andar(carro,carro.getLinha()+1,carro.getColuna());
                break;
            case 4:
                andar(carro,carro.getLinha(),carro.getColuna()-1);
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            case 10:
            case 11:
            case 12:
        }
    }

}
