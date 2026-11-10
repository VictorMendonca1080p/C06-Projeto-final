import locacao.Filme;
import locacao.Locadora;
import pessoa.Cliente;
import Helpers.Menu;

public class Main {
    public static void main(String[] args) {
        
        Locadora locadora = new Locadora("Locadora master");
        Cliente cliente = new Cliente("Jaum", "129348093");

        locadora.adicionarFilme(1, "a culpa não é das estrelas", 5);
        locadora.adicionarFilme(2, "carros 4", 10);
        locadora.adicionarFilme(3, "velórios e furiosos", 9);


        //laís:entrega a locadora e o cliente pro menu
        Menu.Start(locadora, cliente);
    }

}
