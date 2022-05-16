package com.threads.implementacao;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadRunnable implements Runnable {

    public static int somaNumeroDePrimos = 0; //variável compartilhada
    private int iInicial, jInicial, iFinal, jFinal;

    private static Lock lock = new ReentrantLock();//para controle da zona crítica

    // contrutor para o modo do Threads
    public ThreadRunnable(int iInicial, int jInicial, int iFinal, int jFinal) {
        this.iInicial = iInicial;
        this.jInicial = jInicial;
        this.iFinal = iFinal;
        this.jFinal = jFinal;
    }

    // construtor para o modo sequencial
    public ThreadRunnable(int iFinal, int jFinal) {
        this.iInicial = 0;
        this.jInicial = 0;
        this.iFinal = iFinal;
        this.jFinal = jFinal;

        modoSequencial();
    }

    @Override
    // execução no modo Thread
    public void run() {
        int i = iInicial, j = jInicial;
        int somaNumeroDePrimosLocal = 0;

        while (i != iFinal || j != jFinal + 1) {
            if (j == ThreadExecute.TAM) {
                j = 0;
                i++;
            }
            if (isPrimo(ThreadExecute.matrizDeNumeros[i][j]))
                somaNumeroDePrimosLocal++;

            j++;
        }

        lock.lock();
            somaNumeroDePrimos += somaNumeroDePrimosLocal; //soma na variável global compartilhada
        lock.unlock();
    }

    // execução no modo serial
    public void modoSequencial() {
        for (int i = iInicial; i < iFinal; i++)
            for (int j = jInicial; j < jFinal; j++)
                if (isPrimo(ThreadExecute.matrizDeNumeros[i][j]))
                    somaNumeroDePrimos++;
    }

    public boolean isPrimo(int numero) {
        if (numero <= 1)
            return false;

        for (int divisor = 2; Math.pow(divisor, 2) <= numero; divisor++)
            if (numero % divisor == 0)
                return false; // se achar algum divisor menor ou igual do que a raiz quadrada do proprio número, entao nao é primo
        return true;
    }

    public static int getQuantidadeDePrimos() {
        return somaNumeroDePrimos;
    }

    public static void setQuantidadeDePrimos(int somaNumeroDePrimos) {
        ThreadRunnable.somaNumeroDePrimos = somaNumeroDePrimos;
    }
}