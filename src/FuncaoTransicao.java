public class FuncaoTransicao {
    public String entrada;
    public Estado estadoFinal;
    public boolean aceitacao;

    public FuncaoTransicao(
        String entrada, Estado estadoFinal, boolean aceitacao
    ) {
        this.entrada = entrada;
        this.estadoFinal = estadoFinal;
        this.aceitacao= aceitacao;
    }
}
