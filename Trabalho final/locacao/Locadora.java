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
        
        Path arquivo = Paths.get("C:\\Documentos\\C06\\Trabalho final\\locacao\\catalogo.txt");
        try {
            Files.readString(arquivo);
            } catch (IOException e) {
                e.getStackTrace();
            }

        try {
            Files.writeString(arquivo, newChave + " " + newNome + " " + newEstrelas);
            System.out.println("filme adicionado com sucesso !");
            } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void removerFilme(int chaveFilme){
        for (Filme filme : catalogo) {
            if (filme != null && filme.getChave() == chaveFilme) {
                filme = null;
                return;
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
                if (linhaQuebrada[0] == "" + chaveFilme) 
                {
                    try {
                        Files.writeString(arquivo, "removido");
                        System.out.println("filme removido com sucesso !");
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

        for (Filme filme : catalogo) {
            if(filme.getChave() == chaveAntiga){
                filme = new Filme(chaveAntiga,nomeNovo,estrelasNovo);
                return;
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
                        System.out.println("filme trocado com sucesso !");
                    } catch (IOException e) {
                        e.getStackTrace();
                    }
                    
                }
            });

            } catch (IOException e) {
            e.getStackTrace();
            }
    }

    public void adicionarFuncionario(Funcionario novoFuncionario){
        for (Funcionario funcionario : funcionarios) {
            if(funcionario == null){
                funcionario = novoFuncionario;
            }
        }
    }

    public void removerFuncionario(int idFuncao){

        for (Funcionario funcionario : funcionarios) {
            if(funcionario != null && funcionario.getIdFuncao() == idFuncao){
                funcionario = null;
                return;
            }
        }
        
        throw new MissingFuncionarioException("Funcionario nao encontrado para remocao! " +
                "Verifique se o ID do funcionario existe.");
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
        return 0; // retorn 0 caso n esteja no catalogo
        }
    }
}
