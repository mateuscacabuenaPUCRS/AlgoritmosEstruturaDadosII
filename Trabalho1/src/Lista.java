import java.util.LinkedList;

public class Lista {
    private LinkedList<Character> lista;
    
    public Lista() {
        lista = new LinkedList<Character>();
    }

    public void add(char element) {
        lista.add(element);
    }

    public int size() {
        return lista.size();
    }

    public void deteriorar() {
        if (lista.size() <= 0) {
            return;
        }
        int indice = 0;

        while(indice < lista.size() - 1) {
            if (lista.get(indice) != lista.get(indice + 1)) {
                char c = compare(lista.get(indice), lista.get(indice + 1));
                lista.remove(indice);
                lista.remove(indice);
                lista.addLast(c);
                indice = 0;
            } else {
                indice++;
            }
        }
     }

     public char compare(char a, char b) {
        if ((a == 'D' && b == 'N') || (a == 'N' && b == 'D') ) {
            return 'A';
        } else if ((a == 'D' && b == 'A') || (a == 'A' && b == 'D')) {
            return 'N';
        } else if ((a == 'A' && b == 'N') || (a == 'N' && b == 'A')) {
            return 'D';
        } else {
            return '*';
        }
     }

     @Override
     public String toString() {
        String s = "";
        for (int i = 0; i < lista.size(); i++) {
            s += lista.get(i);
        }
        return s;
     }
}
