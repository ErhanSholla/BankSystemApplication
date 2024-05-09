package org.example.banksystemlinkplus.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ExceptionResponse {
    @JsonProperty("message")
    private String msg;
    public ExceptionResponse(@JsonProperty("message") String msg) {
        this.msg = msg;
    }
}
