public class Programa {
    public static void main(String[] args) {
        CentralDeInformacoes central = new CentralDeInformacoes();
        Persistencia persistencia = new Persistencia();
        central = persistencia.recuperarCentral();
        Scanner scanner = new Scanner(System.in);

       do{
         System.out.println("1- novo jogador"+
                            "2- listar todos os jogadores"+
                            "3- exibir informações de um jogador específico"+
                            "S- sair"
         );

         switch (Scanner.nextLine().toUpperCase()){
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
                for(Jogador j : central.getJogadores()){
                    System.out.println(j);
                }
            case "3":
                Sytem.out.println("Digite o nome do jogador que deseja exibir as informações: ");
                String nomeJogador = scanner.nextLine();
                Jogador jogadorEncontrado = central.buscarJogador(nomeJogador);
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
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente!");

            }
       }while(opcao.equals("S"));

    }
}