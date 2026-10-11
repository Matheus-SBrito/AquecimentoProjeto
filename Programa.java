import java.util.Scanner;

public class Programa {
    public static void main(String[] args) {
        CentralDeInformacoes central = new CentralDeInformacoes();
        Persistencia persistencia = new Persistencia();
        central = persistencia.recuperarCentral();
        Scanner scanner = new Scanner(System.in);
        int cont=0;
       do{
         System.out.println("1- novo jogador"+
                            "2- listar todos os jogadores"+
                            "3- exibir informações de um jogador específico"+
                            "S- sair"
         );
        
         String opcao = scanner.nextLine().toUpperCase();
         switch (opcao){
            case "1":
                System.out.println("Digite o nome do jogador: ");
                String nome = scanner.nextLine();
                System.out.println("Digite o sexo do jogador: ");
                Sexo sexo = Sexo.valueOf(scanner.nextLine().toUpperCase());
                System.out.println("Digite o CPF do jogador: ");
                String cpf = scanner.nextLine();
                System.out.println("Digite o email do jogador: ");
                String email = scanner.nextLine();
                Jogador jogador = new Jogador(nome, sexo, cpf, email);
                if(central.adicionarJogador(jogador)){
                    System.out.println("Jogador adicionado com sucesso!");
                }else{
                    System.out.println("Algo deu errado, tente novamente!");
                }
            case "2":
                System.out.println("Lista de jogadores:");
                for(Jogador j : central.getTodosOsJogadores()){
                    System.out.println(j);
                }
            case "3":
                System.out.println("Digite o CPF do jogador que deseja exibir as informações: ");
                String dadosJogador = scanner.nextLine();
                Jogador jogadorEncontrado = central.recuperarJogadorPorCPF(dadosJogador);
                if(jogadorEncontrado != null){
                    System.out.println("Nome: " + jogadorEncontrado.getNome());
                    System.out.println("Sexo: " + jogadorEncontrado.getSexo());
                    System.out.println("CPF: " + jogadorEncontrado.getCpf());
                    System.out.println("Email: " + jogadorEncontrado.getEmail());
                }else{
                    System.out.println("Jogador não encontrado!");
                }
                case "S":
                    persistencia.salvarCentral(central);
                    System.out.println("Saindo do programa...");
                    cont++;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");

            }
       }while(cont>0);
       scanner.close();
    }
}