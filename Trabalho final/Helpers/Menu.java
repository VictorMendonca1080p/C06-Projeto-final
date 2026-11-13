package Helpers;
import java.util.Scanner;
import excesao.MissingFuncionarioException;

import locacao.*;
import pessoa.*;

public class Menu {

    static private Scanner entrada = new Scanner(System.in);
    static private int opcao = -1;
    static private String opcaoString = null;

    static public void Start(Locadora locadora, Cliente cliente)

    // seleciona o menu de cliente ou funcionario
    // o de cliente escolhe o filme e aluga
    // o de funcionario gerencia os filmes e funcionarios
    {
        double totalDaCompra = 0;
        do{
            System.out.println("Bem-vindo a locadora " + locadora.getNome() + "! Você é: ");
            System.out.println("Cliente-(1) | Funcionario-(2) | Sair-(0)");

            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    // mostra filmes 
                    // escolhe filmes 
                    // calcula preco alugel 
                    opcao = -1;
                    do {
                        System.out.println("Menu do Cliente: escolha uma opção...");
                        System.out.println("1 - Adicionar ao carrinho");
                        System.out.println("2 - Finalizar compra");
                        System.out.println("3 - Ver meus dados"); //laís: chama o exibirDetalhes do Cliente
                        System.out.println("4 - Sair do menu de Clientes");
                        locadora.mostrarFilmes();
                        opcao = entrada.nextInt();
                        
                        switch (opcao) {
                            case 1:
                                System.out.print("Selecione a chave do produto: ");
                                opcao = entrada.nextInt();
                                totalDaCompra += locadora.calcularPrecoAluguel(opcao);
                                cliente.alugarFilme(opcao);
                                break;

                            case 2:
                                System.out.println("Obrigado pela preferência!");
                                System.out.println("Valor total: " + totalDaCompra);
                                opcao = 2;
                                break;

                            case 3:
                                System.out.println(cliente.exibirDetalhes()); //laís: mostra os dados do proprio cliente que ests usando o programa
                                break;

                            default:
                                System.out.print("Escolha uma opção válida: ");
                                break;
                        }

                    } while (opcao != 4);
                    break;

                case 2:
                    // mostra filmes 
                    // adiciona filme
                    // deleta filme
                    // troca filmes 
                    // adiciona funcionario
                    // remove funcionario 
                
                    do {
                        System.out.println("Menu do Funcionário: escolha uma opção...");

                        System.out.println("1 - Mostrar filmes");
                        System.out.println("2 - Adicionar filme");
                        System.out.println("3 - Remover filme");
                        System.out.println("4 - Trocar filme");
                        System.out.println("5 - Adicionar funcionário");
                        System.out.println("6 - Remover funcionário");
                        System.out.println("7 - Sair do menu de Funcionários");
                        
                        opcao = entrada.nextInt(); 

                        int newChave;
                        String newNome;
                        int newEstrelas;

                        switch (opcao) {
                            case 1:
                                locadora.mostrarFilmes();
                                break;

                            case 2:
                                System.out.println("Informe uma chave para o produto: ");
                                opcao = entrada.nextInt();
                                newChave = opcao;

                                System.out.println("Informe o nome do novo filme: ");
                                opcaoString = entrada.nextLine();
                                newNome = opcaoString;

                                System.out.println("Informe a quantidade de estrelas do novo filme: ");
                                opcao = entrada.nextInt();
                                newEstrelas = opcao;

                                locadora.adicionarFilme(newChave, newNome, newEstrelas);
                                break;

                            case 3:
                                System.out.println("Informe a chave do produto a ser removido: ");
                                opcao = entrada.nextInt();
                                locadora.removerFilme(opcao);

                                break;

                            case 4:
                                System.out.println("Informe uma nova chave para o produto: ");
                                opcao = entrada.nextInt();
                                newChave = opcao;

                                System.out.println("Informe um novo nome para o filme: ");
                                opcaoString = entrada.nextLine();
                                newNome = opcaoString;

                                System.out.println("Informe a nova quantidade de estrelas do filme: ");
                                opcao = entrada.nextInt();
                                newEstrelas = opcao;

                                locadora.trocarFilme(newChave, newNome, newEstrelas);
                                break;
                      
                            case 5: //laís: cadastra um funcionario novo com o que o usuario digitar
                            {
                                entrada.nextLine(); //laís: o nextint deixa o enter pra trás. essa linha
                                //limpa o que teve antes de chegar aqui, senao a pergunta sai vazia.
                                //e o chris disse que vaitestar de tudo na entrada de dados

                                String nomeFuncionario = lerTexto("Nome do funcionário: ");
                                String cpfFuncionario = lerTexto("CPF do funcionário: ");
                                int idDaFuncao = lerInteiro("Código da função(número inteiro): ");

                                Funcionario novoFuncionario = new Funcionario(nomeFuncionario, cpfFuncionario, idDaFuncao);
                                locadora.adicionarFuncionario(novoFuncionario);

                                //laís: mostra o funcionsrio recem-criado e manda ele trabalahr
                                System.out.println("Funcionário cadastrado: ");
                                System.out.println(novoFuncionario.exibirDetalhes());
                                novoFuncionario.executarFuncao();
                                break;
                            }

                            case 6: //laís: remove um funcionario
                            {
                                entrada.nextLine(); //laís: mesma limpeza do enter

                                int idRemover = lerInteiro("Código da função do funcionário que sai: ");

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

                            default:
                                System.out.println("escolha uma opção valida");
                                break;
                        }

                    } while (opcao != 7);

                    break;
            
                default:
                    System.out.println("escolha uma opção valida");
                    break;
            }

        } while(opcao != 0);
    }

    // laís: le uma linha do teclado e nao aceita resposta em branco.
    // fica perguntando ate o usuario digitar alguma coisa
    static public String lerTexto(String pergunta) //laís
    {
        String resposta = "";
        boolean textoValido = false;

        while(textoValido == false)
        {
            System.out.println(pergunta);
            resposta = entrada.nextLine();

            if(resposta.trim().isEmpty())
            {
                System.out.println("Este campo não pode ficar em branco.");
            }
            else
            {
                textoValido = true;
            }
        }

        return resposta;
    }

    // laís: le uma linha e converte para numero inteiro
    // se vier letra ou vazio, o catch avisa e a pergunta se repete, sem derrubar o programa
    static public int lerInteiro(String pergunta) //laís
    {
        int numeroLido = 0;
        boolean numeroValido = false;

        while(numeroValido == false)
        {
            System.out.println(pergunta);
            String digitado = entrada.nextLine(); //laís: le como texto para o nextInt nao bugar

            try
            {
                numeroLido = Integer.parseInt(digitado.trim());
                numeroValido = true;
            }
            catch (NumberFormatException erroFormato)
            {
                System.out.println("Isso não é um número inteiro. Tente novamente: ");
            }
        }

        return numeroLido;
    }
}
