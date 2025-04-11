package org.esprit.user_microservice.Services;

import lombok.AllArgsConstructor;
import org.esprit.user_microservice.Entities.Gender;
import org.esprit.user_microservice.Entities.Role;
import org.esprit.user_microservice.Entities.User;
import org.esprit.user_microservice.Repository.UserRepository;
import org.springframework.stereotype.Service;
;import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User modifyUser(Long idUser, User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
    }



    @Override
    public User retrieveUser(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByRole(String role) {
        Role roleEnum = Role.valueOf(role); // Convertir le String en Enum
        return userRepository.findByRole(roleEnum);
    }
    public Map<String, Long> getGenderStatistics() {
        Map<String, Long> stats = new HashMap<>();
        stats.put("Male", userRepository.countByGender(Gender.MALE));
        stats.put("Female", userRepository.countByGender(Gender.FEMALE));
        return stats;
    }
}