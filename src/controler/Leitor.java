/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.ImageIcon;
import model.Estrada;
import model.EstradaMonitor;
import model.EstradaSemaforo;
import model.Matriz;

/**
 *
 * @author Adroan
 */
public class Leitor {

    public void lerMatriz(File arquivo,int modo) throws FileNotFoundException {
        Scanner in = new Scanner(arquivo);

        int linha = Integer.parseInt(in.next().trim());
        int coluna = Integer.parseInt(in.next().trim());
        System.out.println("Linha: " + linha + " Coluna: " + coluna);
        Matriz matriz = Matriz.getInstance();
        matriz.criarMatriz(linha, coluna);
        matriz.setLinha(linha);
        matriz.setColuna(coluna);
        if(modo ==1){
            System.out.println("semaforo");
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                int valor = Integer.parseInt(in.next().trim());
                if (valor == 5 || valor == 6 || valor == 7 || valor == 8 || valor == 9 || valor == 10 || valor == 11 || valor == 12) {
                    matriz.setValorMatriz(i, j, new EstradaSemaforo(i, j, valor, null, true, "assets/cruzamento.png"));
                } else if (valor != 0) {
                    matriz.setValorMatriz(i, j, new EstradaSemaforo(i, j, valor, null, false, "assets/estrada" + valor + ".png"));
                } else {
                    matriz.setValorMatriz(i, j, new EstradaSemaforo(i, j, valor, null, false, "assets/grama.png"));
                }
                try {
                    if (matriz.getValorMatriz(i, j).getItem() == 1 && matriz.getValorMatriz(i, j - 1).getItem() == 2 || matriz.getValorMatriz(i, j).getItem() == 2 && matriz.getValorMatriz(i - 1, j).getItem() == 3) {
                        matriz.getValorMatriz(i, j).setImagem(new ImageIcon("assets/curva.png"));
                    }else if( matriz.getValorMatriz(i, j).getItem() == 4 && matriz.getValorMatriz(i, j - 1).getItem() == 3){
                        matriz.getValorMatriz(i, j-1).setImagem(new ImageIcon("assets/curva.png"));
                    }else if(matriz.getValorMatriz(i, j).getItem() == 1 && matriz.getValorMatriz(i-1, j).getItem() == 4){
                        matriz.getValorMatriz(i-1, j).setImagem(new ImageIcon("assets/curva.png"));
                    }
                } catch (Exception e) {
                    
                }
            }
        }
        }else{
            System.out.println("monitor");
            for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                int valor = Integer.parseInt(in.next().trim());
                if (valor == 5 || valor == 6 || valor == 7 || valor == 8 || valor == 9 || valor == 10 || valor == 11 || valor == 12) {
                    matriz.setValorMatriz(i, j, new EstradaMonitor(i, j, valor, null, true, "assets/cruzamento.png"));
                } else if (valor != 0) {
                    matriz.setValorMatriz(i, j, new EstradaMonitor(i, j, valor, null, false, "assets/estrada" + valor + ".png"));
                } else {
                    matriz.setValorMatriz(i, j, new EstradaMonitor(i, j, valor, null, false, "assets/gramaOutono.png"));
                }
                try {
                    if (matriz.getValorMatriz(i, j).getItem() == 1 && matriz.getValorMatriz(i, j - 1).getItem() == 2 || matriz.getValorMatriz(i, j).getItem() == 2 && matriz.getValorMatriz(i - 1, j).getItem() == 3) {
                        matriz.getValorMatriz(i, j).setImagem(new ImageIcon("assets/curva.png"));
                    }else if( matriz.getValorMatriz(i, j).getItem() == 4 && matriz.getValorMatriz(i, j - 1).getItem() == 3){
                        matriz.getValorMatriz(i, j-1).setImagem(new ImageIcon("assets/curva.png"));
                    }else if(matriz.getValorMatriz(i, j).getItem() == 1 && matriz.getValorMatriz(i-1, j).getItem() == 4){
                        matriz.getValorMatriz(i-1, j).setImagem(new ImageIcon("assets/curva.png"));
                    }
                } catch (Exception e) {
                    
                }
            }
        }
        }

    }

    public void imprimirMatriz() {
        System.out.println("imprimindo.....");
        Matriz matriz = Matriz.getInstance();
        for (int i = 0; i < matriz.getLinha(); i++) {
            for (int j = 0; j < matriz.getColuna(); j++) {
                System.out.printf(matriz.getValorMatriz(i, j).toString() + "\t");
            }
            System.out.println("");
        }
        System.out.println("impresso!");
    }


}
