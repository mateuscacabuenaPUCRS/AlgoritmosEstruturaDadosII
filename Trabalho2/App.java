package Trabalho2;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        DigrafoValorado g = new DigrafoValorado();
        g.lerArquivo("Trabalho2\\teste.txt");
        System.out.println(g.toDot());
        System.out.println("pronto");

        //Dijkstra d = new Dijkstra(g, 0);
        //d.imprimirResultado();
    }
}

//possivel ideia:
/*
         * registra os hidrogenios 
         * vai botando no array <elemento, qtd>
         * ao chegar na ->, calculaHidrogenio
         * calculaHidrogenio = caminhamento feito nas regras para chegar no elemento * qtd
         * o resultado retorna o peso da aresta(hidrogenios).
         */


/*

10 hidrogenio -> 1 iterbio
1 hidrogenio -> 1 praseodimio
2 hidrogenio -> 1 cromo
8 hidrogenio -> 1 protactinio
3 hidrogenio -> 1 cadmio
6 hidrogenio -> 1 europio
7 hidrogenio -> 1 cesio
10 hidrogenio -> 1 itrio
1 hidrogenio -> 1 erbio
3 iterbio 3 itrio -> 1 promecio
4 cromo 3 praseodimio -> 1 zinco
8 cadmio 6 netunio -> 1 paladio
3 europio 3 protactinio -> 1 selenio
5 cesio 2 erbio -> 1 netunio
8 promecio 9 paladio -> 1 zinco
6 zinco 1 selenio -> 1 ouro
 */
