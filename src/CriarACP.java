import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CriarACP {    
    public static Scanner scanner = new Scanner(System.in);

    public static List<Estado> criarEstados(){      
        List<Estado> novosEstados = new ArrayList<Estado>();

        System.out.println("--------------------------------");
        System.out.println("Quantos estados terá o ACP: ");
        int numEstados = scanner.nextInt();
        System.out.println();
        
        for(int i=1 ; i<=numEstados ; i++){
            String nome;
            boolean aceitacao = false;

            System.out.println("Qual o nome do estado "+((i==1)?"Inicial? ":i+"º?"));
            nome = scanner.next();

            System.out.println("Ele é de aceitação?(1 para sim)");
            int resposta = scanner.nextInt();
            if(resposta == 1) aceitacao=true;

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
        System.out.println("Quantos símbolos terá o alfabeto do Automato?");
        int numSimbolos = scanner.nextInt();
        System.out.println("====");
        for(int i=1;i<=numSimbolos;i++){
            System.out.print(i+": ");
            Character simbolos = scanner.next().charAt(0);
            alfabetoAutomato.add(simbolos);
        }
        System.out.println("====");

        System.out.println();
        System.out.println("Quantos símbolos terá o alfabeto da Pilha?");
        numSimbolos = scanner.nextInt();
        System.out.println("====");
        for(int i=1;i<=numSimbolos;i++){
            System.out.print(i+": ");
            Character simbolos = scanner.next().charAt(0);
            alfabetoPilha.add(simbolos);
        }
        System.out.println("====");

        System.out.println();
        System.out.println("Qual será o símbolos de inicio da Pilha?");
        simboloInicio = scanner.next().charAt(0);

        ACP acp = new ACP(
            estadoInicial, alfabetoAutomato, 
            alfabetoPilha, simboloInicio
        );

        return acp;

    }


    public static void definirFuncoesTransicao(List<Estado> estados, ACP acp){
        System.out.println("--------------------------------");
        for(Estado trasicoes: estados){

            System.out.println();
            System.out.println("Quantas funções de Transição o estado "+trasicoes.nome+" terá?");
            int numFuncoes = scanner.nextInt();
            List<FuncaoTransicao> funcoesTransicao = new ArrayList<FuncaoTransicao>(); 

            for(int i=0; i<numFuncoes ; i++){
                char entrada = ' ';
                char topoPilha = ' ';
                Estado proximoEstado;
                char proximoSimbolo = ' ';
                System.out.println();

                boolean entradaValida = false;
                while(entradaValida == false){
                    System.out.print("Entrada: ");
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
                    System.out.print("Topo da Pilha('-'para tirar e '='para permanecer): ");
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

            if(estadoEncontrado == null){
                System.out.println("Estado "+nomeBusca+" não encontrado!");
            }
        }
        return estadoEncontrado;
    }
}    
