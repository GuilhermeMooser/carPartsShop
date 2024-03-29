package moser.carShop.carShop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="endereco")
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String rua;

    @Column
    private String bairro;

    @Column
    private String cidade;

    @Column
    private String estado;

    @Column
    private String pais;

    @ManyToOne
    @JoinColumn(name="empresa_id")
    private Empresa empresa;

    public Endereco(String rua, String bairro, String cidade, String estado, String pais, Empresa empresa) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.empresa = empresa;
    }

}
