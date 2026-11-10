package pessoa;

import java.util.Random;

public class Funcionario extends Pessoa
{
    private int idFuncao;

    public Funcionario(String nome, String cpf, int idFuncao)
    {
        super(nome, cpf);
        this.idFuncao = idFuncao;
    }

    @Override
    public String exibirDetalhes()
    {
        return "INFORMAÇÕES DO(A) FUNCIONÁRIO(A): " + "\n ID: " + idFuncao + "\n Nome: " + nome + "\n CPF: " + cpf;
    }

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

    public int getIdFuncao()
    {
        return this.idFuncao;
    }

}
