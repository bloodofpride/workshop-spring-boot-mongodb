package com.maxwellponte.workshopmongo.services;

import com.maxwellponte.workshopmongo.domain.Post;
import com.maxwellponte.workshopmongo.domain.User;
import com.maxwellponte.workshopmongo.dtos.AuthorDTO;
import com.maxwellponte.workshopmongo.dtos.UserDTO;
import com.maxwellponte.workshopmongo.repositories.PostRepository;
import com.maxwellponte.workshopmongo.repositories.UserRepository;
import com.maxwellponte.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;


    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public List<UserDTO> findAll(){
        List<User> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).collect(Collectors.toList());
    }

    public UserDTO findById(String id){
        User user = findUser(id);
        return new UserDTO(user);
    }

    public User save(UserDTO userDTO) {
        return userRepository.insert(fromDto(userDTO));
    }

    public void delete(String id){
        User user = findUser(id);
        userRepository.delete(user);
    }

    public UserDTO update(UserDTO userDTO) {
        User newUser = findUser(userDTO.getId());
        updateData(newUser, userDTO);
        userRepository.save(newUser);
        return new UserDTO(newUser);
    }

    public List<Post> findPosts(String id) {
       User user = findUser(id);
       for (Post p : user.getPosts()){
           if(!Objects.equals(user.getName(), p.getAuthor().getName())) {
               p.setAuthor(new AuthorDTO(user));
               postRepository.save(p);
           }
       }
       userRepository.save(user);
       return user.getPosts();
    }

    private void updateData(User newUser, UserDTO userDTO) {
        newUser.setName(userDTO.getName());
        newUser.setEmail(userDTO.getEmail());
    }

    private User fromDto(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    private User findUser(String id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com o id: "+id));
    }

}
