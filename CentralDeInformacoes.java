import java.util.ArrayList;

public class CentralDeInformacoes {

    private ArrayList<Jogador> todosOsJogadores;
    private ArrayList<Palavra> todasAsPalavras;

    public CentralDeInformacoes() {
        this.todosOsJogadores = new ArrayList<>();
        this.todasAsPalavras = new ArrayList<>();
    }

    // Proteções da classe (Getters e Setters)
    public ArrayList<Jogador> getTodosOsJogadores() {
        if (todosOsJogadores == null) {
            todosOsJogadores = new ArrayList<>();
        }
        return todosOsJogadores;
    }

    public void setTodosOsJogadores(ArrayList<Jogador> lista) {
        this.todosOsJogadores = (lista != null) ? lista : new ArrayList<>();
    }

    public ArrayList<Palavra> getTodasAsPalavras() {
        if (todasAsPalavras == null) {
            todasAsPalavras = new ArrayList<>();
        }
        return todasAsPalavras;
    }

    // Comportamentos para Jogador
    public boolean adicionarJogador(Jogador obj) {
        if (obj == null || obj.getCpf() == null || obj.getEmail() == null) {
            return false;
        }

        if (recuperarJogadorPeloEmail(obj.getEmail()) != null || recuperarJogadorPorCPF(obj.getCpf()) != null) {
            return false;
        }

        return getTodosOsJogadores().add(obj);
    }

    public Jogador recuperarJogadorPorCPF(String cpf) {
        if (cpf == null || todosOsJogadores == null) {
            return null;
        }

        for (Jogador jogador : todosOsJogadores) {
            if (jogador.getCpf() != null && jogador.getCpf().trim().equalsIgnoreCase(cpf.trim())) {
                return jogador;
            }
        }
        return null;
    }

    public Jogador recuperarJogadorPeloEmail(String email) {
        if (email == null || todosOsJogadores == null) {
            return null;
        }

        for (Jogador jogador : todosOsJogadores) {
            if (jogador.getEmail() != null && jogador.getEmail().trim().equalsIgnoreCase(email.trim())) {
                return jogador;
            }
        }
        return null;
    }

    // Comportamentos para Palavra
    public boolean adicionarPalavra(Palavra palavra) {
        if (palavra == null || palavra.getPalavra() == null) {
            return false;
        }

        if (recuperarPalavra(palavra.getPalavra()) != null) {
            return false;
        }

        return getTodasAsPalavras().add(palavra);
    }

    // Alias para manter compatibilidade
    public boolean cadastrarPalavra(Palavra palavra) {
        return adicionarPalavra(palavra);
    }

    public Palavra recuperarPalavra(String palavra) {
        if (palavra == null || todasAsPalavras == null) {
            return null;
        }

        for (Palavra p : todasAsPalavras) {
            if (p.getPalavra() != null && p.getPalavra().trim().equalsIgnoreCase(palavra.trim())) {
                return p;
            }
        }
        return null;
    }

    // Alias para manter compatibilidade
    public Palavra recuPalavra(String palavra) {
        return recuperarPalavra(palavra);
    }
}