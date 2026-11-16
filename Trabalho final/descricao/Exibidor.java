package descricao;

//polimorfismo: o parametro eh do tipo da interface, nao de uma classe concreta.
//por isso o mesmo metodo atende Filme, Cliente e Funcionario,
//e o exibirDetalhes executado eh sempre o da classe real que chegou
public class Exibidor
{
    public static void mostrar(Descritivel item)
    {
        System.out.println(item.exibirDetalhes());
    }
}
