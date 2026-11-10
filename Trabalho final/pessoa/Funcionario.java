package pessoa;

import java.util.Random;

//laís: funcionario e o objeto-parte da relaçao com a locadora
public class Funcionario extends Pessoa
{
    //laís: codigo da funçao. e por ele que a locadora acha o funcionario para remover
    private int idFuncao;

    public Funcionario(String nome, String cpf, int idFuncao)
    {
        super(nome, cpf);
        this.idFuncao = idFuncao;
    }

    //laís: versao do funcionario, mostra o id, nom e e cpf
    @Override
    public String exibirDetalhes()
    {
        return "INFORMAÇÕES DO(A) FUNCIONÁRIO(A): " + "\n ID: " + idFuncao + "\n Nome: " + nome + "\n CPF: " + cpf;
    }

    //laís: imprime o que o funcionario esta fazendo de modo aleatorio a cada chamada
    //aproveitei o desafio passado em sala
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

    //laís: idFuncao é private, entao a locadora precisa deste getter pra comparar
    public int getIdFuncao()
    {
        return idFuncao;
    }
}
