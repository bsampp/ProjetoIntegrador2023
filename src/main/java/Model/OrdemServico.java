package Model;

import java.time.LocalDate;

public class OrdemServico {
    private int codOrdemServico;
    private float orcamentoFinal;
    private LocalDate previsaoEntrega;
    private LocalDate dataSolicitacao;
    private int codCliente;
    private String placa;
    private String status;

    public OrdemServico(int codOrdemServico, float orcamentoFinal, LocalDate previsaoEntrega,
                        LocalDate dataSolicitacao, int codCliente, String placa, String status) {
        this.codOrdemServico = codOrdemServico;
        this.orcamentoFinal = orcamentoFinal;
        this.previsaoEntrega = previsaoEntrega;
        this.dataSolicitacao = dataSolicitacao;
        this.codCliente = codCliente;
        this.placa = placa;
        this.status = status;
    }

    public int getCodOrdemServico() {
        return codOrdemServico;
    }

    public void setCodOrdemServico(int codOrdemServico) {
        this.codOrdemServico = codOrdemServico;
    }

    public float getOrcamentoFinal() {
        return orcamentoFinal;
    }

    public void setOrcamentoFinal(float orcamentoFinal) {
        this.orcamentoFinal = orcamentoFinal;
    }

    public LocalDate getPrevisaoEntrega() {
        return previsaoEntrega;
    }

    public void setPrevisaoEntrega(LocalDate previsaoEntrega) {
        this.previsaoEntrega = previsaoEntrega;
    }

    public LocalDate getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(LocalDate dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }


    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

