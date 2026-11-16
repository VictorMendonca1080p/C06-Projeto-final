package pessoa;
import descricao.Descritivel;

//herda estado e construtor de Pessoa, e assina o contrato de Descritivel
public class Cliente extends Pessoa implements Descritivel
{
    private int filmesAlugados;

    public Cliente(String nome, String cpf)
    {
        //chama o construtor da superclasse para inicializar o estado herdado
        super(nome, cpf);
    }

    //sobrescrita: a assinatura vem da superclasse, o corpo eh proprio dessa classe
    @Override
    public String exibirDetalhes()
    {
        return "INFORMAÇÕES DO(A) CLIENTE: " + "\n Nome: " + nome + "\n CPF: " + cpf;
    }

    @Override
    public void identificarPermissao()
    {
        System.out.println("--- Acesso ao cliente: " + nome + " ---");
        System.out.println("Permissões: Consultar catálogo e alugar filmes.");
    }

    //aqui so registra a intencao do cliente. conferir se a chave existe no catalogo e
    //calcular o preco sao responsabilidade da Locadora, que eh quem conhece o catalogo
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
