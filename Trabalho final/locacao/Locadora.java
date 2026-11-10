package locacao;
import excesao.MissingFilmeException;
import excesao.MissingFuncionarioException;
import pessoa.Funcionario; //laís

public class Locadora {

    private String nome;
    private Filme[] catalogo;
    private Funcionario[] funcionarios;

    public Locadora(String nome){
        this.nome = nome;
        this.catalogo = new Filme[100];
        this.funcionarios = new Funcionario[50];
    }

    public void adicionarFilme(Filme novoFilme){
        for(int i = 0; i < catalogo.length; i++){
            if (catalogo[i] == null) {
                catalogo[i] = novoFilme;
            }
        }
    }

    public void removerFilme(int chaveFilme){
        for (int i = 0; i < catalogo.length; i++) {
            if (catalogo[i] != null && catalogo[i].getChave() == chaveFilme) {
                catalogo[i] = null;
                return;
            }
        }
        throw new MissingFilmeException(
                "Filme não encontrado para a remoção! " +
                        "Verifique se o código do filme está certo ou se o filme existe."
        );
    }

    public void trocarFilme(int chaveAntiga, String nomeNovo, int estrelasNovo){
        for(int i = 0; i < catalogo.length; i++){
            if(catalogo[i].getChave() == chaveAntiga){
                catalogo[i] = new Filme(chaveAntiga,nomeNovo,estrelasNovo);
                return;
            }
        }
    }

    public void adicionarFuncionario(Funcionario novoFuncionario){
        for(int i = 0; i < funcionarios.length; i++){
            if(funcionarios[i] == null){
                funcionarios[i] = novoFuncionario;
            }
        }
    }

    public void removerFuncionario(int idFuncao){
        for(int i = 0; i < funcionarios.length; i++){
            if(funcionarios[i] != null && funcionarios[i].getIdFuncao() == idFuncao){
                funcionarios[i] = null;
                return;
            }
        }
        throw new MissingFuncionarioException("Funcionario nao encontrado para remocao! " +
                "Verifique se o ID do funcionario existe.");
    }

    public void mostrarFilmes() {
        boolean catalogoVazio = true;

        for (int i = 0; i < catalogo.length; i++) {
            if (catalogo[i] != null) {
                System.out.println(catalogo[i].exibirDetalhes());
                System.out.println("--------------------");
                catalogoVazio = false;
            }
        }
        if (catalogoVazio) {
            System.out.println("O servidor não tem nenhum filme no momento!");
        }
    }

    public double calcularPrecoAluguel(int chaveFilme){
        for(int i = 0; i < catalogo.length; i++){
            if(catalogo[i].getChave() == chaveFilme){
                return 1; // retorna 1 se achou o fimle, e pode ser add uma formula para calcular o preço
            }
        }
        return 0; // retorn 0 caso n esteja no catalogo
    }
}
