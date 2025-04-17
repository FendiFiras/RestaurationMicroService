package org.esprit.user_microservice.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;
import  lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    private String firstName;
    private String lastName;

    private LocalDate dateOfBirth;
    private String password;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String email;
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;



}