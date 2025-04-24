package tn.esprit.livraisonmicroservice.Clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.livraisonmicroservice.dto.User;

@FeignClient(name = "user-client", url = "http://localhost:8087")
public interface UserClient {
    @GetMapping("/users/{id}")
    User getUtilisateur(@PathVariable("id") Long id);

    @GetMapping("/users/users/{id}")
    User CheckUtilisateur(@PathVariable("id") Long id);

}
