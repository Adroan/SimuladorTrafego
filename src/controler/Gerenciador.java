/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;



/**
 *
 * @author Adroan
 */
public class Gerenciador {

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
//    private List<Carro> carros = new ArrayList<>();
//    private Random rand = new Random();
//    private Matriz matriz;
//
//    public Gerenciador(Matriz matriz) {
//        this.matriz = matriz;
//    }
//
//    public void nascer() {
//        int orientacao = rand.nextInt(4);
////        int orientacao =  4;
//        switch (orientacao) {
//            case 0:
////                System.out.println("sul");
//                nascerSul();
//                break;
//            case 1:
////                System.out.println("oeste");
//                nascerOeste();
//                break;
//            case 2:
////                System.out.println("norte");
//                nascerNorte();
//                break;
//            case 3:
////                System.out.println("leste");
//                nascerLeste();
//                break;
//        }
//    }
//
//    private void nascerSul() {
//        List<Integer> posicoes = new ArrayList<>();
//        for (int i = 0; i < matriz.getColuna(); i++) {
//            if (matriz.getValorMatriz(matriz.getLinha() - 1, i) == 1) {
//                posicoes.add(i);
//            }
//        }
//        int colunaNascer = rand.nextInt(posicoes.size());
//        Carro carro = new Carro(matriz.getLinha() - 1, posicoes.get(colunaNascer), matriz.getValorMatriz(matriz.getLinha() - 1,posicoes.get(colunaNascer)));
//        carros.add(carro);
//    }
//
//    private void nascerOeste() {
//        List<Integer> posicoes = new ArrayList<>();
//        for (int i = 0; i < matriz.getLinha(); i++) {
//            if (matriz.getValorMatriz(i, 0) == 2) {
//                posicoes.add(i);
//            }
//        }
//        int colunaNascer = rand.nextInt(posicoes.size());
//        Carro carro = new Carro(posicoes.get(colunaNascer), 0,matriz.getValorMatriz(posicoes.get(colunaNascer),0));
//        carros.add(carro);
//    }
//
//    private void nascerNorte() {
//        List<Integer> posicoes = new ArrayList<>();
//        for (int i = 0; i < matriz.getColuna(); i++) {
//            if (matriz.getValorMatriz(0, i) == 3) {
//                posicoes.add(i);
//            }
//        }
//        int colunaNascer = rand.nextInt(posicoes.size());
//        Carro carro = new Carro(0, posicoes.get(colunaNascer),matriz.getValorMatriz(0,posicoes.get(colunaNascer)));
//        carros.add(carro);
//    }
//
//    private void nascerLeste() {
//        List<Integer> posicoes = new ArrayList<>();
//        for (int i = 0; i < matriz.getLinha(); i++) {
//            if (matriz.getValorMatriz(i, matriz.getColuna() - 1) == 4) {
//                posicoes.add(i);
//            }
//        }
//        int colunaNascer = rand.nextInt(posicoes.size());
//        Carro carro = new Carro(posicoes.get(colunaNascer), matriz.getColuna() - 1,matriz.getValorMatriz(posicoes.get(colunaNascer),matriz.getColuna()-1));
//        carros.add(carro);
//    }
//
//    public void imprimirCarros() {
//        for (Carro carro : carros) {
//            System.out.println("nasceu linha: " + carro.getLinha() + " Coluna:" + carro.getColuna());
//        }
//    }
//
//    private void andar(Carro carro, int proxLinha, int proxColuna) {
//        carro.setLinha(proxLinha);
//        carro.setColuna(proxColuna);
//    }
//
//    public Carro dirigir(Carro carro) {
//        int itemMatriz = carro.getItemPosicao();
//        switch (itemMatriz) {
//            case 1:
//                andar(carro,carro.getLinha()-1,carro.getColuna());
//                break;
//            case 2:
//                andar(carro,carro.getLinha(),carro.getColuna()+1);
//                break;
//            case 3:
//                andar(carro,carro.getLinha()+1,carro.getColuna());
//                break;
//            case 4:
//                andar(carro,carro.getLinha(),carro.getColuna()-1);
//                break;
//            case 5:
//            case 6:
//            case 7:
//            case 8:
//            case 9:
//            case 10:
//            case 11:
//            case 12:
//        }
//        return carro;
//    }
//    private int proxItemMatriz(Carro carro){
//        int proxItem = 0;
//        switch(carro.getItemPosicao()){
//            case 1:
//                proxItem = matriz.getValorMatriz(carro.getColuna(), carro.getLinha()-1);
//                break;
//            case 2:
//                proxItem = matriz.getValorMatriz(carro.getColuna()+1, carro.getLinha());
//                break;
//            case 3:
//                proxItem = matriz.getValorMatriz(carro.getLinha()+1,carro.getColuna());
//                break;
//            case 4:
//                proxItem = matriz.getValorMatriz(carro.getLinha(),carro.getColuna()-1);
//                break;
//        }
//        return proxItem;
//    }

}
