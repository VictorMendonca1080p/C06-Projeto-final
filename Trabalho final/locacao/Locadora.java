package locacao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import excesao.MissingFilmeException;
import excesao.MissingFuncionarioException;
import pessoa.Funcionario; //laís

public class Locadora {

    private String nome;
    private List<Filme> catalogo;
    private List<Funcionario> funcionarios;

    public Locadora(String nome){
        this.nome = nome;
        this.catalogo = new ArrayList<Filme>();
        this.funcionarios = new ArrayList<Funcionario>();
    }

    public String getNome()
    {
        return this.nome;
    }

    public void adicionarFilme(int newChave, String newNome, int newEstrelas){
        Filme novoFilme = new Filme(newChave, newNome, newEstrelas);
        catalogo.add(novoFilme);
        
    

        Path arquivo = Paths.get("C:\\Documentos\\C06\\C06-Projeto-final-main\\Trabalho final\\locacao\\catalogo.txt");

        try {
            Files.writeString(arquivo, newChave + " " + newNome + " " + newEstrelas);
            
            System.out.println("Filme adicionado com sucesso!");
            } catch (IOException e) {
            e.getStackTrace();
        }
    }



    public void removerFilme(int chaveFilme){
        for (int i = 0; i < catalogo.size(); i++)
        {
            Filme filme = catalogo.get(i);
            if (filme != null && filme.getChave() == chaveFilme)
            {
                catalogo.remove(i);
                System.out.println("Filme removido.");
                break;
            }
        }



        Path arquivo = Paths.get("C:\\\\Documentos\\\\C06\\\\C06-Projeto-final-main\\\\Trabalho final\\\\locacao\\\\catalogo.txt");
    
        try {
            List<String> filecontent = Files.readAllLines(arquivo);

            filecontent.forEach((linha) -> {
                String[] linhaQuebrada = linha.split(" ");
                if (linhaQuebrada[0] == chaveFilme) 
                {
                    try {
                        Files.writeString(arquivo, "Removido");
                        System.out.println("Filme removido com sucesso!");
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                    
                }
            });

            } catch (IOException e) {
            e.getStackTrace();
            }
        
    }

    public void trocarFilme(int chaveAntiga, String nomeNovo, int estrelasNovo){

        /* for (Filme filme : catalogo) {
            if(filme.getChave() == chaveAntiga){
                filme = new Filme(chaveAntiga,nomeNovo,estrelasNovo);
                return;
            }
        } */

        for (int i = 0; i < catalogo.size(); i++)
        {
            Filme filme = catalogo.get(i);
            if (filme != null && filme.getChave() == chaveAntiga)
            {
                catalogo.set(i, new Filme(chaveAntiga, nomeNovo, estrelasNovo));
                break; //sai só for para continuar até a atualização do arquivo
            }
        }

        Path arquivo = Paths.get("C:\\Documentos\\C06\\Trabalho final\\locacao\\catalogo.txt");
        try {
            Files.readString(arquivo);
            } catch (IOException e) {
                e.getStackTrace();
            }

        try {
            List<String> filecontent = Files.readAllLines(arquivo);

            filecontent.forEach((linha) -> {
                String[] linhaQuebrada = linha.split(" ");
                if (linhaQuebrada[0] == "" + chaveAntiga) 
                {
                    try {
                        Files.writeString(arquivo, chaveAntiga + " " + nomeNovo + " " + estrelasNovo);
                        System.out.println("Filme trocado com sucesso!");
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                    
                }
            });

            } catch (IOException e) {
            e.getStackTrace();
            }
    }

    public void adicionarFuncionario(Funcionario novoFuncionario){ //consertado
        /*for (Funcionario funcionario : funcionarios) {
            if(funcionario == null){
                funcionario = novoFuncionario;
            }
        }*/ //esse for nao adicionava nada

        if(novoFuncionario == null)
        {
            return;
        }

        for (Funcionario funcionario : funcionarios) //percorre a lista pra ver se tem ID repetido
        {
            if (funcionario.getIdFuncao() == novoFuncionario.getIdFuncao())
            {
                System.out.println("Já existe um funcionário com esse ID!");
                return; //return eh melhor que break pois sairá do metodo por completo, e não somente do for e irá
                        //para o proximo comando, senão não cumpriria o que pede, que é cancelar o cadastro sem adicionar
                        //se houver ID repetido
            }
        }

        funcionarios.add(novoFuncionario); //se nao tiver nada de errado, adiciona

    }

    public void removerFuncionario(int idFuncao){

        /*for (Funcionario funcionario : funcionarios) {
            if(funcionario != null && funcionario.getIdFuncao() == idFuncao){
                funcionario = null;
                return;
            }
        }*/ //esse for nao removia nada

        /* for(Funcionario funcionario : funcionarios)
        {
            if(funcionario == null)
            {
                return;
            }

            else if (funcionario.getIdFuncao() == idFuncao)
            {
                funcionarios.remove(funcionario);
                System.out.println("Funcionário do ID " + idFuncao + " removido!");
                return;
            }
        }*/

        for (int i = 0; i < funcionarios.size(); i++)
        {
            Funcionario f = funcionarios.get(i);
            if (f != null && f.getIdFuncao() == idFuncao)
            {
                funcionarios.remove(i);
                System.out.println("Funcionário do ID " + idFuncao + " removido!");
                return;
            }
        }
        
        throw new MissingFuncionarioException("Funcionário não encontrado para remoção! " +
                "Verifique se o ID do funcionário existe.");
    }

    public void mostrarFilmes() {
        boolean catalogoVazio = true;

        for (Filme filme : catalogo) {
            if (filme != null) {
                System.out.println(filme.exibirDetalhes());
                System.out.println("--------------------");
                catalogoVazio = false;
            }
        }
        if (catalogoVazio) {
            System.out.println("O servidor não tem nenhum filme no momento!");
        }
    }

    public float calcularPrecoAluguel(int chaveFilme){

        for (Filme filme : catalogo) {
            if(filme.getChave() == chaveFilme){
                return 20; // retorna o valor do filme2
        }
        }
        return 0; // retorn 0 caso n esteja no catalogo (return estava dentro do for, por isso não compilava)
    }
}
