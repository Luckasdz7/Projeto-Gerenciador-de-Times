package pacotes;

public class TimeBO {
    private TimeDAO timeDao = new TimeDAO();

    public void carregarArquivos() {
        timeDao.carregarArquivos();
    }

    public void salvarDados(Time time) {
        // Validação da regra de negócio (10 caracteres)s
        if (time.getIdentificador().length() != 10) {
            System.out.println(" Erro: O Identificador do time deve ter EXATAMENTE 10 caracteres. Operação cancelada.");
            return; // Impede que continue e chame o DAO
        }
        timeDao.salvar(time);
    }

    public void buscarDados(String id) {
        timeDao.buscar(id);
    }
 // Repassa o time do DAO para a classe Principal
    public Time retornartime(String id) {
        return timeDao.retornartime(id);
    }

    // Repassa o time editado da classe Principal para o DAO salvar no .txt
    public void editarTime(Time time) {
        timeDao.editar(time);
    }
 // Repassa a ordem de exclusão para o DAO
    public void excluirTime(String id) {
        timeDao.excluir(id);
    }
}