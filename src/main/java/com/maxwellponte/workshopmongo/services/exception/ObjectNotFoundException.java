package com.maxwellponte.workshopmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -9189965849420950464L;

    public ObjectNotFoundException(String msg){
        super(msg);
    }
}
