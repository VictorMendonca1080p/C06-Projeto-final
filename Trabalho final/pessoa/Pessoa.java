package pessoa;
// quem fizer a interface: descomentar a linha 4
// se eu fizer isso agora, o código não rodará
// import descricao.Descritivel;

// ah, e troque a linha " public abstract class Pessoa { " por
// public abstract class Pessoa implements Descritivel {

public abstract class Pessoa
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
