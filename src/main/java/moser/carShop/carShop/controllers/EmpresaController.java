package moser.carShop.carShop.controllers;

import moser.carShop.carShop.dto.EmpresaDTO;
import moser.carShop.carShop.entities.Empresa;
import moser.carShop.carShop.services.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/companies")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @GetMapping(value = "/{idCompany}")
    public ResponseEntity<EmpresaDTO> findById(@PathVariable Long idCompany) {
        EmpresaDTO e = empresaService.findById(idCompany);
        return ResponseEntity.ok().body(e);
    }

    @GetMapping(value = "/allCompanies")
    public ResponseEntity<List<EmpresaDTO> > findAll() {
        List<EmpresaDTO> e = empresaService.findAll();
        return ResponseEntity.ok().body(e);
    }

    @PostMapping(value = "")
    public ResponseEntity<EmpresaDTO> createCompany(@RequestBody EmpresaDTO empresaDTO) {
        Empresa e = empresaService.createCompany(empresaDTO.transformToObject());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(e.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmpresaDTO(e));
    }

    @DeleteMapping(value = "/{idCompany}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long idCompany) {
        empresaService.delete(idCompany);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idCompany}")
    public ResponseEntity<EmpresaDTO> updateCompanie(@PathVariable Long idCompany, @RequestBody EmpresaDTO empresaDTO) {
        Empresa e = empresaService.update(idCompany, empresaDTO);
        return ResponseEntity.ok().body(new EmpresaDTO(e));
    }

}
