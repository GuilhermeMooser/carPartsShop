package moser.carShop.carShop.dto;

import lombok.Getter;
import lombok.Setter;
import moser.carShop.carShop.entities.Empresa;
import moser.carShop.carShop.entities.User;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
public class UserDTO {

    private Long id;

    private String nome;

    private String cpf;

    private Set<Empresa> empresas = new LinkedHashSet<>(0);

    private Date dtCriacao;

    public UserDTO(User user) {
        id = user.getId();
        nome = user.getNome();
        cpf = user.getCpf();
        empresas = user.getEmpresas();
        dtCriacao = user.getDtCriacao();
    }

    public UserDTO() {

    }

    public User transformToObject() {
        return new User(nome, cpf, dtCriacao, empresas);
    }

}
