package moser.carShop.carShop.dto;

import lombok.Getter;
import lombok.Setter;
import moser.carShop.carShop.entities.Car;
import moser.carShop.carShop.entities.Piece;

import java.util.Date;

@Getter
@Setter
public class CarDTO {

    private Long id;

    private String nome;

    private int ano;

    private Date dtCriacao;

    private Date dtModificacao;

    private Piece piece;
    public CarDTO(Car car) {
        id = car.getId();
        nome = car.getNome();
        ano = car.getAno();
        dtModificacao = car.getDtModificacao();
        dtCriacao = car.getDtCriacao();
        piece = car.getPiece();
    }

    public CarDTO() {

    }

    public Car transformToObject() {
        return new Car(nome, ano);
    }

}
