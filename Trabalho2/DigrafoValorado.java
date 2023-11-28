package Trabalho2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DigrafoValorado {
    private int numeroArestas;
    private int numeroVertices;
    private long custo;
    private String ultimaLinha;
    private ArrayList<ArestaDirecionadaValorada> listaAdjacencia;
    private List<String> vertices;
    private List<String> waitList;

    public DigrafoValorado() {
        this.numeroArestas = 0;
        this.custo = 0;
        this.listaAdjacencia = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.waitList = new ArrayList<>();
    }

    public long getCusto() {
        return custo;
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

    public void adicionarAresta(String origem, String destino, long peso) {
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

                if (!scanner.hasNextLine()) {
                    ultimaLinha = linha;
                }
            }
            completaDigrafo();
            calculaCusto(ultimaLinha, listaAdjacencia);
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

    private long calculaHidrogenio(List<Elemento> elementos) {
        int hidrogenio = 0;
        for (Elemento elemento : elementos) {
            for (ArestaDirecionadaValorada aresta : listaAdjacencia) {
                if (elemento.getNome().equals(aresta.getDestino())) {
                    hidrogenio += aresta.getPeso() * elemento.getQuantidade();
                }
            }
        }
        return hidrogenio;
    }

    private long calculaCusto(String ultimaLinha, ArrayList<ArestaDirecionadaValorada> listaAdjacencia) {
        String[] partes = ultimaLinha.split(" -> 1 |\\s+");
        List<Elemento> elementos = new ArrayList<>();
        for (int i = 0; i < partes.length - 1; i += 2) {
                int quantidade = Integer.parseInt(partes[i]);
                String nome = partes[i + 1];
                if(!vertices.contains(nome)){
                    waitList.add(ultimaLinha);
                    return -1;
                }
                elementos.add(new Elemento(quantidade, nome));
            }
            custo = calculaHidrogenio(elementos);
            return custo;
    }
}
