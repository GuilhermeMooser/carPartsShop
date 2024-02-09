package moser.carShop.carShop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private int ano;

    @Column
    private String nome;

    @Column
    private Date dtCriacao;

    @Column
    @Version
    private Date dtModificacao;

    @ManyToOne
    @JoinColumn(name="peca_id")
    private Piece piece;


    public Car(String nome, int ano) {
        this.nome = nome;
        this.ano = ano;
    }
}
