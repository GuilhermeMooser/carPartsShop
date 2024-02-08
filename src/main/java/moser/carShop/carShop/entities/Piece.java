package moser.carShop.carShop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import moser.carShop.carShop.enums.EstadoConservacao;
import moser.carShop.carShop.enums.PieceStatus;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="piece")
public class Piece {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private String marca;

    @Column(unique = true)
    private String codigo;

    @Enumerated (EnumType.ORDINAL)
    @Column
    private EstadoConservacao estadoConservacao;

    @Enumerated (EnumType.ORDINAL)
    @Column
    private PieceStatus pieceStatus;

    @Column
    private Date dtCriacao;

    @Column
    @Version
    private Date dtModificacao;

    @OneToMany(mappedBy = "piece")
    private Set<Car> carros = new LinkedHashSet<>(0);

    @ManyToOne
    @JoinColumn(name="empresa_id")
    private Empresa empresa;

    public Piece(Long id, String nome, String codigo, String marca, EstadoConservacao estadoConservacao, PieceStatus pieceStatus, Date dtCriacao, Set<Car> carros, Empresa empresa) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
        this.marca = marca;
        this.estadoConservacao = estadoConservacao;
        this.pieceStatus = pieceStatus;
        this.dtCriacao = dtCriacao;
        this.carros = carros;
        this.empresa = empresa;
    }
}
