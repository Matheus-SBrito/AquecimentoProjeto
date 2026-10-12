import java.util.Objects;

public class Jogador {
    private String nome;
    private Sexo sexo;
    private String cpf;
    private String email;

    public Jogador(String nome, Sexo sexo, String cpf, String email) {
        this.nome = nome;
        this.sexo = sexo;
        this.cpf = cpf;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Jogador)) {
            return false;
        }
        Jogador other = (Jogador) obj;
        return Objects.equals(this.cpf, other.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.cpf);
    }
}
