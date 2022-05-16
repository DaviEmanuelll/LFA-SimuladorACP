import java.util.List;

public class Estado {
    public String nome;
    public boolean aceitacao;
    public List<FuncaoTransicao> funcoesTransicao;

    public Estado(String nome, boolean aceitacao) {
        this.nome = nome;
        this.aceitacao = aceitacao;
    }
}
