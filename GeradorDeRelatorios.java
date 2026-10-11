import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PdfPTable;

public class GeradorDeRelatorios {
	public static void gerarRelatorio(CentralDeInformacoes central) {
		
		Document doc = new Document (PageSize.A4,72,72,72,72);
		
		try {
			PdfWriter.getInstance(doc, new FileOutputStream("relatorio.pdf"));
		doc.open();
			
		Font fonteTitulo= new Font (FontFamily.COURIER,20,Font.BOLD);
		Paragraph j = new Paragraph("Todos os Jogadores",fonteTitulo);
		doc.add(j);
		
		PdfPTable tabelaJogador = new PdfPTable(4);
		tabelaJogador.addCell("Nome");
		tabelaJogador.addCell("Sexo");
		tabelaJogador.addCell("CPF");
		tabelaJogador.addCell("Email");
		
		for (Jogador jogador: central.getTodosOsJogadores()) {
			tabelaJogador.addCell(jogador.getNome());
			tabelaJogador.addCell(String.valueOf(jogador.getSexo()));
			tabelaJogador.addCell(jogador.getCpf());
			tabelaJogador.addCell(jogador.getEmail());
			
		}
		doc.add(tabelaJogador);
		
		Paragraph p = new Paragraph("Todas as Palavras", fonteTitulo);
		doc.add(p);
		
		PdfPTable tabelaPalavra = new PdfPTable(4);
		tabelaPalavra.addCell("Palavra");
		tabelaPalavra.addCell("Dica");
		tabelaPalavra.addCell("Data de Cadastro");
		tabelaPalavra.addCell("Dificuldade");
		
		for (Palavra palavra: central.getTodasAsPalavras()) {
			tabelaPalavra.addCell(palavra.getPalavra());
			tabelaPalavra.addCell(palavra.getDica());
			tabelaPalavra.addCell(String.valueOf(palavra.getDataDeCadastroDaPalavra()));
			tabelaPalavra.addCell(String.valueOf(palavra.getNivelDeDificuldade()));
		}
		doc.add(tabelaPalavra);
		
		doc.close();
		} 
		catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		}
		
		
	}
}
