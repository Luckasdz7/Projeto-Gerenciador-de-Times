package pacotes;


public class Atacante extends Jogador {
    private int nro_gols;

    public Atacante(String nome, String dataNascimento, int nro_gols) {
        super(nome, dataNascimento);
        this.nro_gols = nro_gols;
    }

    public int getNro_gols() { return nro_gols; }
    public void setNro_gols(int nro_gols) { this.nro_gols = nro_gols; }
}