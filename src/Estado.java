import java.util.ArrayList;
import java.util.List;

public class Estado {
    public String nome;
    public boolean aceitacao;
    public List<FuncaoTransicao> funcoesTransicao;

    public Estado(String nome, boolean aceitacao) {
        this.nome = nome;
        this.aceitacao = aceitacao;
    }

    public List<Estado> obterEstadosTransicao(char entrada) {
        List<Estado> proximosEstados = new ArrayList<Estado>();

        for(FuncaoTransicao funcaoTransicao : funcoesTransicao) {
            if(funcaoTransicao.entrada == entrada) {
                proximosEstados.add(funcaoTransicao.proximoEstado);
            }
        }

        return proximosEstados;
    }
}
