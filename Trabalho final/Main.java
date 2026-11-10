import locacao.Locadora;
import pessoa.Cliente;
import Helpers.Menu;

public class Main {
    public static void main(String[] args) {
        //Menu.Start();

        //laís:a locadora é criada com o que for digitado
        String nomeLocadora = Menu.lerTexto("Nome da locadora: ");
        Locadora locadora = new Locadora(nomeLocadora);

        //laís: cria o cliente que for usar o programa
        String nomeCliente = Menu.lerTexto("Seu nome: ");
        String cpfCliente = Menu.lerTexto("seu CPDF: ");
        Cliente cliente = new Cliente(nomeCliente, cpfCliente);

        //laís:entrega a locadora e o cliente pro menu
        Menu.Start(locadora, cliente);
    }

}
