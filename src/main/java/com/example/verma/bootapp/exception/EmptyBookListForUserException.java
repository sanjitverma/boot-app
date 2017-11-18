package com.example.verma.bootapp.exception;

import java.io.Serializable;

/**
 * Created by SANJIT on 17/11/17.
 */

public class EmptyBookListForUserException extends RuntimeException implements Serializable {

    private String message;

    public EmptyBookListForUserException(){

    }

    public EmptyBookListForUserException(String message){
        super(message);
    }

    public EmptyBookListForUserException(Exception e){
        super(e);
    }

    public EmptyBookListForUserException(String message, Exception e){
        super(message,e);
    }



}
