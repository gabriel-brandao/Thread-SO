package com.threads.implementacao;

public class ThreadRunnable implements Runnable {

    private static int somaNumeroDePrimos = 0;
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
        int flag = -1;

        System.out.println("Intervalos [" + iInicial + "][" + jInicial + "] [" + iFinal + "][" + jFinal + "]");
        for (int i = iInicial; i < ThreadExecute.TAM; i++) {
            for (int j = jInicial; j < ThreadExecute.TAM;) {

                System.out.println("[" + i + "]" + "[" + j + "] = " + ThreadExecute.matrizDeNumeros.get(i).get(j));

                if (isPrimo(ThreadExecute.matrizDeNumeros.get(i).get(j)))
                    synchronized (ThreadRunnable.class) {
                        somaNumeroDePrimos++;
                        // System.out.println(
                        // Thread.currentThread().getName() + "[" + i + "]" + "[" + j + "] = "
                        // + ThreadExecute.matrizDeNumeros.get(i).get(j) + " - " + somaNumeroDePrimos);
                    }

                if (i == iFinal && j == jFinal) {
                    flag = 0;
                    break;
                }

                j++;
                j %= ThreadExecute.TAM;
            }
            if (flag == 0)
                break;
        }

        /*
         * int i = 0;
         * synchronized (ThreadRunnable.class) { // faz a sincronização da variavel
         * static da classe
         * somaNumeroDePrimos++;
         * System.out.println(Thread.currentThread().getName() + ":" + "[" + iInicial +
         * "]" + "[" + jInicial + "] : "
         * + "[" + iFinal + "]" + "[" + jFinal + "]" + somaNumeroDePrimos);
         * }
         */

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
}
