package moser.carShop.carShop.services;

import moser.carShop.carShop.dto.UserDTO;
import moser.carShop.carShop.entities.User;
import moser.carShop.carShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserDTO findById(Long idUser) {
        User u = repository.findById(idUser).get();
        UserDTO userDTO = new UserDTO(u);
        return userDTO;
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
        repository.deleteById(idUser);
//        try {
//            repository.deleteById(idUser);
//        }
//        catch (EmptyResultDataAccessException e) {
//            throw new ResourceNotFoundException(id);
//        }
//        catch (DataIntegrityViolationException e) {
////            throw new DatabaseException(e.getMessage());
//        }
    }

    public User update(Long idUser, UserDTO userDTO) {
        try {
            User originalUser = repository.getReferenceById(idUser);

            updateUser(originalUser, userDTO.transformToObject());

            return repository.save(originalUser);
        } catch(Exception e) {
            e.printStackTrace();
        }
        //AjustarRetorno
        return null;
    }

    public void updateUser(User originalUser, User u) {
        originalUser.setAdmin(u.getAdmin());
        originalUser.setCpf(u.getCpf());
        originalUser.setNome(u.getNome());
        originalUser.setEmpresas(u.getEmpresas());
        originalUser.setPassword(u.getPassword());
    }

}
