package org.esprit.user_microservice.Services;


import org.esprit.user_microservice.Entities.User;

import java.util.List;

public interface IUserService {
    User addUser(User user);
    List<User> getAllUsers();
    User modifyUser(Long idUser, User user);
    void deleteUser(Long id);
    User retrieveUser(Long id);
    User findByEmail(String email);
    List<User> findByRole(String role);


}