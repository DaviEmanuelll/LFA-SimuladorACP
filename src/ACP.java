import java.util.Stack;

public class ACP {
    public Stack<Character> pilha;

    public Estado estadoInicial;
    public Estado estados[];
    public char alfabeto[];
    public char alfabetoAutomato[];
    public Estado estadosAceitacao[];
    public Estado estadosAtuais[];
    public char simboloInicio;

    public ACP(
        Estado estadoInicial, Estado estados[],
        char alfabeto[], char alfabetoAutomato[],
        Estado estadosAceitacao[], char simboloInicio,
        Estado estadosAtuais[]
    ) {
        this.estadoInicial = estadoInicial;
        this.estados = estados;
        this.alfabeto = alfabeto;
        this.alfabetoAutomato = alfabetoAutomato;
        this.estadosAceitacao = estadosAceitacao;
        this.simboloInicio = simboloInicio;
        this.estadosAtuais = estadosAtuais;

        pilha = new Stack<Character>();
        pilha.push(simboloInicio);
    }

    public void avaliarEntrada(char entrada){
        for(int i = 0; i<estadosAtuais.length; i++){
            proximosEstados= funcao(estadosAtuais[i],entrada);

           if(proximosEstados.length > 0){
               estadosAtuais.remove(estadosAtuais[i]);
               estadorAtuais.add(estadosAtuais[i].proximosEstados);
           }else{
               System.out.println("entrada invalida");
           }
        }
    }

    public char obterTopoPilha() {
        return pilha.peek();
    }

    public boolean eValido(char entrada){
        for(char letra: alfabeto){
            if(letra == entrada){
                return true;
            }
        }
        return false;
    }
}
