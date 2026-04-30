package pacotes;

import java.util.ArrayList;
import java.util.List;

public class Time {
    private String nome;
    private String identificador;
    private List<Jogador> jogadores = new ArrayList<>();

    public Time(String nome, String identificador) {
        this.nome = nome;
        this.identificador = identificador;
    }

    public String getNome() 
    { return nome;
    }
    public void setNome(String nome) {
    	this.nome = nome; 
    	}
    public String getIdentificador() {
    	return identificador; 
    	}
    public void setIdentificador(String identificador) { 
    	this.identificador = identificador; 
    	}

    public List<Jogador> getJogadores() {
    	return jogadores; 
    	}
    public void addJogador(Jogador jogador) { 
    	this.jogadores.add(jogador);
    	}
}