package moser.carShop.carShop.services;

import jakarta.persistence.EntityNotFoundException;
import moser.carShop.carShop.dto.EnderecoDTO;
import moser.carShop.carShop.entities.Endereco;
import moser.carShop.carShop.repositories.EnderecoRepository;
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
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public EnderecoDTO findById(Long idAdress) {
        Optional<Endereco> endereco = repository.findById(idAdress);

        if(endereco.get() != null) {
            EnderecoDTO enderecoDTO = new EnderecoDTO(endereco.get());
            return enderecoDTO;
        } else {
            throw new ResourceNotFoundException(idAdress);
        }
    }

    public List<EnderecoDTO> findAll() {
        List<Endereco> enderecoList = repository.findAll();
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();
        for (Endereco e : enderecoList) {
            enderecoDTOS.add(new EnderecoDTO(e));
        }

        return enderecoDTOS;
    }

    public Endereco createAdress(Endereco endereco) {
        Endereco e = repository.save(endereco);
        return e;
    }

    public void delete(Long idAdress) {
        try {
            repository.deleteById(idAdress);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(idAdress);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Endereco update(Long idAdress, EnderecoDTO enderecoDTO) {
        try {

            Endereco originalAdress = repository.getReferenceById(idAdress);
            updateAdress(originalAdress, enderecoDTO.transformToObject());

            return repository.save(originalAdress);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(idAdress);
        }
    }

    public void updateAdress(Endereco originalAdress, Endereco e) {
        originalAdress.setBairro(e.getBairro());
        originalAdress.setPais(e.getPais());
        originalAdress.setCidade(e.getCidade());
        originalAdress.setRua(e.getRua());
        originalAdress.setEstado(e.getEstado());
        originalAdress.setEmpresa(e.getEmpresa());

    }

}
