package com.example.Puzzlogging.common.exception.model;

import com.example.Puzzlogging.common.exception.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDTO {
    private String code;
    private String message;

    public static ErrorDTO of(ErrorCode errorCode) {
        return new ErrorDTO(errorCode.getCode(), errorCode.getMessage());
    }

    public static ErrorDTO of(ErrorCode errorCode, String message) {
        return new ErrorDTO(errorCode.getCode(), message);
    }
}
