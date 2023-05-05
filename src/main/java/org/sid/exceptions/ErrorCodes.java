package org.sid.exceptions;

public enum ErrorCodes {
    SALLE_NOT_FOUND(1000),
    REUNION_NOT_VALID(2000),
    COLLABORATEUR_NOT_VALID(2000),
    RESERVATION_NOT_FOUND(2000);


    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}