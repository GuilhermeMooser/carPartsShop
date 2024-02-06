package moser.carShop.carShop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import moser.carShop.carShop.enums.EstadoConservacao;

import java.sql.Timestamp;
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

    @Column(unique = true)
    private String codigo;

    @Enumerated (EnumType.ORDINAL)
    @Column
    private EstadoConservacao estadoConservacao;

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
}
