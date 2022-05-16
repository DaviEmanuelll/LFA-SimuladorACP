public class FuncaoTransicao {
    public char entrada;
    public char topoPilha;
    public Estado proximoEstado;
    public char proximoSimbolo;

    public FuncaoTransicao(
        char entrada, char topoPilha,
        Estado proximoEstado, char proximoSimbolo
    ) {
        this.entrada = entrada;
        this.topoPilha = topoPilha;
        this.proximoEstado = proximoEstado;
        this.proximoSimbolo = proximoSimbolo;
    }
}
