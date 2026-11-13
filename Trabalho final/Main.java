import locacao.Locadora;
import pessoa.Cliente;
import Helpers.Menu;

public class Main {
    public static void main(String[] args) {
        
        Locadora locadora = new Locadora("Master Tape"); //estava aparecendo locadora 2x na saída.
        Cliente cliente = new Cliente("Jaum", "129348093");

        locadora.adicionarFilme(1, "A culpa não é das estrelas", 5);
        locadora.adicionarFilme(2, "Carros 4", 3);
        locadora.adicionarFilme(3, "Velórios e Furiosos", 4);


        //laís:entrega a locadora e o cliente pro menu
        Menu.Start(locadora, cliente);
    }

}
