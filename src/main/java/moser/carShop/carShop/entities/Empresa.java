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
@Table(name="empresa")
public class Empresa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String nome;

    @Column
    private Date dtCriacao;

    @Column
    @Version
    private Date dtModificacao;

    @Column
    private String cpnj;

    @Column
    private String razaoSocial;

    @Column
    private String nomeFantasia;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private Set<Endereco> enderecos = new LinkedHashSet<>(0);

    public Empresa (String nome, String cpnj, String razaoSocial,  String nomeFantasia,  User user) {
        this.nome = nome;
        this.cpnj = cpnj;
        this.razaoSocial = razaoSocial;
        this.nomeFantasia = nomeFantasia;
        this.user = user;
    }

    public void carregaProxy() {
        if(this.user != null) {
            this.user.getId();
        }

        if(!this.enderecos.isEmpty()) {
            this.enderecos.size();
        }
    }
}
