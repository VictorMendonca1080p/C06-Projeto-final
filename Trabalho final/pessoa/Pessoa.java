package pessoa;

//laís: superclasse abstrata, nao vira objeto. existe so para cliente e funcionario herdarem
public abstract class Pessoa
{
    protected String nome;
    protected String cpf;

    public Pessoa(String nome, String cpf)
    {
        this.nome = nome;
        this.cpf = cpf;
    }

    //laís: sem o corpo de proposito. cada filha PRECISA escrever sua versao
    //é abstrata. nosso foco tem que ser redirecionar o descritivel, e não tirar o requisito da abstracao
    public abstract String exibirDetalhes();
}
