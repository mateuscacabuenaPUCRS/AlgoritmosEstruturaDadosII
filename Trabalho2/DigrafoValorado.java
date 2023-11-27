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
    private ArrayList<ArestaDirecionadaValorada> listaAdjacencia;
    private Map<String, Integer> indiceVertices; // Mapeia o nome do vértice para seu índice inteiro
    private List<String> vertices; // Lista de vértices

    public DigrafoValorado() {
        this.numeroArestas = 0;
        this.listaAdjacencia = new ArrayList<>();
        this.indiceVertices = new HashMap<>();
        this.vertices = new ArrayList<>();
    }

    public void adicionarVertice(String vertice) {
        for (String v : vertices) {
            if (v.equals(vertice)) {
                return;
            }
        }
        vertices.add(vertice);
    }

    public void adicionarAresta(String origem, String destino, int peso) {
        adicionarVertice(origem);
        adicionarVertice(destino);

        // int indiceOrigem = indiceVertices.get(origem);
        // int indiceDestino = indiceVertices.get(destino);

        ArestaDirecionadaValorada a = new ArestaDirecionadaValorada(origem, destino, peso);
        listaAdjacencia.add(a);
        numeroArestas++;
    }

    public String toDot() {
        StringBuilder resultado = new StringBuilder("digraph G {").append(System.lineSeparator());

        for (String vertice : vertices) {
            resultado.append("\t\"").append(vertice).append("\";").append(System.lineSeparator());
        }

        for (ArestaDirecionadaValorada aresta : listaAdjacencia) {
            String origem = aresta.getOrigem();
            String destino = aresta.getDestino();

            resultado.append("\t\"").append(origem).append("\" -> \"").append(destino)
                    .append("\"  [label=").append(aresta.getPeso()).append("];").append(System.lineSeparator());
        }
        resultado.append("}");

        return resultado.toString();
    }

    public int getNumeroArestas() {
        return numeroArestas;
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
            for (int i = 0; i < partes.length - 1; i += 2) {
                int quantidade = Integer.parseInt(partes[i]);
                String nome = partes[i + 1];
                //if(!vertices.contains(nome)){}
                elementos.add(new Elemento(quantidade, nome));
            }
            String origem = "hidrogenio";
            String destino = partes[partes.length - 1];
            adicionarAresta(origem, destino, calculaHidrogenio(elementos));

        }
    }

    private int calculaHidrogenio(List<Elemento> elementos) {
        int hidrogenio = 0;
        for (ArestaDirecionadaValorada aresta : listaAdjacencia) {
            for (Elemento elemento : elementos) {
                if (elemento.getNome().equals(aresta.getDestino())) {
                    hidrogenio += aresta.getPeso() * elemento.getQuantidade();
                }
            }
        }
        return hidrogenio;
    }

    public int getCusto(String destino) {
        int custo = 0;
        for(ArestaDirecionadaValorada a : listaAdjacencia){
            if(a.getDestino().equals(destino)){
                custo = a.getPeso();
            }
        }
        return custo;
    }
}
