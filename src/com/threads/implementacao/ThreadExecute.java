package com.threads.implementacao;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ThreadExecute {

    public static int TAM = 0;
    public static int numeroDeThreads = 0;
    public static ArrayList<ArrayList<Integer>> matrizDeNumeros;
    public static ArrayList<Thread> listaDeThreads;

    private static final int rangeNumerosAleatorios = 11; // 0 - 10
    private static final Scanner in = new Scanner(System.in);
    private static long start, now;

    public static void menu() {
        int opcao = 0;

        while (opcao != 7) {

            System.out.println("1 - Definir tamanho da matriz");
            System.out.println("2 - Preencher a matriz com numeros aleatorios");
            System.out.println("3 - Definir número de Threads");
            System.out.println("4 - Executar com Threads");
            System.out.println("5 - Executar sem Threads");
            System.out.println("6 - Vizualizar quantidade de números primos e tempo de execução");
            System.out.println("7 - Encerrar");

            opcao = in.nextInt();

            switch (opcao) {

                case 1:
                    definirTamanhoDaMatriz();
                    break;

                case 2:
                    preencherMatriz();
                    // imprimirMatriz();
                    break;

                case 3:
                    definirNumeroDeThreads();
                    break;

                case 4:
                    executar(true);
                    break;

                case 5:
                    executar(false);
                    break;

                case 6:
                    numerosPrimosETempo();
                    break;

                case 7:
                    break;

                default:
                    System.out.println("Opção inválida");
                    break;

            }
        }

    }

    public static void executar(boolean isThread) {
        if (isThread)
            criarThreads();
        else
            modoSerial();
    }

    public static void numerosPrimosETempo() {
        System.out.println("A matriz possui = " + ThreadRunnable.getQuantidadeDePrimos());
        System.out.println("O tempo levado para percorrer toda matriz foi de = " + (now - start) + "ms");
    }

    public static void definirTamanhoDaMatriz() {
        System.out.println("Informe o tamanho da matriz sendo que a matriz terá o formato TAMxTAM:");
        TAM = in.nextInt();
    }

    public static void preencherMatriz() {
        Random numero = new Random(2); // inicia semente para sempre gerar a mesma matriz
        ArrayList<Integer> linha;

        matrizDeNumeros = new ArrayList<ArrayList<Integer>>(TAM);
        for (int i = 0; i < TAM; i++) {
            linha = new ArrayList<Integer>();

            for (int j = 0; j < TAM; j++)
                linha.add(numero.nextInt(rangeNumerosAleatorios));

            matrizDeNumeros.add(linha); // adiciona uma linha a cada posição, formando uma matriz
        }
    }

    public static void definirNumeroDeThreads() {
        System.out.println("Informe o numero de Threads que se deseja dividir a matriz:");
        numeroDeThreads = in.nextInt();
    }

    public static void criarThreads() {
        float iInicial, jInicial, iFinal, jFinal, indice;
        float numerosPorThreads;
        start = 0;

        listaDeThreads = new ArrayList<Thread>();
        numerosPorThreads = (TAM * TAM) / numeroDeThreads;

        ThreadRunnable.setQuantidadeDePrimos(0);// reinicia quantidade de numeros primos

        for (int n = 0; n < numeroDeThreads; n++) {

            iInicial = (n * numerosPorThreads) / TAM;
            jInicial = (n * numerosPorThreads) % TAM;

            indice = ((n + 1) * numerosPorThreads) / TAM;
            iFinal = ((indice * 10) % 10) == 0 ? (int) indice - 1 : (int) indice;

            indice = ((n + 1) * numerosPorThreads) % TAM;
            jFinal = indice == 0 ? TAM - 1 : (int) indice - 1;

            ThreadRunnable runnable = new ThreadRunnable((int) iInicial, (int) jInicial, (int) iFinal, (int) jFinal);
            Thread thread = new Thread(runnable); // mesmo que Thread t = new Thread(new ThreadRunnable ()); fora dessa
                                                  // classe
            thread.start();

            listaDeThreads.add(thread); // guarda threads em um arrayList

            if (start == 0)// a partir da criação da primeira Thread começa a contar o tempo
                start = System.currentTimeMillis();
        }
        // aguarda TODAS as threads morrerem (serem finalizadas) para só então contar o
        // tempo final
        for (int i = 0; i < numeroDeThreads; i++) {
            while (listaDeThreads.get(i).isAlive()) {
            }
        }
        now = System.currentTimeMillis();
    }

    public static void modoSerial() {
        ThreadRunnable.setQuantidadeDePrimos(0); //// reinicia quantidade de numeros primos

        start = System.currentTimeMillis();
        ThreadRunnable serial = new ThreadRunnable(TAM, TAM);
        now = System.currentTimeMillis();
    }

    public static void imprimirMatriz() {
        for (int i = 0; i < matrizDeNumeros.size(); i++) {
            for (int j = 0; j < matrizDeNumeros.get(i).size(); j++)
                System.out.print(matrizDeNumeros.get(i).get(j) + " ");

            System.out.println("");
        }
    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().getName());
        menu();
    }

}
