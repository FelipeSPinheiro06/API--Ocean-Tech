package com.oceantech.oceantech.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oceantech.oceantech.Model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

}
