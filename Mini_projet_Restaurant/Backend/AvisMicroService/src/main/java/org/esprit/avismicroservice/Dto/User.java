package org.esprit.avismicroservice.Dto;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
public class User {

    private Long idUser;

    private String firstName;
    private String lastName;




}