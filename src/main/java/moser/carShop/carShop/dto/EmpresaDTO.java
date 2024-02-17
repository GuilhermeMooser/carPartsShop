package moser.carShop.carShop.dto;

import lombok.Getter;
import lombok.Setter;
import moser.carShop.carShop.entities.Empresa;
import moser.carShop.carShop.entities.Endereco;
import moser.carShop.carShop.entities.User;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
@Getter
@Setter
public class EmpresaDTO {

    private Long id;
    private String nome;
    private Date dtCriacao;
    private Date dtModificacao;
    private String cpnj;
    private String razaoSocial;
    private String nomeFantasia;
    private User user;
    private Set<Endereco> enderecos = new LinkedHashSet<>(0);

    public EmpresaDTO(Empresa empresa) {
        id = empresa.getId();
        nome = empresa.getNome();
        cpnj = empresa.getCpnj();
        razaoSocial = empresa.getRazaoSocial();
        nomeFantasia = empresa.getNomeFantasia();
        user = empresa.getUser();
        enderecos = empresa.getEnderecos();
    }

    public EmpresaDTO() {

    }

    public Empresa transformToObject() {
        return new Empresa(nome, cpnj, razaoSocial, nomeFantasia, user);
    }
}
