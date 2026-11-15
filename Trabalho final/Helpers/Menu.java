package Helpers;
import descricao.Exibidor;
import java.util.Scanner;
import excesao.MissingFuncionarioException; //laís
import excesao.MissingFilmeException; //laís

import locacao.*;
import pessoa.*;

public class Menu {

    static private Scanner entrada = new Scanner(System.in);

    static public void Start(Locadora locadora, Cliente cliente) {

        int opcaoPrincipal;

        do {
            System.out.println("\nBem vindo a locadora " + locadora.getNome());
            System.out.println("Escolha seu cadastro:");
            System.out.println("Cliente-(1) | Funcionario-(2) | Sair-(0)");

            opcaoPrincipal = lerInteiro("Escolha uma opção: ");

            switch (opcaoPrincipal) {

                case 1: {

                    //laís: o carrinho nasce aqui, entao cada cliente comeca do zero com o total
                    double totalDaCompra = 0;
                    int opcaoCliente;

                    do {
                        System.out.println("\nMenu do Cliente:");
                        System.out.println("1 - Adicionar ao carrinho");
                        System.out.println("2 - Finalizar compra");
                        System.out.println("3 - Ver meus dados");
                        System.out.println("4 - Sair do menu de Clientes");

                        //laís: o catalogo saiu daqui. aparecia inteiro entre as opcoes e a pergunta
                        //

                        opcaoCliente = lerInteiro("Escolha uma opção: ");

                        switch (opcaoCliente) {

                            case 1: {
                                //laís: agora as chaves ficam aqui. mais organizado
                                locadora.mostrarFilmes();
                                int chaveProduto = lerInteiro(
                                        "Selecione a chave do produto:"
                                );
                                if(locadora.calcularPrecoAluguel(chaveProduto) != -1){
                                    totalDaCompra += locadora.calcularPrecoAluguel(chaveProduto);

                                    cliente.alugarFilme(chaveProduto);
                                }
                                else {
                                    System.out.println("Essa chave não existe, insira uma chave válida: ");
                                }

                                break;
                            }

                            case 2:

                                System.out.println("Obrigado pela preferência!");
                                System.out.println("Valor total: " + totalDaCompra);

                                opcaoCliente = 4;

                                break;

                            case 3:

                                Exibidor.mostrar(cliente); //laís

                                break;

                            case 4:

                                System.out.println("Saindo do menu de clientes...");

                                break;

                            default:

                                System.out.println("Escolha uma opção válida!");

                                break;
                        }

                    } while (opcaoCliente != 4);

                    break;
                }

                case 2: {

                    int opcaoFuncionario;

                    do {

                        System.out.println("\nMenu do Funcionário:");
                        System.out.println("1 - Mostrar filmes");
                        System.out.println("2 - Adicionar filme");
                        System.out.println("3 - Remover filme");
                        System.out.println("4 - Trocar filme");
                        System.out.println("5 - Adicionar funcionário");
                        System.out.println("6 - Remover funcionário");
                        System.out.println("7 - Listar funcionários"); //laís
                        System.out.println("8 - Sair do menu de funcionários");

                        opcaoFuncionario = lerInteiro("Escolha uma opção: ");

                        switch (opcaoFuncionario) {

                            case 1:

                                locadora.mostrarFilmes();

                                break;

                            case 2: {

                                int newChave = lerInteiro(
                                        "Informe uma chave para o produto: "
                                );

                                String newNome = lerTexto(
                                        "Informe o nome do novo filme: "
                                );

                                int newEstrelas = lerInteiro(
                                        "Informe a quantidade de estrelas (0 a 5): ",
                                        0,
                                        5
                                );

                                locadora.adicionarFilme(
                                        newChave,
                                        newNome,
                                        newEstrelas
                                );

                                break;
                            }

                            case 3: {

                                int chaveRemover = lerInteiro(
                                        "Informe a chave do produto a ser removido: "
                                );

                                //laís: a locadora lanca excecao quando a chave nao existe
                                try
                                {
                                    locadora.removerFilme(chaveRemover);
                                }
                                catch (MissingFilmeException erroFilme)
                                {
                                    System.out.println(erroFilme.getMessage());
                                }

                                break;
                            }

                            case 4: {

                                int newChave = lerInteiro(
                                        "Informe a chave para substituir: "
                                );

                                String newNome = lerTexto(
                                        "Informe um novo nome para o filme: "
                                );

                                int newEstrelas = lerInteiro(
                                        "Informe a nova quantidade de estrelas (0 a 5): ",
                                        0,
                                        5
                                );

                                //laís: mesma protecao do remover
                                try
                                {
                                    locadora.trocarFilme(newChave, newNome, newEstrelas);
                                }
                                catch (MissingFilmeException erroFilme)
                                {
                                    System.out.println(erroFilme.getMessage());
                                }

                                break;
                            }

                            case 5: {

                                String nomeFuncionario = lerTexto(
                                        "Nome do funcionário: "
                                );

                                String cpfFuncionario = lerTexto(
                                        "CPF do funcionário: "
                                );

                                int idDaFuncao = lerInteiro(
                                        "Código da função (número inteiro): "
                                );

                                Funcionario novoFuncionario = new Funcionario(
                                        nomeFuncionario,
                                        cpfFuncionario,
                                        idDaFuncao
                                );

                                locadora.adicionarFuncionario(novoFuncionario);

                                System.out.println("\nFuncionário cadastrado: ");
                                Exibidor.mostrar(novoFuncionario); //laís

                                novoFuncionario.executarFuncao();

                                break;
                            }

                            case 6: {

                                int idRemover = lerInteiro(
                                        "Código da função do funcionário que sai: "
                                );

                                //laís: a locadora lanca excecao quando o ID nao existe.
                                //sem este try/catch o programa inteiro morre
                                try
                                {
                                    locadora.removerFuncionario(idRemover);
                                }
                                catch (MissingFuncionarioException erroFuncionario)
                                {
                                    System.out.println(erroFuncionario.getMessage());
                                }

                                break;
                            }

                            //laís: listar antes de remover, senao ninguem sabe qual ID existe
                            case 7:

                                locadora.mostrarFuncionarios();

                                break;

                            case 8:

                                System.out.println("Saindo do menu de funcionários...");

                                break;

                            default:

                                System.out.println("Escolha uma opção válida!");

                                break;
                        }

                    } while (opcaoFuncionario != 8);

                    break;
                }

                case 0:

                    System.out.println("Encerrando o programa...");

                    break;

                default:

                    System.out.println("Escolha uma opção válida!");

                    break;
            }

        } while (opcaoPrincipal != 0);
    }


    static public String lerTexto(String pergunta) {

        String resposta;

        while (true) {

            System.out.print(pergunta);

            resposta = entrada.nextLine();

            if (resposta.trim().isEmpty()) {

                System.out.println("Este campo não pode ficar em branco!");

            } else {

                return resposta;
            }
        }
    }

    static public int lerInteiro(String pergunta) {

        int numeroLido;

        while (true) {

            System.out.print(pergunta);

            String digitado = entrada.nextLine();

            try {

                numeroLido = Integer.parseInt(digitado.trim()); //formata para numeros inteiros e nega letras

                return numeroLido;

            } catch (NumberFormatException erroFormato) {

                System.out.println(
                        "Entrada inválida! Digite apenas números inteiros."
                );
            }
        }
    }

    static public int lerInteiro(String pergunta, int minimo, int maximo) {

        int numeroLido;

        while (true) {

            numeroLido = lerInteiro(pergunta);

            if (numeroLido >= minimo && numeroLido <= maximo) {

                return numeroLido;

            } else {

                System.out.println(
                        "Valor inválido! Digite um número entre "
                                + minimo + " e " + maximo + "."
                );
            }
        }
    }
}
