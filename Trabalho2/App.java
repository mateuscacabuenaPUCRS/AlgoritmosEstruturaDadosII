package Trabalho2;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        long tempoInicial = System.currentTimeMillis();
        DigrafoValorado g = new DigrafoValorado();
        Scanner in = new Scanner(System.in);

        do {
            try {
                System.out.println("Bem-vindo ao sistema de controle de hidrogênio!");
                System.out.println("Digite o nome do arquivo (ou digite 0 para sair do programa):");
                String nomeArquivo = in.nextLine();

                if (nomeArquivo.equals("0")) {
                    System.out.println("Saindo do programa...");
                    break;
                }

                g.lerArquivo(nomeArquivo);
                System.out.println("Custo: " + g.getCusto());
                long tempoFinal = System.currentTimeMillis();
                printTime(tempoFinal - tempoInicial);
                System.out.println("");
            } catch (FileNotFoundException e) {
                System.out.println("Erro: Arquivo não encontrado. Por favor, verifique o nome do arquivo e tente novamente.");
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }
        } while (true);

        in.close();
    }

      private static void printTime(long time) {
        long minutes = time / 60000;
        long seconds = (time % 60000) / 1000;
        long milis = time % 1000;
        System.out.println("Tempo de execucao: " + minutes + " min, " + seconds + " seg, " + milis + " ms");
    }
}
