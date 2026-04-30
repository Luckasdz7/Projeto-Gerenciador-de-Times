package pacotes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TimeDAO {
    private Map<String, Time> mapaTimes = new HashMap<>();

    
    private File arquivo = new File("C:\\Users\\lucas\\OneDrive\\Documentos\\crackneto\\dados.txt");

    public void salvar(Time time) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo, true))) {
            StringBuilder linha = new StringBuilder();

            
            linha.append(time.getIdentificador()).append(";").append(time.getNome()).append(";");

            // Formata os jogadores
            for (Jogador j : time.getJogadores()) {
                if (j instanceof Atacante) {
                    Atacante a = (Atacante) j;
                    linha.append("A,").append(a.getNome()).append(",").append(a.getDataNascimento()).append(",").append(a.getNro_gols()).append("|");
                } else if (j instanceof Goleiro) {
                    Goleiro g = (Goleiro) j;
                    linha.append("G,").append(g.getNome()).append(",").append(g.getDataNascimento()).append(",").append(g.getNro_defesas()).append("|");
                }
            }

            bw.write(linha.toString());
            bw.newLine();

            // Adiciona no mapa para busca imediata
            mapaTimes.put(time.getIdentificador(), time);
            System.out.println(" Time salvo com sucesso no arquivo e na memória!");

        } catch (IOException e) {
            System.err.println(" Erro ao salvar o arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void buscar(String identificador) {
        Time t = mapaTimes.get(identificador);

        if (t != null) {
            System.out.println("\n======= DADOS DO TIME =======");
            System.out.println("ID: " + t.getIdentificador());
            System.out.println("Nome: " + t.getNome());
            System.out.println("-----------------------------");
            System.out.println("JOGADORES:");

            for (Jogador j : t.getJogadores()) {
                if (j instanceof Atacante) {
                    Atacante aj = (Atacante) j;
                    System.out.println("[Atacante] Nome: " + aj.getNome() + " | Nasc: " + aj.getDataNascimento() + " | Gols: " + aj.getNro_gols());
                } else if (j instanceof Goleiro) {
                    Goleiro gj = (Goleiro) j;
                    System.out.println("[Goleiro]  Nome: " + gj.getNome() + " | Nasc: " + gj.getDataNascimento() + " | Defesas: " + gj.getNro_defesas());
                }
            }
            System.out.println("=============================\n");
        } else {
            System.out.println(" Aviso: Time com ID '" + identificador + "' não encontrado!");
        }
    }

    public void carregarArquivos() {
        if (!arquivo.exists()) return; 

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;//se a linha lida nao tiver nenhuma informação, o while volta

                String[] partes = linha.split(";");
                String id = partes[0];
                String nome = partes[1];

               
                Time time = new Time(nome, id);

                if (partes.length > 2) {
                    String[] jogadoresStr = partes[2].split("\\|");
                    for (String atbJogador : jogadoresStr) {
                        if (atbJogador.isEmpty()) continue;

                        String[] p = atbJogador.split(",");
                        String tipo = p[0];
                        String nomeJog = p[1];
                        String dataNasc = p[2];
                        int numero = Integer.parseInt(p[3]);

                        if (tipo.equals("G")) {
                            time.addJogador(new Goleiro(nomeJog, dataNasc, numero));
                        } else if (tipo.equals("A")) {
                            time.addJogador(new Atacante(nomeJog, dataNasc, numero));
                        }
                    }
                }
                mapaTimes.put(time.getIdentificador(), time);
            }
        } catch (IOException e) {
            System.err.println("❌ Erro na leitura do arquivo: " + e.getMessage());
        }
    }
    
    private void reescreverarquivo() {
    	try(BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo ,false))){
    		
    		StringBuilder linhatime = new StringBuilder();
    		for( Time time : mapaTimes.values()) {
    			linhatime.append(time.getIdentificador()).append(";").append(time.getNome());
    			
    			
    			for (Jogador j : time.getJogadores()) {
                    if (j instanceof Atacante) {
                        Atacante a = (Atacante) j;
                        linhatime.append("A,").append(a.getNome()).append(",").append(a.getDataNascimento()).append(",").append(a.getNro_gols()).append("|");
                    } else if (j instanceof Goleiro) {
                        Goleiro g = (Goleiro) j;
                        linhatime.append("G,").append(g.getNome()).append(",").append(g.getDataNascimento()).append(",").append(g.getNro_defesas()).append("|");
                    }
                }
    			bw.write(linhatime.toString());
    			bw.newLine();
    			
    		}
    		
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void excluir(String id) {
    	carregarArquivos();
    	mapaTimes.remove(id);
    	
    	System.out.println("removido com sucesso!");
    	reescreverarquivo();
    }
    public void editar(Time time) {
    	carregarArquivos();
    	mapaTimes.put(time.getIdentificador(), time);
    	
    	reescreverarquivo();
    }
}