import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArchiveReader {
    private Lista lista;
    private String path;

    public ArchiveReader(String path) {
        lista = new Lista();
        this.path = path;
    }

    public void read() {
        char c;
        try {
            Path p = Paths.get(path);
            BufferedReader br = Files.newBufferedReader(p, Charset.defaultCharset());
            String line = null;

            while ((line = br.readLine()) != null) {
                for (int i = 0; i < line.length(); i++) {
                    c = line.charAt(i);
                    lista.add(c);
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
        } catch(IOException e) {
            System.out.println("Erro de leitura");
        }
    }

    public Lista getLista() {
        return lista;
    }
}
