package pacotes;

public class TimeBO {
    private TimeDAO timeDao = new TimeDAO();

    public void carregarArquivos() {
        timeDao.carregarArquivos();
    }

    public void salvarDados(Time time) {
        // Validação da regra de negócio (10 caracteres)
        if (time.getIdentificador().length() != 10) {
            System.out.println(" Erro: O Identificador do time deve ter EXATAMENTE 10 caracteres. Operação cancelada.");
            return; // Impede que continue e chame o DAO
        }
        timeDao.salvar(time);
    }

    public void buscarDados(String id) {
        timeDao.buscar(id);
    }
}