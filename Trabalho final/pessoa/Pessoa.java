package pessoa;

//classe abstrata: concentra o estado comum (nome e cpf) e o construtor,
//mas nao pode virar objeto, porque "pessoa" nao existe para a locadora
public abstract class Pessoa
{
    //encapsulamento: protected deixa fechado para fora, mas aberto para as subclasses (as filhas)
    protected String nome;
    protected String cpf;

    public Pessoa(String nome, String cpf)
    {
        this.nome = nome;
        this.cpf = cpf;
    }

    //metodo abstrato: a superclasse define o contrato, cada subclasse escreve a implementacao.
    //a interface Descritivel fica nas filhas, que sao quem cumpre o contrato,
    //e nao nesta classe, que so o repassa
    public abstract void identificarPerfil();
}
