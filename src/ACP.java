import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class ACP {
    public Estado estadoInicial;
    public Set<Character> alfabetoAutomato;
    public Set<Character> alfabetoPilha;
    public List<EstadoAtual> estadosAtuais;
    public char simboloInicio;

    public ACP (
        Estado estadoInicial, Set<Character> alfabetoAutomato, 
        Set<Character> alfabetoPilha, char simboloInicio
    ) {
        this.estadoInicial = estadoInicial;
        this.simboloInicio = simboloInicio;

        this.alfabetoAutomato = new HashSet<Character>();
        alfabetoAutomato.add('-');
        this.alfabetoAutomato.addAll(alfabetoAutomato);

        this.alfabetoPilha = new HashSet<Character>();
        this.alfabetoPilha.add('-');
        this.alfabetoPilha.add('=');
        this.alfabetoPilha.add(simboloInicio);
        this.alfabetoPilha.addAll(alfabetoPilha);

        estadosAtuais = new ArrayList<EstadoAtual>();
        Stack<Character> pilhaInicial = new Stack<Character>();
        pilhaInicial.push(simboloInicio);

        estadosAtuais.add(new EstadoAtual(estadoInicial, pilhaInicial));
    }

    public void atualizarACP(char entrada) {
        List<EstadoAtual> estadosAtuaisAtualizados = new ArrayList<EstadoAtual>();

        for(EstadoAtual estadoAtual : estadosAtuais){
            List<EstadoAtual> proximosEstados = estadoAtual.obterEstadosTransicao(entrada);

            System.out.println("Estado anterior: " + estadoAtual.estado.nome);
            System.out.print("Pilha anterior(Do início ao topo): ");
            estadoAtual.exibirPilha();
            System.out.println("\nEntrada: " + entrada);

            System.out.print("Estados resultantes: ");
            if(proximosEstados.size() == 0) System.out.print("Ramificação Morta");
            else {
                estadosAtuaisAtualizados.addAll(proximosEstados);
                EstadoAtual ultimoProximoEstado = proximosEstados.get(
                    proximosEstados.size()-1
                );
    
                for(EstadoAtual proximoEstado : proximosEstados) {
                    boolean ramificacaoMorta = proximoEstado == null || proximoEstado.estado == null;
                    System.out.print(
                        ramificacaoMorta ? "Ramificação Morta" : proximoEstado.estado.nome 
                    );

                    if(!ramificacaoMorta) {
                        System.out.print(" (Pilha: ");
                        proximoEstado.exibirPilha();
                        System.out.print(")");
                    }
                    if(proximoEstado != ultimoProximoEstado) System.out.print(", ");
                }
            }

            System.out.println('\n');
        }

        estadosAtuaisAtualizados.removeAll(Collections.singleton(null));
        estadosAtuais = estadosAtuaisAtualizados;
    }

    public void validarEntradaCompleta(String entradaCompleta) throws Exception {
        for(int ind=0 ; ind<entradaCompleta.length() ; ind++) {
            char letraEntrada = entradaCompleta.charAt(ind);

            if(!entradaInclusaNoAlfabeto(letraEntrada)) {
                throw new Exception("Entrada inválida");
            }
        }
    }

     boolean entradaInclusaNoAlfabeto(char entrada){
        for(char letraAlfabeto : alfabetoAutomato){
            if(letraAlfabeto == entrada) return true;
        }

        System.out.println("Entrada Invalida");
        return false;
    }

    public boolean entradaInclusaNoAlfabetoPilha(
        char simboloPilha
    ) {
        for(char letraAlfabeto : alfabetoPilha){
            if(letraAlfabeto == simboloPilha) return true;
        }

        System.out.println("Entrada Invalida");
        return false;
    }

    public boolean existeTransicaoVazia() {
        for(EstadoAtual estadoAtual : estadosAtuais) {
            List<FuncaoTransicao> funcoesTransicao = estadoAtual.estado.funcoesTransicao;

            for(FuncaoTransicao funcaoTransicao : funcoesTransicao) {
                if(funcaoTransicao.entrada == '-') return true;
            }
        }

        return false;
    }
}
