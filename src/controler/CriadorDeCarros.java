/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import model.Carro;
import model.Matriz;

/**
 *
 * @author Adroan
 */
public class CriadorDeCarros implements Buffer {
    Matriz matriz = Matriz.getInstance();
    private Carro[] carros;
    private int quantidade = 0;
    private int inicio = 0;
    private int fim = 0;
    private Random rand = new Random();
    
    private Semaphore mutex;
    private Semaphore cheio;
    private Semaphore livre;

    public CriadorDeCarros(int capacidade) {
        this.carros = new Carro[capacidade];
        cheio=new Semaphore(0);
        livre = new Semaphore(capacidade);
        mutex = new Semaphore(1);
    }

    @Override
    public void addCarro(Carro carro) throws Exception {
        try {
            livre.acquire();
            mutex.acquire();
            if (quantidade == carros.length) {
                throw new Exception("Buffer cheio");
            }

            carros[fim] = carro;
            fim = (fim + 1) % carros.length;
            quantidade++;
        } catch (InterruptedException e) {
            System.out.println("interrompido");
            e.printStackTrace();
        }finally{
            mutex.release();
            cheio.release();
        }
    }

    @Override
    public Carro removerCarro() throws Exception {
       Carro carro = null;
       try{
           
           cheio.acquire();
           mutex.acquire();
           if(quantidade == 0){
               throw new Exception("Buffer vazio");
           }
           carro = carros[inicio];
           carros[inicio] = null;
           inicio = (inicio +1) % carros.length;
           quantidade--;
       }catch(InterruptedException e){
           System.out.println("interrompido");
           e.printStackTrace();
           return null;
       }finally{
           mutex.release();
           livre.release();
       }      return carro;
    }
    
    public void spawn() throws Exception{
        
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
        Gerenciador ger = Gerenciador.getInstance();
        ger.notificarEstradaAlterada();
    }

//
    private void nascerSul() throws Exception {
        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < matriz.getColuna(); i++) {
            if (matriz.getValorMatriz(matriz.getLinha() - 1, i).getItem() == 1) {
                posicoes.add(i);
            }
        }
        int colunaNascer = rand.nextInt(posicoes.size());
        
        Carro carro =new Carro(0,0,0,geradorVelocidade());
        matriz.getValorMatriz(matriz.getLinha()-1, posicoes.get(colunaNascer)).addCarroEstrada(carro);
        carro.start();
        addCarro(carro);
        
    }

    private void nascerOeste() throws Exception {
        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < matriz.getLinha(); i++) {
            if (matriz.getValorMatriz(i, 0).getItem() == 2) {
                posicoes.add(i);
            }
        }
        int linhaNascer = rand.nextInt(posicoes.size());
        Carro carro =new Carro(0,0,0,geradorVelocidade());
        matriz.getValorMatriz(posicoes.get(linhaNascer), 0).addCarroEstrada(carro);
        carro.start();
        addCarro(carro);
        
    }

    private void nascerNorte() throws Exception {
        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < matriz.getColuna(); i++) {
            if (matriz.getValorMatriz(0, i).getItem() == 3) {
                posicoes.add(i);
            }
        }
        int colunaNascer = rand.nextInt(posicoes.size());
        
        Carro carro =new Carro(0,0,0,geradorVelocidade());
        matriz.getValorMatriz(0, posicoes.get(colunaNascer)).addCarroEstrada(carro);
        carro.start();
        addCarro(carro);
    }

    private void nascerLeste() throws Exception {
        List<Integer> posicoes = new ArrayList<>();
        for (int i = 0; i < matriz.getLinha(); i++) {
            if (matriz.getValorMatriz(i, matriz.getColuna() - 1).getItem() == 4) {
                posicoes.add(i);
            }
        }
        int linhaNascer = rand.nextInt(posicoes.size());
        Carro carro =new Carro(0,0,0,geradorVelocidade());
        matriz.getValorMatriz(posicoes.get(linhaNascer), matriz.getColuna()-1).addCarroEstrada(carro);
        carro.start();
        addCarro(carro);
        
    }
    
    private double geradorVelocidade(){
        return 250 +(rand.nextDouble()*(3000-250));
    }
}
