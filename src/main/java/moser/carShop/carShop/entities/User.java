package moser.carShop.carShop.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Date dtCriacao;

    @Column
    @Version
    private Date dtModificacao;

    @Column
    private Boolean admin = false;

    @Column
    private String nome;

    @Column
    private String cpf;

    @OneToMany(mappedBy = "user")
    private Set<Empresa> empresas = new LinkedHashSet<>(0);

    @Column
    private Long password;

    public User(String nome, String cpf, Date dtCriacao, Set<Empresa> empresas) {
        this.dtCriacao = dtCriacao;
        this.nome = nome;
        this.cpf = cpf;
        this.empresas = empresas;
    }
}
