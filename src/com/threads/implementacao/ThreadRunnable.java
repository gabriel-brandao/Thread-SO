package com.threads.implementacao;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadRunnable implements Runnable {

    public static int somaNumeroDePrimos = 0;
    private int iInicial, jInicial, iFinal, jFinal;

    private static final Semaphore SEMAFORO = new Semaphore(1);
    private static Lock lock = new ReentrantLock();

    public ThreadRunnable(int iInicial, int jInicial, int iFinal, int jFinal) {
        this.iInicial = iInicial;
        this.jInicial = jInicial;
        this.iFinal = iFinal;
        this.jFinal = jFinal;

        Thread t = new Thread(this); // mesmo que Thread t = new Thread(new ThreadRunnable ()); fora dessa classe
        t.start();
    }

    public ThreadRunnable(int iFinal, int jFinal) {
        this.iInicial = 0;
        this.jInicial = 0;
        this.iFinal = iFinal;
        this.jFinal = jFinal;

        modoSerial(iFinal, jFinal);
    }

    @Override
    public void run() {
        lock.lock();
        int i = iInicial, j = jInicial;
        while (i != iFinal || j != jFinal + 1) {

            if (j == ThreadExecute.TAM) {
                j = 0;
                i++;
            }

            // System.out.println(Thread.currentThread().getName() + "[" + i + "]" + "[" + j
            // + "] = "
            // + ThreadExecute.matrizDeNumeros.get(i).get(j) + " - " + somaNumeroDePrimos);
            if (isPrimo(ThreadExecute.matrizDeNumeros.get(i).get(j)))
                somaNumeroDePrimos++;

            j++;
        }
        lock.unlock();
    }

    public void modoSerial(int iFinal, int jFinal) {
        for (int i = 0; i < iFinal; i++)
            for (int j = 0; j < jFinal; j++)
                if (isPrimo(ThreadExecute.matrizDeNumeros.get(i).get(j)))
                    somaNumeroDePrimos++;
    }

    public boolean isPrimo(int numero) {

        if (numero <= 1)
            return false;

        for (int divisor = 2; Math.pow(divisor, 2) <= numero; divisor++)
            if (numero % divisor == 0)
                return false; // se achar algum divisor menor ou igual do que a raiz quadrada do proprio
                              // número, entao nao é primo

        return true;
    }

    public static int getQuantidadeDePrimos() {
        return somaNumeroDePrimos;
    }

    public static void setQuantidadeDePrimos(int somaNumeroDePrimos) {
        ThreadRunnable.somaNumeroDePrimos = somaNumeroDePrimos;
    }
}
