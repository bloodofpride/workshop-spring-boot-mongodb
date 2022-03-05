package com.maxwellponte.workshopmongo.services;

import com.maxwellponte.workshopmongo.domain.User;
import com.maxwellponte.workshopmongo.dtos.UserDTO;
import com.maxwellponte.workshopmongo.repositories.UserRepository;
import com.maxwellponte.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO findById(String id){
        Optional<User> user = userRepository.findById(id);
        return new UserDTO(user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com o id: "+id)));
    }

    public User save(UserDTO userDTO) {
        User user = userRepository.insert(fromDto(userDTO));
        return user;
    }

    public void delete(String id){
        User user = fromDto(findById(id));
        userRepository.delete(user);
    }

    public UserDTO update(UserDTO userDTO) {
        User newUser = fromDto(findById(userDTO.getId()));
        updateData(newUser, userDTO);
        userRepository.save(newUser);
        return new UserDTO(newUser);
    }

    private void updateData(User newUser, UserDTO userDTO) {
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
    }

    private User fromDto(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }


}
