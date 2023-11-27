package Trabalho2;

public class Dijkstra {
    private int[] antecessor;
    private int[] distancia;
    private DigrafoValorado grafo;

    Dijkstra(DigrafoValorado g, int origem) {
        grafo = g;
        antecessor = new int[g.getNumeroVertices()];
        distancia = new int[g.getNumeroVertices()];
        for (int v = 0; v < g.getNumeroVertices(); v++) {
            antecessor[v] = -1;
            distancia[v] = Integer.MAX_VALUE;
        }

        FilaPrioridadeMinima pq = new FilaPrioridadeMinima();
        distancia[origem] = 0;
        pq.enfileirar(origem, distancia[origem]);

        //faz um laço enquanto a fila nao estiver vazia
        while(!pq.estaVazia()) {
            int vertice = pq.desenfileirar();
            for (ArestaDirecionadaValorada a : g.arestas(vertice)) {
                String destino = a.getDestino(); // tem que ser string
                int peso = a.getPeso();
                if(distancia[destino] > distancia[vertice] + peso) {
                    antecessor[destino] = vertice;
                    distancia[destino] = distancia[vertice] + peso;
                    if(pq.existe(destino)) pq.atualizarDistancia(destino, distancia[destino]);
                    else pq.enfileirar(destino, distancia[destino]);
                }
            }
        }
    }

    public void imprimirResultado() {
        System.out.println("vertice,distancia,antecessor");
        for (int i = 0; i < grafo.getNumeroVertices(); i++) {
            System.out.println(i + "," + distancia[i] + "," + antecessor[i]);
        }
    }

}

