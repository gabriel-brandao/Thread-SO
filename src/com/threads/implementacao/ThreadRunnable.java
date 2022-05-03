package com.threads.implementacao;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadRunnable implements Runnable {

    private String nome;
    private int tempo;

    public static int i = -1; //variavel estatica pertence a classe nao a instancia

    public ThreadRunnable(String nome, int tempo) {
        this.nome = nome;
        this.tempo = tempo;

        Thread t = new Thread(this); 
        t.start();
    }

    public void run() {
        synchronized (ThreadRunnable.class) {          
            try {
                i++;
                Thread.sleep(tempo);
                System.out.println(Thread.currentThread().getName() + ":->" + i);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
    }
}