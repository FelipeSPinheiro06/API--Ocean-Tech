package com.oceantech.oceantech.Service;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.oceantech.oceantech.DTO.UserRequest;
import com.oceantech.oceantech.Model.Users;
import com.oceantech.oceantech.Repository.UserRepository;

@Service
public class UserService {
    
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<Users> GrabAllUsers() {
        return userRepository.findAll();
    }

    public Users createUser(UserRequest userRequest) {
        Users user = constructUser(userRequest);
        return userRepository.save(user);
    }

    public Users updateUser(Long id, UserRequest userRequest) {
        Users user = constructUser(userRequest);
        checkExistence(id);
        user.setId(id);
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public Users GrabUserByID(Long id) {
        return checkExistence(id);
    }

    public Users checkExistence(Long id) {
        return userRepository
            .findById(id)
            .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Não existe um usuário com este ID"));
    }

    public Users constructUser(UserRequest user) {
        return new Users(user.id(), 
                        user.name(), 
                        user.email(), 
                        user.password(), 
                        user.registrationDate(), 
                        user.score());
    }
}
