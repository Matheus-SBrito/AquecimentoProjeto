import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ExtratorPalavrasCSV {

    public static ArrayList<Palavra> extrairPalavras(String nomeArquivo) {
        if (nomeArquivo == null || nomeArquivo.trim().isEmpty()) {
            return null;
        }

        File arquivo = new File(nomeArquivo);
        if (!arquivo.exists() || !arquivo.isFile()) {
            System.err.println("Arquivo CSV não encontrado: " + nomeArquivo);
            return null;
        }

        ArrayList<Palavra> palavras = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty()) {
                    continue;
                }

                // Espera-se: palavra, dificuldade, dica
                String[] partes = linha.split(",", 3);
                if (partes.length < 3) {
                    System.err.println("Formato inválido (menos de 3 campos): " + linha);
                    return null;
                }

                String palavraTexto = partes[0].trim();
                String dificuldadeStr = partes[1].trim();
                String dicaTexto = partes[2].trim();

                if (palavraTexto.isEmpty() || dicaTexto.isEmpty()) {
                    System.err.println("Formato inválido (palavra ou dica vazia): " + linha);
                    return null;
                }

                NivelDeDificuldade nivel;
                if (dificuldadeStr.equals("0") || dificuldadeStr.equalsIgnoreCase("FACIL")) {
                    nivel = NivelDeDificuldade.FACIL;
                } else if (dificuldadeStr.equals("1") || dificuldadeStr.equalsIgnoreCase("MEDIO")) {
                    nivel = NivelDeDificuldade.MEDIO;
                } else if (dificuldadeStr.equals("2") || dificuldadeStr.equalsIgnoreCase("DIFICIL")) {
                    nivel = NivelDeDificuldade.DIFICIL;
                } else {
                    System.err.println("Nível de dificuldade inválido ('" + dificuldadeStr + "'): " + linha);
                    return null;
                }

                palavras.add(new Palavra(palavraTexto, dicaTexto, nivel));
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo CSV: " + e.getMessage());
            return null;
        }

        return palavras;
    }
}