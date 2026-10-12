import java.time.LocalDate;
import java.util.Objects;

public class Palavra {
    private String palavra;
    private String dica;
    private LocalDate dataDeCadastroDaPalavra;
    private NivelDeDificuldade nivelDeDificuldade;

    public Palavra(String palavra, String dica, NivelDeDificuldade nivelDeDificuldade) {
        this.palavra = palavra;
        this.dica = dica;
        this.nivelDeDificuldade = nivelDeDificuldade;
        this.dataDeCadastroDaPalavra = LocalDate.now();
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getDica() {
        return dica;
    }

    public void setDica(String dica) {
        this.dica = dica;
    }

    public LocalDate getDataDeCadastroDaPalavra() {
        return dataDeCadastroDaPalavra;
    }

    public void setDataDeCadastroDaPalavra(LocalDate dataDeCadastroDaPalavra) {
        this.dataDeCadastroDaPalavra = dataDeCadastroDaPalavra;
    }

    public LocalDate getDataDeCadastro() {
        return dataDeCadastroDaPalavra;
    }

    public void setDataDeCadastro(LocalDate dataDeCadastro) {
        this.dataDeCadastroDaPalavra = dataDeCadastro;
    }

    public NivelDeDificuldade getNivelDeDificuldade() {
        return nivelDeDificuldade;
    }

    public void setNivelDeDificuldade(NivelDeDificuldade nivelDeDificuldade) {
        this.nivelDeDificuldade = nivelDeDificuldade;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Palavra)) {
            return false;
        }
        Palavra outra = (Palavra) obj;

        if (this.palavra == null) {
            return outra.palavra == null;
        }

        return this.palavra.equalsIgnoreCase(outra.palavra);
    }

    @Override
    public int hashCode() {
        return palavra != null ? palavra.toLowerCase().hashCode() : 0;
    }

    @Override
    public String toString() {
        return this.palavra + " (Dica: " + this.dica + ")";
    }
}
