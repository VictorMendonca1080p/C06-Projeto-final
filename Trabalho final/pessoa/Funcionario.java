package pessoa;
import descricao.Descritivel;
import java.util.Random;

//herda estado e construtor de Pessoa, e assina o contrato de Descritivel
public class Funcionario extends Pessoa implements Descritivel
{
    //codigo do cargo, nao id da pessoa. e por ele que a Locadora acha quem remover
    private int idFuncao;

    public Funcionario(String nome, String cpf, int idFuncao)
    {
        //chama o construtor da superclasse para inicializar o estado herdado
        super(nome, cpf);
        this.idFuncao = idFuncao;
    }

    //sobrescrita: a assinatura vem da superclasse, o corpo eh proprio dessa classe
    @Override
    public String exibirDetalhes()
    {
        return "INFORMAÇÕES DO(A) FUNCIONÁRIO(A): " + "\n ID: " + idFuncao + "\n Nome: " + nome + "\n CPF: " + cpf;
    }

    @Override
    public void identificarPermissao()
    {
        System.out.println("Permissões de acesso: Gerenciar catálogo e quadro de funcionários.");
    }

    //sorteia a atividade a cada chamada, entao o mesmo funcionario pode aparecer fazendo coisas diferentes
    public void executarFuncao()
    {
        Random rand = new Random();
        int escolha = rand.nextInt(4);

        String[] trabalhando = {
                "limpando.",
                "em pausa.",
                "atendendo ao cliente.",
                "se demitindo agorinha pra jogar GTA6..."
        };

        System.out.println("Funcionário(a) do ID " + idFuncao + " está " + trabalhando[escolha]);
    }

    //encapsulamento: o membro eh private, o acesso de fora passa por este metodo.
    //public porque a Locadora esta em outro pacote e so encontra por getter
    public int getIdFuncao()
    {
        return idFuncao;
    }
}
