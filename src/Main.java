import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        Estado estadoInicial = new Estado("0", false);
        Estado estado1 = new Estado("1", false);
        Estado estado2 = new Estado("2", true);

        List<FuncaoTransicao> funcoesTransicaoInicial = new ArrayList<FuncaoTransicao>(); 
        funcoesTransicaoInicial.add(new FuncaoTransicao('a', estado1));
        funcoesTransicaoInicial.add(new FuncaoTransicao('b', estado1));

        estadoInicial.funcoesTransicao = funcoesTransicaoInicial;

        List<FuncaoTransicao> funcoesTransicao1 = new ArrayList<FuncaoTransicao>(); 
        funcoesTransicao1.add(new FuncaoTransicao('a', estado1));
        funcoesTransicao1.add(new FuncaoTransicao('b', estado2));

        estado1.funcoesTransicao = funcoesTransicao1;

        List<FuncaoTransicao> funcoesTransicao2 = new ArrayList<FuncaoTransicao>(); 
        funcoesTransicao2.add(new FuncaoTransicao('a', estado2));
        funcoesTransicao2.add(new FuncaoTransicao('b', estado1));

        estado2.funcoesTransicao = funcoesTransicaoInicial;

        List<Estado> estadosAutomato = new ArrayList<Estado>();
        estadosAutomato.add(estadoInicial);
        estadosAutomato.add(estado1);
        estadosAutomato.add(estado2);

        List<Character> alfabeto = new ArrayList<Character>();
        alfabeto.add('a');
        alfabeto.add('b');

        ACP acp = new ACP(
            estadoInicial, estadosAutomato, alfabeto, 
            alfabeto, 'z'
        );
    }

    private static ACP obterData() {
        return null;
    }
}