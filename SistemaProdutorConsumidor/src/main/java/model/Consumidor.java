package model;

public class Consumidor implements Runnable{

    private static final int DELAY = 1000;
    private final Buffer buffer;
    private final Integer count;
    
    public Consumidor(Buffer buffer, Integer count){
	this.buffer = buffer;
	this.count = count;
    }
    
    @Override
    public void run() {
        try{
            for (int i = 1; i <= count; i++) {
                System.out.println("Retirando Valor: " + buffer.retirar());
                Thread.sleep(DELAY);
            }
        }
        catch (InterruptedException exception) {}
    }
}