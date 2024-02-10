package moser.carShop.carShop.controllers;

import moser.carShop.carShop.dto.EnderecoDTO;
import moser.carShop.carShop.entities.Endereco;
import moser.carShop.carShop.entities.User;
import moser.carShop.carShop.services.EnderecoService;
import moser.carShop.carShop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/adresses")
public class EnderecoController {

    @Autowired
    EnderecoService enderecoService;

    @GetMapping(value = "/{idAdress}")
    public ResponseEntity<EnderecoDTO> findById(@PathVariable Long idAdress) {
        EnderecoDTO u = enderecoService.findById(idAdress);
        return ResponseEntity.ok().body(u);
    }

    @GetMapping(value = "/allAdresses")
    public ResponseEntity<List<EnderecoDTO> > findAll() {
        List<EnderecoDTO> u = enderecoService.findAll();
        return ResponseEntity.ok().body(u);
    }

    @PostMapping(value = "")
    public ResponseEntity<EnderecoDTO> createAdress(@RequestBody EnderecoDTO enderecoDTO) {
        Endereco e = enderecoService.createAdress(enderecoDTO.transformToObject());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();
        return ResponseEntity.created(uri).body(new EnderecoDTO(e));
    }

    @DeleteMapping(value = "/{idAdress}")
    public ResponseEntity<Void> deleteAdress(@PathVariable Long idAdress) {
        enderecoService.delete(idAdress);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idAdress}")
    public ResponseEntity<EnderecoDTO> updateAdress(@PathVariable Long idAdress, @RequestBody EnderecoDTO enderecoDTO) {
        Endereco endereco = enderecoService.update(idAdress, enderecoDTO);
        return ResponseEntity.ok().body(new EnderecoDTO(endereco));
    }

}
