package model;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    
    private final Integer tamanhoTotal;
    private Integer quantidadeAtual;
    private final Integer pilha[];
    private Lock chaveAlteracaoBuffer;
    private Condition condicaoBufferNaoVazio;
    private Condition condicaoBufferNaoCheio;
    
    public Buffer(Integer tamanho){
        tamanhoTotal = tamanho;
        quantidadeAtual = 0;
        pilha = new Integer[tamanhoTotal];
        chaveAlteracaoBuffer = new ReentrantLock(); 
	condicaoBufferNaoVazio = chaveAlteracaoBuffer.newCondition();
        condicaoBufferNaoCheio = chaveAlteracaoBuffer.newCondition();
    }
    
    public Boolean bufferVazio(){
        return (quantidadeAtual == 0);
    }
    
    public void inserir(Integer valor) throws InterruptedException{
        chaveAlteracaoBuffer.lock();
        try{
            while (quantidadeAtual == tamanhoTotal){
                System.out.println("Pilha Cheia!");
		condicaoBufferNaoCheio.await();
            }
            pilha[quantidadeAtual] = valor;
            quantidadeAtual++;
            System.out.println(this);
            condicaoBufferNaoVazio.signalAll();
        } finally{
            chaveAlteracaoBuffer.unlock();
        }
    }
    
    public Integer retirar() throws InterruptedException{
        chaveAlteracaoBuffer.lock();
        Integer valor = null;
        try{
            while (bufferVazio()){
                System.out.println("Pilha Vazia!");
		condicaoBufferNaoVazio.await();
            }
            quantidadeAtual--;
            valor = pilha[quantidadeAtual];
            System.out.println(this);
            condicaoBufferNaoCheio.signalAll();
        } finally{
            chaveAlteracaoBuffer.unlock();
        }
        return valor;
    }

    @Override
    public String toString() {
        String buffer = "";
        if (bufferVazio()) {
            buffer = "Buffer Vazio!";
        } else{
            buffer = "Valores Buffer:";
            for (int i = 0; i < quantidadeAtual; i++) {
                buffer += "\n" + "  " + pilha[i];
            }
        }
        return buffer;
    }
}