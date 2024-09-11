package com.ordereart.OrderEat.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public enum ErrorCode {
    EXISTS(1001,"Has been existed !"),
    USER_LARGER(1002,"Username must be larger 8 characters !"),
    NOTFOUND(1003,"Not found !"),
    NOTEXIST(1004,"User not Exist !"),
    UNAUTHENTICATED(1005, "Unauthenticated !")
    ;
    final int code ;
    final String message;
}
