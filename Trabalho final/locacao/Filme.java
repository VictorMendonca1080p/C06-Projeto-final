package locacao;

import descricao.Descritivel;

public class Filme implements Descritivel{

    private int chave;
    private String nome;
    private int estrelas;

    public Filme(int chave,String nome,int estrelas){
        this.chave = chave;
        this.nome = nome;
        this.estrelas = estrelas;
    }

    @Override 
    public String exibirDetalhes() {
        return "Chave: " + chave + "\nNome: " + nome + "\nEstrelas: " + estrelas;
    }

    protected int getChave(){
        return this.chave;
    }
}
