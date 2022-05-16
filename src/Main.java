import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {

        List<Estado> estados = CriarACP.criarEstados();
        ACP acp = CriarACP.criarACP(estados);
        CriarACP.definirFuncoesTransicao(estados, acp);

        System.out.println("Informe a entrada: ");
        String entrada = scanner.nextLine();

        try {
            acp.validarEntradaCompleta(entrada);

            for(int ind=0 ; ind<entrada.length() ; ind++) {
                char letter = entrada.charAt(ind);
                acp.atualizarACP(letter);
            }

            boolean automatoAceito = false;
            String mensagemFinal = "A entrada";
            for(EstadoAtual estadoAtual : acp.estadosAtuais) {
                boolean ramificacaoMorta = estadoAtual == null || estadoAtual.estado == null;
                if(!ramificacaoMorta && estadoAtual.estado.aceitacao) {
                    automatoAceito = true;
                }
            }

            if(!automatoAceito) mensagemFinal += " não";
            mensagemFinal += " é aceita pelo autômato.";

            System.out.println(mensagemFinal);
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    private static ACP obterDados() {
        return null;
    }
}

/*
char simboloInicio = 'z';

Estado estadoInicial = new Estado("0", false);
Estado estado1 = new Estado("1", false);
Estado estado2 = new Estado("2", true);

List<FuncaoTransicao> funcoesTransicaoInicial = new ArrayList<FuncaoTransicao>(); 
funcoesTransicaoInicial.add(new FuncaoTransicao('a', simboloInicio, estado1, 'X'));
funcoesTransicaoInicial.add(new FuncaoTransicao('b', simboloInicio, estado1, 'Y'));

estadoInicial.funcoesTransicao = funcoesTransicaoInicial;

List<FuncaoTransicao> funcoesTransicao1 = new ArrayList<FuncaoTransicao>(); 
funcoesTransicao1.add(new FuncaoTransicao('a', 'z', estado1, 'Y'));
funcoesTransicao1.add(new FuncaoTransicao('a', 'X', estado1, '-'));
funcoesTransicao1.add(new FuncaoTransicao('b', 'X', estado2, '-'));
funcoesTransicao1.add(new FuncaoTransicao('b', 'Y', estado2, '='));


estado1.funcoesTransicao = funcoesTransicao1;

List<FuncaoTransicao> funcoesTransicao2 = new ArrayList<FuncaoTransicao>(); 
funcoesTransicao2.add(new FuncaoTransicao('a', 'Y', estado1, 'X'));
funcoesTransicao2.add(new FuncaoTransicao('a', 'X', estado2, 'Y'));
funcoesTransicao2.add(new FuncaoTransicao('b', 'Y', null, '='));

estado2.funcoesTransicao = funcoesTransicao2;

List<Estado> estadosAutomato = new ArrayList<Estado>();
estadosAutomato.add(estadoInicial);
estadosAutomato.add(estado1);
estadosAutomato.add(estado2);

Set<Character> alfabetoAutomato = new HashSet<Character>();
alfabetoAutomato.add('a');
alfabetoAutomato.add('b');

Set<Character> alfabetoPilha = new HashSet<Character>();
alfabetoPilha.add('X');
alfabetoPilha.add('Y');

ACP acp = new ACP(
    estadoInicial, alfabetoAutomato, 
    alfabetoPilha, simboloInicio
);

String entrada = "aaabb";

try {
    acp.validarEntradaCompleta(entrada);

    for(int ind=0 ; ind<entrada.length() ; ind++) {
        char letter = entrada.charAt(ind);
        acp.atualizarACP(letter);
    }

    boolean automatoAceito = false;
    String mensagemFinal = "A entrada";
    for(EstadoAtual estadoAtual : acp.estadosAtuais) {
        boolean ramificacaoMorta = estadoAtual == null || estadoAtual.estado == null;
        if(!ramificacaoMorta && estadoAtual.estado.aceitacao) {
            automatoAceito = true;
        }
    }

    if(!automatoAceito) mensagemFinal += " não";
    mensagemFinal += " é aceita pelo autômato.";

    System.out.println(mensagemFinal);
} catch (Exception exception) {
    exception.printStackTrace();
}
*/