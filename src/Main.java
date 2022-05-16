import java.util.List;
import java.util.Scanner;

public class Main{
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String args[]) {
        ACP acpPadrao = null;
        
        System.out.print("Deseja usar o APC de exemplo ou criar um ACP (0 - exemplo | 1 - criar)? ");
        boolean criar = scanner.next().charAt(0) != '0';
        
        if(criar){
            List<Estado> estados = CriarACP.criarEstados();
            acpPadrao = CriarACP.criarACP(estados);
            CriarACP.definirFuncoesTransicao(estados, acpPadrao);
        } else {
            acpPadrao = CriarACP.chamarACP();
            System.out.println("O ACP usado será: " + acpPadrao.nome);
        }

        boolean avaliarOutraEntrada = true;
        while(avaliarOutraEntrada) {
            System.out.print("Informe a entrada: ");
            String entrada = scanner.next();

            ACP acp = new ACP( 
                acpPadrao.nome, acpPadrao.estadoInicial, acpPadrao.alfabetoAutomato, 
                acpPadrao.alfabetoPilha, acpPadrao.simboloInicio
            );

            try {
                acp.validarEntradaCompleta(entrada);

                for(int ind=0 ; ind<entrada.length() || acp.existeTransicaoVazia() ; ind++) {
                    char letter = ind >= entrada.length() ? '-' : entrada.charAt(ind);
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

                System.out.println("\n-> "+mensagemFinal);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
                
            System.out.print(
                "\nDeseja avaliar outra entrada para o ACP " +
                acp.nome + "(0 - não | 1 - sim)? "
            );
            avaliarOutraEntrada = scanner.next().charAt(0) != '0';
        }

        System.out.println("Programa finalizado!");
    }

}

