package pacotes;

public abstract class Jogador {
    private String nome;
    private String dataNascimento; // Corrigido de 'datan' para seguir o PDF

    public Jogador(String nome, String dataNascimento) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(String dataNascimento) { this.dataNascimento = dataNascimento; }
}