package com.maxwellponte.workshopmongo.services;

import com.maxwellponte.workshopmongo.domain.Post;
import com.maxwellponte.workshopmongo.repositories.PostRepository;
import com.maxwellponte.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(String id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado com o id: " + id));
    }
}