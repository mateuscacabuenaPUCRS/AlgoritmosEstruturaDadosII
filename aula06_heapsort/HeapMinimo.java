package aula06_heapsort;

import utils.ArrayUtils;

public class HeapMinimo {
    private int[] chaves;
    private int capacidade;
    private int tamanho;

    public int[] getChaves() {
        return chaves;
    }

    public HeapMinimo(int capacidade) {
        this.capacidade = capacidade;
        this.tamanho = 0;
        chaves = new int[capacidade];
        for (int i = 0; i < capacidade; i++) {
            chaves[i] = -1;
        }
    }

    private int pai(int posicao) {
        int pai = (posicao - 1) / 2;
        if (pai < 0){
            pai = 0;
        }
        return pai;
    }

    private int filhoEsquerda(int posicao) {
        return 2 * posicao + 1;
    }

    private int filhoDireita(int posicao) {
        return 2 * posicao + 2;
    }

    public void inserir(int chave) {
        if (tamanho + 1 > capacidade) {
            System.out.println("lotou");
            aumentarCapacidade();
        }
        System.out.println("Inserindo " + chave);
        int posicao = tamanho;
        chaves[posicao] = chave;
        tamanho++;
        ArrayUtils.imprimir(chaves);
        swimNadar(posicao);
    }

    public int removerMaximo() { //remove o maior elemento, a raiz
        /*
         * Trocar a chave do pai com o último elemento do heap, ou seja o último elemento vira a raiz e a raiz anterior eh removida do heap
         * Isso pode deixar o heap inconsistente, com uma raiz menor que os filhos
         * Entao o algoritmo deve executar a operacao "afundar" até que o heap fique consistente
         * */

        if(tamanho==0) return -1;
        //troca
        int chaveMaxima = chaves[0];
        trocar(0, tamanho-1);

        //remove a antiga raiz
        chaves[tamanho-1] = -1;
        tamanho = tamanho - 1;

        System.out.println("Removido " + chaveMaxima);

        ArrayUtils.imprimir(getChaves());
        //inicia o ajuste afundando a nova raiz ate que o heap seja restaurado
        sinkAfundar(0);
        return chaveMaxima;
    }

    public static void main(String[] args) {
        HeapMinimo hmin = new HeapMinimo(5);
        ArrayUtils.imprimir(hmin.chaves);

        hmin.inserir(10);
        hmin.inserir(5);
        hmin.inserir(30);
        hmin.inserir(2);
        ArrayUtils.imprimir(hmin.chaves);
        hmin.removerMaximo();
    }

    public void trocar(int pos1, int pos2) {
        System.out.printf("Trocando %d por %d. ", chaves[pos1], chaves[pos2]);
        int aux = chaves[pos1];
        chaves[pos1] = chaves[pos2];
        chaves[pos2] = aux;
    }

    private void swimNadar(int posicao) {
        while (posicao > 0 && chaves[pai(posicao)] > chaves[posicao]) {
            trocar(posicao, pai(posicao));
            posicao = pai(posicao);
        }
    }

    private void sinkAfundar(int posicao) {
        while(filhoEsquerda(posicao) <= tamanho) {
            int menor = posicao;
            int filhoEsquerda = filhoEsquerda(posicao);
            int filhoDireita = filhoDireita(posicao);

            if(filhoEsquerda < tamanho && chaves[filhoEsquerda] < chaves[menor]) menor = filhoEsquerda;
            if(filhoDireita < tamanho && chaves[filhoDireita] < chaves[menor]) menor = filhoDireita;

            if(menor!=posicao) { 
                trocar(posicao, menor);
            }
            ArrayUtils.imprimir(getChaves());
            posicao = 2*posicao+1;
        }
    }

    private void aumentarCapacidade() {
        capacidade = capacidade * 2;
        int[] novoArray = new int[capacidade];
        for (int i = 0; i < capacidade; i++) {
            novoArray[i] = -1;
        } // inicializa com -1
        for (int i = 0; i < tamanho; i++) {
            novoArray[i] = chaves[i];
        } // copia valores anteriores
        chaves = novoArray;
    }

}
