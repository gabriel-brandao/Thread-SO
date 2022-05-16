package com.threads.implementacao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ThreadExecute {

    public static int TAM = 0;
    public static int numeroDeThreads = 0;
    public static int[][] matrizDeNumeros;
    public static ArrayList<Thread> listaDeThreads;

    private static final int rangeNumerosAleatorios = 100000001; // 0 - 100 Milhões
    private static final Scanner in = new Scanner(System.in);
    private static long start, now; //usado para calcular tempo de execução
 
    public static void menu() {
        int opcao = 0;

        while (opcao != 6) {

            System.out.println("1 - Definir tamanho da matriz");
            System.out.println("2 - Preencher a matriz com numeros aleatórios");
            System.out.println("3 - Executar com Threads");
            System.out.println("4 - Executar sem Threads");
            System.out.println("5 - Limpar tela");
            System.out.println("6 - Encerrar");

            opcao = in.nextInt();

            switch (opcao) {
                case 1:
                    definirTamanhoDaMatriz();
                    System.out.println("TAMANHO DA MATRIZ DEFINIDO EM "+TAM+" X "+TAM+"!!");
                break;

                case 2:
                    if(TAM != 0){
                        System.out.println("preenchendo matriz...");
                        preencherMatriz();
                        System.out.println("MATRIZ PREENCHIDA COM SUCESSO !!");
                    }
                     else
                        System.out.println("Tamanho da matriz NÃO definido !!");
                break;

                case 3:
                    if(matrizDeNumeros != null && TAM != 0){
                        definirNumeroDeThreads();
                        System.out.println("executando para "+numeroDeThreads+" threads...");
                        executar(true);
                        System.out.println("EXECUÇÃO FEITA COM : "+numeroDeThreads+" THREADS!!");
                        numerosPrimosETempo();
                    }
                     else
                        System.out.println("Matriz NÃO preenchida OU tamanho NÃO declarado!!");
                break;

                case 4:
                    if(matrizDeNumeros != null && TAM != 0){
                        System.out.println("executando modo sequencial...");
                        executar(false);
                        System.out.println("EXECUÇÃO SEQUENCIAL CONCLUÍDA!!");
                        numerosPrimosETempo();
                    }
                     else
                        System.out.println("Matriz NÃO preenchida OU tamanho NÃO declarado!!");
                break;

                case 5:
                    limpaTela();
                break;

                case 6:
                break;

                default:
                    System.out.println("OPÇÃO INVÁLIDA");
                break;
            }
        }
    }

    public static void executar(boolean isThread) {
        if (isThread)
            criarThreads();
        else
            modoSequencial();
    }

    public static void numerosPrimosETempo() {
        System.out.println("A matriz possui = " + ThreadRunnable.getQuantidadeDePrimos() + " números primos");
        System.out.println("O tempo levado para percorrer toda matriz foi de " +(now - start)/1000f+ "s ou "+(now - start)+"ms");
    }

    public static void definirTamanhoDaMatriz() {
        System.out.println("Informe o tamanho da matriz sendo que a matriz terá o formato TAMxTAM:");
        TAM = in.nextInt();
    }

    public static void preencherMatriz() {
        Random numero = new Random(2); // inicia semente para sempre gerar a mesma matriz
        matrizDeNumeros = new int[TAM][TAM];
        
        for (int i = 0; i < TAM; i++)
            for (int j = 0; j < TAM; j++)
                matrizDeNumeros[i][j] = numero.nextInt(rangeNumerosAleatorios);
    }

    public static void definirNumeroDeThreads() {
        System.out.println("Informe o numero de Threads que se deseja dividir a matriz:");
        numeroDeThreads = in.nextInt();
    }

    public static void criarThreads() {
        float iInicial, jInicial, iFinal, jFinal, indice;
        float numerosPorThreads = (TAM * TAM) / numeroDeThreads;;
        start = 0;

            listaDeThreads = new ArrayList<Thread>();
            ThreadRunnable.setQuantidadeDePrimos(0);// reinicia quantidade de numeros primos

            for (int n = 0; n < numeroDeThreads; n++) {

                iInicial = (n * numerosPorThreads) / TAM;
                jInicial = (n * numerosPorThreads) % TAM;

                indice = ((n + 1) * numerosPorThreads) / TAM;
                iFinal = ((indice * 10) % 10) == 0 ? (int) indice - 1 : (int) indice;

                indice = ((n + 1) * numerosPorThreads) % TAM;
                jFinal = indice == 0 ? TAM - 1 : (int) indice - 1;

                ThreadRunnable runnable = new ThreadRunnable((int) iInicial, (int) jInicial, (int) iFinal, (int) jFinal);
                Thread thread = new Thread(runnable);
                thread.start(); //thread iniciada

                listaDeThreads.add(thread); // guarda threads em um arrayList

                if (start == 0)// a partir da criação da primeira Thread começa a contar o tempo
                    start = System.currentTimeMillis();
            }
            
            // aguarda TODAS as threads morrerem (serem finalizadas) para só então contar o tempo final
            for (int i = 0; i < numeroDeThreads; i++) {
                try {
                    listaDeThreads.get(i).join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            now = System.currentTimeMillis();
    }

    public static void modoSequencial() {
        ThreadRunnable.setQuantidadeDePrimos(0); // reinicia quantidade de numeros primos

        start = System.currentTimeMillis();
            ThreadRunnable sequencial = new ThreadRunnable(TAM, TAM); //construtor para modo sequencial
        now = System.currentTimeMillis();
    }

    public static void imprimirMatriz() {
        for (int i = 0; i < TAM; i++) {
            for (int j = 0; j < TAM; j++)
                System.out.print(matrizDeNumeros[i][j] + " ");

            System.out.println("");
        }
    }

    public static void limpaTela(){
        try{
            String so = System.getProperty("os.name"); //checa qual o SO usado
            ProcessBuilder pb;

            if(so.contains("Windows"))      
                pb = new ProcessBuilder("cmd", "/c", "cls");
            else
                pb = new ProcessBuilder("clear");
            
            Process startProcess = pb.inheritIO().start();
            startProcess.waitFor();
        }catch(IOException | InterruptedException e){
            System.out.println("Erro ao limpar tela");
        }
    }
    
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName()+"7");
        menu();
    }
}
