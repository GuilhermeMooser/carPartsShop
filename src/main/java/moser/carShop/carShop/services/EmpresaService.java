package moser.carShop.carShop.services;

import jakarta.persistence.EntityNotFoundException;
import moser.carShop.carShop.dto.EmpresaDTO;
import moser.carShop.carShop.entities.Empresa;
import moser.carShop.carShop.repositories.EmpresaRepository;
import moser.carShop.carShop.services.exceptions.DatabaseException;
import moser.carShop.carShop.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository repository;

    public EmpresaDTO findById(Long idCompany) {
        Optional<Empresa> empresa = repository.findById(idCompany);

        if(empresa.get() != null) {
            EmpresaDTO empresaDTO = new EmpresaDTO(empresa.get());
            return empresaDTO;
        } else {
            throw new ResourceNotFoundException(idCompany);
        }
    }

    public List<EmpresaDTO> findAll() {
        List<Empresa> empresaList = repository.findAll();
        List<EmpresaDTO> empresaDTOS = new ArrayList<>();
        for (Empresa e : empresaList) {
            empresaDTOS.add(new EmpresaDTO(e));
        }

        return empresaDTOS;
    }

    public Empresa createCompany(Empresa empresa) {
        Empresa e = repository.save(empresa);
        return e;
    }

    public void delete(Long idCompany) {
        try {
            repository.deleteById(idCompany);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(idCompany);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Empresa update(Long idCompany, EmpresaDTO empresaDTO) {
        try {

            Empresa originalCompany = repository.getReferenceById(idCompany);
            updateCompany(originalCompany, empresaDTO.transformToObject());

            return repository.save(originalCompany);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(idCompany);
        }
    }

    public void updateCompany(Empresa originalAdress, Empresa e) {
        originalAdress.setCpnj(e.getCpnj());
        originalAdress.setNome(e.getNome());
        originalAdress.setEnderecos(e.getEnderecos());
        originalAdress.setUser(e.getUser());
        originalAdress.setNomeFantasia(e.getNomeFantasia());
        originalAdress.setRazaoSocial(e.getRazaoSocial());
    }
}
