package locacao;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import excesao.MissingFuncionarioException;
import pessoa.Funcionario; //laís
import descricao.Exibidor;
import excesao.MissingFilmeException; //laís

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
        //laís: nao deixa cadastrar dois filmes com a mesma chave
        if(buscarFilme(newChave) != null)
        {
            System.out.println("Já existe um filme com a chave " + newChave + " !");
            return;
        }

        Filme novoFilme = new Filme(newChave, newNome, newEstrelas);
        catalogo.add(novoFilme);
        

        //adiciona no arquivo
        //Path arquivo = Paths.get("locacao\\catalogo.txt");

        //laís: so o nome do arquivo, igual a aula 14 slide 10.
        //o caminho com pasta nao funcionava porque é relativo ao diretorio de execucao, nao ao .java
        Path arquivo = Paths.get("catalogo.txt");

        try {
            Files.writeString(arquivo, newChave + " " + newNome + " " + newEstrelas + System.lineSeparator(), StandardOpenOption.CREATE, StandardOpenOption.APPEND );

            System.out.println("Filme adicionado com sucesso!");
            } catch (IOException e) {
            e.printStackTrace(); //laís: getStackTrace devolve um array e nao imprime nada. era por isso que a falha do arquivo sumia
        }
    }



    public void removerFilme(int chaveFilme){

        boolean removeuDaLista = false; //laís: marcador
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

        //laís: avisa quando a chave nao existe, em vez de voltar pro menu em silencio.
        //usa a MissingFilmeException, que estava importada e sem uso nenhum
        if(removeuDaLista == false)
        {
            throw  new MissingFilmeException("Filme de chave " + chaveFilme + " não encontrado para remoção.");
        }


        //remove do arquivo
        //Path arquivo = Paths.get("locacao\\catalogo.txt");
        Path arquivo = Paths.get("catalogo.txt"); //laís
    
        try {
            List<String> filecontent = Files.readAllLines(arquivo);
            List<String> listaAtualizada = new ArrayList<String>();

            filecontent.forEach((linha) -> {
                String[] linhaQuebrada = linha.split(" ");
                //laís: monta uma lista nova com todas as linhas menos a do filme removido.
                //e assim que a linha sai do arquivo quando o Files.write grava a lista de volta
                if (linhaQuebrada[0].equals(String.valueOf(chaveFilme)) == false)
                {
                    listaAtualizada.add(linha);
                }
                /* if (linhaQuebrada[0].equals(String.valueOf(chaveFilme)))
                {
                    listaAtualizada.add("Removido");
                    System.out.println("Filme removido com sucesso!");

                }
                else
                {
                    listaAtualizada.add(linha);
                } */
                
            });
            
            Files.write(arquivo, listaAtualizada);

            } catch (IOException e) {
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
                break; //sai só for para continuar até a atualização do arquivo
            }
        }

        //laís: mesma ideia do removerFilme
        if (trocouNaLista == false)
        {
            throw new MissingFilmeException("Filme de chave " + chaveAntiga + " nao encontrado para troca.");
        }

        // chaveAntiga + " " + nomeNovo + " " + estrelasNovo
        //troca no arquivo
        //Path arquivo = Paths.get("locacao\\catalogo.txt");
        Path arquivo = Paths.get("catalogo.txt"); //laís
    
        try {
            List<String> filecontent = Files.readAllLines(arquivo);
            List<String> listaAtualizada = new ArrayList<String>();

            filecontent.forEach((linha) -> {
                String[] linhaQuebrada = linha.split(" ");
                if (linhaQuebrada[0].equals(String.valueOf(chaveAntiga)))
                {
                    //laís: sem println aqui, quem avisa e o laco da lista, senao duplica a mensagem
                    listaAtualizada.add(chaveAntiga + " " + nomeNovo + " " + estrelasNovo);
                }
                else
                {
                    listaAtualizada.add(linha);
                }
                
            });
            
            Files.write(arquivo, listaAtualizada);

            } catch (IOException e) {
            e.printStackTrace();
            }
    }

    public void adicionarFuncionario(Funcionario novoFuncionario){ //consertado
        if(novoFuncionario != null)
        {
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
    }

    public void removerFuncionario(int idFuncao){

        
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

    //laís: le o catalogo.txt e enche a lista ao iniciar o programa.
    //sem isso o arquivo so era escrito e nunca lido de volta
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

                //laís: linha valida tem pelo menos chave, nome e estrelas
                if (pedacos.length < 3)
                {
                    continue;
                }

                try
                {
                    int chave = Integer.parseInt(pedacos[0]);
                    int estrelas = Integer.parseInt(pedacos[pedacos.length - 1]);

                    //laís: o nome fica no meio e pode ter varias palavras
                    String nome = "";
                    for (int i = 1; i < pedacos.length - 1; i++)
                    {
                        nome = nome + pedacos[i];
                        if (i < pedacos.length - 2)
                        {
                            nome = nome + " ";
                        }
                    }

                    //laís: nao repete filme que ja esta no catalogo
                    if (buscarFilme(chave) == null)
                    {
                        catalogo.add(new Filme(chave, nome, estrelas));
                    }
                }
                catch (NumberFormatException linhaInvalida)
                {
                    //laís: linha sem numero, tipo a palavra "Removido". pula
                    continue;
                }
            }

            System.out.println("Catalogo carregado: " + catalogo.size() + " filme(s).");
        }
        catch (IOException erroDeArquivo)
        {
            System.out.println("Nao foi possivel ler o catalogo: " + erroDeArquivo.getMessage());
        }
    }

    //laís: devolve o filme da chave, ou null se nao existir
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

    //laís: lista os funcionarios cadastrados. sem isso nao da pra saber qual ID existe antes de remover
    public void mostrarFuncionarios()
    {

        if (funcionarios.isEmpty() == true)
        {
            System.out.println("Nenhum funcionário cadastrado no momento.");
            return;
        }

        for (Funcionario funcionario : funcionarios) {
            Exibidor.mostrar(funcionario); //laís
            System.out.println("--------------------");
        }
    }

    public void mostrarFilmes() {
        boolean catalogoVazio = true;

        for (Filme filme : catalogo) {
            if (filme != null) {
                Exibidor.mostrar(filme); //laís
                System.out.println("--------------------");
                catalogoVazio = false;
            }
        }
        if (catalogoVazio) {
            System.out.println("A locadora não tem nenhum filme disponível no momento. Desculpe o transtorno.");
        }
    }

    public float calcularPrecoAluguel(int chaveFilme){

        for (Filme filme : catalogo) {
            if(filme.getChave() == chaveFilme){
                return 20; // retorna o valor do filme2
            }
        }
        return -1; // retorn -1 caso n esteja no catalogo (return estava dentro do for, por isso não compilava)
    }
}
