package org.esprit.reservationmicroservice.Services;

import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;

@Component
public class UserClientFake {

    private static final Map<Long, String> fakeUsers = new HashMap<>();

    static {
        fakeUsers.put(1L, "Ali Ben Salah");
        fakeUsers.put(2L, "Meriem Jebali");
        fakeUsers.put(3L, "Oussama Khelifi");
    }

    public String getNomUtilisateur(Long idUser) {
        return fakeUsers.getOrDefault(idUser, "Utilisateur inconnu");
    }
}
