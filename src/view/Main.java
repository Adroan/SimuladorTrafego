/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controler.Gerenciador;
import controler.Leitor;
import java.io.File;
import java.io.FileNotFoundException;
import model.Matriz;

/**
 *
 * @author Adroan
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Leitor ler = new Leitor();
        File arquivo = new File("src/malha-exemplo-2.txt");
        ler.lerMatriz(arquivo);
        ler.imprimirMatriz();
    }
}
