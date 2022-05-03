package com.threads.implementacao;

public class ThreadExecute {

    public static void main(String[] args) {
        ThreadRunnable thread1 = new ThreadRunnable("#0", 100);
        ThreadRunnable thread2 = new ThreadRunnable("#1", 20);
        ThreadRunnable thread3 = new ThreadRunnable("#2", 150);
        ThreadRunnable thread4 = new ThreadRunnable("#3", 75);

        
        System.out.println(Thread.currentThread().getName());
    }

}