package pessoa;


public class Cliente extends Pessoa
{
    //laís: conta quantos filmes o cliente ja alugou.
    private int filmesAlugados;

    public Cliente(String nome, String cpf)
    {
        super(nome, cpf);
    }

    //laís: versão de exibiçao do cliente
    @Override
    public String exibirDetalhes()
    {
        return "INFORMAÇÕES DO(A) CLIENTE: " + "\n Nome: " + nome + "\n CPF: " + cpf;
    }

    //laís: so registra a intencao do cliente. conferir o catalogo e calcular o preco é da locadora
    public void alugarFilme(int chaveFilme)
    {
        //laís: chave zero ou negativa nao existe no catalogo, entao nem continua
        if(chaveFilme <= 0)
        {
            System.out.println("Solicitação recusada: a chave " + chaveFilme + " não é válida.");
            return; //laís: o metodo é void, entao este return so interrompe
        }

        filmesAlugados++;
        System.out.println(nome + " solicitou o aluguel do filme de chave " + chaveFilme + ".");
        System.out.println("Total de filmes solicitados por " + nome + ": " + filmesAlugados);
    }

}
