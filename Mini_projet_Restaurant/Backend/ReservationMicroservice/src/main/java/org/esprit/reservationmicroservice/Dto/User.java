package org.esprit.reservationmicroservice.Dto;

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
