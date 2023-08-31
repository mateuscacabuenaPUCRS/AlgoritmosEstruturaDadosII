package aula09_tabelas_hash_encadeamento_separado;

public class ListaDuplamenteEncadeada {
    int tamanho;
    Nodo inicio;
    Nodo fim;
    private class Nodo {
        int chave;
        String chave2;
        Nodo proximo;
        Nodo anterior;
        public Nodo(int chave) {
            this.chave = chave;
            this.proximo = null;
            this.anterior = null;
        }
        public Nodo(String chave2) {
            this.chave2 = chave2;
            this.proximo = null;
            this.anterior = null;
        }
    }
    public ListaDuplamenteEncadeada() {
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }
    public void adicionar(int chave) {
        Nodo novoNodo = new Nodo(chave);
        if(this.tamanho==0) {
            this.inicio = novoNodo;
            this.fim = novoNodo;
        }
        else {
            novoNodo.anterior = this.fim;
            this.fim.proximo = novoNodo;
            this.fim = novoNodo;
        }
        tamanho++;
    }
    public void adicionar(String chave) {
        Nodo novoNodo = new Nodo(chave);
        if(this.tamanho==0) {
            this.inicio = novoNodo;
            this.fim = novoNodo;
        }
        else {
            novoNodo.anterior = this.fim;
            this.fim.proximo = novoNodo;
            this.fim = novoNodo;
        }
        tamanho++;
    }
    public void imprimir() {
        Nodo aux = this.inicio;
        for (int i = 0; i < this.tamanho; i++) {
            if(aux.chave == 0) System.out.println(aux.chave2);
            else System.out.println(aux.chave);
            aux = aux.proximo;
        }
    }
    public static void main(String[] args) {
        ListaDuplamenteEncadeada ld = new ListaDuplamenteEncadeada();
        ld.adicionar(10);
        ld.adicionar(20);
        ld.imprimir();
    }
}
