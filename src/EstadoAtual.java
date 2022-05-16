import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class EstadoAtual {
    public Estado estado;
    public Stack<Character> pilhaMomentanea;

    public EstadoAtual(
        Estado estado, Stack<Character> pilhaMomentanea
    ) {
        this.estado = estado;
        this.pilhaMomentanea = pilhaMomentanea;
    }

    private void tratarAdicaoPilha(Stack<Character> pilha, char entrada) {
        if(entrada == '-') pilha.pop();
        else if(entrada != '=') pilha.push(entrada);
    }

    private List<EstadoAtual> obterEstadosTransicao(
        Estado estado, char entrada, Stack<Character> pilhaInicial
    ) {
        List<EstadoAtual> proximosEstados = new ArrayList<EstadoAtual>();
        
        for(FuncaoTransicao funcaoTransicao : estado.funcoesTransicao) {
            boolean consumiuEntrada = funcaoTransicao.entrada == entrada;

            boolean entradaEquivalente = funcaoTransicao.entrada == '-' || consumiuEntrada;
            boolean topoEquivalente =
                pilhaMomentanea.size() != 0 && funcaoTransicao.topoPilha == pilhaMomentanea.peek();

            if(entradaEquivalente && topoEquivalente) {
                Stack<Character> pilhaAtualizada = new Stack<Character>();
                if(pilhaInicial != null) pilhaAtualizada.addAll(pilhaInicial);
                else pilhaAtualizada.addAll(pilhaMomentanea);
                tratarAdicaoPilha(pilhaAtualizada, funcaoTransicao.proximoSimbolo);

                Estado proximoEstado = funcaoTransicao.proximoEstado;
                if(consumiuEntrada) {
                    proximosEstados.add(
                        new EstadoAtual(proximoEstado, pilhaAtualizada)
                    );
                } else {
                    proximosEstados.addAll(
                        obterEstadosTransicao(proximoEstado, entrada, pilhaAtualizada)
                    );
                }
            }
        }

        return proximosEstados;
    }

    public List<EstadoAtual> obterEstadosTransicao(char entrada) {
        if(entrada == '-' && estado.funcoesTransicao.size() == 0) {
            List<EstadoAtual> proximosEstados = new ArrayList<EstadoAtual>();
            proximosEstados.add(this);
            return proximosEstados;
        }
       
        return obterEstadosTransicao(estado, entrada, null);
    }

    public void exibirPilha() {
        if(pilhaMomentanea.size() == 0) return;
        Character ultimoElemento = pilhaMomentanea.get(
            pilhaMomentanea.size()-1
        );

        for(Character elementoPilha : pilhaMomentanea) {
            System.out.print(elementoPilha);
            if(elementoPilha != ultimoElemento) System.out.print(" ");
        }
    }
}
