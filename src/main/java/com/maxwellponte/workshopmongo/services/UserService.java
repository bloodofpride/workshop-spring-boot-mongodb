package com.maxwellponte.workshopmongo.services;

import com.maxwellponte.workshopmongo.domain.User;
import com.maxwellponte.workshopmongo.dtos.UserDTO;
import com.maxwellponte.workshopmongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }
}
