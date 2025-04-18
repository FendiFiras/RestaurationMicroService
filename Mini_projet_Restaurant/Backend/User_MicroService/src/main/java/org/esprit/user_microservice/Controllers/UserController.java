package org.esprit.user_microservice.Controllers;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.esprit.user_microservice.Entities.User;
import org.esprit.user_microservice.Repository.UserRepository;
import org.esprit.user_microservice.Services.IUserService;
import org.esprit.user_microservice.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private   UserServiceImpl userServiceImpl;
    @Autowired
    private  UserRepository userRepository;
    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{idUser}")
    public User modifyUser(@PathVariable Long idUser, @RequestBody User user) {
        return userService.modifyUser(idUser, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable Long id) {
        return userService.retrieveUser(id);
    }

    @GetMapping("/email/{email}")
    public User findByEmail(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping("/role/{role}")
    public List<User> findByRole(@PathVariable String role) {
        return userService.findByRole(role);
    }
    @GetMapping("/gender-stats")
    public ResponseEntity<Map<String, Long>> getGenderStats() {
        return ResponseEntity.ok(userServiceImpl.getGenderStatistics());
    }
    @GetMapping("/sorted/asc")
    public List<User> getUsersSortedByDateOfBirthAsc() {
        return userRepository.findAllByOrderByDateOfBirthAsc();
    }

    @GetMapping("/sorted/desc")
    public List<User> getUsersSortedByDateOfBirthDesc() {
        return userRepository.findAllByOrderByDateOfBirthDesc();
    }
    @GetMapping("/count")
    public Long countUsers() {
        return userRepository.count();
    }

}