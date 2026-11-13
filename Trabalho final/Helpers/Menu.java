package Helpers;

import java.util.Scanner;

import locacao.*;
import pessoa.*;

public class Menu {

    static private Scanner entrada = new Scanner(System.in);

    static public void Start(Locadora locadora, Cliente cliente) {

        double totalDaCompra = 0;
        int opcaoPrincipal;

        do {
            System.out.println("\nBem vindo a locadora " + locadora.getNome());
            System.out.println("Escolha seu cadastro:");
            System.out.println("Cliente-(1) | Funcionario-(2) | Sair-(0)");

            opcaoPrincipal = lerInteiro("Escolha uma opção:");

            switch (opcaoPrincipal) {

                case 1: {

                    int opcaoCliente;

                    do {
                        System.out.println("\nMenu do Cliente:");
                        System.out.println("1 - Adicionar ao carrinho");
                        System.out.println("2 - Finalizar compra");
                        System.out.println("3 - Ver meus dados");
                        System.out.println("4 - Sair do menu de Clientes");

                        locadora.mostrarFilmes();

                        opcaoCliente = lerInteiro("Escolha uma opção:");

                        switch (opcaoCliente) {

                            case 1: {

                                int chaveProduto = lerInteiro(
                                        "Selecione a chave do produto:"
                                );

                                totalDaCompra += locadora.calcularPrecoAluguel(chaveProduto);

                                cliente.alugarFilme(chaveProduto);

                                break;
                            }

                            case 2:

                                System.out.println("Obrigado pela preferência!");
                                System.out.println("Valor total: " + totalDaCompra);

                                opcaoCliente = 4;

                                break;

                            case 3:

                                System.out.println(cliente.exibirDetalhes());

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
                        System.out.println("7 - Sair do menu de funcionários");

                        opcaoFuncionario = lerInteiro("Escolha uma opção:");

                        switch (opcaoFuncionario) {

                            case 1:

                                locadora.mostrarFilmes();

                                break;

                            case 2: {

                                int newChave = lerInteiro(
                                        "Informe uma chave para o produto:"
                                );

                                String newNome = lerTexto(
                                        "Informe o nome do novo filme:"
                                );

                                int newEstrelas = lerInteiro(
                                        "Informe a quantidade de estrelas (0 a 5):",
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
                                        "Informe a chave do produto a ser removido:"
                                );

                                locadora.removerFilme(chaveRemover);

                                break;
                            }

                            case 4: {

                                int newChave = lerInteiro(
                                        "Informe uma nova chave para o produto:"
                                );

                                String newNome = lerTexto(
                                        "Informe um novo nome para o filme:"
                                );

                                int newEstrelas = lerInteiro(
                                        "Informe a nova quantidade de estrelas (0 a 5):",
                                        0,
                                        5
                                );

                                locadora.trocarFilme(
                                        newChave,
                                        newNome,
                                        newEstrelas
                                );

                                break;
                            }

                            case 5: {

                                String nomeFuncionario = lerTexto(
                                        "Nome do funcionário:"
                                );

                                String cpfFuncionario = lerTexto(
                                        "CPF do funcionário:"
                                );

                                int idDaFuncao = lerInteiro(
                                        "Código da função (número inteiro):"
                                );

                                Funcionario novoFuncionario = new Funcionario(
                                        nomeFuncionario,
                                        cpfFuncionario,
                                        idDaFuncao
                                );

                                locadora.adicionarFuncionario(novoFuncionario);

                                System.out.println("\nFuncionário cadastrado:");
                                System.out.println(novoFuncionario.exibirDetalhes());

                                novoFuncionario.executarFuncao();

                                break;
                            }

                            case 6: {

                                int idRemover = lerInteiro(
                                        "Código da função do funcionário que sai:"
                                );

                                locadora.removerFuncionario(idRemover);

                                System.out.println(
                                        "Remoção solicitada para o código " + idRemover + "."
                                );

                                break;
                            }

                            case 7:

                                System.out.println("Saindo do menu de funcionários...");

                                break;

                            default:

                                System.out.println("Escolha uma opção válida!");

                                break;
                        }

                    } while (opcaoFuncionario != 7);

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

            System.out.println(pergunta);

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

            System.out.println(pergunta);

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
