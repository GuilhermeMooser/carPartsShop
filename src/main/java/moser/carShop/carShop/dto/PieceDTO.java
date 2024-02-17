package moser.carShop.carShop.dto;

import lombok.Getter;
import lombok.Setter;
import moser.carShop.carShop.entities.Car;
import moser.carShop.carShop.entities.Empresa;
import moser.carShop.carShop.entities.Piece;
import moser.carShop.carShop.enums.EstadoConservacao;
import moser.carShop.carShop.enums.PieceStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class PieceDTO {

    private Long id;
    private String nome;
    private String codigo;
    private String marca;
    private BigDecimal price;
    private EstadoConservacao estadoConservacao;
    private PieceStatus pieceStatus;
    private Date dtCriacao;
    private Date dtModificacao;
    private Set<Car> carros = new LinkedHashSet<>(0);
    private Empresa empresa;

    public PieceDTO(Piece piece) {
        id = piece.getId();
        nome = piece.getNome();
        codigo = piece.getCodigo();
        marca = piece.getMarca();
        price = piece.getPrice();
        estadoConservacao = piece.getEstadoConservacao();
        pieceStatus = piece.getPieceStatus();
        dtCriacao = piece.getDtCriacao();
        carros = piece.getCarros();
        empresa = piece.getEmpresa();
    }

    public PieceDTO() {

    }

    public Piece transformToObject() {
        return new Piece(id, nome, codigo, marca, price, estadoConservacao, pieceStatus, dtCriacao, carros, empresa);
    }


}
