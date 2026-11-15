package descricao;

//laís: existe para o projeto tratar filme, cliente e funcionario
public class Exibidor
{
    public static void mostrar(Descritivel item)
    {
        System.out.println(item.exibirDetalhes());
    }
}
