/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.Estrada;
import model.Matriz;

/**
 *
 * @author Adroan
 */
public class Leitor {

    public void lerMatriz(File arquivo) throws FileNotFoundException {
        Scanner in = new Scanner(arquivo);

        int linha = Integer.parseInt(in.next().trim());
        int coluna = Integer.parseInt(in.next().trim());
        System.out.println("Linha: " + linha + " Coluna: " + coluna);
        Matriz matriz = Matriz.getInstance();
        matriz.criarMatriz(linha, coluna);
        matriz.setLinha(linha);
        matriz.setColuna(coluna);
        
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                String valor = in.next().trim();
                matriz.setValorMatriz(i, j, new Estrada(i,j,Integer.parseInt(valor)));
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
