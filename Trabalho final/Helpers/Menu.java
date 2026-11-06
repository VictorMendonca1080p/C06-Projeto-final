import java.util.Scanner;

public class Menu {

    static private Scanner entrada = new Scanner(System.in);
    static private int opcao = -1;

    static public void Start()
    {
        do{
            System.out.println("Bem vindo escolha uma opção");
            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    
                    break;
            
                default:
                    System.out.println("escolha uma opção valida");
                    break;
            }

        } while(opcao != 0);
    }
}
