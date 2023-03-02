package Model;
import Model.TipoCliente;



public class Cliente {
    private int codCliente;
    private String telefone;
    private String nome;
    private String email;
    private String cpf;
    private TipoCliente tipoCliente;

    public Cliente(int codCliente, String telefone, String nome, String email, String cpf, TipoCliente tipoCliente) {
        this.codCliente = codCliente;
        this.telefone = telefone;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.tipoCliente = tipoCliente;
    }

    // getters e setters
    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
}

