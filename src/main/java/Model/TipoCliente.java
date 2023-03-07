package Model;

public enum TipoCliente {
    F("F"),
    J("J");

    private String tipo;

    TipoCliente(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }




}

