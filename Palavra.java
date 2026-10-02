import java.time.LocalDate;
public class Palavra {
    private String palavra;
    private String dica;
    private LocalDate dataDeCadastroDaPalavra;
    private NivelDeDificuldade nivelDeDificuldade;

    public Palavra(String palavra , String dica , NivelDeDificuldade nivelDeDificuldade){
        this.palavra = palavra;
        this.dica = dica;
        this.nivelDeDificuldade = nivelDeDificuldade;
        this.dataDeCadastroDaPalavra = LocalDate.now();
    }

    public String getPalavra(){
        return palavra;
    }
    public String getDica(){
        return dica;
    }
    public LocalDate getDataDeCadastroDaPalavra(){
        return dataDeCadastroDaPalavra;
    }
    public NivelDeDificuldade getNivelDeDificuldade(){
        return nivelDeDificuldade;
    }
    public void setPalavra(String palavra){
        this.palavra = palavra;
    }
    public void setDica(String dica){
        this.dica = dica;
    }

    public void setNivelDeDificuldade(NivelDeDificuldade nivelDeDificuldade){
        this.nivelDeDificuldade = nivelDeDificuldade;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Palavra) {
            Palavra outra = (Palavra) obj;

        if (this.palavra == null) {
            return outra.palavra == null;
            }

        return this.palavra.equals(outra.palavra);
        }
        return false;
    }
    @Override 
    public String toString(){
        return "[" + this.palavra + "]" + "[" + this.dica + "]";
    }   

}
