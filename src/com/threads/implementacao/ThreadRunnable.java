package com.threads.implementacao;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadRunnable implements Runnable {

    public static int somaNumeroDePrimos = 0;
    private int iInicial, jInicial, iFinal, jFinal;

    public ThreadRunnable(int iInicial, int jInicial, int iFinal, int jFinal) {
        this.iInicial = iInicial;
        this.jInicial = jInicial;
        this.iFinal = iFinal;
        this.jFinal = jFinal;

        Thread t = new Thread(this); // mesmo que Thread t = new Thread(new ThreadRunnable ()); fora dessa classe
        t.start();
    }

    @Override
    public void run() {
        int i = iInicial, j = jInicial;
      
        while(i != iFinal || j != jFinal+1){

            if(j == ThreadExecute.TAM){
                j = 0;
                i ++;
            }
/*
            System.out.println("[" + i + "]" + "[" + j + "] = " + ThreadExecute.matrizDeNumeros.get(i).get(j));
*/
            //synchronized(ThreadRunnable.class){
                if (isPrimo(ThreadExecute.matrizDeNumeros.get(i).get(j)))
                    somaNumeroDePrimos++;
            //}

            j ++;

        } 
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

    public static int getQuantidadeDePrimos(){
        return somaNumeroDePrimos;
    }
}
