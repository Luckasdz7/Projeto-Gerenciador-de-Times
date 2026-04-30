package pacotes;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TimeBO timeBo = new TimeBO();

        System.out.println("Carregando base de dados...");
        timeBo.carregarArquivos(); // Carrega os dados pro Map logo que abre

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n--- MENU DE TIMES ---");
            System.out.println("1 -- Salvar Time");
            System.out.println("2 -- Consultar por Identificador");
            System.out.println("3 -- Editar Time");
            System.out.println("4 -- Excluir Time");
            System.out.println("5 -- Mostrar times");//posteriomente será uma funcção de administrador
            System.out.println("0 -- Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a quebra de linha do enter

            if (opcao == 1) {
                System.out.print("Quantos times vai cadastrar? ");
                int numTimes = sc.nextInt();
                sc.nextLine();

                for (int i = 0; i < numTimes; i++) {
                    System.out.println("\n[Cadastro do Time " + (i + 1) + "]");
                    System.out.print("Nome do Time: ");
                    String nomeTime = sc.nextLine();

                    System.out.print("Identificador do time (exatamente 10 caracteres): ");
                    String id = sc.nextLine();

                    Time time = new Time(nomeTime, id);

                    System.out.print("Quantos jogadores tem o time? ");
                    int numJogadores = sc.nextInt();
                    sc.nextLine();

                    for (int j = 0; j < numJogadores; j++) {
                        System.out.println("\n[Jogador " + (j + 1) + "]");
                        System.out.print("Nome: ");
                        String nomeJog = sc.nextLine();

                        System.out.print("Data de nascimento (DD/MM/AAAA): ");
                        String dataNasc = sc.nextLine();

                        System.out.print("É Goleiro(1) ou Atacante(2)? ");
                        String tipo = sc.nextLine();

                        if (tipo.equals("1")) {
                            System.out.print("Número de defesas: ");
                            int defesas = sc.nextInt();
                            sc.nextLine();
                            time.addJogador(new Goleiro(nomeJog, dataNasc, defesas));
                        } else if (tipo.equals("2")) {
                            System.out.print("Número de gols: ");
                            int gols = sc.nextInt();
                            sc.nextLine();
                            time.addJogador(new Atacante(nomeJog, dataNasc, gols));
                        }
                    }
                    // Manda pro BO para validar e salvar
                    timeBo.salvarDados(time);
                }

            } else if (opcao == 2) {
                System.out.print("\nDigite o Identificador do time para buscar: ");
                String idBusca = sc.nextLine();
                timeBo.buscarDados(idBusca);
            }
            else if(opcao == 3) {//a ser melhorado
            	System.out.println("coloque o id do time a ser editado");
            	 String idBusca = sc.nextLine();
                 timeBo.buscarDados(idBusca);
                 /*// ... dentro do seu switch-case ...

case 3: // EDITAR TIME
    System.out.print("\nDigite o ID do Time que deseja editar: ");
    String idEditar = ler.nextLine();
    Time timeExistente = timeBO.buscarPorId(idEditar); // Busca o time no BO

    if (timeExistente != null) {
        System.out.println("\n--- Editando o Time: " + timeExistente.getNome() + " ---");
        System.out.println("Pressione [ENTER] se não quiser alterar um campo.");

        // 1. Editando o Nome do Time
        System.out.print("Novo nome [" + timeExistente.getNome() + "]: ");
        String novoNome = ler.nextLine();
        if (!novoNome.trim().isEmpty()) {
            timeExistente.setNome(novoNome); // Precisará criar o setNome() na classe Time
        }

        // 2. Menu de Edição de Jogadores
        boolean editandoJogadores = true;
        while (editandoJogadores) {
            System.out.println("\n--- Jogadores Atuais ---");
            List<Jogador> jogadores = timeExistente.getJogadores();
            
            if (jogadores.isEmpty()) {
                System.out.println("Nenhum jogador cadastrado.");
            } else {
                // Lista os jogadores com seus índices (começando do 1 para o usuário)
                for (int i = 0; i < jogadores.size(); i++) {
                    System.out.println((i + 1) + ". " + jogadores.get(i).getNome() + " (" + jogadores.get(i).getClass().getSimpleName() + ")");
                }
            }

            System.out.println("\nOpções de Jogadores:");
            System.out.println("1 - Editar jogador existente");
            System.out.println("2 - Adicionar novo jogador");
            System.out.println("3 - Remover jogador");
            System.out.println("0 - Concluir edição e Salvar");
            System.out.print("Escolha: ");
            int opcJogador = Integer.parseInt(ler.nextLine());

            switch (opcJogador) {
                case 1: // Editar jogador
                    System.out.print("Digite o número do jogador a editar: ");
                    int numEdit = Integer.parseInt(ler.nextLine());
                    // Verifica se o número é válido
                    if (numEdit > 0 && numEdit <= jogadores.size()) {
                        Jogador jogAtual = jogadores.get(numEdit - 1); // Pega o jogador pelo índice (numEdit - 1 porque o array começa no 0)
                        
                        System.out.println("\nEditando: " + jogAtual.getNome());
                        System.out.print("Novo nome [" + jogAtual.getNome() + "]: ");
                        String nNome = ler.nextLine();
                        if (!nNome.trim().isEmpty()) jogAtual.setNome(nNome); // Precisa do setNome() em Jogador
                        
                        // Você pode adicionar a mesma lógica para Data de Nascimento e atributos específicos (gols/defesas) verificando se é Goleiro ou Atacante
                    } else {
                        System.out.println("Jogador inválido.");
                    }
                    break;
                case 2: // Adicionar
                    // ... chama a mesma lógica que você já tem para adicionar jogador ...
                    break;
                case 3: // Remover
                    System.out.print("Digite o número do jogador a remover: ");
                    int numRemover = Integer.parseInt(ler.nextLine());
                    if (numRemover > 0 && numRemover <= jogadores.size()) {
                        jogadores.remove(numRemover - 1);
                        System.out.println("Jogador removido do time.");
                    } else {
                        System.out.println("Jogador inválido.");
                    }
                    break;
                case 0:
                    editandoJogadores = false;
                    break;
            }
        }

        // Após concluir as edições na memória, chama o BO para salvar
        if (timeBO.editarTime(timeExistente)) {
            System.out.println("\n✅ Time atualizado e salvo com sucesso!");
        } else {
            System.out.println("\n❌ Erro ao salvar as edições.");
        }

    } else {
        System.out.println("❌ Time não encontrado.");
    }
    break;*/
            }
        }

        System.out.println("Saindo do sistema...");
        sc.close();
    }
}