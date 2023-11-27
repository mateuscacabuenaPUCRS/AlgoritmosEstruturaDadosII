package Trabalho2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class DigrafoValorado {
    private int numeroArestas;
    private List<List<ArestaDirecionadaValorada>> listaAdjacencia;
    private Map<String, Integer> indiceVertices; // Mapeia o nome do vértice para seu índice inteiro
    private List<String> vertices; // Lista de vértices

    public DigrafoValorado() {
        this.numeroArestas = 0;
        this.listaAdjacencia = new ArrayList<>();
        this.indiceVertices = new HashMap<>();
        this.vertices = new ArrayList<>();
    }

    public void adicionarVertice(String vertice) {
        if (!indiceVertices.containsKey(vertice)) {
            indiceVertices.put(vertice, vertices.size());
            vertices.add(vertice);
            listaAdjacencia.add(new ArrayList<>());
        }
    }

    public void adicionarAresta(String origem, String destino, int peso) {
        adicionarVertice(origem);
        adicionarVertice(destino);

        int indiceOrigem = indiceVertices.get(origem);
        int indiceDestino = indiceVertices.get(destino);

        ArestaDirecionadaValorada a = new ArestaDirecionadaValorada(indiceOrigem, indiceDestino, peso);
        listaAdjacencia.get(indiceOrigem).add(a);
        numeroArestas++;
    }

    public String toDot() {
        StringBuilder resultado = new StringBuilder("digraph G {").append(System.lineSeparator());

        for (String vertice : vertices) {
            resultado.append("\t\"").append(vertice).append("\";").append(System.lineSeparator());
        }

        for (int i = 0; i < vertices.size(); i++) {
            for (ArestaDirecionadaValorada aresta : listaAdjacencia.get(i)) {
                String origem = vertices.get(i);
                String destino = vertices.get(aresta.getDestino());

                resultado.append("\t\"").append(origem).append("\" -> \"").append(destino)
                        .append("\"  [label=").append(aresta.getPeso()).append("];").append(System.lineSeparator());
            }
        }

        resultado.append("}");

        return resultado.toString();
    }

    public int getNumeroArestas() {
        return numeroArestas;
    }

    public Iterable<ArestaDirecionadaValorada> arestas(String vertice) {
        int indice = indiceVertices.get(vertice);
        return listaAdjacencia.get(indice);
    }

    public void lerArquivo(String caminhoArquivo) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new File(caminhoArquivo))) {
            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                adicionaDigrafo(linha);
            }
        }
    }

    private void adicionaDigrafo(String linha) {
        String[] partes = linha.split(" -> 1 |\\s+");
        List<Elemento> elementos = new ArrayList<>();

        if (partes[1].equals("hidrogenio")) {
            int peso = Integer.parseInt(partes[0]);
            String origem = partes[1];
            String destino = partes[2];
            adicionarAresta(origem, destino, peso);
        } else {
            //TODO: implementar
        }

    }
}
