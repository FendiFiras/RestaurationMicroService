package org.esprit.reservationmicroservice.Clients;

import org.esprit.reservationmicroservice.Dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-client", url = "http://localhost:8087")
public interface UserClient {
    @GetMapping("/users/showbyid/{id}")
    User getUtilisateur(@PathVariable("id") Long id);

    @GetMapping("/users/users/{id}")
    User CheckUtilisateur(@PathVariable("id") Long id);

}
