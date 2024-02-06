package moser.carShop.carShop.controllers;

import moser.carShop.carShop.dto.UserDTO;
import moser.carShop.carShop.entities.User;
import moser.carShop.carShop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/{idUser}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long idUser) {
        UserDTO u = userService.findById(idUser);
        return ResponseEntity.ok().body(u);
    }

    @GetMapping(value = "/allUsers")
    public ResponseEntity<List<UserDTO> > findAll() {
        List<UserDTO> u = userService.findAll();
        return ResponseEntity.ok().body(u);
    }

    @PostMapping(value = "")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        User u = userService.createUser(userDTO.transformToObject());

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
        return ResponseEntity.created(uri).body(new UserDTO(u));
    }

    @DeleteMapping(value = "/{idUser}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long idUser) {
        userService.delete(idUser);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{idUser}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long idUser, @RequestBody UserDTO userdto) {
        User user = userService.update(idUser, userdto);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

}
