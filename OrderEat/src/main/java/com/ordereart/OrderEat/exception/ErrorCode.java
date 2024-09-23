package com.ordereart.OrderEat.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    EXISTS(1001,"Has been existed !", HttpStatus.INTERNAL_SERVER_ERROR),
    NOTFOUND(1003,"Not found !", HttpStatus.BAD_REQUEST),
    NOTEXIST(1004,"User not Exist !", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED(1005, "Unauthenticated !", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1006, "Unauthorized !", HttpStatus.UNAUTHORIZED)
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    int code;
    String message;
    HttpStatusCode statusCode;
}
