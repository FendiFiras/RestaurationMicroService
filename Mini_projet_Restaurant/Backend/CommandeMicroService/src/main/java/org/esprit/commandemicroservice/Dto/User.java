package org.esprit.commandemicroservice.Dto;

import lombok.Data;

@Data
public class User {
    private Long idUser;
    private String firstName;
    private String lastName;

    public String getNom() {
        return firstName + " " + lastName;
    }
}
