import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ExtratorPalavrasCSV {
    public static ArrayList<Palavra> extrairPalavras(String nomeArquivo) {
        ArrayList<Palavra> palavras = new ArrayList();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(nomeArquivo))) {
            String linhas;

            while ((linhas = br.readLine()) != null) {
                linhas = linhas.trim();
                if (linhas.isEmpty()) {
                    continue;
                }
                String[] divisor = linhas.split(",");

                if (divisor.length >= 3) {
                    String palavraTexto = divisor[0].trim();
                    String indiceDificuldade = divisor[1].trim().toUpperCase();
                    NivelDeDificuldade nivel = NivelDeDificuldade.valueOf(indiceDificuldade);
                    String dica = divisor[2].trim();
                    Palavra obj = new Palavra(palavraTexto, dica, nivel);

                    palavras.add(obj);

                }
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            return null;
        }

        return palavras;
    }
}