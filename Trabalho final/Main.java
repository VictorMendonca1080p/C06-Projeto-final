import locacao.Locadora;
import pessoa.Cliente;
import Helpers.Menu;

public class Main {
    public static void main(String[] args) {
        
        Locadora locadora = new Locadora("Master Tape"); //estava aparecendo locadora 2x na saída.
        Cliente cliente = new Cliente("Jaum", "129348093");

        //laís: o catalogo vem do arquivo. nao mais fixo aqui
        //antes, quanto mais rodava a main, os 3 filmes apareciam multiplicados. ou seja, o arquivo crescia sozinho(lixo)
        //e cadastrava os mesmos 3 filmes
        locadora.carregarCatalogo();
        //cadastrei os filmes pelo menu enquanto rodava main mesmo, como funcionária :]
        //não mexa pelamor de Deus! Deu trabalho funcionar certo

        /* locadora.adicionarFilme(1, "A culpa não é das estrelas", 5);
        locadora.adicionarFilme(2, "Carros 4", 3);
        locadora.adicionarFilme(3, "Velórios e Furiosos", 4);  só pra anotar os nomes dos filmes*/


        //laís:entrega a locadora e o cliente pro menu
        Menu.Start(locadora, cliente);
    }

}
