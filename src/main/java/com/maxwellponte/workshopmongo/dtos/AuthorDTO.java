package com.maxwellponte.workshopmongo.dtos;

import com.maxwellponte.workshopmongo.domain.User;

import java.io.Serializable;

public class AuthorDTO implements Serializable {
    private static final long serialVersionUID = -415921509602755646L;
    private String id;
    private String name;


    public AuthorDTO(){}

    public AuthorDTO(User user){
        name = user.getName();
        id = user.getId();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
