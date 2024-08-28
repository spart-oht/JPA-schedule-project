package org.sparta.jpaschedule.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CommonException extends RuntimeException{

    private final HttpStatus status;  // HTTP 상태 코드

    public CommonException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

}
