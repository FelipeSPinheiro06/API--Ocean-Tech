package com.oceantech.oceantech.DTO;

import java.time.LocalDate;

import com.oceantech.oceantech.Model.ObjectDictionary;
import com.oceantech.oceantech.Model.Users;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record ObjectRequest(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id,

    @NotNull
    @Size(min = 3, max = 64)
    String name,
    
    @NotNull
    @Enumerated(EnumType.STRING)
    ObjectDictionary type,
    
    @NotNull
    @PastOrPresent
    LocalDate recycleDate,
    
    @NotNull
    @ManyToOne
    Users user


) {}
