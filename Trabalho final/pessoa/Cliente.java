package pessoa;

public class Cliente extends Pessoa
{
    private int filmesAlugados;

    public Cliente(String nome, String cpf)
    {
        super(nome, cpf);
    }

    @Override
    public String exibirDetalhes()
    {
        return "INFORMAÇÕES DO(A) CLIENTE: " + "\n Nome: " + nome + "\n CPF: " + cpf;
    }

    public void alugarFilme(int chaveFilme)
    {
        if(chaveFilme <= 0)
        {
            System.out.println("Solicitação recusada: a chave " + chaveFilme + " não é válida.");
            return;
        }

        filmesAlugados++;
        System.out.println(nome + " solicitou o aluguel do filme de chave " + chaveFilme + ".");
        System.out.println("Total de filmes solicitados por " + nome + ": " + filmesAlugados);
    }
    
}