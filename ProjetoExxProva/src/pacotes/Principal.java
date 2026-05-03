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
            else if(opcao == 3) {
            	System.out.println("Coloque o ID do time a ser editado:");
                String idEditar = sc.nextLine();
                
                // Busca o objeto Time real, não apenas imprime
                Time timeExistente = timeBo.retornartime(idEditar);

                if (timeExistente != null) {
                    System.out.println("\n--- Editando o Time: " + timeExistente.getNome() + " ---");
                    System.out.println("Pressione [ENTER] se não quiser alterar um campo.");

                    // 1. Editando o Nome do Time
                    System.out.print("Novo nome [" + timeExistente.getNome() + "]: ");
                    String novoNome = sc.nextLine();
                    if (!novoNome.trim().isEmpty()) {
                        timeExistente.setNome(novoNome); 
                    }

                    // 2. Menu de Edição de Jogadores
                    boolean editandoJogadores = true;
                    while (editandoJogadores) {
                        System.out.println("\n--- Jogadores Atuais ---");
                        
                        java.util.List<Jogador> jogadores = timeExistente.getJogadores(); 
                        
                        if (jogadores.isEmpty()) {
                            System.out.println("Nenhum jogador cadastrado.");
                        } else {
                            // Lista os jogadores 
                            for (int i = 0; i < jogadores.size(); i++) {
                                System.out.println((i + 1) + ". " + jogadores.get(i).getNome() + " (" + jogadores.get(i).getClass().getSimpleName() + ")");
                            }
                        }

                        System.out.println("\nOpções de Edição:");
                        System.out.println("1 - Editar nome do jogador");
                        System.out.println("2 - Adicionar novo jogador");
                        System.out.println("3 - Remover jogador");
                        System.out.println("0 - Concluir edição e Salvar Time");
                        System.out.print("Escolha: ");
                        
                        // Proteção caso o usuário digite algo que não é número
                        int opcJogador = -1;
                        try {
                            opcJogador = Integer.parseInt(sc.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida.");
                            continue;
                        }

                        switch (opcJogador) {
                            case 1: // Editar jogador
                                System.out.print("Digite o número do jogador a editar: ");
                                int numEdit = Integer.parseInt(sc.nextLine());
                                
                                if (numEdit > 0 && numEdit <= jogadores.size()) {
                                    Jogador jogAtual = jogadores.get(numEdit - 1); 
                                    
                                    System.out.println("\nEditando: " + jogAtual.getNome());
                                    System.out.print("Novo nome [" + jogAtual.getNome() + "]: ");
                                    String nNome = sc.nextLine();
                                    if (!nNome.trim().isEmpty()) {
                                        jogAtual.setNome(nNome); 
                                    }
                                } else {
                                    System.out.println("Jogador inválido.");
                                }
                                break;
                            case 2: // Adicionar Jogador (código similar ao da opção 1 de cadastro)
                                System.out.println("\n[Novo Jogador]");
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
                                    timeExistente.addJogador(new Goleiro(nomeJog, dataNasc, defesas));
                                    System.out.println("Goleiro adicionado.");
                                } else if (tipo.equals("2")) {
                                    System.out.print("Número de gols: ");
                                    int gols = sc.nextInt();
                                    sc.nextLine();
                                    timeExistente.addJogador(new Atacante(nomeJog, dataNasc, gols));
                                    System.out.println("Atacante adicionado.");
                                }
                                break;
                            case 3: // Remover Jogador
                                System.out.print("Digite o número do jogador a remover: ");
                                int numRemover = Integer.parseInt(sc.nextLine());
                                if (numRemover > 0 && numRemover <= jogadores.size()) {
                                    jogadores.remove(numRemover - 1);
                                    System.out.println("Jogador removido do time temporariamente (será salvo ao concluir).");
                                } else {
                                    System.out.println("Jogador inválido.");
                                }
                                break;
                            case 0:
                                editandoJogadores = false; // Sai do loop interno
                                break;
                            default:
                                System.out.println("Opção inválida.");
                        }
                    }

                   
                    
                    try{
                    	timeBo.editarTime(timeExistente);
                    	 System.out.println("\n✅ Time atualizado e salvo com sucesso!");
                    }catch(Exception e) {
                       e.getMessage();
                    }
                } else {
                    System.out.println("❌ Time não encontrado.");
                }
            }else if(opcao == 4) {
            	System.out.println("Coloque o ID do time a ser excluído:");
                String idExcluir = sc.nextLine();
                
             
                Time timeExistente = timeBo.retornartime(idExcluir);
                
                if (timeExistente != null) {
                   
                    System.out.println("CUIDADO: Você tem certeza que deseja excluir o time '" + timeExistente.getNome() + "' e todos os seus jogadores? (S/N)");
                    String confirmacao = sc.nextLine();
                    
                    if (confirmacao.equalsIgnoreCase("S")) {
                        );
                      
                        System.out.println("Operação de exclusão cancelada.");
                    }
                } else {
                    System.out.println("Erro: Time não encontrado!");
                }
            }
        }

        System.out.println("Saindo do sistema...");
        sc.close();
    }
}