package model;

import java.util.Random;

public class Produtor implements Runnable{

    private static final int DELAY = 1000;
    private final Buffer buffer;
    private final Random valor;
    private final Integer count;
    
    public Produtor(Buffer buffer, Integer count){
	this.buffer = buffer; 
	valor = new Random();
	this.count = count;
    }
    
    @Override
    public void run() {
        try{
            for (int i = 1; i <= count; i++) {
                Integer v = valor.nextInt(50);
                System.out.println("Inserindo Valor: " + v);
                buffer.inserir(v);
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException exception) {}
    }
}