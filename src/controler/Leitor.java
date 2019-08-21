/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import model.Matriz;

/**
 *
 * @author Adroan
 */
public class Leitor {

    public Matriz lerMatriz(File arquivo) throws FileNotFoundException {
        Scanner in = new Scanner(arquivo);

        int linha = Integer.parseInt(in.next().trim());
        int coluna = Integer.parseInt(in.next().trim());
        System.out.println("Linha: " + linha + " Coluna: " + coluna);
        int[][] matriz = new int[linha][coluna];
        for (int i = 0; i < linha; i++) {
            for (int j = 0; j < coluna; j++) {
                String valor = in.next().trim();
                matriz[i][j] = Integer.parseInt(valor);
            }
        }
        Matriz obj = new Matriz(linha, coluna, matriz);
        return obj;
    }

    public void imprimirMatriz(Matriz matriz) {
        System.out.println("imprimindo.....");
        for (int i = 0; i < matriz.getLinha(); i++) {
            for (int j = 0; j < matriz.getColuna(); j++) {
                System.out.printf(matriz.getValorMatriz(i, j) + "\t");
            }
            System.out.println("");
        }
        System.out.println("impresso!");
    }
}
