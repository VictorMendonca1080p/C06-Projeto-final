package pessoa;

import descricao.Descritivel;

public abstract class Pessoa implements Descritivel
{
    protected String nome;
    protected String cpf;

    public Pessoa(String nome, String cpf)
    {
        this.nome = nome;
        this.cpf = cpf;
    }

    public abstract String exibirDetalhes();
}
