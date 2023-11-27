package Trabalho2;

public class ArestaDirecionadaValorada {
    private String origem;
    private String destino;
    private int peso;
    public ArestaDirecionadaValorada(String origem, String destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }
    @Override
    public String toString() {
        return origem + " -> " + destino + " [" + peso + "]";
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }
}
