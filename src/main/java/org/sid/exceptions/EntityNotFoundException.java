package org.sid.exceptions;

import lombok.Data;

@Data
public class EntityNotFoundException extends RuntimeException {
    private ErrorCodes errorCodes;

    public EntityNotFoundException(String message){
        super(message);
    }
    public EntityNotFoundException(String message,Throwable cause){
        super(message,cause);
    }
    public EntityNotFoundException(String message,Throwable cause,ErrorCodes codes){
        super(message,cause);
        this.errorCodes=codes;
    }
    public EntityNotFoundException(String message,ErrorCodes codes){
        super(message);
        this.errorCodes=codes;
    }

}