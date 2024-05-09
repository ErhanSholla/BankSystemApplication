package org.example.banksystemlinkplus.exception;

public class EntityAlreadyExistsException  extends RuntimeException{
    public EntityAlreadyExistsException(String msg) {
        super(msg);
    }
}
