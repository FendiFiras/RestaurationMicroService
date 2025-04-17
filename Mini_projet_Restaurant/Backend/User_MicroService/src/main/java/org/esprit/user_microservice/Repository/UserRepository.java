package org.esprit.user_microservice.Repository;

import org.esprit.user_microservice.Entities.Gender;
import org.esprit.user_microservice.Entities.Role;
import org.esprit.user_microservice.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email); // Méthode personnalisée pour trouver un utilisateur par email
    List<User> findByRole(Role role);
    long countByGender(Gender gender);
    List<User> findAllByOrderByDateOfBirthAsc();

    List<User> findAllByOrderByDateOfBirthDesc();
}