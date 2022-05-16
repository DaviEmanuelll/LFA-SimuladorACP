import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CriarACP {    
    public static Scanner scanner = new Scanner(System.in);

    public static ACP chamarACP(){
        List<Estado> estado = new ArrayList<Estado>();
        Estado estado0 = new Estado("q0", false);
        Estado estado1 = new Estado("q1", false);
        Estado estado2 = new Estado("q2", true);
        
        Set<Character> alfabetoAutomato =  new HashSet<Character>();
        alfabetoAutomato.add('a');
        alfabetoAutomato.add('b');
        Set<Character> alfabetoPilha = new HashSet<Character>();
        alfabetoPilha.add('a');
        ACP acp = new ACP("a(n)b(n+1)", estado0, alfabetoAutomato, alfabetoPilha, 'Z');

        List<FuncaoTransicao> funcoesTransicao0 = new ArrayList<FuncaoTransicao>(); 
        funcoesTransicao0.add(new FuncaoTransicao('a', 'Z', estado0, 'a'));
        funcoesTransicao0.add(new FuncaoTransicao('a', 'a', estado0, 'a'));
        funcoesTransicao0.add(new FuncaoTransicao('b', 'a', estado1, '='));
        estado0.funcoesTransicao = funcoesTransicao0;
        List<FuncaoTransicao> funcoesTransicao1 = new ArrayList<FuncaoTransicao>(); 
        funcoesTransicao1.add(new FuncaoTransicao('b', 'a', estado1, '-'));
        funcoesTransicao1.add(new FuncaoTransicao('-', 'Z', estado2, '-'));
        estado1.funcoesTransicao = funcoesTransicao1;

        estado.add(estado0);
        estado.add(estado1);
        estado.add(estado2);

        return acp;
    }
    public static List<Estado> criarEstados(){      
        List<Estado> novosEstados = new ArrayList<Estado>();

        System.out.println("--------------------------------");
        System.out.print("Quantos estados terá o ACP: ");
        int numEstados = scanner.nextInt();
        System.out.println();
        
        for(int i=1 ; i<=numEstados ; i++){
            String nome;
            
            System.out.print("Qual o nome do estado "
                + ((i==1) ? "Inicial" : (i + "º")) + "? "
            );
            nome = scanner.next();

            System.out.print("Ele é de aceitação (0 - não | 1 - sim)? ");
            boolean aceitacao = scanner.nextInt() == 0 ? false : true;

            novosEstados.add(new Estado(nome,aceitacao));
            System.out.println();
        }

        return novosEstados;
    }

    public static ACP criarACP(List<Estado> estados){
        Estado estadoInicial = estados.get(0);
        Set<Character> alfabetoAutomato = new HashSet<Character>();;
        Set<Character> alfabetoPilha = new HashSet<Character>();;
        char simboloInicio;

        System.out.println("--------------------------------");
        System.out.print("Qual nome do ACP? ");
        String nome = scanner.next();

        System.out.print("Quantos símbolos terá o alfabeto do Automato? ");
        int numSimbolos = scanner.nextInt();

        System.out.println("====");
        for(int i=1;i<=numSimbolos;i++){
            System.out.print(i+": ");
            Character simbolos = scanner.next().charAt(0);
            alfabetoAutomato.add(simbolos);
        }
        System.out.println("====");

        System.out.println();
        System.out.print("Quantos símbolos terá o alfabeto da Pilha? ");
        numSimbolos = scanner.nextInt();

        System.out.println("====");
        for(int i=1;i<=numSimbolos;i++){
            System.out.print(i+": ");
            Character simbolos = scanner.next().charAt(0);
            alfabetoPilha.add(simbolos);
        }
        System.out.println("====");

        System.out.println();
        System.out.print("Qual será o símbolos de inicio da Pilha? ");
        simboloInicio = scanner.next().charAt(0);

        ACP acp = new ACP( nome,
            estadoInicial, alfabetoAutomato, 
            alfabetoPilha, simboloInicio
        );

        return acp;
    }


    public static void definirFuncoesTransicao(List<Estado> estados, ACP acp){
        System.out.println("--------------------------------");
        for(Estado trasicoes: estados){

            System.out.println();
            System.out.print("Quantas funções de Transição o estado "+trasicoes.nome+" terá? ");
            int numFuncoes = scanner.nextInt();
            List<FuncaoTransicao> funcoesTransicao = new ArrayList<FuncaoTransicao>(); 

            for(int i=0; i<numFuncoes ; i++){
                char entrada = ' ';
                char topoPilha = ' ';
                Estado proximoEstado;
                char proximoSimbolo = ' ';
                System.out.println("-"+i+"º Função de Transição");

                boolean entradaValida = false;
                while(entradaValida == false){
                    System.out.print("Entrada(- para transição vazia): ");
                    entrada = scanner.next().charAt(0);
                    entradaValida = acp.entradaInclusaNoAlfabeto(entrada);
                }

                entradaValida = false;
                while(entradaValida == false){
                    System.out.print("Topo da Pilha: ");
                    topoPilha = scanner.next().charAt(0);
                    entradaValida = acp.entradaInclusaNoAlfabetoPilha(topoPilha);
                }

                proximoEstado = escolherEstados(estados);
                
                entradaValida = false;
                while(entradaValida == false){
                    System.out.print("Adicionar à pilha(- para tirar e = para permanecer): ");
                    proximoSimbolo = scanner.next().charAt(0);
                    entradaValida = acp.entradaInclusaNoAlfabetoPilha(proximoSimbolo);
                }

                funcoesTransicao.add(new FuncaoTransicao(entrada, topoPilha, proximoEstado, proximoSimbolo));
                System.out.println(" ");
            }

            trasicoes.funcoesTransicao = funcoesTransicao;
        }
    }   

    public static Estado escolherEstados(List<Estado> estados){
        Estado estadoEncontrado = null;
        while(estadoEncontrado==null){
            System.out.print("Proximo Estado");
            for(Estado estado: estados){
                System.out.print(estado);
            }
            System.out.print(": ");
            String nomeBusca = scanner.next();

            for(Estado estado: estados){
                
                if(estado.nome.equals(nomeBusca)){
                    estadoEncontrado = estado;
                    break;
                }
            }

            if(estadoEncontrado == null) {
                System.out.println("Estado " + nomeBusca + " não encontrado!");
            }
        }
        return estadoEncontrado;
    }
}    
