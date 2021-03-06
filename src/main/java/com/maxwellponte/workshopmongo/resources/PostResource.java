package com.maxwellponte.workshopmongo.resources;

import com.maxwellponte.workshopmongo.domain.Post;
import com.maxwellponte.workshopmongo.resources.util.URL;
import com.maxwellponte.workshopmongo.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        return ResponseEntity.ok().body(postService.findById(id));
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        text = URL.decodeParam(text);
        return ResponseEntity.ok().body(postService.findByTitle(text));
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearchByDate(@RequestParam(value = "text", defaultValue = "") String text,
                                                       @RequestParam(value = "minDate") String minDate,
                                                       @RequestParam(value = "maxDate") String maxDate){
        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date());
        return ResponseEntity.ok().body(postService.fullSearch(text, min, max));
    }
}
