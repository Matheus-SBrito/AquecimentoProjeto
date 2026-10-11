import java.util.ArrayList;
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
                            "4- salvar palavras a partir de um arquivo CSV"+
                            "5- listar todas as palavras salvas na central"+
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
                break;
            case "2":
                System.out.println("Lista de jogadores:");
                for(Jogador j : central.getTodosOsJogadores()){
                    System.out.println(j);
                }
                break;
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
                break;
                case "4":
                    System.out.println("Digite o nome do arquivo CSV: ");
                    String nomeArquivo = scanner.nextLine();
                    ArrayList<Palavra> listaExtraida = ExtratorPalavrasCSV.extrairPalavras(nomeArquivo);
                    if (listaExtraida != null) {
                        int cadastradasComSucesso = 0;
                        for (Palavra p : listaExtraida) {

                            if (central.cadastrarPalavra(p)) {
                                cadastradasComSucesso++;
                            }
                        }
                        System.out.println("Operação concluída! " + cadastradasComSucesso + " palavra(s) salva(s) na central.");
                    } else {
                        
                        System.out.println("Erro: A operação de extração não foi bem sucedida (formato do CSV inválido ou erro de leitura).");
                    }
                    break;
                    case "5": 
                    System.out.println("Lista de palavras salvas na central:");
                    if (central.getTodasAsPalavras() != null && !central.getTodasAsPalavras().isEmpty()) {
                        for (Palavra p : central.getTodasAsPalavras()) {
                            System.out.println(p);
                        }
                    } else {
                        System.out.println("Nenhuma palavra cadastrada na central.");
                    }
                    break;
                case "S":
                    persistencia.salvarCentral(central);
                    System.out.println("Saindo do programa...");
                    cont++;
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");

            }
       }while(cont == 0);
       scanner.close();
    }
}