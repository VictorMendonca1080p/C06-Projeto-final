import locacao.Locadora;
import pessoa.Cliente;
import helpers.Menu;

public class Main
{
    public static void main(String[] args)
    {
        Locadora locadora = new Locadora("Master Tape");
        Cliente cliente = new Cliente("Jaum", "129348093");

        locadora.carregarCatalogo();

        Menu.Start(locadora, cliente);
    }
}
