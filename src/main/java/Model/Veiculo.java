package Model;

public class Veiculo {
    private String placa;
    private String modelo;
    private String cor;
    private int anoFabricacao;
    private int codCliente;

    public Veiculo(String placa, String modelo, String cor, int anoFabricacao, int codCliente) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.anoFabricacao = anoFabricacao;
        this.codCliente = codCliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(int anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }
}