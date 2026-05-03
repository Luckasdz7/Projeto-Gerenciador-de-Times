package pacotes;

public class Goleiro extends Jogador {
    private int nro_defesas;

    public Goleiro(String nome, String dataNascimento, int nro_defesas) {
        super(nome, dataNascimento);
        this.nro_defesas = nro_defesas;
    }

    public int getNro_defesas() { return nro_defesas; }
    public void setNro_defesas(int nro_defesas) { this.nro_defesas = nro_defesas; }
}