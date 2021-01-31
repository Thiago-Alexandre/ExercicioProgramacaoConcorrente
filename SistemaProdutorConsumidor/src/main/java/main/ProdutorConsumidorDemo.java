package main;

import model.Buffer;
import model.Consumidor;
import model.Produtor;

public class ProdutorConsumidorDemo {
    
    public static void main(String[] args) throws InterruptedException {
        
        Buffer buffer = new Buffer(5);
        Produtor produtor = new Produtor(buffer, 10);
        Consumidor consumidor = new Consumidor(buffer, 20);
        Thread produtorT = new Thread(produtor);
        Thread produtor2T = new Thread(produtor);
        Thread consumidorT = new Thread(consumidor);
        produtorT.start();
        produtor2T.start();
        consumidorT.start(); 
    }
}