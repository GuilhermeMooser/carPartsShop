package moser.carShop.carShop.enums;

public enum PieceStatus {

    ENVIADO(1),
    AGUARDANDO_RETIRADA(2),
    ENTREGUE(3),
    DISPONIVEL(4);

    private int code;

    PieceStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
