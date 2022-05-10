package com.threads.implementacao;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ThreadExecute {

    public static int TAM = 0;
    public static int numeroDeThreads = 0;
    public static ArrayList<ArrayList<Integer>> matrizDeNumeros;

    private static final int rangeNumerosAleatorios = 11; // 0 - 10
    private static final Scanner in = new Scanner(System.in);
    private static int somaNumeroDePrimos = 0;

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

        start = System.currentTimeMillis();

        if (numeroDeThreads != 0 && matrizDeNumeros.size() != 0) {
            numerosPorThreads = (TAM * TAM) / numeroDeThreads;

            ThreadRunnable.setQuantidadeDePrimos(0);

            for (int n = 0; n < numeroDeThreads; n++) {
                // converte um dos termos para float pois divisão entre inteiros sempre retorna
                // inteiro
                iInicial = (n * numerosPorThreads) / TAM;
                jInicial = (n * numerosPorThreads) % TAM;

                indice = ((n + 1) * numerosPorThreads) / TAM;
                iFinal = ((indice * 10) % 10) == 0 ? (int) indice - 1 : (int) indice;

                indice = ((n + 1) * numerosPorThreads) % TAM;
                jFinal = indice == 0 ? TAM - 1 : (int) indice - 1;
                // converte para indices inteiros
                ThreadRunnable thread = new ThreadRunnable((int) iInicial, (int) jInicial, (int) iFinal, (int) jFinal);
            }

        } else
            System.out.println("Numero de Threads não definida ou matriz não preenchida");

        now = System.currentTimeMillis();
    }

    public static void modoSerial() {
        start = System.currentTimeMillis();

        ThreadRunnable serial = null;

        ThreadRunnable.setQuantidadeDePrimos(0);

        if (matrizDeNumeros.size() != 0)
            serial = new ThreadRunnable(TAM, TAM);
        else
            System.out.println("matriz não preenchida");

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
