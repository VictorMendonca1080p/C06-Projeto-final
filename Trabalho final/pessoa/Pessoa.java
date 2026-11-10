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

    @Override
    public String exibirDetalhes()
    {
        return "Nome: " + this.nome + "\nCPF: " + this.cpf;
    }
}
