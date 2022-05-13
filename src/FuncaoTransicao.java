public class FuncaoTransicao {
    public char entrada;
    public Estado proximoEstado;

    public FuncaoTransicao(
        char entrada, Estado proximoEstado
    ) {
        this.entrada = entrada;
        this.proximoEstado = proximoEstado;
    }
}
