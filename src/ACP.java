import java.util.List;
import java.util.Stack;

public class ACP {
    public Stack<Character> pilha;

    public Estado estadoInicial;
    public List<Estado> estados;
    public List<Character> alfabetoAutomato;
    public List<Character> alfabetoPilha;
    public List<Estado> estadosAtuais;
    public char simboloInicio;

    public ACP (
        Estado estadoInicial, List<Estado> estados,
        List<Character> alfabetoAutomato, List<Character> alfabetoPilha,
        char simboloInicio
    ) {
        this.estadoInicial = estadoInicial;
        this.estados = estados;
        this.alfabetoAutomato = alfabetoAutomato;
        this.alfabetoPilha = alfabetoPilha;
        this.simboloInicio = simboloInicio;

        pilha = new Stack<Character>();
        pilha.push(simboloInicio);
    }

    public void avaliarEntrada(char entrada){
        for(Estado estadoAtual : estadosAtuais){
            List<Estado> proximosEstados = estadoAtual.obterEstadosTransicao(entrada);

           if(proximosEstados.size() > 0){
               estadosAtuais.remove(estadoAtual);
               estadosAtuais.addAll(proximosEstados);
           } else {
               System.out.println("entrada invalida");
           }
        }
    }

    public char obterTopoPilha() {
        return pilha.peek();
    }

    public boolean eValido(char entrada){
        for(char letra : alfabetoAutomato){
            if(letra == entrada){
                return true;
            }
        }
        return false;
    }
}
