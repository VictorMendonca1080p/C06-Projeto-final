import java.util.Scanner;

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
            System.out.println("Bem vindo a locadora " + locadora.getnome() + ", escolha seu cadastro");
            System.out.println("Cliente-(1) | Funcionario-(2)");

            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    // mostra filmes 
                    // escolhe filmes 
                    // calcula preco alugel 
                    opcao = -1;
                    do {
                        System.out.println("Menu do Cliente: escolha uma opcao...");
                        System.out.println("1 - adicionar ao carrinho");
                        System.out.println("2 - finalizar compra");
                        System.out.println("3 - ver meus dados"); //laís: chama o exibirDetalhes do Cliente

                        locadora.mostrarFilmes();
                        opcao = entrada.nextInt();
                        
                        switch (opcao) {
                            case 1:
                                System.out.println("selecione a chave do produto:");
                                opcao = entrada.nextInt();
                                totalDaCompra += locadora.calcularPrecoAluguel(opcao);
                                cliente.alugarFilme(opcao);
                                break;

                            case 2:
                                System.out.println("Obrigado pela preferencia");
                                System.out.println("valor total: " + totalDaCompra);
                                opcao = 2;
                                break;

                            case 3:
                                System.out.println(cliente.exibirDetalhes()); //laís: mostra os dados do proprio cliente que ests usando o programa
                                break;
                        
                            default:
                                System.out.println("escolha uma opção valida");
                                break;
                        }

                    } while (opcao != 2);
                    break;

                case 2:
                    // mostra filmes 
                    // adiciona filme
                    // deleta filme
                    // troca filmes 
                    // adiciona funcionario
                    // remove funcionario 
                
                    do {
                        System.out.println("Menu do Funcionario: escolha uma opcao...");
                        opcao = entrada.nextInt(); 

                        int newChave;
                        String newNome;
                        int newEstrelas;

                        switch (opcao) {
                            case 1:
                                locadora.mostrarFilmes();
                                break;

                            case 2:
                                opcao = entrada.nextInt();
                                newChave = opcao;

                                opcaoString = entrada.nextLine();
                                newNome = opcaoString;

                                opcao = entrada.nextInt();
                                newEstrelas = opcao;


                                
                                locadora.adicionarFilme(newChave, newNome, newEstrelas);
                                break;

                            case 3: //laís: cadastra um funcionario novo com o que o usuario digitar
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

                            case 4: //laís: remove um funcionario
                            {
                                entrada.nextLine(); //laís: mesma limpeza do enter

                                int idRemover = lerInteiro("Código da função do funcionário que sai: ");
                                locadora.removerFuncionario(idRemover);
                                System.out.println("Remoção solicitada para o código " + idRemover + ".");
                                break;
                            }

                            case 5:
                                break;

                            case 6:
                                break;
                        
                            default:
                                System.out.println("escolha uma opção valida");
                                break;
                        }

                    } while (opcao != 2);

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
            } catch (NumberFormatException erroFormato)
            {
                System.out.println("Isso não é um número inteiro. Tente novamente: ");
            }
        }

        return numeroLido;
    }
}
