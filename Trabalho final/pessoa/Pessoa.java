package pessoa;

import descricao.Descritivel;

public class Pessoa implements Descritivel{
    protected String nome;
    protected String CPF;

    public Pessoa(String nome, String cpf)
    {
        this.nome = nome;
        this.CPF = cpf;
    }

    @Override
    public String exibirDetalhes() {
        return "Nome: " + this.nome + "\nCPF: " + this.CPF;
    }
}
