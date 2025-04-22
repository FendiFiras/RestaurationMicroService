package org.esprit.commandemicroservice.clients;

import org.esprit.commandemicroservice.Dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-client", url = "http://localhost:8087")
public interface UserClient {
    @GetMapping("/users/{id}")
    User getUtilisateur(@PathVariable("id") Long id);
}
