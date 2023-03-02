package Model;

public enum TipoCliente {
    FISICO("F"),
    JURIDICO("J");

    private String tipo;

    TipoCliente(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }
}

