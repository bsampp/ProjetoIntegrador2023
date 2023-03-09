package Model;

public class Servico {
    private int codServico;
    private String nome;
    private Float valor;

    public Servico(int codServico, String nome, Float valor){
        this.codServico = codServico;
        this.nome = nome;
        this.valor = valor;
    }

    public int getCodServico() {
        return codServico;
    }

    public String getNome() {
        return nome;
    }

    public Float getValor() {
        return valor;
    }

    public void setCodServico(int codServico) {
        this.codServico = codServico;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

}
