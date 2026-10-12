import java.io.FileOutputStream;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class GeradorDeRelatorios {

    public static void gerarRelatorio(CentralDeInformacoes central) {
        String nomeArquivo = "relatorio.pdf";
        Document doc = new Document(PageSize.A4, 36, 36, 36, 36);

        try (FileOutputStream fos = new FileOutputStream(nomeArquivo)) {
            PdfWriter.getInstance(doc, fos);
            doc.open();

            Font fonteTituloPrincipal = new Font(FontFamily.HELVETICA, 18, Font.BOLD);
            Font fonteSecao = new Font(FontFamily.HELVETICA, 14, Font.BOLD);
            Font fonteCabecalho = new Font(FontFamily.HELVETICA, 10, Font.BOLD);
            Font fonteNormal = new Font(FontFamily.HELVETICA, 10, Font.NORMAL);

            Paragraph tituloPrincipal = new Paragraph("Relatório Geral do Sistema", fonteTituloPrincipal);
            tituloPrincipal.setAlignment(Element.ALIGN_CENTER);
            tituloPrincipal.setSpacingAfter(15);
            doc.add(tituloPrincipal);

            // Seção: Jogadores
            Paragraph secaoJogadores = new Paragraph("Todos os Jogadores Cadastrados", fonteSecao);
            secaoJogadores.setSpacingBefore(10);
            secaoJogadores.setSpacingAfter(8);
            doc.add(secaoJogadores);

            ArrayList<Jogador> jogadores = (central != null) ? central.getTodosOsJogadores() : new ArrayList<>();
            if (jogadores.isEmpty()) {
                Paragraph vazio = new Paragraph("Nenhum jogador cadastrado.", fonteNormal);
                vazio.setSpacingAfter(10);
                doc.add(vazio);
            } else {
                PdfPTable tabelaJogador = new PdfPTable(4);
                tabelaJogador.setWidthPercentage(100);
                tabelaJogador.setSpacingAfter(15);

                tabelaJogador.addCell(new Paragraph("Nome", fonteCabecalho));
                tabelaJogador.addCell(new Paragraph("Sexo", fonteCabecalho));
                tabelaJogador.addCell(new Paragraph("CPF", fonteCabecalho));
                tabelaJogador.addCell(new Paragraph("Email", fonteCabecalho));

                for (Jogador jogador : jogadores) {
                    tabelaJogador.addCell(new Paragraph(jogador.getNome() != null ? jogador.getNome() : "", fonteNormal));
                    tabelaJogador.addCell(new Paragraph(jogador.getSexo() != null ? jogador.getSexo().toString() : "", fonteNormal));
                    tabelaJogador.addCell(new Paragraph(jogador.getCpf() != null ? jogador.getCpf() : "", fonteNormal));
                    tabelaJogador.addCell(new Paragraph(jogador.getEmail() != null ? jogador.getEmail() : "", fonteNormal));
                }
                doc.add(tabelaJogador);
            }

            // Seção: Palavras
            Paragraph secaoPalavras = new Paragraph("Todas as Palavras Cadastradas", fonteSecao);
            secaoPalavras.setSpacingBefore(10);
            secaoPalavras.setSpacingAfter(8);
            doc.add(secaoPalavras);

            ArrayList<Palavra> palavras = (central != null) ? central.getTodasAsPalavras() : new ArrayList<>();
            if (palavras.isEmpty()) {
                Paragraph vazio = new Paragraph("Nenhuma palavra cadastrada.", fonteNormal);
                doc.add(vazio);
            } else {
                PdfPTable tabelaPalavra = new PdfPTable(4);
                tabelaPalavra.setWidthPercentage(100);

                tabelaPalavra.addCell(new Paragraph("Palavra", fonteCabecalho));
                tabelaPalavra.addCell(new Paragraph("Dica", fonteCabecalho));
                tabelaPalavra.addCell(new Paragraph("Data de Cadastro", fonteCabecalho));
                tabelaPalavra.addCell(new Paragraph("Dificuldade", fonteCabecalho));

                for (Palavra palavra : palavras) {
                    tabelaPalavra.addCell(new Paragraph(palavra.getPalavra() != null ? palavra.getPalavra() : "", fonteNormal));
                    tabelaPalavra.addCell(new Paragraph(palavra.getDica() != null ? palavra.getDica() : "", fonteNormal));
                    tabelaPalavra.addCell(new Paragraph(palavra.getDataDeCadastroDaPalavra() != null ? palavra.getDataDeCadastroDaPalavra().toString() : "", fonteNormal));
                    tabelaPalavra.addCell(new Paragraph(palavra.getNivelDeDificuldade() != null ? palavra.getNivelDeDificuldade().toString() : "", fonteNormal));
                }
                doc.add(tabelaPalavra);
            }

            doc.close();
            System.out.println("Relatório em PDF gerado com sucesso: '" + nomeArquivo + "'");
        } catch (Exception e) {
            System.err.println("Erro ao gerar o relatório em PDF: " + e.getMessage());
        }
    }
}
