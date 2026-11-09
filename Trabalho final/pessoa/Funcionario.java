package pessoa;

public class Funcionario extends Pessoa{
    private int idFuncao;

    public Funcionario(String nome, String CPF, int idFuncao)
    {
        super(nome, CPF);
        this.idFuncao = idFuncao;
    }

    public void executarFuncao(int idFuncao)
    {
        //pensar em algo ///////////////////////////////////////////////////////////
    }

    public int getIdFuncao()
    {
        return this.idFuncao;
    }

}
