import java.util.ArrayList;
import java.util.Scanner;

public class Programa {
    private static final String ARQUIVO_XML = "central.xml";

    public static void main(String[] args) {
        Persistencia persistencia = new Persistencia();
        CentralDeInformacoes central = persistencia.recuperarCentral(ARQUIVO_XML);
        Scanner scanner = new Scanner(System.in);

        boolean executando = true;

        while (executando) {
            System.out.println("\n=================== MENU PRINCIPAL ===================");
            System.out.println("1 - Novo jogador");
            System.out.println("2 - Listar todos os jogadores");
            System.out.println("3 - Exibir informações de um jogador específico");
            System.out.println("4 - Salvar palavras a partir de um arquivo CSV");
            System.out.println("5 - Listar todas as palavras salvas na central");
            System.out.println("6 - Geração do relatório em PDF");
            System.out.println("7 - Enviar uma mensagem para todos os jogadores");
            System.out.println("S - Sair");
            System.out.println("======================================================");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine().trim().toUpperCase();

            switch (opcao) {
                case "1":
                    System.out.print("Digite o nome do jogador: ");
                    String nome = scanner.nextLine().trim();

                    Sexo sexo = null;
                    while (sexo == null) {
                        System.out.print("Digite o sexo do jogador (MASCULINO ou FEMININO): ");
                        String sexoStr = scanner.nextLine().trim().toUpperCase();
                        if (sexoStr.equals("M") || sexoStr.equals("MASCULINO")) {
                            sexo = Sexo.MASCULINO;
                        } else if (sexoStr.equals("F") || sexoStr.equals("FEMININO")) {
                            sexo = Sexo.FEMININO;
                        } else {
                            System.out.println("Valor inválido! Digite MASCULINO ou FEMININO.");
                        }
                    }

                    System.out.print("Digite o CPF do jogador: ");
                    String cpf = scanner.nextLine().trim();

                    System.out.print("Digite o email do jogador: ");
                    String email = scanner.nextLine().trim();

                    Jogador novoJogador = new Jogador(nome, sexo, cpf, email);
                    if (central.adicionarJogador(novoJogador)) {
                        // A cada novo jogador cadastrado, a central deve ser salva novamente no arquivo XML
                        persistencia.salvarCentral(central, ARQUIVO_XML);
                        System.out.println("Jogador adicionado com sucesso e central persistida!");
                    } else {
                        System.out.println("Erro ao cadastrar jogador! Verifique se CPF ou E-mail já estão cadastrados.");
                    }
                    break;

                case "2":
                    System.out.println("\n--- Lista de Jogadores ---");
                    ArrayList<Jogador> jogadores = central.getTodosOsJogadores();
                    if (jogadores.isEmpty()) {
                        System.out.println("Nenhum jogador cadastrado.");
                    } else {
                        for (Jogador j : jogadores) {
                            System.out.println(j);
                        }
                    }
                    break;

                case "3":
                    System.out.print("Digite o CPF do jogador que deseja exibir as informações: ");
                    String cpfBusca = scanner.nextLine().trim();
                    Jogador jogadorEncontrado = central.recuperarJogadorPorCPF(cpfBusca);
                    if (jogadorEncontrado != null) {
                        System.out.println("\n--- Dados do Jogador ---");
                        System.out.println("Nome: " + jogadorEncontrado.getNome());
                        System.out.println("Sexo: " + jogadorEncontrado.getSexo());
                        System.out.println("CPF: " + jogadorEncontrado.getCpf());
                        System.out.println("Email: " + jogadorEncontrado.getEmail());
                    } else {
                        System.out.println("Jogador não encontrado!");
                    }
                    break;

                case "4":
                    System.out.print("Digite o nome do arquivo CSV: ");
                    String nomeArquivo = scanner.nextLine().trim();
                    ArrayList<Palavra> listaExtraida = ExtratorPalavrasCSV.extrairPalavras(nomeArquivo);

                    if (listaExtraida != null) {
                        int cadastradasComSucesso = 0;
                        for (Palavra p : listaExtraida) {
                            if (central.adicionarPalavra(p)) {
                                cadastradasComSucesso++;
                            }
                        }
                        if (cadastradasComSucesso > 0) {
                            persistencia.salvarCentral(central, ARQUIVO_XML);
                        }
                        System.out.println("Operação concluída! " + cadastradasComSucesso + " palavra(s) salva(s) na central.");
                    } else {
                        System.out.println("Erro: A operação de extração não foi bem sucedida (formato do CSV inválido ou erro de leitura).");
                    }
                    break;

                case "5":
                    System.out.println("\n--- Lista de Palavras Salvas na Central ---");
                    ArrayList<Palavra> palavras = central.getTodasAsPalavras();
                    if (palavras.isEmpty()) {
                        System.out.println("Nenhuma palavra cadastrada na central.");
                    } else {
                        for (Palavra p : palavras) {
                            System.out.println(p);
                        }
                    }
                    break;

                case "6":
                    System.out.println("Gerando relatório em PDF...");
                    GeradorDeRelatorios.gerarRelatorio(central);
                    break;

                case "7":
                    System.out.println("\n--- Enviar Mensagem para Todos os Jogadores ---");
                    ArrayList<Jogador> listaDestinatarios = central.getTodosOsJogadores();
                    if (listaDestinatarios.isEmpty()) {
                        System.out.println("Nenhum jogador cadastrado na central.");
                    } else {
                        System.out.print("Digite o assunto da mensagem: ");
                        String assunto = scanner.nextLine().trim();
                        System.out.print("Digite o conteúdo da mensagem: ");
                        String mensagem = scanner.nextLine().trim();

                        System.out.println("Enviando mensagens...");
                        for (Jogador j : listaDestinatarios) {
                            Mensageiro.enviarMensagem(j.getEmail(), assunto, mensagem);
                        }
                        System.out.println("Envio finalizado.");
                    }
                    break;

                case "S":
                    persistencia.salvarCentral(central, ARQUIVO_XML);
                    System.out.println("Central salva com sucesso. Saindo do programa...");
                    executando = false;
                    break;

                default:
                    System.out.println("Opção inválida, tente novamente!");
                    break;
            }
        }

        scanner.close();
    }
}