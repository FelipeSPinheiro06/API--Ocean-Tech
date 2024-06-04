package com.oceantech.oceantech.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    
    @NotBlank(message = "{users.name.notblank}")
    @Size(min = 3, max = 64)
    public String name;

    @NotBlank(message = "{users.email.notblank}")
    @Email
    public String email;

    @NotBlank(message = "{users.password.notblank}")
    @Size(min = 8, max = 16)
    public String password;

    @NotNull(message = "{users.registrationDate.notnull}")
    @PastOrPresent
    public LocalDate registrationDate;
    
    @NotNull(message = "{users.score.notnull}")
    public BigDecimal score;
    
}