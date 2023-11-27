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
    private int numeroVertices;
    private ArrayList<ArestaDirecionadaValorada> listaAdjacencia;
    private List<String> vertices;
    private List<String> waitList;

    public DigrafoValorado() {
        this.numeroArestas = 0;
        this.listaAdjacencia = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.waitList = new ArrayList<>();
    }

    public void adicionarVertice(String vertice) {
        for (String v : vertices) {
            if (v.equals(vertice)) {
                return;
            }
        }
        vertices.add(vertice);
        numeroVertices++;
    }

    public void adicionarAresta(String origem, String destino, int peso) {
        adicionarVertice(origem);
        adicionarVertice(destino);

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

    public int getNumeroVertices() {
        return numeroVertices;
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
            completaDigrafo();
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
                if(!vertices.contains(nome)){
                    waitList.add(linha);
                    return;
                }
                elementos.add(new Elemento(quantidade, nome));
            }
            String origem = "hidrogenio";
            String destino = partes[partes.length - 1];
            adicionarAresta(origem, destino, calculaHidrogenio(elementos));

        }
    }

    private void completaDigrafo() {
        while (!waitList.isEmpty()) {
            String linha = waitList.remove(0);
            adicionaDigrafo(linha);
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
