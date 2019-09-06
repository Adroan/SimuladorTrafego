/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

import model.Carro;

/**
 *
 * @author Adroan
 */
public interface Buffer {

    public void addCarro(Carro carro) throws Exception;

    public Carro removerCarro() throws Exception;

}
