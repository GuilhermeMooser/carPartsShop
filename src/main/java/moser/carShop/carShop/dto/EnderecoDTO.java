package moser.carShop.carShop.dto;

import lombok.Getter;
import lombok.Setter;
import moser.carShop.carShop.entities.Empresa;
import moser.carShop.carShop.entities.Endereco;

@Getter
@Setter
public class EnderecoDTO {


    private Long id;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private Empresa empresa;


    public EnderecoDTO(Endereco endereco) {
        id = endereco.getId();
        rua = endereco.getRua();
        bairro = endereco.getBairro();
        cidade = endereco.getCidade();
        estado = endereco.getEstado();
        pais = endereco.getPais();
        empresa = endereco.getEmpresa();
    }
    public EnderecoDTO() {

    }
    public Endereco transformToObject() {
        return new Endereco(rua, bairro, cidade, estado, pais, empresa);
    }

}
