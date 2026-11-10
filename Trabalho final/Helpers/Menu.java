package Helpers;

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

                            case 3:
                                break;

                            case 4:
                                break;

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
}
