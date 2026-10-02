import java.util.ArrayList;

public class CentralDeInformacoes{

    private ArrayList<Jogador> todosJogadores;
    private int tamanhoLista = 0;
    private ArrayList<Palavra> todasAsPalavras;

    // Proteções da classe
    public ArrayList<Jogador> getTodosOsJogadores (){return todosJogadores;}
    public void setTodosOsJogadores (ArrayList<Jogador> lista){todosJogadores = lista;}
    public ArrayList<Palavra> getTodasAsPalavras(){return todasAsPalavras;}

    // Comportamentos da classe
    public boolean adicionarJogador (Jogador obj){
        if (
            (recuperarJogadorPeloEmail(
            obj.getEmail())!= null)
            || 
            (recuperarJogadorPorCPF(
            obj.getCpf()) != null)

            )return false;

        todosJogadores.add(obj);
        return true;
    }



    public Jogador recuperarJogadorPorCPF(String cpf){
        if (tamanhoLista == 0 || todosJogadores == null)
            return null;

        for(int contador = 0; contador < tamanhoLista; contador++){
            Jogador jogador = todosJogadores.get(contador);
            if (jogador.getCpf().equals(cpf))
                return jogador;
        }
        return null;
    }


    public Jogador recuperarJogadorPeloEmail (String email){
        if (tamanhoLista == 0 || todosJogadores == null)
            return null;

        for(int contador = 0; contador < tamanhoLista; contador++){
            Jogador jogador = todosJogadores.get(contador);
            if (jogador.getEmail().equals(email))
                return jogador;
        }
        return null;
    }
    public boolean cadastrarPalavra(Palavra palavra){
        if(todasAsPalavras.contains(palavra)){
            return false;
        }
        todasAsPalavras.add(palavra);
        return true;

    }
    public Palavra recuPalavra(String palavra){
        for(Palavra p : todasAsPalavras){
            if(p.getPalavra().equalsIgnoreCase(palavra)){
                return p;
            }
        }
        return null;
    }
    
}