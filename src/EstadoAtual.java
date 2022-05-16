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

    public List<EstadoAtual> obterEstadosTransicao(char entrada) {
        List<EstadoAtual> proximosEstados = new ArrayList<EstadoAtual>();

        if(entrada == '-' && estado.funcoesTransicao.size() == 0) proximosEstados.add(this);
        else {
            for(FuncaoTransicao funcaoTransicao : estado.funcoesTransicao) {
                boolean entradaEquivalente = 
                    funcaoTransicao.entrada == '-' || funcaoTransicao.entrada == entrada;
                boolean topoEquivalente =
                    pilhaMomentanea.size() != 0 && funcaoTransicao.topoPilha == pilhaMomentanea.peek();
    
                if(entradaEquivalente && topoEquivalente) {
                    Stack<Character> pilhaAtualizada = new Stack<Character>();
                    pilhaAtualizada.addAll(pilhaMomentanea);
                    tratarAdicaoPilha(pilhaAtualizada, funcaoTransicao.proximoSimbolo);
    
                    proximosEstados.add(
                        new EstadoAtual(funcaoTransicao.proximoEstado, pilhaAtualizada)
                    );
                }
            }
        }

        return proximosEstados;
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
