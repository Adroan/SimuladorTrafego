/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import model.Carro;
import model.Matriz;
import observer.Observer;

/**
 *
 * @author Adroan
 */
public class Gerenciador {

    private Matriz matriz = Matriz.getInstance();
    private static Gerenciador instance;
    private Leitor leitor;
    private File arquivo;
    private boolean emAndamento = true;
    private int carrosSpawnados = 0;
    private int quantidadeDeCarros;
    private boolean terminou = false;
    private double intervaloInsercao;
    private int modo;
    private String[] tiposEstrada = {"Exemplo 1", "Exemplo 2", "Exemplo3"};
    private List<Observer> observadores = new ArrayList<>();
    private ExecutorService executor;
    private int carrosMortos = 0;

    private Gerenciador() {
         executor = Executors.newCachedThreadPool();
    }

    public void addObservador(Observer obs) {
        observadores.add(obs);
    }

    public void lerMatriz(int modo) {
        try {
            leitor = new Leitor();
            leitor.lerMatriz(arquivo,modo);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Gerenciador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void escolherMatriz(int matriz) {
        switch (matriz) {
            case 0:
                arquivo = new File("src/malha-exemplo-1.txt");
                break;
            case 1:
                arquivo = new File("src/malha-exemplo-2.txt");
                break;
            case 2:
                arquivo = new File("src/malha-exemplo-3.txt");
                break;
        }
    }

    public synchronized static Gerenciador getInstance() {
        if (instance == null) {
            instance = new Gerenciador();
        }
        return instance;
    }

    public Icon getImageMatriz(int col, int row) {
        return matriz.getValorMatriz(row, col).getImagem();
    }

    public int getLinhaCount() {
        return matriz.getLinha();
    }

    public int getColunaCount() {
        return matriz.getColuna();
    }

    public String[] getTiposEstrada() {
        return tiposEstrada;
    }
    
    public void encerrarSimulacao(){
        emAndamento = false;
    }

    public void iniciarSimulacao(int qtdCarros, double intervalo, int modo) {
        this.quantidadeDeCarros = qtdCarros;
        this.intervaloInsercao = intervalo;
        this.modo = modo;
            matriz.setMatriz(null);
            lerMatriz(modo);
       
            notificarEstradaAlterada();
        if (intervaloInsercao == 0) {
            intervaloInsercao = 700;
        }       
        spawnarCarros();
    }
    
    public void spawnarCarros() {
        if ((carrosSpawnados < quantidadeDeCarros) && emAndamento) {
            Carro carro = new Carro(0, 0, 0, 0, quantidadeDeCarros, intervaloInsercao);           
            executor.execute(carro);
            carrosSpawnados++;
        }
    }
    
    public void reset(){
        carrosSpawnados = 0;
        carrosMortos = 0;
        emAndamento = true;
    }
    
    public void verificarFim() {
        carrosMortos++;
        if ((carrosMortos == carrosSpawnados)) {
            for (Observer obs : observadores) {
                obs.notificarSimulacaoEncerrada();
            }
        }
    }

    public void notificarEstradaAlterada() {
        for (Observer obs : observadores) {
            obs.notificarEstradaAlterada();
        }
    }

    public int getCarrosSpawnados() {
        return carrosSpawnados;
    }

    public int getQuantidadeDeCarros() {
        return quantidadeDeCarros;
    }

}

