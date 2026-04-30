package pacotes;

import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TimeBO timeBo = new TimeBO();

        System.out.println("Carregando base de dados...");
        timeBo.carregarArquivos(); // Carrega os dados pro Map logo que abre

        int opcao = -1;
        while (opcao != 3) {
            System.out.println("\n--- MENU DE TIMES ---");
            System.out.println("1 -- Salvar Time");
            System.out.println("2 -- Consultar por Identificador");
            System.out.println("3 -- Sair");
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
        }

        System.out.println("Saindo do sistema...");
        sc.close();
    }
}