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
public class GerenciadorCarros implements Buffer {
    Matriz matriz = Matriz.getInstance();
    private Carro[] carros;
    private int quantidade = 0;
    private int inicio = 0;
    private int fim = 0;

    public GerenciadorCarros(int capacidade) {
        this.carros = new Carro[capacidade];
    }

    @Override
    public void addCarro(Carro carro) throws Exception {
        try {
            if (quantidade == carros.length) {
                throw new Exception("Buffer cheio");
            }

            carros[fim] = carro;
            fim = (fim + 1) % carros.length;
            quantidade++;
        } catch (InterruptedException e) {
            System.out.println("interrompido");
            e.printStackTrace();
        }
    }

    @Override
    public Carro removerCarro() throws Exception {
       Carro carro = null;
       try{
           if(quantidade == 0){
               throw new Exception("Buffer vazio");
           }
           carro = carros[inicio];
           carros[inicio] = null;
           inicio = (inicio +1) % carros.length;
           quantidade--;
       }catch(InterruptedException e){
           System.out.println("interrompido");
       }
       return carro;
    }
    
    public void spawn(){
        
    }

}
