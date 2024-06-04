package com.oceantech.oceantech.DTO;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record UserRequest(
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    @NotBlank
    @Size(min = 3, max = 64)
    String name,

    @NotBlank
    @Email
    String email,

    @NotBlank
    @Size(min = 8, max = 16)
    String password,

    @NotNull
    @PastOrPresent
    LocalDate registrationDate,
    
    @NotNull
    BigDecimal score

){}
