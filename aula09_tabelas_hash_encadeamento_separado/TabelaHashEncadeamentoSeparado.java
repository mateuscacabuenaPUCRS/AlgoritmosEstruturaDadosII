package aula09_tabelas_hash_encadeamento_separado;

import java.util.Scanner;

public class TabelaHashEncadeamentoSeparado {

    private int capacidade;
    private int quantidade; //quantidde de chaves
    private ListaDuplamenteEncadeada[] tabela;
    public TabelaHashEncadeamentoSeparado(int capacidade) {
        this.capacidade = capacidade;
        tabela = new ListaDuplamenteEncadeada[this.capacidade];
        for (int i = 0; i < tabela.length; i++) {
            tabela[i] = new ListaDuplamenteEncadeada();
        }
        this.quantidade = 0;
    }
    public void inserir(int chave) {
        int h = funcaoHash(chave);
        tabela[h].adicionar(chave);
        System.out.println("Chave inserida na posicao " + h);
    }

    public void inserir(String chave) {
        int h = funcaoHash(chave);
        tabela[h].adicionar(chave);
        System.out.println("Chave inserida na posicao " + h);
    }

    private int funcaoHash(int chave) {
        return chave % this.capacidade;
    }

    private int funcaoHash(String chave) {
        return chave.length() % this.capacidade;
    }

    public void imprimirTabela() {
        for (int i = 0; i < tabela.length; i++) {
            System.out.printf("Posição %d: ", i);
            tabela[i].imprimir();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TabelaHashEncadeamentoSeparado hash = new TabelaHashEncadeamentoSeparado(5);
        String chave;

        while(true) {
            System.out.printf("%nDigite um valor para inserir na tabela ou 0 para terminar: ");
            chave = sc.nextLine();
            if (chave=="0") break;
            try {
                int numeroInt = Integer.parseInt(chave);
                hash.inserir(numeroInt);
            } catch (NumberFormatException e) {
                System.out.println("A string não contém um número válido.");
                hash.inserir(chave);
            }
            
            System.out.println("");

            hash.imprimirTabela();
        }
    }
}
