package moser.carShop.carShop.enums;

public enum EstadoConservacao {

    NOVO(1),
    PRESERVADO(2),
    UTILIZADO(3);

    private int code;

    EstadoConservacao(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
