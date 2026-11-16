package locacao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import excecao.MissingFuncionarioException;
import pessoa.Funcionario;
import descricao.Exibidor;
import excecao.MissingFilmeException;

public class Locadora
{

    private String nome;
    private List<Filme> catalogo;
    private List<Funcionario> funcionarios;

    public Locadora(String nome)
    {
        this.nome = nome;
        this.catalogo = new ArrayList<Filme>();
        this.funcionarios = new ArrayList<Funcionario>();
    }

    public String getNome()
    {
        return this.nome;
    }

    public void adicionarFilme(int newChave, String newNome, int newEstrelas)
    {
        Filme novoFilme = new Filme(newChave, newNome, newEstrelas);
        catalogo.add(novoFilme);

        //caminho relativo: conta a partir da pasta de onde o programa roda,
        //nao da pasta onde esta o arquivo .java
        Path arquivo = Paths.get("catalogo.txt");

        try {
            Files.writeString(arquivo, newChave + " " + newNome + " " + newEstrelas + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND );

            System.out.println("Filme adicionado com sucesso!");
            } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void removerFilme(int chaveFilme)
    {

        boolean removeuDaLista = false; //marcador: o throw la embaixo so pode acontecer se o laco nao achou nada

        for (int i = 0; i < catalogo.size(); i++)
        {
            Filme filme = catalogo.get(i);

            if (filme != null && filme.getChave() == chaveFilme)
            {
                catalogo.remove(i);
                System.out.println("Filme de chave " + chaveFilme + " removido.");
                removeuDaLista = true;
                break;
            }
        }

        if(removeuDaLista == false)
        {
            //lanca excecao em vez de devolver false ou nao fazer nada:
            //assim quem chamou e obrigado a lidar com o caso
            throw  new MissingFilmeException("Filme de chave " + chaveFilme + " não encontrado para remoção.");
        }

        Path arquivo = Paths.get("catalogo.txt");
    
        try
        {
            List<String> filecontent = Files.readAllLines(arquivo);
            List<String> listaAtualizada = new ArrayList<String>();

            //monta uma lista nova com todas as linhas menos a do filme removido.
            //eh assim que a linha sai do arquivo quando o Files.write grava de volta
            filecontent.forEach((linha) ->
            {
                String[] linhaQuebrada = linha.split(" ");

                if (linhaQuebrada[0].equals(String.valueOf(chaveFilme)) == false)
                {
                    listaAtualizada.add(linha);
                }
            });
            
            Files.write(arquivo, listaAtualizada);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }

    public void trocarFilme(int chaveAntiga, String nomeNovo, int estrelasNovo){

        boolean trocouNaLista = false;

        for (int i = 0; i < catalogo.size(); i++)
        {
            Filme filme = catalogo.get(i);

            if (filme != null && filme.getChave() == chaveAntiga)
            {
                catalogo.set(i, new Filme(chaveAntiga, nomeNovo, estrelasNovo));
                System.out.println("Filme de chave " + chaveAntiga + " trocado.");
                trocouNaLista = true;
                break;
            }
        }

        if (trocouNaLista == false)
        {
            //lanca excecao em vez de devolver false ou nao fazer nada:
            //assim quem chamou e obrigado a lidar com o caso
            throw new MissingFilmeException("Filme de chave " + chaveAntiga + " nao encontrado para troca.");
        }

        Path arquivo = Paths.get("catalogo.txt");
    
        try
        {
            List<String> filecontent = Files.readAllLines(arquivo);
            List<String> listaAtualizada = new ArrayList<String>();

            filecontent.forEach((linha) ->
            {
                String[] linhaQuebrada = linha.split(" ");

                if (linhaQuebrada[0].equals(String.valueOf(chaveAntiga)))
                {
                    listaAtualizada.add(chaveAntiga + " " + nomeNovo + " " + estrelasNovo);
                }
                else
                {
                    listaAtualizada.add(linha);
                }
                
            });
            
            Files.write(arquivo, listaAtualizada);

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void adicionarFuncionario(Funcionario novoFuncionario)
    {
        if(novoFuncionario != null)
        {
            for (Funcionario funcionario : funcionarios)
            {
                if (funcionario.getIdFuncao() == novoFuncionario.getIdFuncao())
                {
                    System.out.println("Já existe um funcionário com esse ID!");
                    return;
                }
            }
            funcionarios.add(novoFuncionario);
        }
    }

    public void removerFuncionario(int idFuncao)
    {

        
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

        //lanca excecao em vez de devolver false ou nao fazer nada:
        //assim quem chamou e obrigado a lidar com o caso
        throw new MissingFuncionarioException("Funcionário não encontrado para remoção! " + "Verifique se o ID do funcionário existe.");
    }

    //le o catalogo.txt na inicializacao e enche a lista.
    public void carregarCatalogo()
    {
        Path arquivo = Paths.get("catalogo.txt");

        if(Files.exists(arquivo) == false)
        {
            System.out.println("Nenhum catálogo salvo foi encontrado. Começando vazio.");
            return;
        }

        try
        {
            List<String> linhasDoArquivo = Files.readAllLines(arquivo);

            for (String linha : linhasDoArquivo)
            {
                String[] pedacos = linha.trim().split(" ");

                //chave, nome e estrelas: linha com menos de tres pedacos nao eh um filme valido
                if (pedacos.length < 3)
                {
                    continue;
                }

                try
                {
                    int chave = Integer.parseInt(pedacos[0]);
                    int estrelas = Integer.parseInt(pedacos[pedacos.length - 1]);

                    String nome = "";
                    for (int i = 1; i < pedacos.length - 1; i++)
                    {
                        nome = nome + pedacos[i];
                        if (i < pedacos.length - 2)
                        {
                            nome = nome + " ";
                        }
                    }

                    if (buscarFilme(chave) == null) //nao duplica filme que ja esta no catalogo
                    {
                        catalogo.add(new Filme(chave, nome, estrelas));
                    }
                }
                catch (NumberFormatException linhaInvalida)
                {
                    //tratamento de excecao: linha corrompida no arquivo nao derruba o carregamento.
                    //o programa avisa, descarta so aquela linha e continua lendo as outras
                    System.out.println("Linha ignorada no catalogo.txt: " + linha);
                }
            }

            System.out.println("Catalogo carregado: " + catalogo.size() + " filme(s).");
        }
        catch (IOException erroDeArquivo)
        {
            System.out.println("Nao foi possivel ler o catalogo: " + erroDeArquivo.getMessage());
        }
    }

    //devolve null quando nao acha, para quem chama decidir o que fazer
    public Filme buscarFilme(int chaveFilme)
    {
        for (Filme filme : catalogo)
        {
            if (filme != null && filme.getChave() == chaveFilme)
            {
                return filme;
            }
        }
        return null;
    }

    //sem isso nao da pra saber quais ids existem antes de remover um funcionario
    public void mostrarFuncionarios()
    {

        if (funcionarios.isEmpty() == true)
        {
            System.out.println("Nenhum funcionário cadastrado no momento.");
            return;
        }

        for (Funcionario funcionario : funcionarios)
        {
            //chama pelo Exibidor: o Funcionario entra como Descritivel, pelo tipo da interface
            Exibidor.mostrar(funcionario);
            System.out.println("--------------------");
        }
    }

    public void mostrarFilmes()
    {
        boolean catalogoVazio = true;

        for (Filme filme : catalogo)
        {
            if (filme != null)
            {
                //chama pelo Exibidor: o Filme entra como Descritivel, pelo tipo da interface
                Exibidor.mostrar(filme);
                System.out.println("--------------------");
                catalogoVazio = false;
            }
        }
        if (catalogoVazio)
        {
            System.out.println("A locadora não tem nenhum filme disponível no momento. Desculpe o transtorno.");
        }
    }

    public float calcularPrecoAluguel(int chaveFilme)
    {

        for (Filme filme : catalogo)
        {
            if(filme.getChave() == chaveFilme)
            {
                return 20;
            }
        }
        return -1;
    }
}
