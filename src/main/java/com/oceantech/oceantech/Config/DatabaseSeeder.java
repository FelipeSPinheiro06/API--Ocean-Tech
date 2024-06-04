package com.oceantech.oceantech.Config;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;


import com.oceantech.oceantech.Model.ObjectDictionary;
import com.oceantech.oceantech.Model.Object;

import com.oceantech.oceantech.Model.Users;

import com.oceantech.oceantech.Repository.ObjectRepository;

import com.oceantech.oceantech.Repository.UserRepository;

@Configuration
public class DatabaseSeeder implements CommandLineRunner {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectRepository objectRepository;


    @Override
    public void run(String... args) throws Exception {
        userRepository.saveAll(
            List.of(
                Users.builder().id(1L).name("Felipe").email("Felipe@gmail.com").password("Felipe12")
                    .registrationDate(LocalDate.now().minusYears(2)).score(BigDecimal.ZERO).build(),
                Users.builder().id(2L).name("Gabriel").email("GabrielG@gmail.com").password("Gabriel34")
                    .registrationDate(LocalDate.now().minusYears(4)).score(BigDecimal.ZERO).build(),
                Users.builder().id(3L).name("Gustavo").email("GustavoV@gmail.com").password("Gustavo56")
                    .registrationDate(LocalDate.now().minusYears(6)).score(BigDecimal.ZERO).build()
            )
        );

        
        objectRepository.saveAll(
            List.of(
                Object.builder().id(1L).name("Canudo").type(ObjectDictionary.PLASTIC).recycleDate(LocalDate.of(2024, 4, 30)).user(userRepository.findById(1L).get()).build(),
                Object.builder().id(2L).name("Papel").type(ObjectDictionary.PAPER).recycleDate(LocalDate.of(2024, 3, 25)).user(userRepository.findById(2L).get()).build(),
                Object.builder().id(3L).name("Garrafa").type(ObjectDictionary.GLASS).recycleDate(LocalDate.of(2024, 1, 12)).user(userRepository.findById(3L).get()).build()
            )
        );

    }


}