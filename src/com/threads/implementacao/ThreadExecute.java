package com.threads.implementacao;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ThreadExecute {

    public static int TAM = 0;
    public static int numeroDeThreads = 0;
    public static ArrayList<ArrayList<Integer>> matrizDeNumeros;

    private static final int rangeNumerosAleatorios = 11;
    private static final Scanner in = new Scanner(System.in);

    public static void menu() {
        int opcao = 0;

        while (opcao != 6) {

            System.out.println("1 - Definir tamanho da matriz");
            System.out.println("2 - Preencher a matriz com numeros aleatorios");
            System.out.println("3 - Definir número de Threads");
            System.out.println("4 - Executar");
            System.out.println("5 - Vizualizar quantidade de números primos e tempo de execução");
            System.out.println("6 - Encerrar");

            opcao = in.nextInt();

            switch (opcao) {

                case 1:
                    definirTamanhoDaMatriz();
                    break;

                case 2:
                    preencherMatriz();
                    imprimirMatriz();
                    break;

                case 3:
                    definirNumeroDeThreads();
                    break;

                case 4:
                    criarThreads();
                    break;

                case 5:

                    break;

                case 6:
                    break;

            }
        }

    }

    public static void definirTamanhoDaMatriz() {
        System.out.println("Informe o tamanho da matriz sendo que a matriz terá o formato TAMxTAM:");
        TAM = in.nextInt();
    }

    public static void preencherMatriz() {
        Random numero = new Random(2);
        ArrayList<Integer> linha;

        if (TAM != 0) {
            matrizDeNumeros = new ArrayList<ArrayList<Integer>>(TAM);
            for (int i = 0; i < TAM; i++) {
                linha = new ArrayList<Integer>(); // // linha.clear(); limpa linha

                for (int j = 0; j < TAM; j++)
                    linha.add(numero.nextInt(rangeNumerosAleatorios));

                matrizDeNumeros.add(linha);
            }
        } else
            System.out.println("Tamanho da matriz não definida");
    }

    public static void definirNumeroDeThreads() {
        System.out.println("Informe o numero de Threads que se deseja dividir a matriz:");
        numeroDeThreads = in.nextInt();
    }

    public static void criarThreads() {
        float iInicial, jInicial, iFinal, jFinal, indice;
        float numerosPorThreads;

        if (numeroDeThreads != 0 && matrizDeNumeros.size() != 0) {
            numerosPorThreads = (TAM * TAM) / numeroDeThreads;

            for (int n = 0; n < numeroDeThreads; n++) {
                // converte um dos termos para float pois divisão entre inteiros sempre retorna
                // inteiro
                iInicial = (n * numerosPorThreads) / TAM;
                jInicial = (n * numerosPorThreads) % TAM;

                indice = ((n + 1) * numerosPorThreads) / TAM;
                iFinal = ((indice * 10) % 10) == 0 ? (int)indice - 1 : (int)indice;

                indice = ((n + 1) * numerosPorThreads) % TAM;
                jFinal = indice == 0 ? TAM - 1 : (int)indice - 1;
                // converte para indices inteiros
                ThreadRunnable thread = new ThreadRunnable((int)iInicial, (int)jInicial, (int)iFinal, (int)jFinal);
            }

        } else
            System.out.println("Numero de Threads não definida ou matriz não preenchida");

    }

    public static void imprimirMatriz() {
        for (int i = 0; i < matrizDeNumeros.size(); i++) {
            for (int j = 0; j < matrizDeNumeros.get(i).size(); j++)
                System.out.print(matrizDeNumeros.get(i).get(j) + " ");

            System.out.println("");
        }
    }

    public static void main(String[] args) {
        /*
         * ThreadRunnable thread1 = new ThreadRunnable("#0", 500);
         * ThreadRunnable thread2 = new ThreadRunnable("#1", 500);
         * ThreadRunnable thread3 = new ThreadRunnable("#2", 500);
         * ThreadRunnable thread4 = new ThreadRunnable("#3", 500);
         */
        System.out.println(Thread.currentThread().getName());

        int a = 3 / 2;
        System.out.println(a);

        menu();

    }

}
