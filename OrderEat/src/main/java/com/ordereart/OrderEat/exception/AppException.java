package com.ordereart.OrderEat.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class AppException extends RuntimeException{

    private ErrorCode errorCode;
}
