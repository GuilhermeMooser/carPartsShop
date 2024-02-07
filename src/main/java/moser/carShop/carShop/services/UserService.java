package moser.carShop.carShop.services;

import jakarta.persistence.EntityNotFoundException;
import moser.carShop.carShop.dto.UserDTO;
import moser.carShop.carShop.entities.User;
import moser.carShop.carShop.repositories.UserRepository;
import moser.carShop.carShop.services.exceptions.DatabaseException;
import moser.carShop.carShop.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO findById(Long idUser) {
        Optional<User> user = repository.findById(idUser);

        if(user.get() != null) {
            UserDTO userDTO = new UserDTO(user.get());
            return userDTO;
        } else {
            throw new ResourceNotFoundException(idUser);
        }
    }

    public List<UserDTO> findAll() {
        List<User> userList = repository.findAll();
        List<UserDTO> userDTOS = new ArrayList<>();
        for (User u : userList) {
            userDTOS.add(new UserDTO(u));
        }

        return userDTOS;
    }

    public User createUser(User user) {
        user.setDtCriacao(new Date());
        User u = repository.save(user);
        return u;
    }

    public void delete(Long idUser) {
        try {
            repository.deleteById(idUser);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(idUser);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public User update(Long idUser, UserDTO userDTO) {
        try {

            User originalUser = repository.getReferenceById(idUser);
            updateUser(originalUser, userDTO.transformToObject());

            return repository.save(originalUser);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(idUser);
        }
    }

    public void updateUser(User originalUser, User u) {
        originalUser.setAdmin(u.getAdmin());
        originalUser.setCpf(u.getCpf());
        originalUser.setNome(u.getNome());
        originalUser.setEmpresas(u.getEmpresas());
        originalUser.setPassword(u.getPassword());
    }

}
